package by.epam.dkozyrev1.ecafe.entity;

import java.util.Objects;

public class Comment {
    private int id;
    private String message;
    public Comment(){

    }

    public Comment(String message) {
        this.message = message;
    }

    public Comment(int id, String message) {
        this(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(message, comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
