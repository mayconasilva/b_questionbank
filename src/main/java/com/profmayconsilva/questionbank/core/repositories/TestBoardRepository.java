package com.profmayconsilva.questionbank.core.repositories;

import com.profmayconsilva.questionbank.core.entities.Question;
import com.profmayconsilva.questionbank.core.entities.TestBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestBoardRepository extends JpaRepository<TestBoard, Long> {
    Optional<TestBoard> findByTestBoardName(String testBoardName);
    Optional<TestBoard> findByTestBoardAcronym(String testBoardAcronym);

}
