package nesteen.springboot.project.SpringBootProject.dao;

import nesteen.springboot.project.SpringBootProject.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    public List<Post> findByOrderByPublishedAtDesc();

    @Query("SELECT u FROM Post u WHERE u.author = :theAuthor")
    public List<Post> findListByAuthorName(String theAuthor);

    List<Post> findByUpdatedAtLike(String publishDate);

    List<Post> findByTitleIgnoreCaseLike(String title);

    List<Post> findByContentIgnoreCaseLike(String content);

    List<Post> findByAuthorIgnoreCaseLike(String author);

    public List<Post> findByOrderByPublishedAtAsc();
// @Query(value = "select p from Post p where p.title like %:keyword% or p.content like %:keyword% or p.author like %:keyword% or p.tag like %:keyword%", nativeQuery = true)
    @Query(value = "select p from Post p where "+
            "concat(p.title, p.content, p.author, p.tag) LIKE %?1%")


//@Query("SELECT DISTINCT p FROM post p, post_tags pt WHERE p.id = pt.post_id AND pt.tag_id IN :allTags " +
//        "AND (p.author = :search OR p.title = :search OR p.content like '%:search%' OR p.publishedAt LIKE" +
//        " '%:date%') ORDER BY p.publishedAt :orderBy")
//@Query("select distinct p from post p, post_tags pt where"+
//        "p.id = pt.post_id"+
//        "concat(p.title,p.content.p.author) like %?1%"+
//        "concat(pt.tag_id IN ?2)"+
//        "concat")
    Page<Post> findByKeyword(String keyword, Pageable pageable);

    @Query(value = "select p from Post p where "+
            "concat(p.title, p.content, p.author, p.tag) LIKE %?1%")
    Page<Post> findByKeywords(String keyword, Pageable pageable);

    @Query(value = "select DISTINCT(p.author) from Post p")
    List<String> findDistinctAuthor();
//SELECT e FROM Employee e WHERE e.employeeName IN (:names)
    @Query("SELECT p FROM Post p WHERE p.author IN :authors or p.updatedAt IN :updatedAt or p.id IN :id")
    public Page<Post> findPageByAuthorName(List<String> authors,List<String> updatedAt,List<Integer> id,Pageable pageable);
//List<String> updatedAt,
    //           or p.updatedAt IN:updatedAt

}
//@Param("keyword")

//Select * from tags where name in ?1