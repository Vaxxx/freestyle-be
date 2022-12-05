package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.dto.request.ProfileRequest;
import ng.com.createsoftware.freestylebe.model.*;
import ng.com.createsoftware.freestylebe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private HobbyRepository hobbyRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SecModelRepository secModelRepository;

    @Override
    public Profile saveProfile(MultipartFile pictureFile, MultipartFile bannerFile, ProfileRequest profileRequest, Long userId) throws Exception {
        //String picture = StringUtils.cleanPath(Objects.requireNonNull(pictureFile.getOriginalFilename()));
        Profile profile = null;
//        String picture = StringUtils.cleanPath(Objects.requireNonNull(pictureFile.getOriginalFilename()));
//        String banner = StringUtils.cleanPath(Objects.requireNonNull(bannerFile.getOriginalFilename()));
        //get banner
       // String banner = StringUtils.cleanPath(Objects.requireNonNull(bannerFile.getOriginalFilename()));

        try{
//            if(picture.contains("..") || banner.contains(".."))
//                throw new Exception("Please check your upload path");

            //business logic to save user

            User user = userRepository.findById(userId).get();

            profile = new Profile();
            profile.setFirstname(profileRequest.getFirstname());
            profile.setLastname(profileRequest.getLastname());
            profile.setUser(user);
            profile.setPhone(profileRequest.getPhone());
            profile.setCity(profileRequest.getCity());
            profile.setCountry(profileRequest.getCountry());
            profile.setBio(profileRequest.getBio());



            ////create a container for genre
            List<Genre> genres = new ArrayList<>();
            List<String> allGenres = profileRequest.getGenre();
            for(String genre : allGenres){
                Genre newGenre = new Genre(genre);
                genres.add(newGenre);
            }

            //container for hobby
            List<Hobby> hobbies = new ArrayList<>();
            List<String> allHobbies = profileRequest.getHobby();
            for(String hobby : allHobbies){
                Hobby  newHobby = new Hobby(hobby);
                hobbies.add(newHobby);
            }

            //Container for discipline
            List<Discipline> disciplines = new ArrayList<>();
            List<String> allDisciplines = profileRequest.getDiscipline();
            for(String discipline : allDisciplines){
                Discipline newDiscipline = new Discipline(discipline);
                disciplines.add(newDiscipline);
            }
            profile.setPictureName(pictureFile.getOriginalFilename());
            profile.setPicturez(ImageData.compressImage(pictureFile.getBytes()));
          //  System.out.println(Arrays.toString(pictureFile.getBytes()));
            profile.setBannerName(bannerFile.getOriginalFilename());
            profile.setBannerz(ImageData.compressImage(bannerFile.getBytes())); ;
         //   System.out.println(Arrays.toString(bannerFile.getBytes()));
            //get the user
//            long userId = profileRequest.getUser_id();
//            User user = userRepository.findById(userId).get();



            //save the genre
            for(Genre genre: genres){
                Genre newGenre = new Genre(genre.getName(), user);
                genreRepository.save(newGenre);
            }
            //sve the hobby
            for(Hobby hobby : hobbies){
                Hobby newHobby = new Hobby(hobby.getName(), user);
                hobbyRepository.save(newHobby);
            }

            //save the Discipline

            for(Discipline discipline : disciplines){
                Discipline newDiscipline = new Discipline(discipline.getName(), user);
                disciplineRepository.save(newDiscipline);
            }
                      String question = profileRequest.getQuestion();
                      String answer = profileRequest.getAnswer();

                      SecModel secModel  = new SecModel(user, question, answer);
                       secModelRepository.save(secModel) ;

            //save the profile
            profileRepository.save(profile);
        }catch(Exception ex){
            throw new Exception("Issue with registering the user: " + ex);
        }
        return profile;
    }

    @Override
    public Profile getProfileByUser(Long userId) {

        User user = userRepository.findById(userId).get();
        return profileRepository.findByUser(user).orElseThrow(() ->
                new IllegalArgumentException("User cannot be found"));
//
//        return profileRepository.findByUser(userId).get();
    }


    @Transactional
    @Override
    public Profile editProfile(Profile profile, Long userId, MultipartFile pictureFile, MultipartFile bannerFile) throws Exception {
        Profile profileToEdit =  getProfileByUser(userId);

        profileToEdit.setFirstname(profile.getFirstname());
        profileToEdit.setLastname(profile.getLastname());
        profileToEdit.setPhone(profile.getPhone());
        profileToEdit.setCity(profile.getCity());
        profileToEdit.setCountry(profile.getCountry());
        profileToEdit.setBio(profile.getBio());
        profileToEdit.setPictureName(pictureFile.getOriginalFilename());
        profileToEdit.setPicturez(ImageData.compressImage(pictureFile.getBytes()));
        profileToEdit.setBannerName(bannerFile.getOriginalFilename());
        profileToEdit.setBannerz(ImageData.compressImage(bannerFile.getBytes()));

        return profileRepository.save(profileToEdit);
    }

    public byte[] downloadPictureImage(String fileName){
        Optional<Profile> pictureData = profileRepository.findByPictureName(fileName);
        return ImageData.decompressImage(pictureData.get().getPicturez());
    }

    public byte[] downloadBannerImage(String fileName){
        Optional<Profile> bannerData = profileRepository.findByBannerName(fileName);
        return ImageData.decompressImage(bannerData.get().getBannerz());
    }

