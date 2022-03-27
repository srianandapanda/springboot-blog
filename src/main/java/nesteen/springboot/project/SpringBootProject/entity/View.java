package nesteen.springboot.project.SpringBootProject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view")
public class View {

    @Id
    private int id;

    private String postTitle;

    private String tags;

    private String author;

    private String content;

    public View() {
    }

    public View(int postId, String postTitle, String tags, String author, String content) {
        this.id = postId;
        this.postTitle = postTitle;
        this.tags = tags;
        this.author = author;
        this.content = content;
    }

    public int getPostId() {
        return id;
    }

    public void setPostId(int id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "View{" +
                "postId=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", tags='" + tags + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
