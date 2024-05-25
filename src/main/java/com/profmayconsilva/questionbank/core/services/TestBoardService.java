package com.profmayconsilva.questionbank.core.services;

import com.profmayconsilva.questionbank.core.dtos.TestBoardRequestDTO;
import com.profmayconsilva.questionbank.core.dtos.TestBoardResponseDTO;
import com.profmayconsilva.questionbank.core.entities.TestBoard;
import com.profmayconsilva.questionbank.core.exceptions.InvalidDataException;
import com.profmayconsilva.questionbank.core.repositories.TestBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
public class TestBoardService {
    @Autowired
    private TestBoardRepository testBoardRepository;

    public void createNewTestBoard(TestBoardRequestDTO data) throws InvalidDataException {
        validateTestBoardData(data);
        TestBoard testBoard = new TestBoard(data);
        testBoard.setCreatedDate(LocalDateTime.now());
        testBoardRepository.save(testBoard);
    }
    public void deleteTestBoard(Long testBoardId) throws InvalidDataException {
        if (!testBoardRepository.existsById(testBoardId)) {
            throw new InvalidDataException("Test Board with the given ID does not exist.");
        }
        testBoardRepository.deleteById(testBoardId);
    }

    public void updateTestBoard(Long testBoardId, TestBoardRequestDTO data) throws InvalidDataException {
        if (!testBoardRepository.existsById(testBoardId)) {
            throw new InvalidDataException("Test Board with the given ID does not exist.");
        }
        validateTestBoardData(data);
        TestBoard existingTestBoard = testBoardRepository.findById(testBoardId).orElseThrow(() -> new InvalidDataException("Test Board not found"));
        existingTestBoard.setTestBoardName(data.testBoardName());
        existingTestBoard.setTestBoardAcronym(data.testBoardAcronym());
        existingTestBoard.setLastModifiedDate(LocalDateTime.now());
        testBoardRepository.save(existingTestBoard);
    }

    public TestBoard getTestBoardById(Long testBoardId) throws InvalidDataException {
        return testBoardRepository.findById(testBoardId).orElseThrow(() -> new InvalidDataException("Test Board not found"));
    }

    public Page<TestBoardResponseDTO> getAllTestBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return testBoardRepository.findAll(pageable).map(TestBoardResponseDTO::new);
    }

    private void validateTestBoardData(TestBoardRequestDTO data) throws InvalidDataException {
        if (data.testBoardName() == null || data.testBoardName().isEmpty()) {
            throw new InvalidDataException("Test Board name is required.");
        }
        if (data.testBoardAcronym() == null || data.testBoardAcronym().isEmpty()) {
            throw new InvalidDataException("Test Board acronym is required.");
        }
        if (testBoardRepository.findByTestBoardName(data.testBoardName()).isPresent()) {
            throw new InvalidDataException("A Test Board with the same name already exists.");
        }
        if (testBoardRepository.findByTestBoardName(data.testBoardAcronym()).isPresent()) {
            throw new InvalidDataException("A Test Board with the same acronym already exists.");
        }
    }
}
