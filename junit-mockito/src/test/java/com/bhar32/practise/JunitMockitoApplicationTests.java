package com.bhar32.practise;

import com.bhar32.practise.service.TutorialService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JunitMockitoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void checkBuildIdLogic() {
		TutorialService tutorialService = new TutorialService();
		String builtId = tutorialService.buildId(123, "Spring");
		String builtIdTwo = tutorialService.buildId(456, "Java");
		assertEquals("123Spring", builtId);
		assertEquals("Java456Java", builtIdTwo);
	}

}
