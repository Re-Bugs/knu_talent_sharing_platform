package com.zeroone.kstp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
