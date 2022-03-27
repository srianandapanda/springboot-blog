package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    public List<Post> findAll();
    public void save(Post thePost);
    public void deleteById(int theId);
    public Post findById(int theId);
    public List<Post> findAllSort();
    public List<Post> findListByAuthorName(String theAuthor);
    List<Post> findByPublishedAtLike(String publishDate);
    List<Post> findByTitleIgnoreCaseLike(String title);
    List<Post> findByContentIgnoreCaseLike(String content);
    List<Post> findByAuthorIgnoreCaseLike(String author);
    public List<Post> findByOrderByPublishedAtAsc();
    public Page<Post> findPage(int pageNumber);
    public Page<Post> findSortedPage(int pageNumber);
    //public List<Post> findAllWithSort(String field);
    public Page<Post> findAllWithSort(String field, int pageNumber);
    public Page<Post> findByKeyword(String keyword,int pageNumber);
    List<String> findDistinctAuthor();
    Page<Post> findPageByAuthorName(List<String> authors,List<Integer> id,List<String> updatedAt, int pageNumber);
}
