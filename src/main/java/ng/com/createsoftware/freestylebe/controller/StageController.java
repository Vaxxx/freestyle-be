package ng.com.createsoftware.freestylebe.controller;

import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.model.Profile;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import ng.com.createsoftware.freestylebe.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class StageController {

    private final ProfileService profileService;
    private final UserRepository userRepository;
    public StageController(ProfileService profileService, UserRepository userRepository) {
        this.profileService = profileService;
        this.userRepository = userRepository;
    }

    private final String  pictureDir = "ImageUploads/pictures/";
    private final String  bannerDir = "ImageUploads/banners/";



    @PostMapping("/addProfile/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> registerUser(@RequestParam("pictureImage")MultipartFile pictureFile, @RequestParam("bannerImage")MultipartFile bannerFile, ProfileRequest profileRequest, @PathVariable("userId") Long userId) throws Exception{

        Profile profile = new Profile();
        String downloadUrl = "";

      //  set a download path
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(userId.toString())
                .toUriString();


        profileService.saveProfile(pictureFile, bannerFile, profileRequest, userId);

        return new ResponseEntity<>("Profile Added Successfully", HttpStatus.OK);

    }


//
//    public ResponseEntity<?> addProfile(@RequestParam("pictureImage")MultipartFile pictureFile,
//                                        @RequestParam("bannerImage") MultipartFile bannerFile,
//                                        @ModelAttribute ProfileRequest request,
//                                        @PathVariable("userId") long userId
//    ) throws Exception {
//        Profile profile = new Profile();
//        profile.setFirstname(request.getFirstname());
//        profile.setLastname(request.getLastname());
//        profile.setPhone(request.getPhone());
//        profile.setCity(request.getCity());
//        profile.setCountry(request.getCountry());
//        profile.setBio(request.getBio());
//
//        System.out.println("User Id: " + userId);
//        profileService.saveProfile(pictureFile, bannerFile, pictureDir, bannerDir,request, userId);
//
//        return ResponseEntity.ok(new MessageResponse("Profile has been added successfully!"));
//    }
}
