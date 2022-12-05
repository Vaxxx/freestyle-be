package ng.com.createsoftware.freestylebe.controller;

import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.model.Genre;
import ng.com.createsoftware.freestylebe.model.Profile;
import ng.com.createsoftware.freestylebe.service.DisciplineService;
import ng.com.createsoftware.freestylebe.service.GenreService;
import ng.com.createsoftware.freestylebe.service.HobbyService;
import ng.com.createsoftware.freestylebe.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class StageController {

    private final ProfileService profileService;

    private final GenreService genreService;

    private final DisciplineService disciplineService;

    private final HobbyService hobbyService;

    public StageController(ProfileService profileService, GenreService genreService, DisciplineService disciplineService, HobbyService hobbyService) {
        this.profileService = profileService;
        this.genreService = genreService;
        this.disciplineService = disciplineService;
        this.hobbyService = hobbyService;
    }

    private final String  pictureDir = "ImageUploads/pictures/";
    private final String  bannerDir = "ImageUploads/banners/";



    @PostMapping("/addProfile/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> registerUserProfile(@RequestParam("pictureImage")MultipartFile pictureFile, @RequestParam("bannerImage")MultipartFile bannerFile, ProfileRequest profileRequest, @PathVariable("userId") Long userId) throws Exception{

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

    @GetMapping("/get/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable("userId") final Long userId){
         Profile profile =   profileService.getProfileByUser(userId);
         return new ResponseEntity<>(profile,  HttpStatus.OK);
    }

    @PostMapping("/edit/{userId}")
    public ResponseEntity<Profile> editProfile(@RequestParam("pictureImage")MultipartFile pictureFile, @RequestParam("bannerImage")MultipartFile bannerFile, Profile  profile , @PathVariable("userId") Long userId) throws Exception{
         Profile profileToEdit = profileService.editProfile(profile, userId, pictureFile, bannerFile);
    return new ResponseEntity<>(profileToEdit, HttpStatus.OK);
    }

    //genre section
    @GetMapping("/genre/get/{userId}")
    public ResponseEntity<List<Genre>> getGenreByUserId(@PathVariable("userId") final Long userId){
        List<Genre> genre =   genreService.getAllGenreByUser(userId);
        return new ResponseEntity<>(genre,  HttpStatus.OK);
    }
//    @PostMapping("/genre/edit/{userId}")
//    public ResponseEntity<List<Genre>> editProfileGenre(@PathVariable("userId") Long userId){
//        List<Genre> genreToEdit = genreService.editGenresByUser(userId);
//       // Genre genreToEdit = genreService.editGenre(genre, userId);
//        return new ResponseEntity<>(genreToEdit, HttpStatus.OK);
//    }
    @PostMapping("/genre/edit/{userId}")
    public ResponseEntity<List<Genre>> editProfileGenre(List<Genre> genres , @PathVariable("userId") Long userId){
        List<Genre> genresToEdit = genreService.editGenresByUser(genres, userId);
        return new ResponseEntity<>(genresToEdit, HttpStatus.OK);
    }
    //end genre section

}//Stage Controlller
