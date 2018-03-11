package entity;

/**
 * Created by Neptune on 2018/3/10.
 */
public class Book {

    //书名
    public String bookName;

    //评分
    public Double score;

    //评价人数
    public Integer numOfEvaluation;

    //作者
    public String author;

    //出版社
    public String press;

    //出版日期
    public String date;

    //单价(带单位，所以string)
    public String price;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getNumOfEvaluation() {
        return numOfEvaluation;
    }

    public void setNumOfEvaluation(Integer numOfEvaluation) {
        this.numOfEvaluation = numOfEvaluation;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
