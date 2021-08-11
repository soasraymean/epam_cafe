package by.epam.dkozyrev1.ecafe.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Meal implements Entity {
    private int id;
    private String name;
    private float price;
    private Category category;
    private String pictureUrl;
    private final List<Ingredient> ingredients = new ArrayList<>();

    public Meal() {

    }

    public Meal(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Meal(String name, float price, Category category) {
        this(name, price);
        this.category = category;
    }

    public Meal(String name, float price, Category category, String pictureUrl, Ingredient... ingredients) {
        this(name, price, category);
        this.pictureUrl = pictureUrl;
        this.ingredients.addAll(Arrays.asList(ingredients));
    }

    public Meal(int id, String name, float price, Category category, String pictureUrl, Ingredient... ingredients) {
        this(name, price, category, pictureUrl, ingredients);
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void removeIngredientId(int ingredientId) {
        ingredients.stream().filter(ingredient -> ingredient.getId() == ingredientId).findAny().ifPresent(this::removeIngredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getWeight() {
        return ingredients.stream().mapToInt(Ingredient::getWeight).sum();
    }

    public boolean contains(Ingredient ingredient){
        return ingredients.stream().anyMatch(i -> i.getName().equals(ingredient.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return id == meal.id &&
                Float.compare(meal.price, price) == 0 &&
                Objects.equals(name, meal.name) &&
                Objects.equals(category, meal.category) &&
                Objects.equals(pictureUrl, meal.pictureUrl) &&
                Objects.equals(ingredients, meal.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, pictureUrl, ingredients);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
