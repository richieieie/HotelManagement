package dao;

import model.Hotel;
import model.HotelList;

public class HotelDAOImpl implements HotelDAO {
    private final HotelList hotelManager;

    public HotelDAOImpl(HotelList hotelManager) {
        this.hotelManager = hotelManager;
    }

    @Override
    public boolean save(Hotel hotel) {
        return hotelManager.addOne(hotel);
    }
    @Override
    public boolean check(String id) {
        return hotelManager.isExist(id);
    }
    @Override
    public HotelList getAll() {
        return hotelManager;
    }
    @Override
    public Hotel updateOne(String id, String name, int avaRooms, String address,
                           String phone, int rating) {
        return hotelManager.updateOne(id, name, avaRooms, address, phone, rating);
    }
    @Override
    public boolean deleteOne(String id) {
        return hotelManager.deleteOne(id);
    }
    @Override
    public Hotel searchById(String id) {
        return hotelManager.searchById(id);
    }
    @Override
    public HotelList searchByName(String name) {
        return hotelManager.searchByName(name);
    }
}
