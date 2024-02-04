package com.bhar32.practise.service;

import com.bhar32.practise.model.Tutorial;
import com.bhar32.practise.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    public String buildId(long id, String title) {
        if(title.startsWith("J")) {
            return "Java" + id + title;
        }
        return id + title;
    }

    public boolean checkTutorialAlreadyExist(Long id) {
        Optional<Tutorial> optionalTutorial = tutorialRepository.findById(id.longValue());
        if(optionalTutorial.isPresent()) {
            return true;
        }
        return false;
    }
}
