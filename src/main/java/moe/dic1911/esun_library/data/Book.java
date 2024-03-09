package moe.dic1911.esun_library.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {
//    (
//    isbn            VARCHAR(255) PRIMARY KEY,
//    name            VARCHAR(255),
//    author          VARCHAR(255),
//    intro           VARCHAR(255),
//    available_count INTEGER,
//    total_count     INTEGER
//);

    @Id
    private String isbn;
    private String name;
    private String author;
    private String intro;
    private Integer availableCount;
    private Integer totalCount;


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getAvailableCount() {
        return availableCount == null ? 0 : availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getTotalCount() {
        return totalCount == null ? 0 : totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
