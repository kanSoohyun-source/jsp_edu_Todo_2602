package org.example.jsp_edu_todo_2602.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.dto.TodoDTO;
import org.example.jsp_edu_todo_2602.service.TodoService;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "todoListController", value = "/todo/list")
public class TodoListController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todo list...");

        List<TodoDTO> todoDTOList = todoService.getAll();
        req.setAttribute("dtoList", todoDTOList);
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    }
}
