package by.epam.dkozyrev1.ecafe.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order implements Entity {
    private int id;
    private Client customer;
    private Date orderDate;
    private boolean isReady;
    private boolean isPaid;
    private boolean isTaken;
    private int clientMark;
    private String clientComment;
    private final List<Meal> meals = new ArrayList<>();

    public Order() {

    }

    public Order(Client customer) {
        this.customer = customer;
    }

    public Order(Client customer, Date orderDate) {
        this(customer);
        this.orderDate = orderDate;
    }

    public Order(int id, Client customer, Date orderDate, boolean isReady, boolean isPaid, boolean isTaken) {
        this(customer, orderDate);
        this.id = id;
        this.isReady = isReady;
        this.isPaid = isPaid;
        this.isTaken = isTaken;
    }

    public Order(int id, Client customer, Date orderDate, boolean isReady, boolean isPaid, boolean isTaken, int clientMark, String clientComment) {
        this(id, customer, orderDate, isReady, isPaid, isTaken);
        this.clientMark = clientMark;
        this.clientComment = clientComment;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getClientMark() {
        return clientMark;
    }

    public void setClientMark(int clientMark) {
        this.clientMark = clientMark;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
    }

    public void removeMeal(int id) {
        meals.stream().filter(meal -> meal.getId() == id).findAny().ifPresent(this::removeMeal);
    }


    public List<Meal> getMeals() {
        return meals;
    }

    public boolean isBlank() {
        return meals.isEmpty();
    }

    public int getSize() {
        return meals.size();
    }

    public float getTotalPrice() {
        return (float) meals.stream().mapToDouble(Meal::getPrice).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && isReady == order.isReady && isPaid == order.isPaid && isTaken == order.isTaken && clientMark == order.clientMark && Objects.equals(customer, order.customer) && Objects.equals(orderDate, order.orderDate) && Objects.equals(clientComment, order.clientComment) && Objects.equals(meals, order.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, orderDate, isReady, isPaid, isTaken, clientMark, clientComment, meals);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", isReady=" + isReady +
                ", isPaid=" + isPaid +
                ", isTaken=" + isTaken +
                ", clientMark=" + clientMark +
                ", clientComment='" + clientComment + '\'' +
                ", meals=" + meals +
                '}';
    }
}
