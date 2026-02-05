package org.example.jsp_edu_todo_2602.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.dto.TodoDTO;
import org.example.jsp_edu_todo_2602.service.TodoService;

import java.io.IOException;
import java.net.CookieHandler;

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2

public class TodoReadController extends HttpServlet {
    private final TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/read get...");
        long tno = Long.parseLong(req.getParameter("tno"));
        TodoDTO todoDTO = todoService.getOne(tno);
        // 데이터 담기
        req.setAttribute("todoDTO", todoDTO);

        // 쿠키 찾기
        Cookie viewTodoCookie = findcookie(req.getCookies(), "viewTodos");
        String todoListStr = viewTodoCookie.getValue(); // 찾은 쿠키의 값을 문자열에 저장

        boolean exists = false; // 해당 tno가 쿠키에 있는지 여부
        if (todoListStr != null) {
            // 보고있는 게시물의 tno를 가진 값이 문자열에 존재한다면
            for (String str : todoListStr.split("-")) {
                if (str.equals(String.valueOf(tno))) {
                    exists = true;
                    break;
                }
            }
        }
        log.info(exists);

        if (!exists) { // 쿠키에 해당 tno가 없으면
            todoListStr += tno + "-"; // 쿠키에 값 추가
            viewTodoCookie.setValue(todoListStr); // 해당 게시물의 tno를 포함한 문자열을 쿠키의 값으로 변경
            viewTodoCookie.setPath("/");
            viewTodoCookie.setMaxAge(60 * 60 * 24);
            resp.addCookie(viewTodoCookie); // response에 쿠키 추가
        }

        req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
    }

    private Cookie findcookie(Cookie[] cookies, String cookieName) {
        /*
        매개변수로 받은 cookie 배열에서 cookieName과 일치하는 쿠키를 찾아서 반환
        없으면 cookieName으로 이름이 지정된 새로운 쿠키를 생성해 반환
         */
        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0) { // 매개변수로 받는 쿠키가 있는 경우
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)) { // 쿠키 이름이 일치하는 경우
                    targetCookie = cookie;
                    break;
                }
            }
        }
        // 쿠키가 없다면 새로운 쿠키를 생성
        if (targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간은 24시간으로 설정
        }
        return targetCookie;
    }
}
