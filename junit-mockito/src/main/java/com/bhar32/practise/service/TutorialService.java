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

    public String buildId(String isbn, int aisle) {
        if (isbn.startsWith("Z")) {
            return "OLD" + isbn + aisle;
        }
        return isbn + aisle;
    }

    public boolean checkTutorialAlreadyExist(String id) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(id);
        if (tutorial.isPresent())
            return true;
        else
            return false;
    }
    public Tutorial getBookById(String id) {
        return tutorialRepository.findById(id).get();
    }
}
