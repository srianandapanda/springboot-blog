package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.dao.CommentsRepository;
import nesteen.springboot.project.SpringBootProject.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceUse implements CommentsService{

    private CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceUse (CommentsRepository theCommentsRepository){
        commentsRepository=theCommentsRepository;
    }

    @Override
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public void save(Comments theComment) {

        commentsRepository.save(theComment);

    }

    @Override
    public List<Comments> findAllComments(int theId) {
        List<Comments> result = commentsRepository.findAllComments(theId);

        return result;
    }

    @Override
    public Comments findById(int theId) {
        Optional<Comments> result = commentsRepository.findById(theId);

        Comments theComments = result.get();;

        return theComments;
    }

    @Override
    public void deleteById(int theId) {
        commentsRepository.deleteById(theId);
    }

    @Override
    public void deletePerPostId(int postId) {
        commentsRepository.deletePerPostId(postId);
    }
}
