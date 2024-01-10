package factory;

import controller.HotelController;
import dao.HotelDAO;
import dao.HotelDAOImpl;
import model.Hotel;
import model.HotelList;
import service.HotelService;
import service.HotelServiceImpl;

import java.util.Comparator;

public class DataFactory {
    public static HotelList hotelManager = new HotelList("src/main/resources/hotel.dat");
    public static HotelDAO hotelDAO = new HotelDAOImpl(hotelManager);
    public static HotelService hotelService = new HotelServiceImpl(hotelDAO);
    public static HotelController hotelController = new HotelController(hotelService);
    public static Comparator<Hotel> idCom = Comparator.comparing(Hotel::getId);
    public static Comparator<Hotel> nameCom = (Hotel h1, Hotel h2) -> -h1.getName().compareTo(h2.getName());
    public static String[] menuOptions = new String[]{"Adding new Hotel", "Checking exits Hotel",
            "Updating Hotel information", "Deleting Hotel", "Searching Hotel", "Displaying a hotel list (descending by Hotel_Name)"};
    static {
        addRawData();
    }
    private static void addRawData() {
        hotelManager.addOne(new Hotel("H01", "Seraton", 10, "189 Ung Van Khiem, Ward 25, Binh Thanh " +
                "District, Ho Chi Minh City", "0911796099", 4));
        hotelManager.addOne(new Hotel("H02", "VinStar", 5, "200 Ung Van Khiem, Ward 25, Binh Thanh " +
                "District, Ho Chi Minh City", "0918940111", 5));
        hotelManager.addOne(new Hotel("H03", "OutString", 7, "300 D1, Ward 24, Binh " +
                "Thanh " +
                "District, Ho Chi Minh City", "0988940222", 6));
        hotelManager.addOne(new Hotel("H04", "Betigar", 8, "189 Duong Quang Ham, Ward 5, Go Vap " +
                "District, Ho Chi Minh City", "0977940100", 3));
    }
}
