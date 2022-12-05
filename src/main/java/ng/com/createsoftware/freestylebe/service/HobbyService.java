package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.Hobby;

public interface HobbyService {
    Hobby getHobbyByUser(Long userId);

    //edit profile
    Hobby editHobby(Hobby hobby, Long userId) ;
}
