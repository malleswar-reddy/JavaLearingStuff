package com.mongo.sloatservice;

import com.mongo.model.Sloat;
import com.mongo.repository.SloatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SloatService {

    @Autowired
    private SloatRepository sloatRepository;

    public List< Sloat > getAllSloats(){
        return sloatRepository.findAll();
    }
}
