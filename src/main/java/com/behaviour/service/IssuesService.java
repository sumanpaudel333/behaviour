package com.behaviour.service;

import com.behaviour.dto.OrderCountDto;
import com.behaviour.dto.PriorityBasedDto;
import com.behaviour.model.Issues;

import java.util.List;

public interface IssuesService {
    List<OrderCountDto> getAllIssuesWithOrderCount();
    Issues addIssues(OrderCountDto issues);
    Issues findIssueByName(String name);
    List<PriorityBasedDto> getAllIssuesWithPriority();
    Issues addPriorityIssues(PriorityBasedDto priorityBasedDto);
}
