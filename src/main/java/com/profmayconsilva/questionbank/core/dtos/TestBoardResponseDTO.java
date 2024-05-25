package com.profmayconsilva.questionbank.core.dtos;

import com.profmayconsilva.questionbank.core.entities.Question;
import com.profmayconsilva.questionbank.core.entities.TestBoard;

import java.util.List;

public record TestBoardResponseDTO(Long testBoardId, String testBoardName, String testBoardAcronym, List<Question> questionList) {
    public TestBoardResponseDTO(TestBoard testBoard){
        this(testBoard.getTestBoardId(), testBoard.getTestBoardName(), testBoard.getTestBoardAcronym(), testBoard.getQuestionList());
    }
}
