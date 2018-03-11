package util;

import entity.Book;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neptune on 2018/3/11.
 */
public class ExcelGenerateUtil {

    public static void createExcel(List<String> rowNameList,String excelName,List<Book> bookList){
        //创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个sheet页
        HSSFSheet sheet = workbook.createSheet("豆瓣编程书籍评分TOP40");
        //添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        //添加表头内容
        for(int i = 0;i<=rowNameList.size();i++){
            HSSFCell headCell = hssfRow.createCell(i);
            if(i==0){
                headCell.setCellValue("序号");
                continue;
            }
            headCell.setCellValue(rowNameList.get(i-1));
        }
        //添加数据
        for(int i = 1;i<=(bookList.size()>40?40:bookList.size());i++){
            hssfRow = sheet.createRow(i);
            Book book = bookList.get(i-1);

            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(i);

            cell = hssfRow.createCell(1);
            cell.setCellValue(book.getBookName());

            cell = hssfRow.createCell(2);
            cell.setCellValue(book.getScore());

            cell = hssfRow.createCell(3);
            cell.setCellValue(book.getNumOfEvaluation());

            cell = hssfRow.createCell(4);
            cell.setCellValue(book.getAuthor());

            cell = hssfRow.createCell(5);
            cell.setCellValue(book.getPress());

            cell = hssfRow.createCell(6);
            cell.setCellValue(book.getDate());

            cell = hssfRow.createCell(7);
            cell.setCellValue(book.getPrice());
        }

        try {
            OutputStream outputStream = new FileOutputStream("D:/" + excelName +".xls");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        List<String> rowNameList = new ArrayList<String>();
        rowNameList.add("书名");
        rowNameList.add("评分");
        rowNameList.add("评价人数");
        rowNameList.add("作者");
        rowNameList.add("出版社");
        rowNameList.add("出版日期");
        rowNameList.add("价格");
        String excelName = "豆瓣";
        List<Book> bookList = new ArrayList<Book>();
        Book book1 = new Book();
        book1.setBookName("JAVA");
        book1.setScore(10.0);
        book1.setNumOfEvaluation(5);
        book1.setAuthor("A");
        book1.setPress("B");
        book1.setDate("2001-1-1");
        book1.setPrice("50.00元");
        bookList.add(book1);
        Book book2 = new Book();
        book2.setBookName("JAVA1");
        book2.setScore(9.0);
        book2.setNumOfEvaluation(7);
        book2.setAuthor("C");
        book2.setPress("D");
        book2.setDate("2002-1-1");
        book2.setPrice("51.00元");
        bookList.add(book2);
        Book book3 = new Book();
        book3.setBookName("JAVA2");
        book3.setScore(3.0);
        book3.setNumOfEvaluation(100);
        book3.setAuthor("E");
        book3.setPress("F");
        book3.setDate("2011-1-1");
        book3.setPrice("150.00元");
        bookList.add(book3);
        createExcel(rowNameList,excelName,bookList);
    }
}
