package com.bank.sys.models;

import java.util.Objects;

import org.bson.types.ObjectId;

public class User {
    private ObjectId id;
    private String name;
    private String surname;
    private String pesel;
    private String address;
    private Double money = 0.0;

    public User(String name, String surname, String pesel, String address, Double money) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        this.money = money;
    }

    public User(String name, String surname, String pesel, String address) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
    }

    public User() {}

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

    public String getAddress() {
        return address;
    }

    public Double getMoney() {
        return money;
    }

    public User setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setPesel(String pesel) {
        this.pesel = pesel;
        return this;
    }

    public User setMoney(Double money) {
        this.money = money;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getPesel() != user.getPesel()) {
            return false;
        }
      
        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User{"
                + "id='" + id + "'"
                + ", name='" + name + "'"
                + ", surname='" + surname + "'"
                + ", PESEL='" + pesel + "'"
                + ", address='" + address + "'"
                + ", money='" + money + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pesel, address);
    }
}
