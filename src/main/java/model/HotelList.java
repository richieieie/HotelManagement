package model;

import factory.DataFactory;
import utils.FileOperator;
import utils.Visual;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class HotelList extends ArrayList<Hotel> {
    private String path;

    public HotelList(int initialCapacity) {
        super(initialCapacity);
    }

    public HotelList() {
    }

    public HotelList(String path) {
        if (FileOperator.readFromFile(path, this)) System.out.println("Data is read into \"hotelManager\"");
        this.path = path;
    }

    public HotelList(Collection<? extends Hotel> c) {
        super(c);
    }

    public boolean addOne(Hotel hotel) {
        if (this.contains(hotel)) return false;


        this.add(hotel);
        if (FileOperator.writeToFile(path, this)) {
            return true;
        }

        this.remove(hotel);
        return false;
    }

    public boolean isExist(String id) {
        return this.contains(new Hotel(id));
    }

    public Hotel updateOne(String id, String name, int avaRooms, String address, String phone, int rating) {
        int i = this.indexOf(new Hotel(id));
        Hotel hotel = this.get(i);
        Hotel cpyHotel = new Hotel(hotel.getId(), hotel.getName(), hotel.getAvaRooms(), hotel.getAddress(), hotel.getPhone(), hotel.getRating());

        if (!name.isEmpty()) hotel.setName(name);
        if (avaRooms >= 0) hotel.setAvaRooms(avaRooms);
        if (!address.isEmpty()) hotel.setAddress(address);
        if (!phone.isEmpty()) hotel.setPhone(phone);
        if (rating >= 0) hotel.setRating(rating);

        if (FileOperator.writeToFile(path, this)) {
            return hotel;
        }

        this.set(i, cpyHotel);
        return null;
    }

    public boolean deleteOne(String id) {
        Hotel hotel = this.searchById(id);

        if (hotel == null) return false;

        this.remove(hotel);
        if (FileOperator.writeToFile(path, this)) {
            return true;
        }

        this.add(hotel);
        return false;
    }

    public Hotel searchById(String id) {
        int i = this.indexOf(new Hotel(id));
        if (i == -1) return null;

        return this.get(i);
    }

    public HotelList searchByName(String name) {
        HotelList list = new HotelList();
        for (Hotel h : this) {
            if (h.getName().equals(name)) list.add(h);
        }

        return list;
    }

    public void displayByOrder() {
        this.sort(DataFactory.idCom);
        Visual.printDataAsTable(this, new String[]{"Hotel_Id", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating"}, "HOTEL");
    }

    public void displayByOrder(Comparator<Hotel> comparator) {
        this.sort(comparator);
        Visual.printDataAsTable(this, new String[]{"Hotel_Id", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating"}, "HOTEL");
    }
}
