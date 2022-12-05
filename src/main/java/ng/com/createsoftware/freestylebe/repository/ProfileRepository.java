package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.Profile;
import ng.com.createsoftware.freestylebe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

   // @Query(value="select * from profile p where p.user.id like %:query%", nativeQuery=true)
    Optional<Profile> findByUser(User user);

    Optional<Profile> findByPictureName(String pictureName);
    Optional<Profile> findByBannerName(String bannerName);

}
