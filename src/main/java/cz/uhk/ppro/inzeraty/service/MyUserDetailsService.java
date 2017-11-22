package cz.uhk.ppro.inzeraty.service;

import cz.uhk.ppro.inzeraty.model.User;
import cz.uhk.ppro.inzeraty.repository.UserRepository;
import cz.uhk.ppro.inzeraty.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);

        optionalUser.orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
        return optionalUser.map(MyUserPrincipal::new).get();
    }
}
