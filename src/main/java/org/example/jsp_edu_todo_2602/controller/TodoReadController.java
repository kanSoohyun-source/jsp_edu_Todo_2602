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

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2

public class TodoReadController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/read get...");
        long tno = Long.parseLong(req.getParameter("tno"));
        TodoDTO todoDTO = todoService.getOne(tno);

        req.setAttribute("todoDTO", todoDTO);
        req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
    }
}
