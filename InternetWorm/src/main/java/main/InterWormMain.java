package main;

import entity.Book;
import util.ExcelGenerateUtil;
import util.PageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Neptune on 2018/3/10.
 */
public class InterWormMain {
    public static void main(String[] args) {
        Long startTime = new Date().getTime();
        StringBuffer url = new StringBuffer("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
        List<Book> bookList = new ArrayList<Book>();
        //获取爬到的书籍
        PageUtil.setUrl(url,bookList);
//        System.out.print(bookList.size());

        List<String> rowNameList = new ArrayList<String>();
        rowNameList.add("书名");
        rowNameList.add("评分");
        rowNameList.add("评价人数");
        rowNameList.add("作者");
        rowNameList.add("出版社");
        rowNameList.add("出版日期");
        rowNameList.add("价格");
        String excelName = "豆瓣编程书籍评分TOP40";
        //生成Excel
        ExcelGenerateUtil.createExcel(rowNameList, excelName,bookList);

        Long endTime = new Date().getTime();

        System.out.println("一共用时" + (endTime - startTime) + "ms");
    }
}
