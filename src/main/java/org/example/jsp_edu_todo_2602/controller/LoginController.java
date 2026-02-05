package org.example.jsp_edu_todo_2602.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.service.MemberService;

import java.io.IOException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {
    private final MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 폼
        log.info("/login get...");
        req.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 로그인 처리
        log.info("/login post...");
        // 1. 입력받은 아이디랑 비번 변수에 저장
        String memberId = req.getParameter("memberId");
        String passwd = req.getParameter("passwd");
        String sessionStr = memberId + passwd;
        String remember = req.getParameter("remember");
        boolean rememberMe = remember != null && remember.equals("on"); // remember check하면 true

        if (memberService.isAuth(memberId, passwd)) {
            // 인증 성공 -> 사용자가 입력한 아이디와 비번이 데이터 베이스에 저장된 정보와 동일
            // session이 내장 객체가 아니기 때문에 type 선언 해야 함
            HttpSession session = req.getSession();

            session.setAttribute("loginInfo", sessionStr);
            // 'loginInfo'로 처리해주지 않으면 아이디 비번을 맞게 입력해도 로그인 처리가 되지 않음
            
            if (rememberMe) {
                // 로그인 성공을 해야 임의의 문자열 생성
                String uuid = UUID.randomUUID().toString(); // 임의의 문자열 생성

                // DB에 문자열 저장
                memberService.modifyUuid(memberId, uuid);

                // Cookie(클라이언트)에 문자열 저장
                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(24 * 60 * 60 * 7); // 쿠키 유효기간 7일
                rememberCookie.setPath("/"); // 모든 경로에서 접근 가능

                resp.addCookie(rememberCookie);// 응답에 쿠키 추가
            }

            // 로그인 성공
            resp.sendRedirect("/todo/list");
        } else {
            // 로그인 실패
            resp.sendRedirect("login?error=1");
        }
    }

}
