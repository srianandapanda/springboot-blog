package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.dao.PostTagsRepository;
import nesteen.springboot.project.SpringBootProject.entity.PostTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagsServiceUse implements PostTagsService {

    private PostTagsRepository postTagsRepository;

    @Autowired
    public PostTagsServiceUse(PostTagsRepository theTagRepository) {
        postTagsRepository = theTagRepository;
    }

    @Override
    public List<PostTags> findAll() {
        return postTagsRepository.findAll();
    }

    @Override
    public void save(PostTags thePostsTag) {
        postTagsRepository.save(thePostsTag);
    }

    @Override
    public List<PostTags> findByPostId(int postId) {
        return postTagsRepository.findByPostId(postId);
    }

    @Override
    public void deleteByPostId(int postId) {
        postTagsRepository.deletePerPostId(postId);
    }

    @Override
    public List<PostTags> findByTagId(int tagId) {
        return postTagsRepository.findByTagId(tagId);
    }
}
