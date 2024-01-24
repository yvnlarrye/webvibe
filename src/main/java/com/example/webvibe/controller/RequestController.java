package com.example.webvibe.controller;
import com.example.webvibe.model.Request;
import com.example.webvibe.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {
    private final RequestService service;

    @Autowired
    public RequestController(RequestService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String openHomePage() {
        return "index";
    }
    @GetMapping("/feedback")
    public String openFeedbackPage(Model model) {
        model.addAttribute("request", new Request());
        return "feedback";
    }
    @PostMapping("/orderCall")
    public String makeRequest(HttpServletRequest request) {
        Request requestDAO = new Request();

        requestDAO.setName(request.getParameter("name"));
        requestDAO.setPhone(request.getParameter("phone"));
        service.saveRequest(requestDAO);
        return "redirect:/complete";
    }
    @GetMapping("/complete")
    public String completePage() {
        return "complete";
    }
}
