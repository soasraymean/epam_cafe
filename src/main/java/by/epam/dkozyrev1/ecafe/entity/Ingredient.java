package by.epam.dkozyrev1.ecafe.entity;

import java.util.Objects;

public class Ingredient implements Entity{

    private int id;
    private String name;
    private String pictureUrl;
    private int weight;

    public Ingredient(){

    }
    public Ingredient(String name){
        this.name=name;
    }

    public Ingredient(String name, String pictureUrl){
        this(name);
        this.pictureUrl=pictureUrl;
    }
    public Ingredient(int id, String name, String pictureUrl){
        this(name, pictureUrl);
        this.id=id;
    }
    public Ingredient(int id, String name, String pictureUrl, int weight){
        this(id, name, pictureUrl);
        this.weight=weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && weight == that.weight && Objects.equals(name, that.name) && Objects.equals(pictureUrl, that.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, pictureUrl);
    }

}
