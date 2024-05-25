package com.profmayconsilva.questionbank.core.controllers;

import com.profmayconsilva.questionbank.core.dtos.QuestionRequestDTO;
import com.profmayconsilva.questionbank.core.dtos.QuestionResponseDTO;
import com.profmayconsilva.questionbank.core.entities.Question;
import com.profmayconsilva.questionbank.core.services.QuestionService;
import com.profmayconsilva.questionbank.exceptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionRequestDTO requestDTO) {
        try {
            questionService.createNewQuestion(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Question created successfully.");
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            return ResponseEntity.ok("Question deleted successfully.");
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionRequestDTO requestDTO) {
        try {
            questionService.updateQuestion(questionId, requestDTO);
            return ResponseEntity.ok("Question updated successfully.");
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId) {
        try {
            return ResponseEntity.ok(questionService.getQuestionId(questionId));
        } catch (InvalidDataException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<QuestionResponseDTO>> getAllQuestions(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        try {
            Page<QuestionResponseDTO> questions = questionService.getAllQuestions(page, size);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para buscar questões por área de conhecimento
    @GetMapping("/by-area-of-knowledge/{areaOfKnowledge}")
    public ResponseEntity<Page<QuestionResponseDTO>> getQuestionsByAreaOfKnowledge(@PathVariable String areaOfKnowledge,
                                                                                   @RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int size) {
        try {
            Page<QuestionResponseDTO> questions = questionService.getQuestionsByAreaOfKnowledge(areaOfKnowledge, page, size);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para buscar questões por disciplina
    @GetMapping("/by-discipline/{discipline}")
    public ResponseEntity<Page<QuestionResponseDTO>> getQuestionsByDiscipline(@PathVariable String discipline,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {
        try {
            Page<QuestionResponseDTO> questions = questionService.getQuestionsByDiscipline(discipline, page, size);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para buscar questões por nome do quadro de teste
    @GetMapping("/by-test-board/{testBoardName}")
    public ResponseEntity<Page<QuestionResponseDTO>> getQuestionsByTestBoardName(@PathVariable String testBoardName,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        try {
            Page<QuestionResponseDTO> questions = questionService.getQuestionsByTestBoardName(testBoardName, page, size);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para buscar questões por conteúdo
    @GetMapping("/by-content")
    public ResponseEntity<Page<QuestionResponseDTO>> getQuestionsByContent(@RequestParam String content,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size) {
        try {
            Page<QuestionResponseDTO> questions = questionService.getQuestionsByContent(content, page, size);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
