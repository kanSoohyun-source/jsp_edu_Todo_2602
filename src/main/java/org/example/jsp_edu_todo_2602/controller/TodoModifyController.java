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

@Log4j2
@WebServlet(name = "todoModifyController", value = "/todo/modify")
public class TodoModifyController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    // 수정 폼
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/modify get...");

        long tno = Long.parseLong(req.getParameter("tno"));
        TodoDTO todoDTO = todoService.getOne(tno);

        req.setAttribute("todoDTO", todoDTO);
        req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
    }

    // 수정 처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/modify post...");
        log.info(req.getParameter("tno"));
        log.info(req.getParameter("title"));
        log.info(req.getParameter("finish"));

        // 1. 파라미터로 dto 저장
        long tno = Long.parseLong(req.getParameter("tno"));
        String title = req.getParameter("title");
        LocalDate dueDate = LocalDate.parse(req.getParameter("dueDate"));
        boolean finish = (req.getParameter("finish").equals("on"));

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(tno)
                .title(title)
                .dueDate(dueDate)
                .finish(finish).build();
        log.info(todoDTO);

        // 2. service의 해당 메서드 호출
        todoService.modify(todoDTO);

        // 3. 페이지 이동
        resp.sendRedirect("/todo/read?tno=" + tno);

    }
}
