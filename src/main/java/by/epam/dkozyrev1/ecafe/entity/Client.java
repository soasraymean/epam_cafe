package by.epam.dkozyrev1.ecafe.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Client implements Actor {
    private int id;
    private User user;
    private String name;
    private String surname;
    private int loyaltyPoints;
    private boolean isBanned=false;
    private int bonuses;

    private Order currentOrder = new Order(this);
    private final List<Order> orders = new ArrayList<>();

    public Client(){

    }
    public Client(User user){
        this.user=user;
    }
    public Client(User user, String name){
        this(user);
        this.name=name;
    }
    public Client(User user, String name, String surname){
        this(user, name);
        this.surname=surname;
    }
    public Client(int id, User user, String name, String surname, int loyaltyPoints, boolean isBanned, int bonuses){
        this(user, name, surname);
        this.id=id;
        this.loyaltyPoints=loyaltyPoints;
        this.isBanned=isBanned;
        this.bonuses=bonuses;
    }

    @Override
    public boolean isPromoted() {
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public void removeOrder(Order order){
        order.setCustomer(null);
        orders.remove(order);
    }
    public void removeOrder(int id){
        orders.stream().filter(o -> o.getId()==id).findAny().ifPresent(this::removeOrder);
    }
    public Optional<Order> getOrder(int id){
        return orders.stream().filter(o->o.getId()==id).findAny();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && loyaltyPoints == client.loyaltyPoints && isBanned == client.isBanned && bonuses == client.bonuses && Objects.equals(user, client.user) && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(currentOrder, client.currentOrder) && Objects.equals(orders, client.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, surname, loyaltyPoints, isBanned, bonuses, currentOrder, orders);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", isBanned=" + isBanned +
                ", bonuses=" + bonuses +
                ", currentOrder=" + currentOrder +
                ", orders=" + orders +
                '}';
    }
}
