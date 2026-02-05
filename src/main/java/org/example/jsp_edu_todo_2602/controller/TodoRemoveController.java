package org.example.jsp_edu_todo_2602.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.service.TodoService;

import java.io.IOException;

@Log4j2
@WebServlet(name = "todoRemoveController", value = "/todo/remove")
public class TodoRemoveController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/remove post...");
        // 파라미터 저장
        long tno = Long.parseLong(req.getParameter("tno"));
        log.info(tno);

        // service 메서드 호출
        todoService.remove(tno);

        // 화면 이동
        resp.sendRedirect("/todo/list");
    }
}
