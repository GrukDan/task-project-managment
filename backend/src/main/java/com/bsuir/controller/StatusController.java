package com.bsuir.controller;


import com.bsuir.model.Role;
import com.bsuir.model.Status;
import com.bsuir.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @RequestMapping(value = "/status",method = RequestMethod.GET)
    public List<Status> getStatus(){
        return statusService.getAllStatus();
    }
}
