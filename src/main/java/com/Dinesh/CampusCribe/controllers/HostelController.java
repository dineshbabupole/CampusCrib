package com.Dinesh.CampusCribe.controllers;

import com.Dinesh.CampusCribe.Services.HostelService;
import com.Dinesh.CampusCribe.models.Hostel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/hostel")
@CrossOrigin(origins = "*")
public class HostelController {

    @Autowired
    public HostelService hostelService;


    @PostMapping(value = "/addHostel", consumes = {"multipart/form-data"})
    public Hostel addHostel(
            @RequestPart("hostel") String hostelJson,
            @RequestPart("images") List<MultipartFile> images) {

        ObjectMapper objectMapper = new ObjectMapper();
        Hostel hostel;

        try {
            hostel = objectMapper.readValue(hostelJson, Hostel.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON for hostel: " + e.getMessage());
        }

        System.out.println("Received Hostel: " + hostel);
        System.out.println("Owner in hostel: " + hostel.getOwner());

        Hostel saved = hostelService.addHostel(hostel, images);
       return saved;
    }


    @GetMapping("/getAll")
    public List<Hostel> getAllHostels(){
        return hostelService.getAllHostels();
    }
    @PutMapping("/updateHostel/{id}")
    public Hostel updateHostel(@RequestBody Hostel hostel,@PathVariable Long id){
        return hostelService.updateHostel(hostel,id);
    }

}
