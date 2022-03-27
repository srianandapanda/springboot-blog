package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.entity.Tags;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsService {

    public void save(Tags theTags);
    public String findStatusById(int theId);
    public List<Tags> findTagByName(String name);
    List<Tags> findByNameIgnoreCaseLike(String theTag);
    public Tags findTagById(int tagId);
    public List<Tags> findAll();
    public int findTagIdByName(String name);
}
