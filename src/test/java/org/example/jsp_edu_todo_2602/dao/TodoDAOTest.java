package org.example.jsp_edu_todo_2602.dao;

import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.domain.TodoVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class TodoDAOTest {
    private TodoDAO todoDAO;

    @BeforeEach
    public void ready() {
        // 테스트 전에 TodoDAO 타입의 객체 생성
        todoDAO = new TodoDAO();
    }

    @Test
    public void getTimeTest() {
        log.info(todoDAO.getTime());
    }

    @Test
    public void getTime2Test() {
        log.info(todoDAO.getTime2());
    }

    @Test
    public void insertTest() {
        TodoVO todoVO = TodoVO.builder()
                .title("sample3")
                .dueDate(LocalDate.of(2026,2,4)).build();
        todoDAO.insert(todoVO);
    }

    @Test
    public void selectAllTest() {
        var todos = todoDAO.selectAll();

        // 1. foreach
        for (TodoVO todoVO : todos) {
            log.info(todoVO);
        }

        // 2. stream
        todos.forEach(it -> log.info(it));
    }

    @Test
    public void selectOneTest() {
        long tno = 2;
        TodoVO todoVO = todoDAO.selectOne(tno);
        log.info(todoVO);
        // null이 아니라면
        Assertions.assertNotNull(todoVO);

        tno = 20;
        todoVO = todoDAO.selectOne(tno);
        log.info(todoVO);
        // null 이라면
        Assertions.assertNull(todoVO);
    }

    @Test
    public void deleteTest() {
        long tno = 2;
        todoDAO.delete(tno);

        var todos = todoDAO.selectAll();
        todos.forEach(it -> log.info(it));
    }

    @Test
    public void updateTest() {
        long tno = 3;
        TodoVO todoVO = TodoVO.builder()
                .tno(tno)
                .title("modify")
                .dueDate(LocalDate.of(2026, 2, 3))
                .finish(true).build();
        todoDAO.update(todoVO);
        TodoVO todoVO1 = todoDAO.selectOne(tno);
        log.info(todoVO1);

    }
}