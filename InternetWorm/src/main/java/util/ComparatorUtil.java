package util;

import entity.Book;

import java.util.Comparator;

/**
 * Created by Neptune on 2018/3/11.
 */
public class ComparatorUtil implements Comparator<Book> {


    public int compare(Book o1, Book o2) {
        Double score1 = o1.getScore();
        Double score2 = o2.getScore();
        return Double.compare(score2,score1);
    }

}
