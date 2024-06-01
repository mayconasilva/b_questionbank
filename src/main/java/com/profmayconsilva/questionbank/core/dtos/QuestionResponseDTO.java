package com.profmayconsilva.questionbank.core.dtos;

import com.profmayconsilva.questionbank.core.entities.Question;
import com.profmayconsilva.questionbank.core.entities.TestBoard;
import com.profmayconsilva.questionbank.core.enums.AreaOfKnowledgeEnum;

import java.time.Year;

public record QuestionResponseDTO(Long questionId, String questionTitle, Year questionYear, TestBoard testBoard, AreaOfKnowledgeEnum areaOfKnowledge, String discipline, String content, String statement, String answer, String solution) {
   public QuestionResponseDTO(Question question){
       this(question.getQuestionId(), question.getQuestionTitle(), question.getQuestionYear(), question.getTestBoard(), question.getAreaOfKnowledge(), question.getDiscipline(), question.getContent(), question.getStatement(), question.getAnswer(), question.getSolution());
   }
}
