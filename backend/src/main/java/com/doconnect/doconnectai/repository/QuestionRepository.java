package com.doconnect.doconnectai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnect.doconnectai.entity.Question;

public interface QuestionRepository
        extends JpaRepository<Question, Long> {
}