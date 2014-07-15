package com.thoughtworks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Library {

    public List<Books> booksList = new ArrayList<Books>();

    //public List<Lendable> booksAndMovies;

    Library() {
        booksList.add(new Books("Head First Java", "Bert Bates", 2004, true));
        booksList.add(new Books("Let us C", "Yashwanth Kanetkar", 2003, true));
        booksList.add(new Books("Let us C++", "Yashwanth Kanetkar", 2010, true));
        booksList.add(new Books("Complete Reference", "Herbert Schildt", 2011, true));
    }

    public int listAllAvailableBooks() {
        int i = 1;
        System.out.println("Book list with details:");
        System.out.format("Sl.No%15s%30s%30s", "Title", "Author", "Publication Year");
        System.out.println("\n-------------------------------------------------------------------------------------");
        for (Books s : booksList) {
            if (s.available == true) {
                System.out.format((i++) + ".     " + "%-30s%-30s%-6d", s.bookTitle, s.bookAuthor, s.bookPublicationYear);
                System.out.println();
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        return i - 1;
    }

    public void checkOutBook() throws IOException {
        System.out.println("Enter the book name to be checked out:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bookToBeCheckedOut = br.readLine();
        if (checkoutBook(bookToBeCheckedOut)) {
            System.out.println("Thank you! Enjoy the book");
        } else
            System.out.println("That book is not available");
    }

    public boolean checkoutBook(String bookToBeCheckedOut) throws IOException {
        Login login = new Login();
        for (Books bookInLibrary : booksList) {
            if (bookInLibrary.matches(bookToBeCheckedOut)) {
                while (login.allowUserToLogin() == 0) {
                    bookInLibrary.available = false;
                    return true;
                }
            }
        }
        return false;
    }

    public int returnBook() throws IOException {
        System.out.println("Enter the book title to be returned.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bookToBeReturned = br.readLine();
        if (returnBook(bookToBeReturned)) {
            System.out.println("Thank you for returning the book");
            return 1;
        } else {
            System.out.println("This is not a valid book to return");
            return 0;
        }

    }

    public boolean returnBook(String bookToBeReturned) {
        Login login = new Login();
        for (Books s : booksList) {
            if (bookToBeReturned.equalsIgnoreCase(s.bookTitle) && !s.available) {
                while (login.allowUserToLogin() == 0) {
                    s.available = true;
                    return true;
                }
            }
        }
        return false;
    }

    public int listOfBooks() {
        int i = 1;
        System.out.format("Sl.No%15s%30s%30s%20s", "Title", "Author", "Publication Year", "Availability");
        System.out.println("\n----------------------------------------------------------------------------------------------------");
        for (Books s : booksList) {
            System.out.format((i++) + ".     " + "%-30s%-30s%-4d%25s", s.bookTitle, s.bookAuthor, s.bookPublicationYear, (s.available == true) ? "Yes" : "No");
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");

        return booksList.size();
    }


}
