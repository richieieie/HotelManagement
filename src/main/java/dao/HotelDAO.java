package dao;

import model.Hotel;
import model.HotelList;

public interface HotelDAO {
    boolean save(Hotel hotel);

    boolean check(String id);

    HotelList getAll();

    Hotel updateOne(String id, String name, int avaRooms, String address,
                    String phone, int rating);

    boolean deleteOne(String id);

    Hotel searchById(String id);

    HotelList searchByName(String name);
}
