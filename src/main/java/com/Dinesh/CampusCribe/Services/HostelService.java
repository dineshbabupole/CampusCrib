package com.Dinesh.CampusCribe.Services;

import com.Dinesh.CampusCribe.Exceptions.UserNotFoundException;
import com.Dinesh.CampusCribe.Reposiories.HostelRepo;
import com.Dinesh.CampusCribe.Reposiories.OwnerRepo;
import com.Dinesh.CampusCribe.models.Hostel;
import com.Dinesh.CampusCribe.models.Images;
import com.Dinesh.CampusCribe.models.Owner;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HostelService {
    private final HostelRepo hostelRepo;
    private final FileService fileService;
    private final OwnerRepo ownerRepo;
    @Value("${app.image.path}")
    private String path;

    @Value("${app.image.url}")
    private String url;
    @Autowired
      public HostelService(HostelRepo hostelRepo,FileService fileService,OwnerRepo ownerRepo){
        this.hostelRepo=hostelRepo;
        this.fileService=fileService;
        this.ownerRepo=ownerRepo;
    }

public List<Hostel> getAllHostels(){
        return hostelRepo.findAll();
}
    public Hostel addHostel(Hostel hostel, List<MultipartFile> images) {
        System.out.println(hostel);
        Long ownerId = hostel.getOwner().getId();
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new UserNotFoundException("Owner not found with ID: " + ownerId));

        owner.setHostel(hostel);
        hostel.setOwner(owner);

        List<Images> imageEntities = new ArrayList<>();
        for (MultipartFile image : images) {
            try {
                Images im = new Images();
                im.setImageUrl(url + "/" + image.getOriginalFilename());
                im.setHostel(hostel);
                imageEntities.add(im);
                fileService.uploadFile(path, image);
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file: " + image.getOriginalFilename(), e);
            }
        }
        hostel.setImages(imageEntities);
        return hostelRepo.save(hostel);
    }


    public Hostel getHostelByClg(String clgName){
        return hostelRepo.findByCollege(clgName);
    }
    public Hostel updateHostel(Hostel updatedHostel,Long id){
        Hostel presHostel=hostelRepo.findById(id).orElseThrow(()->new RuntimeException("hostel not found with id:"+id));
        presHostel.setName(updatedHostel.getName());
        presHostel.setCollege(updatedHostel.getCollege());
        presHostel.setDescription(updatedHostel.getDescription());
        presHostel.setName(updatedHostel.getName());
        return hostelRepo.save(presHostel);

    }

}
