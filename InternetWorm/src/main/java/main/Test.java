package main;

import entity.Book;
import util.ComparatorUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Neptune on 2018/3/11.
 */
public class Test {

    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book();
        book1.setScore(10.0);
        book1.setBookName("JAVA");
        bookList.add(book1);
        Book book2 = new Book();
        book2.setScore(8.0);
        book2.setBookName("C");
        bookList.add(book2);
        Book book3 = new Book();
        book3.setScore(8.8);
        book3.setBookName("C++");
        bookList.add(book3);
        System.out.println("排序前");
        for(Book b : bookList){
            System.out.println(b.getBookName() + "-" + b.getScore());
        }
        Collections.sort(bookList, new ComparatorUtil());
        System.out.println("排序后");
        for(Book b : bookList){
            System.out.println(b.getBookName() + "-" + b.getScore());
        }
    }
}
