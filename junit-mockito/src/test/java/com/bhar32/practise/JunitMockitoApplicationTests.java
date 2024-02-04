package com.bhar32.practise;

import com.bhar32.practise.controller.TutorialController;
import com.bhar32.practise.model.Tutorial;
import com.bhar32.practise.repository.TutorialRepository;
import com.bhar32.practise.service.TutorialService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* 1. From postman if we send the data in json format then spring boot does the duty to convert json to java object.
* 2. But from test case if possible we can pass direct java object
* 3. However, if we have json data then needs to be converted into java object using ObjectMapper then to pass controller level.
* 4. Either create direct class instance or use autowired.
* 5. All the dependency should be mocked using MockBean. */

@SpringBootTest
class JunitMockitoApplicationTests {

	@Autowired
	private TutorialController tutorialController;

	@MockBean
	private TutorialService tutorialService;

	@MockBean
	private TutorialRepository tutorialRepository;

	@Test
	void contextLoads() {
	}

	public Tutorial createTutorial() {
		Tutorial _tutorial = new Tutorial();
		_tutorial.setId(1234);
		_tutorial.setTitle("Core Java");
		_tutorial.setDescription("Basics to advance level of java");
		_tutorial.setPublished(true);
		return _tutorial;
	}

	@Test
	public void checkBuildIdLogic() {
		TutorialService tutorialService = new TutorialService();
		long builtId = tutorialService.buildId(123);
		assertEquals(123 + 5, builtId);
	}

	@Test
	public void addTutorialTest() {
		Tutorial tutorial = createTutorial();

		Mockito.when(tutorialService.buildId(5L)).thenReturn(10L);
		Mockito.when(tutorialService.checkTutorialAlreadyExist(10L)).thenReturn(false);

		ResponseEntity response = tutorialController.createTutorial(tutorial);
		HttpStatusCode statusCode = response.getStatusCode();
		assertEquals(statusCode, HttpStatus.CREATED);
	}
}
