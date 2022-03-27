package nesteen.springboot.project.SpringBootProject.service;

import java.util.Optional;
import nesteen.springboot.project.SpringBootProject.dao.PostRepository;
import nesteen.springboot.project.SpringBootProject.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceUse implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceUse(PostRepository thePostRepository) {
        postRepository = thePostRepository;
    }
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void save(Post thePost) {
        postRepository.save(thePost);
    }

    @Override
    public void deleteById(int theId) {
        postRepository.deleteById(theId);
    }

    @Override
    public Post findById(int theId) {
        Optional<Post> result = postRepository.findById(theId);

        Post thePost = null;

        if (result.isPresent()) {
            thePost = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return thePost;
    }

    @Override
    public List<Post> findAllSort() {
        List<Post> result=postRepository.findByOrderByPublishedAtDesc();
        return result;
    }

    @Override
    public List<Post> findListByAuthorName(String theAuthor) {
        return postRepository.findListByAuthorName(theAuthor);
    }

    @Override
    public List<Post> findByPublishedAtLike(String publishDate) {
        return postRepository.findByUpdatedAtLike(publishDate);
    }

    @Override
    public List<Post> findByTitleIgnoreCaseLike(String title) {
        return postRepository.findByTitleIgnoreCaseLike(title);
    }

    @Override
    public List<Post> findByContentIgnoreCaseLike(String content) {
        return postRepository.findByContentIgnoreCaseLike(content);
    }

    @Override
    public List<Post> findByAuthorIgnoreCaseLike(String author) {
        return postRepository.findByAuthorIgnoreCaseLike(author);
    }

    @Override
    public List<Post> findByOrderByPublishedAtAsc() {
        return postRepository.findByOrderByPublishedAtAsc();
    }

    public Page<Post> findSortedPage(int pageNumber){
        Pageable pageable=PageRequest.of(pageNumber-1,3);
        return (Page<Post>) postRepository.findByOrderByPublishedAtAsc();
    }

    @Override
    public Page<Post> findPage(int pageNumber){
        Pageable pageable= PageRequest.of(pageNumber-1,3);
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findAllWithSort(String field, int pageNumber){
        /*System.out.println("******************************* 11");
        Sort sort= Sort.by(Sort.Direction.ASC.name());//Sort.by(Sort.Direction.ASC.name());
        System.out.println("******************************* 12");
        if(direction.equalsIgnoreCase(Sort.Direction.ASC.name())){
            System.out.println("******************************* 13");
            Sort.by(field).ascending();
            System.out.println("******************************* 14");
        }
        else{
            System.out.println("******************************* 15");
            Sort.by(field).descending();
            System.out.println("******************************* 16");
        }
       // Sort.by(field).ascending():Sort.by(field).descending();*/
        //Sort sort= Sort.by(Sort.Direction.ASC.name());
        Pageable pageable=PageRequest.of(pageNumber-1,3,Sort.by(field));
        return postRepository.findAll(pageable); //Sort.by(field)
    }

    @Override
    public Page<Post> findByKeyword(String keyword,int pageNumber) {
        Pageable pageable=PageRequest.of(pageNumber-1,3); //
        return postRepository.findByKeyword(keyword,pageable);
    }

    @Override
    public List<String> findDistinctAuthor() {
        return postRepository.findDistinctAuthor();
    }


    @Override
    public Page<Post> findPageByAuthorName(List<String> authors,List<Integer> postId,List<String> updatedAt, int pageNumber) {
        Pageable pageable=PageRequest.of(pageNumber-1,3);
        return postRepository.findPageByAuthorName(authors,updatedAt,postId,pageable);       //,updatedAt
    }


}
