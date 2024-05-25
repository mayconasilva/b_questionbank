package com.profmayconsilva.questionbank.core.repositories;

import com.profmayconsilva.questionbank.core.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByAreaOfKnowledge(String areaOfKnowledge, Pageable pageable);

    Page<Question> findByDiscipline(String discipline, Pageable pageable);

    Page<Question> findByTestBoardTestBoardName(String testBoardName, Pageable pageable);

    Page<Question> findByContentContaining(String content, Pageable pageable);
}
