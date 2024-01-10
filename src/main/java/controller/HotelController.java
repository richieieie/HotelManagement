package controller;

import service.HotelService;

public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void active(int choice) {
        switch (choice) {
            case 1 -> hotelService.addHotel();
            case 2 -> hotelService.checkHotelExist();
            case 3 -> hotelService.updateHotel();
            case 4 -> hotelService.deleteHotel();
            case 5 -> hotelService.searchHotel();
            case 6 -> hotelService.displayHotels();
            default -> System.out.println("Exiting...");
        }
    }
}
