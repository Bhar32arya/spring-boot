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

    public long buildId(long id) {
        return id + 5;
    }

    public boolean checkTutorialAlreadyExist(long id) {
        Optional<Tutorial> optionalTutorial = tutorialRepository.findById(id);
        if(optionalTutorial.isPresent()) {
            return true;
        }
        return false;
    }
}
