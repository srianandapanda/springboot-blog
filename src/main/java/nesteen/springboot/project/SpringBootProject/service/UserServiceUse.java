package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.dao.UserRepository;
import nesteen.springboot.project.SpringBootProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceUse implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceUse(UserRepository theUserRepository){
        userRepository=theUserRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
