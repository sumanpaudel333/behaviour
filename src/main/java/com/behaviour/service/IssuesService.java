package com.behaviour.service;

import com.behaviour.model.Issues;

import java.util.List;

public interface IssuesService {
    List<Issues> getAllIssues();

    Issues addIssues(Issues issues);
    Issues findIssueByName(String name);
}
