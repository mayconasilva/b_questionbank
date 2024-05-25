package com.profmayconsilva.questionbank.core.dtos;

import com.profmayconsilva.questionbank.core.entities.Question;
import com.profmayconsilva.questionbank.core.entities.TestBoard;
import com.profmayconsilva.questionbank.core.enums.AreaOfKnowledgeEnum;

import java.time.Year;

public record QuestionRequestDTO(String questionTitle, Year questionYear, TestBoard testBoard, AreaOfKnowledgeEnum areaOfKnowledge, String discipline, String content, String statement, String answer, String solution) {
}
