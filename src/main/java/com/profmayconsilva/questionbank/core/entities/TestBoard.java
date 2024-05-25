package com.profmayconsilva.questionbank.core.entities;

import com.profmayconsilva.questionbank.core.dtos.TestBoardRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "test_boar")
@Table(name = "tb_testBoard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testBoardId;
    @Column(nullable = false, unique = true)
    private String testBoardName;
    @Column(nullable = false, unique = true)
    private String testBoardAcronym;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    @OneToMany
    private List<Question> questionList;

    public TestBoard(TestBoardRequestDTO data) {
       this.testBoardName = data.testBoardName();
       this.testBoardAcronym = data.testBoardAcronym();

    }
}
