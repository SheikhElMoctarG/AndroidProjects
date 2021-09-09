package Model;

public class Movie {
    private String title;
    private String description;
    private String year;
    private int cover;

    public Movie(String title, String description, String year, int cover) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.cover = cover;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }
}
