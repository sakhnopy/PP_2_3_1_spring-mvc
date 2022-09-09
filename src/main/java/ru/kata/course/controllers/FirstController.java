package ru.kata.course.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname", required = false) String surname,
                           Model model) {
           //System.out.println("hello, " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + "" + surname + "news");
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorWeb(@RequestParam(value = "a", required = false) int a,
                                @RequestParam(value = "b", required = false) int b,
                                @RequestParam(value = "calc", required = false) String calc,
                             Model model) {

        double result;

        switch (calc) {
            case "add" -> result = a + b;
            case "mul" -> result = a + b;
            case "sub" -> result = a - b;
            case "div" -> result = a / (double) b;
            default -> result = 0;
        }
        if (result == 0) {
            System.out.println("Неверная операция");
        }

        model.addAttribute("result", "result is : " + a + " " + calc + " " + b + " " + "= " + result);
        return "first/calculator";
    }
}
