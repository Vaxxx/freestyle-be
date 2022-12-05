package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.model.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface ProfileService {
//     Profile saveProfile(MultipartFile pictureFile, MultipartFile bannerFile, String pictureDir, String bannerDir, ProfileRequest profileRequest, long userId) throws Exception;

     Profile saveProfile(MultipartFile pictureFile, MultipartFile bannerFile,  ProfileRequest profileRequest,Long userId)throws Exception;

     //get profile by user id
     Profile getProfileByUser(Long userId);

     //edit profile
     Profile editProfile(Profile profile, Long userId, MultipartFile pictureFile, MultipartFile bannerFile) throws Exception;


}
