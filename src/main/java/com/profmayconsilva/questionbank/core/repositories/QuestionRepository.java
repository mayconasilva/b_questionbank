package com.profmayconsilva.questionbank.core.repositories;

import com.profmayconsilva.questionbank.core.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
