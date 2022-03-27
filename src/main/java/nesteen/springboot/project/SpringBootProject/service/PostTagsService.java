package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.entity.Post;
import nesteen.springboot.project.SpringBootProject.entity.PostTags;

import java.util.List;

public interface PostTagsService {

    public List<PostTags> findAll();

    public void save(PostTags thePostsTag);

    List<PostTags> findByPostId(int postId);

    public void deleteByPostId(int postId);

    List<PostTags> findByTagId(int tagId);

}
