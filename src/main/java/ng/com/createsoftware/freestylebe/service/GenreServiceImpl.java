package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.Genre;
import ng.com.createsoftware.freestylebe.model.Hobby;
import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.GenreRepository;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenreByUser(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Genre> allGenres =   genreRepository.findAllByUser(user);
        return allGenres;
    }

    @Override
    public Genre getGenreByUser(Long userId) {
        User user = userRepository.findById(userId).get();
        return genreRepository.findByUser(user).orElseThrow(() ->
                new IllegalArgumentException("User cannot be found"));
    }


    @Transactional
    @Override
    public List<Genre> editGenresByUser(List<Genre> oldGenres, Long userId) {
        //create new genre list
        List<Genre> newGenre = getAllGenreByUser(userId);
       for(int i = 0; i < oldGenres.size(); i++){
           newGenre.set(i, oldGenres.get(i));
       }
       return newGenre;
    }

}