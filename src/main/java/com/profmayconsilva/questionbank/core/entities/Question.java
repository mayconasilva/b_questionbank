package com.profmayconsilva.questionbank.core.entities;

import com.profmayconsilva.questionbank.core.dtos.QuestionRequestDTO;
import com.profmayconsilva.questionbank.core.enums.AreaOfKnowledgeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @Column(nullable = false)
    private Year questionYear;
    @ManyToOne
    @JoinColumn(name = "test_board_id", nullable = false)
    private TestBoard testBoard;
    @Column(nullable = false)
    private AreaOfKnowledgeEnum areaOfKnowledge;
    @Column(nullable = false)
    private String discipline;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String statement;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String solution;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Question(QuestionRequestDTO data) {
        this.questionTitle = data.questionTitle();
        this.questionYear = data.questionYear();
        this.testBoard = data.testBoard();
        this.areaOfKnowledge = data.areaOfKnowledge();
        this.discipline = data.discipline();
        this.content = data.content();
        this.statement = data.statement();
        this.answer = data.answer();
        this.solution = data.solution();
    }
}
