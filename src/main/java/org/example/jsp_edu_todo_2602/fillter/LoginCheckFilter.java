package org.example.jsp_edu_todo_2602.fillter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

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
        if (session.getAttribute("loginInfo") == null) { // 로그인하지 않은 경우
            log.info("session.loginInfo is null");
            resp.sendRedirect("/login");
            return;
        }

        // 1. 로그인한 사용자인 경우


        // 2. 로그인하지 않은 사용자인 경우
        // 1) remember-me가 없는 경우


        // 2) remember-me는 있는데 DB에 없는 경우


        // 3) remember-me도 있고 DB에도 있는 경우





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

        if(cookies != null && cookies.length > 0) { // 매개변수로 받는 쿠키가 있는 경우
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)) { // 쿠키 이름이 일치하는 경우
                    findcookie = cookie;
                    break;
                }
            }
        }
        return findcookie;
    }
}
