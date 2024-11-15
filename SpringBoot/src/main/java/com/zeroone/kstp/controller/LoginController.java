package com.zeroone.kstp.controller;

import com.zeroone.kstp.DTO.LoginDTO;
import com.zeroone.kstp.DTO.RegisterDTO;
import com.zeroone.kstp.domain.User;
import com.zeroone.kstp.enumeration.UserRole;
import com.zeroone.kstp.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute RegisterDTO registerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model)
    {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "login/register";
        }

        // 닉네임 중복 확인
        if (userService.isStudentNumberExists(registerDTO.getStudentNumber())) {
            bindingResult.rejectValue("studentNumber", "error.studentNumber", "이미 존재하는 학번입니다.");
            return "login/register";
        }

        Optional<User> findUser = userService.findStudentNumber(registerDTO.getStudentNumber());
        if(findUser.isEmpty())
        {
            User newUser = User.builder()
                    .studentNumber(registerDTO.getStudentNumber())
                    .password(registerDTO.getPassword())
                    .name(registerDTO.getName())
                    .tel(registerDTO.getTel())
                    .level(registerDTO.getLevel())
                    .major(registerDTO.getMajor())
                    .lastGrades(registerDTO.getLastGrade())
                    .role(UserRole.user)
                    .build();

            // User 저장
            userService.save(newUser);

            // 회원가입 성공 메시지 및 리다이렉트 처리
            redirectAttributes.addFlashAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
            return "redirect:/login";
        }
        else
        {
            model.addAttribute("errorMessage", "이미 존재하는 아이디입니다.");
            return "login/register";
        }
    }

    @PostMapping
    public String loginProcess(@ModelAttribute LoginDTO loginDTO, RedirectAttributes redirectAttributes, HttpSession session, Model model)
    {
        log.info("{}", loginDTO.getStudentNumber());
        log.info("{}", loginDTO.getPassword());
        if(userService.login(loginDTO))
        {
            User user = userService.findStudentNumber(loginDTO.getStudentNumber()).orElseThrow();
            session.setAttribute("user", user);
            if(user.getRole().equals(UserRole.user))
            {
                return "redirect:/";
            }
            else if(user.getRole().equals(UserRole.admin))
            {
                model.addAttribute("isLoggedIn", true);
                //나중에 추가
            }
        }

        redirectAttributes.addFlashAttribute("errorMessage", "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
        return "redirect:/login";
    }

}
