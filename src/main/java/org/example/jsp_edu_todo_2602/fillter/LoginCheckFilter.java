package org.example.jsp_edu_todo_2602.fillter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.jsp_edu_todo_2602.dto.MemberDTO;
import org.example.jsp_edu_todo_2602.service.MemberService;

import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = "/todo/*") // todo로 접근하는 모든 것을 검사
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // doFilter() - 필터가 필터링이 필요한 로직을 구현하는 부분
        log.info("/login doFilter...");
        // 세션에서 로그인 정보 확인
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        // 1. 로그인한 사용자인 경우 => loginInfo가 null이 아닌 경우
        if (session.getAttribute("loginInfo") != null) {
            chain.doFilter(request, response);
            return;
        }

        // 2. 로그인하지 않은 사용자인 경우 -> session.getAttribute("loginInfo") == null
        // => 위에서 null이 아닌 경우를 이미 걸러낸 상태
        Cookie cookie = findcookie(req.getCookies(), "remember-me");
        // 1) remember-me가 없는 경우
        if (cookie == null) {
            log.info("not exist remember-me cookie");
            resp.sendRedirect("/login");
            return;
        }

        // 2) remember-me는 있는데 DB에 없는 경우 => 로그인 처리 불가
        // DB에서 UUID 조회
        String uuid = cookie.getValue();
        log.info(uuid);
        MemberDTO memberDTO = MemberService.INSTANCE.getMemberByUuid(uuid);
        log.info(memberDTO);
        if (memberDTO == null) {
            log.info("exist remember-me cookie, not exist DB");
            resp.sendRedirect("/login");
            return;
        }

        // 3) remember-me도 있고 DB에도 있는 경우
        // 이미 remember-me의 존재여부, DB 안에 존재 여부가 위에서 이미 확인됨
        log.info("auto-login");
        session.setAttribute("loginInfo", memberDTO.getMemberId() + memberDTO.getPasswd());

        // 있으면 다음 필터 동작, 없으면 로그인 페이지로 이동

         /*
         doFilter()의 마지막에는 다음 필터나 목적지(서블릿, jsp)로 갈 수 있도록 filterChain의 doFilter()를 실행
         만일 문제가 생겨서 더 이상 진행할 수 없다면 다음 단계로 진행하지 않고 다른 방식으로 리다이렉트 처리
          */

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private Cookie findcookie(Cookie[] cookies, String cookieName) {
        /*
        매개변수로 받은 cookie 배열에서 cookieName과 일치하는 쿠키를 찾아서 반환
        없으면 cookieName으로 이름이 지정된 새로운 쿠키를 생성해 반환
         */
        Cookie findcookie = null;

        if (cookies != null && cookies.length > 0) { // 매개변수로 받는 쿠키가 있는 경우
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) { // 쿠키 이름이 일치하는 경우
                    findcookie = cookie;
                    break;
                }
            }
        }
        return findcookie;
    }
}
