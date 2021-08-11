package by.epam.dkozyrev1.ecafe.entity;

import java.util.Objects;

public class Category implements Entity{
    private int id;
    private String name;
    private String pictureUrl;

    public Category(){

    }
    public Category(String name){
        this.name=name;
    }
    public Category(String name, String pictureUrl){
        this(name);
        this.pictureUrl=pictureUrl;
    }
    public Category(int id, String name, String pictureUrl){
        this(name, pictureUrl);
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name) && Objects.equals(pictureUrl, category.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pictureUrl);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
