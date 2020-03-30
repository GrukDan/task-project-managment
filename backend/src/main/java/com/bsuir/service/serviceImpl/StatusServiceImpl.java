package com.bsuir.service.serviceImpl;

import com.bsuir.model.Status;
import com.bsuir.repository.StatusRepository;
import com.bsuir.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAllStatus() {
        return null;
    }

    @Override
    public Status getStatusById(long idstatus) {
        return null;
    }
}
