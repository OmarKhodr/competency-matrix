package com.example.postgresdemo.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Project;
import com.example.postgresdemo.repository.ProjectRepository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository questionRepository;

    @GetMapping("/projects")
    public Page<Project> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }


    @PostMapping("/projects")
    public Project createQuestion(@Valid @RequestBody Project question) {
        return questionRepository.save(question);
    }

    @PutMapping("/projects/{questionId}")
    public Project updateQuestion(@PathVariable Integer questionId,
                                   @Valid @RequestBody Project questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setProject_name(questionRequest.getProject_name());
                    question.setProject_leader(questionRequest.getProject_leader());
                    question.setCompleted(questionRequest.getCompleted());
                    question.setStart_date(questionRequest.getStart_date());
                    question.setEnd_date(questionRequest.getEnd_date());
                    question.setEffectiveness(questionRequest.getEffectiveness());
                    question.setEfficiency(questionRequest.getEfficiency());
                    question.setDeadline(questionRequest.getDeadline());
                    question.setSeverity(questionRequest.getSeverity());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @DeleteMapping("/projects/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}
