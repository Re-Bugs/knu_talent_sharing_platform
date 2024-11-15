package com.zeroone.kstp.config;

import com.zeroone.kstp.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(HttpSession session) {
        // 세션에 user가 있으면 로그인 상태로 간주
        return session.getAttribute("user") != null;
    }

    @ModelAttribute("loggedInUser")
    public User getLoggedInUser(HttpSession session) {
        // 로그인된 사용자를 반환, 없으면 null
        return (User) session.getAttribute("user");
    }
}