package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.entity.User;

public interface UserService {

    public void save(User user);

    User findByEmail(String username);

}
