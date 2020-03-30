package com.bsuir.service;

import com.bsuir.model.Status;

import java.util.List;

public interface StatusService {

    List<Status> getAllStatus();
    Status getStatusById(long idstatus);
}
