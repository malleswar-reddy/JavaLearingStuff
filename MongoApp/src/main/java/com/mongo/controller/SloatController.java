package com.mongo.controller;

import com.mongo.model.Sloat;
import com.mongo.sloatservice.SloatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SloatController {

    @Autowired
    private SloatService sloatService;

    @GetMapping("/All")
    public List< Sloat > getAllSloats(){
      return   sloatService.getAllSloats();
    }
}
