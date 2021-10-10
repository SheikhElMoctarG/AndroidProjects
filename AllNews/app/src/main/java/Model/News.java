package Model;

import java.util.Date;

public class News {
    private String title;
    private String date;
    private String image;
    private String description;
    private String nameSource;
    private String link;

    public News() {
    }

    public News(String title, String link, String date, String image, String description, String nameSource) {
        this.title = title;
        this.link = link;
        this.date = date;
        this.image = image;
        this.description = description;
        this.nameSource = nameSource;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameSource() {
        return nameSource;
    }

    public void setNameSource(String nameSource) {
        this.nameSource = nameSource;
    }

}
