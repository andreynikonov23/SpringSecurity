package nick.pack.controllers;

import nick.pack.models.Worker;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ViewController {
    private final List<Worker> workers = Stream.of(
            new Worker(1, "Andrey", "Developer"),
            new Worker(2, "Sergey", "Developer"),
            new Worker(3, "Alexey", "Developer")
    ).collect(Collectors.toList());

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/api/hello")
    @PreAuthorize("hasAuthority('read')")
    public String hello(Model model){
        System.out.println("Hello world");
        model.addAttribute("devops", workers);
        return "main";
    }
    @GetMapping("/api/create")
    public String createForm(Model model){
        Worker worker = new Worker();
        model.addAttribute("worker", worker);
        return "create";
    }
    @PostMapping("/api/add")
    @PreAuthorize("hasAuthority('write')")
    public String add(@ModelAttribute("worker") Worker worker){
        System.out.println(worker);
        workers.add(worker);
        return "redirect:/api/hello";
    }
}
