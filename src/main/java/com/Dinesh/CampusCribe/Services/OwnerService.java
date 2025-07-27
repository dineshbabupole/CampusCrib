package com.Dinesh.CampusCribe.Services;

import com.Dinesh.CampusCribe.Exceptions.UserNotFoundException;
import com.Dinesh.CampusCribe.Reposiories.OwnerRepo;
import com.Dinesh.CampusCribe.models.Images;
import com.Dinesh.CampusCribe.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepo ownerRepo;
    @Autowired
    private FileService fileService;
    @Value("${app.image.path}")
    private String path;
    public Owner addOwner(Owner owner){
        owner.setId(null);
        owner.setHostel(null);
        return  ownerRepo.save(owner);
    }
    public Owner getOwnerByMail(String mail){
        return ownerRepo.findByMail(mail);
    }

    public Owner updateOwner( Owner updateOwner){
        Owner presOwner=ownerRepo.findById(updateOwner.getId()).orElseThrow(()->new UserNotFoundException("owner not found"));
        presOwner.setFullName(updateOwner.getFullName());
        return ownerRepo.save(presOwner);
    }

    public String dltOwner(String mail){
        try {
            Owner owner=ownerRepo.findByMail(mail);
            List<Images> images=owner.getHostel().getImages();
            List<String> urls=new ArrayList<>();

            images.forEach(image->urls.add(image.getImageUrl()));
            urls.forEach(url-> {
                try {
                    System.out.println(fileService.dltFile(url.substring(url.lastIndexOf("/") + 1),path));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            ownerRepo.delete(owner);
            return "successfully deleted";
        }
        catch (Exception e){
            System.out.println(e);
            return "deleted fail";
        }

    }
}
