package nesteen.springboot.project.SpringBootProject.dao;

import nesteen.springboot.project.SpringBootProject.entity.Post;
import nesteen.springboot.project.SpringBootProject.entity.PostTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostTagsRepository extends JpaRepository<PostTags,Integer> {

    List<PostTags> findByPostId(int postId);
    List<PostTags> findByTagId(int tagId);

    @Transactional
    @Modifying
    @Query("delete from PostTags b where b.postId=:postId")
    public void deletePerPostId(int postId);


}
