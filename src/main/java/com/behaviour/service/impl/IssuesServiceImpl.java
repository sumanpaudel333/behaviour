package com.behaviour.service.impl;

import com.behaviour.model.Issues;
import com.behaviour.repository.IssuesRepository;
import com.behaviour.service.IssuesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public List<Issues> getAllIssues() {
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

        log.info("Returning issues from issue server");
        return allIssues;
    }


    @Override
    public Issues addIssues(Issues issues){

        log.info("Initiating adding of issues");
        Issues addIssues = new Issues();
        addIssues.setName(issues.getName());
        addIssues.setDescription(issues.getDescription());
        addIssues.setOrderCount(0L);

        log.info("Added Issues Successfully");
        return issuesRepository.save(addIssues);
    }

    @Override
    public Issues findIssueByName(String name){
        log.info("Finding issues by name");
        Issues foundIssue = issuesRepository.findIssuesByName(name);
        if(foundIssue != null){
            Long updateOrderCount = foundIssue.getOrderCount() + 1;
            foundIssue.setOrderCount(updateOrderCount);
            issuesRepository.save(foundIssue);
        }

        log.info("Delivered the issues count with increased order count");
        return foundIssue;
    }
}
