package by.epam.dkozyrev1.ecafe.entity;

import java.util.Objects;

public class User implements Entity {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean isPromoted = false;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this(username, password);
        this.email = email;
    }

    public User(String username, String password, String email, String phone) {
        this(username, password, email);
        this.phone = phone;
    }

    public User(int id, String username, String password, String email, String phone) {
        this(username, password, email, phone);
        this.id = id;
    }

    public User(int id, String username, String password, String email, String phone, boolean isPromoted) {
        this(id, username, password, email, phone);
        this.isPromoted = isPromoted;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isPromoted == user.isPromoted &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, phone, isPromoted);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isPromoted=" + isPromoted +
                '}';
    }
}
