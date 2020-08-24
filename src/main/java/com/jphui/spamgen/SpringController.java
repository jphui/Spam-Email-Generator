package com.jphui.spamgen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpringController {

    // HOMEPAGE
    @GetMapping("/")
    public String home(Model model)
    {
        InfoBall ifb = new InfoBall();
        model.addAttribute("ifb", ifb);
        return "index.html";
    }

    @PostMapping("/result")
    public String result(@ModelAttribute("ifb") InfoBall ifb)
    {
        ifb.generate();
        return "result.html";
    }
}
