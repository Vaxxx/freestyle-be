package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.model.Profile;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    Profile registerUser(MultipartFile pictureFile, MultipartFile bannerFile, ProfileRequest profileRequest) throws Exception;

    Profile addUser(ProfileRequest profileRequest);
}
