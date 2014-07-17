package com.thoughtworks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Library {
    public List<Item> items = new ArrayList<Item>();
    Login login = new Login();
    Log log=new Log();
    int res=1;

    public int listAvailableBooks() {
        int counter = 1;
        System.out.format("Sl.No%15s%30s%30s", "Title", "Author", "Publication Year");
        System.out.println("\n-------------------------------------------------------------------------------------");
        for (Item item : items) {
            if (item.checkAvailability()) {
                item.printWithoutAvailability(counter++);
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");
        return counter - 1;
    }
    public int listAvailableMovies() {
        int counter = 1;
        System.out.format("Sl.No%15s%25s%25s%15s", "MovieName", "Year", "Director", "Rating");
        System.out.println("\n-------------------------------------------------------------------------------------");
        for (Item item : items) {
            if (item.checkAvailability()) {
                item.printWithoutAvailability(counter++);
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");
        return counter-1;
    }

    public int checkoutItem(String itemToBeCheckedOut) throws IOException {
        if ((res=login.allowUserToLogin())== 0) {
            for (Item bookInLibrary : items) {
                if (bookInLibrary.matchesForCheckout(itemToBeCheckedOut)) {
                    bookInLibrary.checkoutItem();
                    log.makeEntry(itemToBeCheckedOut, login.currentUserId);
                    return 0;
                }
            }
            return 1;
        }
        return res;
    }

    public int returnItem(String itemToBeReturned) {
        if((res=login.allowUserToLogin())==0) {
            for (Item s : items) {
                if (s.matchesForReturn(itemToBeReturned)) {
                    log.removeEntry(itemToBeReturned, login.currentUserId);
                    s.returnItem();
                    return 0;
                }
            }
            return 1;
        }
        return res;
    }

    public int listAllBooks() {
        int counter = 1;
        System.out.format("Sl.No%15s%30s%30s%20s", "Title", "Author", "Publication Year", "Availability");
        System.out.println("\n----------------------------------------------------------------------------------------------------");
        for (Item item : items)
            item.printWithAvailability(counter++);
        System.out.println("------------------------------------------------------------------------------------------------------");
        return counter-1;
    }
    public int listAllMovies() {
        int counter = 1;
        System.out.format("Sl.No%15s%25s%25s%15s%25s", "MovieName", "Year", "Director", "Rating", "Availability");
        System.out.println("\n------------------------------------------------------------------------------------------------------------");
        for (Item item : items) {
            item.printWithAvailability(counter++);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        return counter - 1;
    }
}
