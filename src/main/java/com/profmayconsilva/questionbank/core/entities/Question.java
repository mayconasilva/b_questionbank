package com.profmayconsilva.questionbank.core.entities;

import com.profmayconsilva.questionbank.core.enums.AreaOfKnowledgeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Entity(name = "question")
@Table(name = "tb_question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String questionTitle;
    private Year questionYear;
    @ManyToOne
    private TestBoard testBoard;
    private AreaOfKnowledgeEnum areaOfKnowledge;
    private String discipline;
    private String content;
    @Lob
    private String statement;
    private String answer;
    @Lob
    private String solution;
}
