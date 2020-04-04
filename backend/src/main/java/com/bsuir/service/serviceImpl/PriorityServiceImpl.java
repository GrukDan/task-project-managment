package com.bsuir.service.serviceImpl;

import com.bsuir.model.Priority;
import com.bsuir.repository.PriorityRepository;
import com.bsuir.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public Priority getPriorityById(long id) {
        return null;
    }

    @Override
    public List<Priority> getAllPriority() {
        return (List<Priority>) priorityRepository.findAll();
    }
}
