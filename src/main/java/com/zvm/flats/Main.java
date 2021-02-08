package com.zvm.flats;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        DatabaseManager.restartDB();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter district: ");
        String district = sc.nextLine().isEmpty() ? null : sc.nextLine();

        System.out.println("Enter address: ");
        String address = sc.nextLine().isEmpty() ? null : sc.nextLine();

        System.out.println("Enter space: ");
        Integer space = sc.nextLine().isEmpty() ? null : Integer.parseInt(sc.nextLine());

        System.out.println("Enter rooms: ");
        Integer rooms = sc.nextLine().isEmpty() ? null : Integer.parseInt(sc.nextLine());

        System.out.println("Enter price: ");
        Integer price = sc.nextLine().isEmpty() ? null : Integer.parseInt(sc.nextLine());

        DatabaseManager.getFlat(
                district,
                address,
                space,
                rooms,
                price
        );
    }
}
