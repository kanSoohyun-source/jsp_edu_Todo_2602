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
import java.time.LocalDate;

@WebServlet(name = "todoAddController", value = "/todo/add")
@Log4j2
public class TodoAddController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/add get...");
        req.getRequestDispatcher("/WEB-INF/todo/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/add post...");
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"))).build();
        log.info(todoDTO);
        todoService.add(todoDTO);

        resp.sendRedirect("/todo/list");
    }
}
