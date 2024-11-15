package com.zeroone.kstp.controller;

import com.zeroone.kstp.DTO.LoginDTO;
import com.zeroone.kstp.DTO.RegisterDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class homeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/learning_core")
    public String learningCore(Model model){
        model.addAttribute("currentMenu", "description");
        return "learning_core/learning_core_main";
    }

    @GetMapping("/major_learner")
    public String majorLearner(Model model){
        model.addAttribute("currentMenu", "description");
        return "major_learner/major_learner_main";
    }

    @GetMapping("/challenge_learner")
    public String challengeLearner(Model model){
        model.addAttribute("currentMenu", "description");
        return "challenge_learner/challenge_learner_main";
    }

    @GetMapping("/introduction")
    public String introduction(Model model){
        model.addAttribute("currentMenu", "introduction");
        return "community/introduction";
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        return "login/login";
    }

    @GetMapping("/register")
    public String showRegister(Model model){
        model.addAttribute("registerDTO", new RegisterDTO());
        return "login/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "로그아웃 되었습니다.");
        return "redirect:/login";
    }
}
