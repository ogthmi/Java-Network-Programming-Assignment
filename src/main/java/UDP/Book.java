package UDP;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Book implements Serializable {
    private String id, title, author, isbn, publishDate;
    private static final long serialVersionUID = 20251107L;

    public Book(String id, String title, String author, String isbn, String publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }

    public void normalize() {
        normalizeDate();
        normalizeISBN();
        normalizeName();
        normalizeTitle();
    }

    private void normalizeName() {
        String[] tokens = this.author.split("\\s+");

        this.author = tokens[0].toUpperCase() + ", " +
                Arrays.stream(tokens, 1, tokens.length)
                        .map(
                                token -> token.substring(0, 1).toUpperCase()
                                + token.substring(1).toLowerCase()
                        )
                        .collect(Collectors.joining(" "));
    }

    private void normalizeTitle() {
        this.title = Arrays.stream(this.title.split("\\s+"))
                .map(token ->
                        token.substring(0, 1).toUpperCase() +
                                token.substring(1).toLowerCase()
                )
                .collect(Collectors.joining(" "));
    }

    private void normalizeISBN() {
        List<String> tokens = List.of(
                isbn.substring(0, 3),
                isbn.substring(3, 4),
                isbn.substring(4, 6),
                isbn.substring(6, 12),
                isbn.substring(12)
        );
        this.isbn = String.join("-", tokens);
    }

    private void normalizeDate() {
        String[] tokens = this.publishDate.split("-");
        this.publishDate = tokens[1] + "/" + tokens[0];
    }
}