//    @Override
//    public Profile saveProfile(MultipartFile pictureFile, MultipartFile bannerFile, String pictureDir, String bannerDir,ProfileRequest profileRequest, long userId)
//            throws Exception {
//                String picture = StringUtils.cleanPath(Objects.requireNonNull(pictureFile.getOriginalFilename()));
//                  String banner = StringUtils.cleanPath(Objects.requireNonNull(bannerFile.getOriginalFilename()));
//                    Profile profile = null;
//                    //get banner
//                  try{
//                        if(picture.contains("..") || banner.contains(".."))
//                             throw new Exception("Please check your upload path");
//
//                      //get picture bytes
//                      byte [] pictureBytes = pictureFile.getBytes();
//                      Path picturePath = Paths.get(pictureDir + pictureFile.getOriginalFilename());
//                      Files.write(picturePath, pictureBytes);
//
//                      //get BAnner bytes-> image
//
//                      byte [] bannerBytes = bannerFile.getBytes();
//                      Path bannerPath = Paths.get(bannerDir + bannerFile.getOriginalFilename());
//                      Files.write(bannerPath, bannerBytes);
//
//                        //business logic to process the profile for Profile, Genre, Discipline, Hobbies and Security
//
//                        ////create a container for genre
//                        List<Genre> genres = new ArrayList<>();
//                        List<String> allGenres = profileRequest.getGenre();
//                        for(String genre : allGenres){
//                            Genre newGenre = new Genre(genre);
//                            genres.add(newGenre);
//                        }
//
//                        //container for hobby
//                        List<Hobby> hobbies = new ArrayList<>();
//                        List<String> allHobbies = profileRequest.getHobby();
//                        for(String hobby : allHobbies){
//                            Hobby  newHobby = new Hobby(hobby);
//                            hobbies.add(newHobby);
//                        }
//
//                        //Container for discipline
//                        List<Discipline> disciplines = new ArrayList<>();
//                        List<String> allDisciplines = profileRequest.getDiscipline();
//                        for(String discipline : allDisciplines){
//                            Discipline newDiscipline = new Discipline(discipline);
//                            disciplines.add(newDiscipline);
//                        }
//
//
//                      System.out.println();
//                        //get the user id
////                        long userId = profileRequest.getUser_id();
//                      System.out.println("The user Id: is:" + userId);
////                        User user = userRepository.findById(userId).get();
//                      User user = userRepository.findById(userId).get();
//                      System.out.println("The user is : " + user);
//
//                        //save the genre
//                        for(Genre genre: genres){
//                            Genre newGenre = new Genre(genre.getName(), user);
//                            genreRepository.save(newGenre);
//                        }
//                        //save the hobby
//                        for(Hobby hobby : hobbies){
//                            Hobby newHobby = new Hobby(hobby.getName(), user);
//                            hobbyRepository.save(newHobby);
//                        }
//
//                        //save the Discipline
//
//                        for(Discipline discipline : disciplines){
//                            Discipline newDiscipline = new Discipline(discipline.getName(), user);
//                            disciplineRepository.save(newDiscipline);
//                        }
//
//                        ///save the security question
//
//                        String question = profileRequest.getQuestion();
//                        String answer = profileRequest.getAnswer();
//
//                      SecModel secModel  = new SecModel(user, question, answer);
//                       secModelRepository.save(secModel) ;
//                       //profile details
//                      profile = new Profile(user,
//                              profileRequest.getFirstname(),
//                              profileRequest.getLastname(),
//                              profileRequest.getPhone(),
//                              profileRequest.getCity(),
//                              profileRequest.getCountry(),
//                              profileRequest.getBio(),
//                              pictureFile.getOriginalFilename(),
//                              bannerFile.getOriginalFilename()
//                              );
////                      profile.setFirstname(profileRequest.getFirstname());
////                      profile.setLastname(profileRequest.getLastname());
////                      profile.setPhone(profileRequest.getPhone());
////                      profile.setCity(profileRequest.getCity());
////                      profile.setCountry(profileRequest.getCountry());
////                      profile.setBio(profileRequest.getBio());
////                      profile.setPicture(pictureFile.getOriginalFilename());
////                      profile.setBanner(bannerFile.getOriginalFilename());
//
//
//                        //save the profile
//                        profileRepository.save(profile);
//                  }catch(Exception ex){
//                    throw new Exception("Issue with registering the user: " + ex);
//                  }
//                  return profile;
//     }


}
