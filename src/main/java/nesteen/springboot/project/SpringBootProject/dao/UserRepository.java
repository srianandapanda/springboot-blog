package nesteen.springboot.project.SpringBootProject.dao;

import nesteen.springboot.project.SpringBootProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String username);
}
