package com.bsuir.controller;


import com.bsuir.model.Priority;
import com.bsuir.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Priority> getPriority(){
        return priorityService.getAllPriority();
    }

}
