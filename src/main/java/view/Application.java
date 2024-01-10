package view;

import factory.DataFactory;
import utils.Inputter;
import utils.Visual;

public class Application {
    public static void run() {
        int choice;
        do {
            Visual.printMenu(DataFactory.menuOptions);
            choice = Inputter.getInt("Your choice: ");
            DataFactory.hotelController.active(choice);
        } while (choice >= 1 && choice < 7);
    }
}
