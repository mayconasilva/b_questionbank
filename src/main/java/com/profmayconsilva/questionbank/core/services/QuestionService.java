package com.profmayconsilva.questionbank.core.services;

import com.profmayconsilva.questionbank.core.dtos.QuestionRequestDTO;
import com.profmayconsilva.questionbank.core.dtos.QuestionResponseDTO;
import com.profmayconsilva.questionbank.core.entities.Question;
import com.profmayconsilva.questionbank.core.entities.TestBoard;
import com.profmayconsilva.questionbank.core.repositories.TestBoardRepository;
import com.profmayconsilva.questionbank.exceptions.InvalidDataException;
import com.profmayconsilva.questionbank.core.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TestBoardRepository testBoardRepository;

    public void createNewQuestion(QuestionRequestDTO data) throws InvalidDataException {
        validateQuestionData(data);
        Question question = new Question(data);
        question.setCreatedDate(LocalDateTime.now());
        questionRepository.save(question);
    }
    public void deleteQuestion(Long questionId) throws InvalidDataException {
        if (!questionRepository.existsById(questionId)) {
            throw new InvalidDataException("Question with the given ID does not exist.");
        }
        questionRepository.deleteById(questionId);
    }

    public void updateQuestion(Long questionId, QuestionRequestDTO data) throws InvalidDataException {
        if (!questionRepository.existsById(questionId)) {
            throw new InvalidDataException("Question with the given ID does not exist.");
        }
        validateQuestionData(data);
        Question existingQuestion= questionRepository.findById(questionId).orElseThrow(() -> new InvalidDataException("Question not found"));
        existingQuestion.setQuestionTitle(data.questionTitle());
        existingQuestion.setQuestionYear(data.questionYear());
        existingQuestion.setTestBoard(data.testBoard());
        existingQuestion.setAreaOfKnowledge(data.areaOfKnowledge());
        existingQuestion.setDiscipline(data.discipline());
        existingQuestion.setContent(data.content());
        existingQuestion.setStatement(data.statement());
        existingQuestion.setAnswer(data.answer());
        existingQuestion.setSolution(data.solution());
        existingQuestion.setLastModifiedDate(LocalDateTime.now());
        questionRepository.save(existingQuestion);
    }

    public Question getQuestionId(Long questionId) throws InvalidDataException {
        return questionRepository.findById(questionId).orElseThrow(() -> new InvalidDataException("Question not found"));
    }

    public Page<QuestionResponseDTO> getAllQuestions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findAll(pageable).map(QuestionResponseDTO::new);
    }

    public Page<QuestionResponseDTO> getQuestionsByAreaOfKnowledge(String areaOfKnowledge, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findByAreaOfKnowledge(areaOfKnowledge, pageable).map(QuestionResponseDTO::new);
    }

    public Page<QuestionResponseDTO> getQuestionsByDiscipline(String discipline, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findByDiscipline(discipline, pageable).map(QuestionResponseDTO::new);
    }

    public Page<QuestionResponseDTO> getQuestionsByTestBoardName(String testBoardName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findByTestBoardTestBoardName(testBoardName, pageable).map(QuestionResponseDTO::new);
    }

    public Page<QuestionResponseDTO> getQuestionsByContent(String content, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.findByContentContaining(content, pageable).map(QuestionResponseDTO::new);
    }

    public void validateQuestionData(QuestionRequestDTO data) throws InvalidDataException {
        if (data.questionTitle() == null || data.questionTitle().isEmpty()) {
            throw new InvalidDataException("Question title is required.");
        }
        if (data.questionYear() == null) {
            throw new InvalidDataException("Question year is required.");
        }
        if (data.testBoard() == null) {
            throw new InvalidDataException("Test board is required.");
        }
        if (data.areaOfKnowledge() == null) {
            throw new InvalidDataException("Area of knowledge is required.");
        }
        if (data.discipline() == null || data.discipline().isEmpty()) {
            throw new InvalidDataException("Discipline is required.");
        }
        if (data.content() == null || data.content().isEmpty()) {
            throw new InvalidDataException("Content is required.");
        }
        if (data.statement() == null || data.statement().isEmpty()) {
            throw new InvalidDataException("Statement is required.");
        }
        if (data.answer() == null || data.answer().isEmpty()) {
            throw new InvalidDataException("Answer is required.");
        }
        if (data.solution() == null || data.solution().isEmpty()) {
            throw new InvalidDataException("Solution is required.");
        }

        // Verificar se o ano da questão é um ano válido
        Year currentYear = Year.now();
        if (data.questionYear().isAfter(currentYear) || data.questionYear().isBefore(Year.of(1900))) {
            throw new InvalidDataException("Invalid question year.");
        }

        if (!testBoardRepository.existsById(data.testBoard().getTestBoardId())) {
            throw new InvalidDataException("Test board with the provided ID does not exist.");
        }

    }
}
