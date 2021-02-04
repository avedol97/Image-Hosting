package pl.krystian.imageuploader.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.krystian.imageuploader.repo.UsersRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepo usersRepo;

    public UserDetailsServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return usersRepo.findByUsername(s);
    }


}
