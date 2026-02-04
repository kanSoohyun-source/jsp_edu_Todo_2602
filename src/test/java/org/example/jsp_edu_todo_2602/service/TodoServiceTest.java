package org.example.jsp_edu_todo_2602.service;

import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.dto.TodoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2

class TodoServiceTest {
    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void addTest() {
        todoService.add(TodoDTO.builder()
                .title("serviceTest")
                .dueDate(LocalDate.of(2026, 2, 2)).build());
    }

    @Test
    public void getAllTest() {
        List<TodoDTO> todoDTOList = todoService.getAll();

        for (TodoDTO todoDTO : todoDTOList) {
            log.info(todoDTO);
        }
    }

    @Test
    public void getOneTest() {
        long tno = 3;
        TodoDTO todoDTO = todoService.getOne(tno);

        log.info(todoDTO);
    }
}