package ng.com.createsoftware.freestylebe.controller;

import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.dto.response.MessageResponse;
import ng.com.createsoftware.freestylebe.model.Profile;
import ng.com.createsoftware.freestylebe.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class StageController {

    private final ProfileService profileService;

    public StageController(ProfileService profileService) {
        this.profileService = profileService;
    }

    private final String  pictureDir = "ImageUploads/pictures/";
    private final String  bannerDir = "ImageUploads/banners/";

    @GetMapping("/welcome")
    public String homewwPage(){
        return "Freestyle League";
    }

    @PostMapping("/addProfile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addProfile(@RequestParam("pictureImage")MultipartFile pictureFile,
                                        @RequestParam("bannerImage") MultipartFile bannerFile,
                                        @ModelAttribute ProfileRequest request
                                        ) throws Exception {
        Profile profile = new Profile();
        profile.setFirstname(request.getFirstname());
        profile.setLastname(request.getLastname());
        profile.setPhone(request.getPhone());
        profile.setCity(request.getCity());
        profile.setCountry(request.getCountry());
        profile.setBio(request.getBio());


        profileService.saveProfile(pictureFile, bannerFile, pictureDir, bannerDir,request);

        return ResponseEntity.ok(new MessageResponse("Profile has been added successfully!"));
    }



}
