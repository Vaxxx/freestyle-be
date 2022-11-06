package ng.com.createsoftware.freestylebe.service;

import lombok.AllArgsConstructor;
import ng.com.createsoftware.freestylebe.model.TestImage;
import ng.com.createsoftware.freestylebe.repository.TestImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
@Service
@AllArgsConstructor
public class TestImageServiceImpl implements TestImageService{

    private TestImageRepository repo;

    @Override
    public TestImage saveImageFile(MultipartFile pictureFile, MultipartFile bannerFile, String pictureDir, String bannerDir, TestImage tImage) throws Exception {
        String picture = StringUtils.cleanPath(Objects.requireNonNull(pictureFile.getOriginalFilename()));
        String banner = StringUtils.cleanPath(Objects.requireNonNull(bannerFile.getOriginalFilename()));

        try{
            if(picture.contains(".."))
              throw new Exception("Please Check the image you are uploading");
            byte [] pictureBytes = pictureFile.getBytes();
            Path picturePath = Paths.get(pictureDir + pictureFile.getOriginalFilename());
            Files.write(picturePath, pictureBytes);
        }catch (Exception ex){
            System.out.println("Exception: " + ex);
        }

        try{
            if(banner.contains(".."))
              throw new Exception("Please Check the Banner you are uploading");
            byte [] bannerBytes = bannerFile.getBytes();
            Path bannerPath = Paths.get(bannerDir + bannerFile.getOriginalFilename());
            Files.write(bannerPath, bannerBytes);
        }catch (Exception ex){
            System.out.println("Exception: " + ex);
        }

        TestImage testImage = new TestImage();
        testImage.setTitle(tImage.getTitle());
        testImage.setPhone(tImage.getPhone());
        testImage.setPicture(pictureFile.getOriginalFilename());
        testImage.setBanner(bannerFile.getOriginalFilename());
        repo.save(testImage);
        return testImage;
    }
}
