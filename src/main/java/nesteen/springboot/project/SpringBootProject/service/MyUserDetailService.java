package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.dao.UserRepository;
import nesteen.springboot.project.SpringBootProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //public UserDetailService(UserRepository theUserRepository){
       // userRepository=theUserRepository;
   // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if(user==null) {
            throw new UsernameNotFoundException("Username Not Found");
        }
        return new UserDetailServiceUse(user);
    }
}
