package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.Genre;
import ng.com.createsoftware.freestylebe.model.Hobby;

import java.util.List;

public interface GenreService {

    List<Genre> getAllGenreByUser(Long userId);

    Genre getGenreByUser(Long userId);

    List<Genre> editGenresByUser(List<Genre> oldGenres, Long userId) ;


}
