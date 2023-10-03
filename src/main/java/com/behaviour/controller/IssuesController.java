package com.behaviour.controller;

import com.behaviour.dto.OrderCountDto;
import com.behaviour.dto.PriorityBasedDto;
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

    @GetMapping("/getIssues/order_count")
    public List<OrderCountDto> getIssuesWithOrderCount(){
        return issuesService.getAllIssuesWithOrderCount();
    }

    @PostMapping("/addIssues")
    public Issues addIssues(@RequestBody OrderCountDto issues){
        return issuesService.addIssues(issues);
    }

    @PostMapping("/addIssues/priority")
    public Issues addIssuesWithPriority(@RequestBody PriorityBasedDto priorityBasedDto){
        return issuesService.addPriorityIssues(priorityBasedDto);
    }

    @PutMapping("/name")
    public Issues findByName(@RequestParam String name){
        return issuesService.findIssueByName(name);
    }

    @GetMapping("/getIssues/priority")
    public List<PriorityBasedDto> getIssuesWithPriority(){
        return issuesService.getAllIssuesWithPriority();
    }
}
