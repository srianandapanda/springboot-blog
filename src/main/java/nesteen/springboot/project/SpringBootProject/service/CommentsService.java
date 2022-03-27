package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.entity.Comments;

import java.util.List;

public interface CommentsService {

    public List<Comments> findAll();
    public void save(Comments theComments);
    public List<Comments> findAllComments(int theId);
    public Comments findById(int theId);
    public void deleteById(int theId);
    public void deletePerPostId(int postId);

}
