package nesteen.springboot.project.SpringBootProject.entity;

import java.time.LocalDate;

public class FilterData {

    private int id;

    private String  author;

    private String tags;

    private String date;

    public FilterData() {
    }

    public FilterData(String author, String tags, String date) {
        this.author = author;
        this.tags = tags;
        this.date = date;
    }

    public FilterData(int id, String author, String tags, String date) {
        this.id = id;
        this.author = author;
        this.tags = tags;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FilterData{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", tags='" + tags + '\'' +
                ", date=" + date +
                '}';
    }
}
