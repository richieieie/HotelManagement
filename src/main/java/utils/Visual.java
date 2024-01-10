package utils;

import model.Data;
import model.HotelList;

import java.util.Arrays;

public class Visual {
    public static void printMenu(String[] options) {
        // ----------------------------Construct------------------------------------
        int max = Arrays.stream(options).map(String::length).max(Integer::compare).orElse(0);
        max = Math.max(max, 20);

        int lHalf = Math.round((float) max / 2);
        int rHalf = max % 2 == 1 ? lHalf - 1 : lHalf;

        String horizontalLine = String.format("+%" + (max + 4) + "s+", "").replaceAll(" ", "-");
        String listTitle = String.format("|%" + lHalf + "s" + "MENU" + "%" + rHalf + "s|", "", "");

        // ----------------------------Print-----------------------------------------
        System.out.println(horizontalLine);
        System.out.println(listTitle);

        for (int i = 0, n = options.length; i < n; i++) {
            System.out.println(horizontalLine);
            System.out.printf("|%-" + (max + 4) + "s|%n", (i + 1) + ". " + options[i]);
        }

        System.out.println(horizontalLine);
        System.out.printf("|%-" + (max + 4) + "s|%n", (options.length + 1) + ". " + "Others - Quit");
        System.out.println(horizontalLine);
    }

    public static int getSubChoice(String[] subOptions) {
        int subChoice;
        Visual.printMenu(subOptions);
        subChoice = Inputter.getInt("Your choice: ");
        return subChoice;
    }

    public static void printDataAsTable(HotelList list, String[] columnNames, String title) {
        // ----------------------------Construct------------------------------------
        // Construct padding of each column
        int[] lens = new int[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].matches("(?i)(^.*(Hotel_Address).*$)")) {
                lens[i] = columnNames[i].length() + 64;
                continue;
            } else if (columnNames[i].matches("(?i)(^.*(Hotel_Name).*$)")) {
                lens[i] = columnNames[i].length() + 10;
                continue;
            }
            lens[i] = columnNames[i].length() + 4;
        }

        // Find maximum len and construct padding for the title row
        int max = Arrays.stream(lens).sum();
        int lHalf = Math.round((float) max / 2);
        int rHalf = max % 2 == 1 ? lHalf - 1 : lHalf;
        int tLen = title.length();

        // Format separate row and title row
        String listTitle = String.format("|%" + lHalf + "s" + title + "%" + rHalf + "s|", "", "");
        String hLine = String.format("+%" + (max + tLen) + "s+", "").replaceAll(" ", "-");

        // Construct horizontal line to separate each columns
        StringBuilder hLineCol = new StringBuilder("+");
        for (int len : lens) {
            String col = String.format("%" + len + "s+", "").replaceAll(" ", "-");
            hLineCol.append(col);
        }

        // ----------------------------Print------------------------------------
        // Print title row
        System.out.println(hLine);
        System.out.println(listTitle);
        System.out.println(hLineCol);

        // Construct and print column names row
        StringBuilder namesRow = new StringBuilder("|");
        for (int i = 0; i < columnNames.length; i++) {
            namesRow.append(String.format("%-" + lens[i] + "s|", columnNames[i]));
        }
        System.out.println(namesRow);
        System.out.println(hLineCol);

        for (Data d : list) {
            System.out.println(d.toRow(lens));
            System.out.println(hLineCol);
        }

    }
}