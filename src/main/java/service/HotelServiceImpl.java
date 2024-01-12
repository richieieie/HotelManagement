package service;

import dao.HotelDAO;
import factory.DataFactory;
import model.Hotel;
import model.HotelList;
import utils.Inputter;
import utils.Visual;

public class HotelServiceImpl implements HotelService {
    public HotelDAO hotelDAO;

    public HotelServiceImpl(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override
    public void displayHotels() {
        hotelDAO.getAll().displayByOrder(DataFactory.nameCom);
    }
    @Override
    public void searchHotel() {
        int subChoice = Visual.getSubChoice(new String[]{"Searching by Hotel_id", "Searching by Hotel_name"});

        if (subChoice == 1) {
            String id = Inputter.getId();
            HotelList subList = new HotelList();
            Hotel hotel = hotelDAO.searchById(id);

            if (hotel != null) {
                subList.add(hotel);
            }

            subList.displayByOrder(DataFactory.idCom);
        } else if (subChoice == 2) {
            String name = Inputter.getStringWithCap("Enter hotel_name to find:");
            HotelList subList = hotelDAO.searchByName(name);

            subList.displayByOrder(DataFactory.idCom);
        } else {
            System.out.println("Going to main menu...");
        }
    }
    @Override
    public void deleteHotel() {
        String id = Inputter.getId();
        System.out.println("Do you ready want to delete this hotel?");
        if (Visual.getSubChoice(new String[]{"Confirm"}) != 1) {
            System.out.println("Cancelled deleting");
        } else if (!hotelDAO.deleteOne(id)) {
            System.out.println("Failed to delete because hotel doesn't exist or something went wrong!");
        } else {
            System.out.println("Succeeded to delete the hotel!");
        }
    }
    @Override
    public void updateHotel() {
        String id = Inputter.getId();
        if (!hotelDAO.check(id)) {
            System.out.println("Hotel does not exist");
            return;
        }

        String name = Inputter.getStringEmpty("Enter hotel_name:");
        int avaRooms = Inputter.getIntEmpty("Enter available_rooms (greater than or equal 0):", 0, Integer.MAX_VALUE);
        String address = Inputter.getStringEmpty("Enter hotel_address:");
        String phone = Inputter.getStringEmpty("Enter phone:");
        int rating = Inputter.getIntEmpty("Enter hotel_rating (0 to 6):", 0, 6);

        Hotel updatedHotel = hotelDAO.updateOne(id, name, avaRooms, address, phone, rating);
        if (updatedHotel != null) {
            HotelList table = new HotelList(1);
            table.add(updatedHotel);
            table.displayByOrder();
            System.out.println("Succeeded to update hotel information");
        } else {
            System.out.println("Failed to update hotel information");
        }
    }
    @Override
    public void checkHotelExist() {
        boolean next;
        String id;

        do {
            next = true;
            id = Inputter.getId();

            if (!hotelDAO.check(id)) {
                System.out.println("No Hotel Found!");
            } else {
                System.out.println("Exist Hotel!");
            }

            if (Visual.getSubChoice(new String[]{"Continue"}) != 1) {
                next = false;
                System.out.println("Going to main menu...");
            }
        } while (next);
    }
    @Override
    public void addHotel() {
        Hotel hotel;
        String id, name, address, phone;
        int avaRooms, rating;
        boolean next;

        do {
            next = true;
            id = Inputter.getId(hotelDAO, false);
            name = Inputter.getStringWithCap("Enter hotel_name:");
            avaRooms = Inputter.getInt("Enter available_rooms (greater than or equal 0):", 0, Integer.MAX_VALUE);
            address = Inputter.getStringWithCap("Enter hotel_address:");
            phone = Inputter.getString("Enter phone:", "\\d+", "Please enter a phone number!");
            rating = Inputter.getInt("Enter hotel_rating (0 to 6):", 0, 6);
            hotel = new Hotel(id, name, avaRooms, address, phone, rating);

            if (!hotelDAO.save(hotel)) {
                System.out.println("Failed to add a new hotel!");
            } else {
                System.out.println("Succeeded to add a new hotel!");
            }

            if (Visual.getSubChoice(new String[]{"Continue"}) != 1) {
                next = false;
                System.out.println("Going to main menu...");
            }
        } while (next);
    }
}
