package nick.pack.controllers;

import nick.pack.models.Worker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ViewController {
    private List<Worker> workers = Stream.of(
            new Worker(1, "Andrey", "Developer"),
            new Worker(2, "Sergey", "Developer"),
            new Worker(3, "Alexey", "Developer")
    ).collect(Collectors.toList());
    @GetMapping("/hello")
    public String hello(Model model){
        System.out.println("Hello world");
        model.addAttribute("devops", workers);
        return "index";
    }
    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("worker", new Worker());
        return "create";
    }
    @PostMapping("/new")
    public String create(@ModelAttribute("worker") Worker worker){
        System.out.println(worker);
        workers.add(worker);
        return "redirect:/hello";
    }
}
