package nesteen.springboot.project.SpringBootProject.dao;

import nesteen.springboot.project.SpringBootProject.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tags,Integer> {

    @Query("SELECT u FROM Tags u WHERE u.postId = :theId")
    public List<Tags> findAllTags(@Param("theId") int theId);

    @Query("SELECT u FROM Tags u WHERE u.name = :name")
    public List<Tags> findTagByName(@Param("name") String name);

    @Query("SELECT p.id FROM Tags p WHERE p.name = :name")
    public int findTagIdByName(@Param("name") String name);

    List<Tags> findByNameIgnoreCaseLike(String theTag);

}
