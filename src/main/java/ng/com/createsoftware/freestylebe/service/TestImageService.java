package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.TestImage;
import org.springframework.web.multipart.MultipartFile;


public interface TestImageService {
    public TestImage saveImageFile(MultipartFile pictureFile, MultipartFile bannerFile, String pictureDir, String bannerDir, TestImage tImage) throws Exception;
}
