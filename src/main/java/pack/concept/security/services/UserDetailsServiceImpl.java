package pack.concept.security.services;

import pack.concept.user_service.model.UsersEntity;
import pack.concept.user_service.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> byName = usersRepository.findByUsername(username);
        if (byName.isEmpty())
                throw  new UsernameNotFoundException("User Not Found with username: " + username);
        UsersEntity usersEntity = byName.get();
        return UserDetailsImpl.build(usersEntity);
    }
}
