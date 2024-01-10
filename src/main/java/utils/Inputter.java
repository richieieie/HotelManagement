package utils;

import dao.HotelDAO;

import java.util.Scanner;

public class Inputter {
    public static Scanner scanner = new Scanner(System.in);

    public static String getString(String prompt) {
        String s;

        do {
            System.out.println(prompt);
            s = scanner.nextLine();
        } while (s.isEmpty());

        scanner.reset();
        return s.trim();
    }

    public static String getString(String prompt, String regex, String errMsg) {
        String s;

        do {
            System.out.println(prompt);
            s = scanner.nextLine().trim();
            if (!s.matches(regex)) System.out.println(errMsg);
        } while (s.isEmpty() || !s.matches(regex));

        return s.trim();
    }

    public static String getStringWithCap(String prompt) {
        return capitalizeWords(getString(prompt));
    }

    public static String getStringEmpty(String prompt) {
        String s;

        System.out.println(prompt);
        s = scanner.nextLine();

        return capitalizeWords(s.trim());
    }

    public static int getInt(String prompt) {
        int num = -1;
        do {
            try {
                String s = getString(prompt);
                num = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number");
            }
        } while (num < 0);
        return num;
    }

    public static int getInt(String prompt, int min, int max) {
        int num = -1;
        do {
            try {
                String s = getString(prompt);
                num = Integer.parseInt(s);

                if (num < min || num > max) System.out.println("Please enter an integer number from " + min + " to " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number");
            }
        } while (num < min || num > max);
        return num;
    }

    public static int getIntEmpty(String prompt, int min, int max) {
        int num = -1;
        do {
            try {
                String s = getStringEmpty(prompt);
                if (s.isEmpty()) return -1;
                num = Integer.parseInt(s);

                if (num < min || num > max) System.out.println("Please enter an integer number from " + min + " to " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number");
            }
        } while (num < min || num > max);
        return num;
    }

    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s");

        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                result.append(firstChar).append(word.substring(1)).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static String getId(HotelDAO hotelDAO, boolean exist) {
        boolean isExist;
        String id;

        if (!exist) {
            do {
                isExist = false;
                id = Inputter.getString("Enter hotel_id:", "(?i)(h\\d{2})", "Please enter id with [H00] format!").toUpperCase();
                if (hotelDAO.check(id)) {
                    System.out.println("hotel_id exists");
                    isExist = true;
                }
            } while (isExist);
        } else {
            do {
                isExist = true;
                id = Inputter.getString("Enter hotel_id:", "(?i)(h\\d{2})", "Please enter id with " + "[H00] format!").toUpperCase();
                if (!hotelDAO.check(id)) {
                    System.out.println("hotel_id doesn't exist");
                    isExist = false;
                }
            } while (!isExist);
        }

        return id;
    }

    public static String getId() {
        return Inputter.getString("Enter hotel_id:", "(?i)(h\\d{2})", "Please enter id " + "with " + "[H00] format!").toUpperCase();
    }
}