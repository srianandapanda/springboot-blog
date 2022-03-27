package nesteen.springboot.project.SpringBootProject.dao;

import nesteen.springboot.project.SpringBootProject.entity.Comments;
import nesteen.springboot.project.SpringBootProject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    @Query("SELECT u FROM Comments u WHERE u.postId = :theId")
    public List<Comments> findAllComments(@Param("theId") int theId);

    @Transactional
    @Modifying
    @Query("delete from Comments b where b.postId=:postId")
    public void deletePerPostId(int postId);

}
