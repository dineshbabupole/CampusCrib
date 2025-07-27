package com.Dinesh.CampusCribe.controllers;

import com.Dinesh.CampusCribe.Services.OwnerService;
import com.Dinesh.CampusCribe.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @PostMapping("/add")
    public Owner addOwner(@RequestBody Owner owenr){
         return ownerService.addOwner(owenr);
    }
    @GetMapping("/getOwner/{mail}")
    public Owner getOwner(@RequestParam String mail){
        return ownerService.getOwnerByMail(mail);
    }

    @DeleteMapping("/dltOwner/{mail}")
    public String dltOwner(@PathVariable String mail){

        return ownerService.dltOwner(mail);
    }
}
