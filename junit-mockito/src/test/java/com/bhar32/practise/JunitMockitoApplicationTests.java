package com.bhar32.practise;

import com.bhar32.practise.model.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.bhar32.practise.controller.TutorialController;
import com.bhar32.practise.model.Tutorial;
import com.bhar32.practise.repository.TutorialRepository;
import com.bhar32.practise.service.TutorialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/* 1. From postman if we send the data in json format then spring boot does the duty to convert json to java object.
* 2. But from test we can pass direct java object
* 3. However, if we have json data then needs to be converted into java object using ObjectMapper then to pass controller level.
* 4. Either create direct class instance or use autowired.
* 5. All the dependency should be mocked using MockBean. */

@AutoConfigureMockMvc
@SpringBootTest
class JunitMockitoApplicationTests {

	@Autowired
	private TutorialController tutorialController;

	@MockBean
	private TutorialService tutorialService;

	@MockBean
	private TutorialRepository tutorialRepository;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	public Tutorial createTutorial() {
		Tutorial _tutorial = new Tutorial();
		_tutorial.setAisle(123);
		_tutorial.setBook_name("Spring");
		_tutorial.setIsbn("xyz");
		_tutorial.setAuthor("Bhartendu Arya");
		_tutorial.setId("001");
		return _tutorial;
	}

	@Test
	public void checkBuildIdLogic() {
		Tutorial tutorial = createTutorial();
		TutorialService tutorialService = new TutorialService();
		String actualId = tutorialService.buildId(tutorial.getIsbn(), tutorial.getAisle());
		assertEquals("xyz123", actualId);
	}

	@Test
	public void addTutorialTest() {
		Tutorial tutorial = createTutorial();

		when(tutorialService.buildId(tutorial.getIsbn(), tutorial.getAisle())).thenReturn(tutorial.getId());
		when(tutorialService.checkTutorialAlreadyExist(tutorial.getId())).thenReturn(true);
		when(tutorialRepository.save(any())).thenReturn(tutorial);

		//Bad practise code, since I called controller method directly. Should have used MockMvc.

		ResponseEntity response = tutorialController.createTutorial(tutorial);
		HttpStatusCode statusCode = response.getStatusCode();
		assertEquals(statusCode, HttpStatus.ACCEPTED);

		//assertEquals(statusCode, HttpStatus.CREATED);

		ApiResponse apiResponse = (ApiResponse) response.getBody();
		apiResponse.getId();
		assertEquals(tutorial.getId(), apiResponse.getId());
		//assertEquals("Successfully Added", apiResponse.getMessage());
		assertEquals("Tutorial already exist", apiResponse.getMessage());
	}

	//Best way to write test code
	@Test
	public void addTutorialControllerTest() throws Exception {

		Tutorial tutorial = createTutorial();
		String jsonString = objectMapper.writeValueAsString(tutorial);

		when(tutorialService.buildId(tutorial.getIsbn(), tutorial.getAisle())).thenReturn(tutorial.getId());
		when(tutorialService.checkTutorialAlreadyExist(tutorial.getId())).thenReturn(false);
		when(tutorialRepository.save(any())).thenReturn(tutorial);

		mockMvc.perform(post("/api/tutorials").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(header().string("unique", "001"))
				.andExpect(jsonPath("$.message").value("Successfully Added"))
				.andExpect(jsonPath("$.id").value(tutorial.getId()));
	}
}
