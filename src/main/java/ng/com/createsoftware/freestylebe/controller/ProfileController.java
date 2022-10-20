package ng.com.createsoftware.freestylebe.controller;

import lombok.AllArgsConstructor;
import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.model.Profile;
import ng.com.createsoftware.freestylebe.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/addProfile")
    public ResponseEntity<Profile> registerUser(@RequestParam("pictureImage")MultipartFile pictureFile, @RequestParam("bannerImage")MultipartFile bannerFile, ProfileRequest profileRequest) throws Exception{

       try{
           Profile profile = new Profile();
           String downloadUrl = "";

           //set a download path
           downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                   .path("/download/")
                   .path(profileRequest.getUser_id().toString())
                   .toUriString();
           System.out.println(downloadUrl);
           profile.setPicture(pictureFile.getBytes());
           profile.setBanner(bannerFile.getBytes())  ;

           profileService.registerUser(pictureFile, bannerFile, profileRequest);
           System.out.println(profile);
           return new ResponseEntity<>(profile, HttpStatus.OK);
       }catch(Exception ex){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
    @PostMapping("/add")
    public ResponseEntity<Profile> addUser( @RequestBody ProfileRequest profileRequest) {

        Profile profile = new Profile();
        profile.setBio(profileRequest.getBio());
               profileService.addUser(profileRequest);
           return new ResponseEntity<>(profile, HttpStatus.OK);

    }
    
}
