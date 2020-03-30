package com.bsuir.service;

import com.bsuir.model.Priority;

import java.util.List;

public interface PriorityService {

    Priority getPriorityById(long idpriority);
    List<Priority> getAllPriority();

}
