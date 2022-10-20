package ng.com.createsoftware.freestylebe.service;


import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String stagename) throws UsernameNotFoundException {
        User user = userRepository.findByStagename(stagename)
           .orElseThrow(() -> new UsernameNotFoundException("User with stage name " + stagename + " cannot be found" ));

           return UserDetailsImpl.build(user);
    }
    
}
