package model;

import java.io.Serializable;
import java.util.Objects;

public class Hotel implements Data, Serializable {
    private String id;
    private String name;
    private int avaRooms;
    private String address;
    private String phone;
    private int rating;

    public Hotel() {

    }

    public Hotel(String id) {
        this.id = id;
    }

    public Hotel(String id, String name, int avaRooms, String address, String phone, int rating) {
        this.id = id;
        this.name = name;
        this.avaRooms = avaRooms;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvaRooms() {
        return avaRooms;
    }

    public void setAvaRooms(int avaRooms) {
        this.avaRooms = avaRooms;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", avaRooms=" + avaRooms + ", address='" + address + '\'' + ", phone='" + phone + '\'' + ", rating=" + rating + '}';
    }

    @Override
    public String toRow(int[] lens) {
        return String.format("|" + "%-" + lens[0] + "s|" + "%-" + lens[1] + "s|" + "%-" + lens[2] + "s|%-" + lens[3] + "s|%-" + lens[4] + "s|" + "%-" + lens[5] + "s|", id, name, avaRooms, address, phone, rating + " star");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        return Objects.equals(getId(), hotel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
