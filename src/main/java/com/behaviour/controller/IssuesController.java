package com.behaviour.controller;

import com.behaviour.model.Issues;
import com.behaviour.service.IssuesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssuesController {

    private final IssuesService issuesService;

    public IssuesController(IssuesService issuesService) {
        this.issuesService = issuesService;
    }

    @GetMapping("/getIssues")
    public List<Issues> getIssues(){
        return issuesService.getAllIssues();
    }

    @PostMapping("/addIssues")
    public Issues addIssues(@RequestBody Issues issues){
        return issuesService.addIssues(issues);
    }

    @PutMapping("/name")
    public Issues findByName(@RequestParam String name){
        return issuesService.findIssueByName(name);
    }
}
