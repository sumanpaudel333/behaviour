package com.behaviour.service.impl;

import com.behaviour.dto.OrderCountDto;
import com.behaviour.dto.PriorityBasedDto;
import com.behaviour.model.Issues;
import com.behaviour.repository.IssuesRepository;
import com.behaviour.service.IssuesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class IssuesServiceImpl implements IssuesService {

    private final IssuesRepository issuesRepository;

    public IssuesServiceImpl(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    @Override
    public List<OrderCountDto> getAllIssuesWithOrderCount() {
        log.info("Calling Issues server");
        List<Issues> allIssues = issuesRepository.findAll();
        log.info("Manipulating data based on Order Count and ASCII");

        allIssues.sort((issue1, issue2) -> {
            if (Objects.equals(issue1.getOrderCount(), issue2.getOrderCount())) {
                return issue1.getName().compareTo(issue2.getName());
            } else {
                return Long.compare(issue2.getOrderCount(), issue1.getOrderCount());
            }
        });

        List<OrderCountDto> orderCountDtos = new ArrayList<>();

        for (Issues issue : allIssues) {
            OrderCountDto orderCountDto = new OrderCountDto();
            orderCountDto.setName(issue.getName());
            orderCountDto.setDescription(issue.getDescription());
            orderCountDto.setOrderCount(issue.getOrderCount());
            orderCountDtos.add(orderCountDto);
        }

        log.info("Returning issues from issue server");
        return orderCountDtos;
    }


    @Override
    public Issues addIssues(OrderCountDto orderCountDto) {

        log.info("Initiating adding of issues");
        Issues addIssues = new Issues();
        addIssues.setName(orderCountDto.getName());
        addIssues.setDescription(orderCountDto.getDescription());
        addIssues.setOrderCount(0L);

        log.info("Added Issues Successfully");
        return issuesRepository.save(addIssues);
    }

    @Override
    public Issues addPriorityIssues(PriorityBasedDto priorityBasedDto){
        log.info("Adding issues with priority");
        Issues addIssues = new Issues();
        addIssues.setName(priorityBasedDto.getName());
        addIssues.setDescription(priorityBasedDto.getDescription());
        addIssues.setPriority(priorityBasedDto.getPriority());

        log.info("Issues with priority added successfully");
        return issuesRepository.save(addIssues);
    }

    @Override
    public Issues findIssueByName(String name) {
        log.info("Finding issues by name");
        Issues foundIssue = issuesRepository.findIssuesByName(name);
        if (foundIssue != null) {
            Long updateOrderCount = foundIssue.getOrderCount() + 1;
            foundIssue.setOrderCount(updateOrderCount);
            issuesRepository.save(foundIssue);
        }

        log.info("Delivered the issues count with increased order count");
        return foundIssue;
    }

    @Override
    public List<PriorityBasedDto> getAllIssuesWithPriority(){
        log.info("Getting Issues list based on priority");
        List<Issues> issues = issuesRepository.findAll();

        issues.sort((issue1, issue2) -> {
            int priorityComparison = issue1.getPriority().compareTo(issue2.getPriority());
            if (priorityComparison == 0) {
                return issue1.getName().compareTo(issue2.getName());
            } else {
                return priorityComparison;
            }
        });

        List<PriorityBasedDto> priorityBasedDtos = new ArrayList<>();
        for (Issues issue : issues) {
            PriorityBasedDto dto = new PriorityBasedDto();
            dto.setName(issue.getName());
            dto.setDescription(issue.getDescription());
            dto.setPriority(issue.getPriority());
            priorityBasedDtos.add(dto);
        }

        log.info("Returning issues based on priority");
        return priorityBasedDtos;
    }

}
