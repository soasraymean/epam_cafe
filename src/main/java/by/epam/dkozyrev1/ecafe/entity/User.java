package by.epam.dkozyrev1.ecafe.entity;

import java.util.Objects;

public class User implements Entity {
    private int id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private boolean isPromoted = false;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String email) {
        this(login, password);
        this.email = email;
    }

    public User(String login, String password, String email, String phone) {
        this(login, password, email);
        this.phone = phone;
    }

    public User(int id, String login, String password, String email, String phone) {
        this(login, password, email, phone);
        this.id = id;
    }

    public User(int id, String login, String password, String email, String phone, boolean isPromoted) {
        this(id, login, password, email, phone);
        this.isPromoted = isPromoted;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, phone, isPromoted);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isPromoted=" + isPromoted +
                '}';
    }
}
