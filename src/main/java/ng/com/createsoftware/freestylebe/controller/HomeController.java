package ng.com.createsoftware.freestylebe.controller;

import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.dto.response.MessageResponse;
import ng.com.createsoftware.freestylebe.model.Profile;
import ng.com.createsoftware.freestylebe.model.SecModel;
import ng.com.createsoftware.freestylebe.model.TestImage;
import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.SecModelRepository;
import ng.com.createsoftware.freestylebe.repository.TestImageRepository;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import ng.com.createsoftware.freestylebe.service.ProfileService;
import ng.com.createsoftware.freestylebe.service.TestImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/page")
public class HomeController {
    @Autowired
    private TestImageService testImageService;

    @Autowired
    private TestImageRepository testImageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecModelRepository repo;

    @Autowired
    private ProfileService profileService;

   // private final String  uploadDir = "C:\\Users\\user\\Documents\\test\\";
    private final String  pictureDir = "ImageUploads/pictures/";
    private final String  bannerDir = "ImageUploads/banners/";

    @GetMapping("/")
    public String homePage(){
        return "Freestyle League";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
      return  "This is a dashboard";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('EDITOR') or hasRole('ADMIN')")
    public String userAccess() {
      return "User Content.";
    }
  
    @GetMapping("/editor")
    @PreAuthorize("hasRole('EDITOR')")
    public String moderatorAccess() {
      return "EDITOR Board.";
    }
  
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
      return "Admin Board.";
    }

    @GetMapping("/welcome")
    public String homePager(){
        return "Freestyle League";
    }

    @PostMapping("/pp")
    public String uploadToDb(@RequestParam("pictureImage") MultipartFile pictureFile, @RequestParam("bannerImage") MultipartFile bannerFile, @ModelAttribute TestImage tImage)throws IOException{
       /* save to database

        TestImage testImage = new TestImage();
        testImage.setTitle(tImage.getTitle());
        testImage.setPhone(tImage.getPhone());
        testImage.setPhotos(file.getBytes());
        testImageRepository.save(testImage);
        return "image saved successfully";


        */
        /*
        upload to dir


        TestImage testImage = new TestImage();
        testImage.setTitle(tImage.getTitle());
        testImage.setPhone(tImage.getPhone());

        byte [] bytes = file.getBytes();
        Path path = Paths.get(uploadDir + file.getOriginalFilename());
        Files.write(path, bytes);
        testImage.setBanner(file.getOriginalFilename());
        testImageRepository.save(testImage);
        return "image saved successfully";

         */

        //save two images
        TestImage testImage = new TestImage();
        testImage.setTitle(tImage.getTitle());
        testImage.setPhone(tImage.getPhone());

        //set first image
        byte [] pictureBytes = pictureFile.getBytes();
        Path picturePath = Paths.get(pictureDir + pictureFile.getOriginalFilename());
        Files.write(picturePath, pictureBytes);

        //set second image
        byte [] bannerBytes = bannerFile.getBytes();
        Path bannerPath = Paths.get(bannerDir + bannerFile.getOriginalFilename());
        Files.write(bannerPath, bannerBytes);

        testImage.setPicture(pictureFile.getOriginalFilename());
        testImage.setBanner(bannerFile.getOriginalFilename());
        testImageRepository.save(testImage);
        return "image saved successfully";
    }

    @PostMapping("/testImage")
    public ResponseEntity<?> addTestImage(@RequestParam("pictureImage")MultipartFile pictureFile, @RequestParam("bannerImage")MultipartFile bannerFile,@ModelAttribute TestImage tImage) throws Exception{
        TestImage testImage = new TestImage();
        testImage.setTitle(tImage.getTitle());
        testImage.setPhone(tImage.getPhone());
        testImage.setPicture(pictureFile.getOriginalFilename());
        testImage.setBanner(bannerFile.getOriginalFilename());
        testImageService.saveImageFile(pictureFile, bannerFile, pictureDir, bannerDir, testImage);
        return ResponseEntity.ok(new MessageResponse("New Test Image added successfully!"));
    }
}
