package util;

import entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Neptune on 2018/3/10.
 */
public class PageUtil {


//    public static void setUrl(String url,List<Book> bookList) {
    public static void setUrl(StringBuffer url,List<Book> bookList) {
        try {
            Document doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36").get();
//            System.out.print(doc);
//            Element mainBody = doc.getElementById("subject_list");
////            System.out.println(mainBody);
            //分页总数
            int totalPage = Integer.parseInt(doc.select("div.paginator > a").last().text());
//            System.out.println(totalPage);
            for (int i = 1; i <= totalPage; i++) {
//           for (int i = 1; i <= 2; i++) {
                System.out.println("正在爬第" + i + "页");
                url = new StringBuffer("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
                url.append("?start=");
                url.append((i-1) * 20);
                url.append("&type=T");
                System.out.println("url = " + url.toString());
                doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36").get();
//                System.out.print(doc);
                Elements elements = doc.select("ul.subject-list").select("li.subject-item");
                if (elements.size() == 0) {//手动看到第96页为空，想跳过这种空页面
                    System.out.println("第" + i + "页没有书籍");
                    continue;
                }else{
    //                System.out.println(elements);
                    for (Element element : elements) {
    //                    System.out.println(element);
                        //评价人数
                        String evaluationDetail = element.select("span.pl").text();
                        Pattern pattern = Pattern.compile("[0-9]*");
                        Matcher isNum = pattern.matcher(evaluationDetail.substring(1, evaluationDetail.length() - 4));
                        Integer numOfEvaluation = 0;
                        if(isNum.matches()){
                            numOfEvaluation = Integer.valueOf(evaluationDetail.substring(1, evaluationDetail.length() - 4));
                        }else {
                            continue;
                        }
    //                    System.out.println(numOfEvaluation);
                        if (numOfEvaluation <= 1000) {
                            continue;
                        } else {
                            //评分
                            Double score = Double.valueOf(element.select("span.rating_nums").text());
    //                        System.out.println(evaluationDetail);
                            //书名
                            String bookName = element.select("a").eq(1).attr("title");
//                            System.out.println(element.select("div.pub").text());
                            //待分割信息
                            String infomation = element.select("div.pub").text();
                            //进行分割
                            String[] split = infomation.split("/");
                            //作者
                            String author = null;
                            //出版社
                            String press = null;
                            //出版日期
                            String date = null;
                            //单价
                            String price = null;
                            if (split.length == 4) {
                                author = split[0];
                                press = split[1];
                                date = split[2];
                                price = split[3];
                            } else {
                                author = split[0];
                                press = split[2];
                                date = split[3];
                                price = split[4];
                            }
    //                        System.out.println("作者：" + author + ",出版社：" + press + ",出版日期：" + date + ",单价：" + price);

    //                        System.out.println("---------------------------------------华丽的分割线-----------------------------------------------");
                            Book book = new Book();
                            book.setAuthor(author);
                            book.setBookName(bookName);
                            book.setDate(date);
                            book.setNumOfEvaluation(numOfEvaluation);
                            book.setPress(press);
                            book.setScore(score);
                            book.setPrice(price);
                            bookList.add(book);
                        }
                    }
                }
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(500) + 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //排序
            Collections.sort(bookList, new ComparatorUtil());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StringBuffer url = new StringBuffer("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
        List<Book> bookList = new ArrayList<Book>();
        setUrl(url,bookList);
        System.out.println();
    }
}
