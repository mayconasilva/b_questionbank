package com.profmayconsilva.questionbank.core.controllers;

import com.profmayconsilva.questionbank.core.dtos.TestBoardRequestDTO;
import com.profmayconsilva.questionbank.core.dtos.TestBoardResponseDTO;
import com.profmayconsilva.questionbank.core.exceptions.InvalidDataException;
import com.profmayconsilva.questionbank.core.repositories.TestBoardRepository;
import com.profmayconsilva.questionbank.core.services.TestBoardService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/test-board")
public class TestBoardController {

    @Autowired
    private TestBoardRepository testBoardRepository;
    @Autowired
    private TestBoardService testBoardService;

    @GetMapping("all")
    public ResponseEntity<Page<TestBoardResponseDTO>> getAllTestBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TestBoardResponseDTO> testBoardPage = testBoardService.getAllTestBoards(page, size);
        return ResponseEntity.ok(testBoardPage);
    }

    @PostMapping("new")
    public ResponseEntity<String> createNewTestBoard(@RequestBody TestBoardRequestDTO data){
        try {
            testBoardService.createNewTestBoard(data);
            return ResponseEntity.ok("Test Board created successfully.");
        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred.");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTestBoard(@PathVariable Long id) {
        try {
            testBoardService.deleteTestBoard(id);
            return ResponseEntity.ok("Test Board deleted successfully.");
        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred.");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTestBoard(@PathVariable Long id, @RequestBody TestBoardRequestDTO data) {
        try {
            testBoardService.updateTestBoard(id, data);
            return ResponseEntity.ok("Test Board updated successfully.");
        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred.");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<TestBoardResponseDTO> getTestBoardById(@PathVariable Long id) {
        try {
            TestBoardResponseDTO testBoard = new TestBoardResponseDTO(testBoardService.getTestBoardById(id));
            return ResponseEntity.ok(testBoard);
        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
