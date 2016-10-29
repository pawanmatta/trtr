/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.cloud.Controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.cloud.DAO.ReportsDAO;
import com.neu.cloud.DAO.UserDAO;
import com.neu.cloud.POJO.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author dishujindal
 */
public class FirstUseCaseController implements Controller {

    final static Logger logger = Logger.getLogger(FirstUseCaseController.class);
    
    UserDAO userDao = new UserDAO();
    ReportsDAO reportsDAO = new ReportsDAO();

    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = hsr.getSession();
        List<User> userList = new ArrayList<User>();

        try{
        List<ArrayList<User>> user1ReportList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setUserID(i);
            String password = "pass" + i;
            user.setPassword(password);
            String username = "user" + i;
            user.setUsername(username);
            String product = "product" + i;
            user.setProductName(product);
            System.out.println("user" + i + " " + "created");
            userList.add(user);
            
        }
        
        userDao.addUser(userList);
        session.setAttribute("usernameList",userList);
        user1ReportList = reportsDAO.generateUseCaseReport(2);
        generateReports(user1ReportList);
        
        if(logger.isInfoEnabled()){
            logger.info("This is use case 1 button:");
        }
        if(logger.isDebugEnabled()){
            logger.debug("This is use case 1 button:");
        }
        mv.setViewName("results");
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        return mv;
    }

//    public void generateReports( List<ArrayList<User>> user1ReportList) throws DocumentException, FileNotFoundException {
//
//        Date date;
//        for (ArrayList<User> users : user1ReportList) {
//            date = new Date();
//            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
////            String path = System.getProperty("user.dir") + "\\reports\\";
//            String path = System.getProperty("user.dir") + "//";
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "ITextTest" + date.getTime() + ".pdf"));
//
//            document.open();
//
//            Table table = new Table(4);
//            table.addCell("userID");
//            table.addCell("username");
//            table.addCell("password");
//            table.addCell("productName");
//
//            for (User u : users) {
//                table.addCell(String.valueOf(u.getUserID()));
//                table.addCell(u.getUsername());
//                table.addCell(u.getPassword());
//                table.addCell(u.getProductName());
//            }
//
//            document.add(table);
//            document.close();
//        }
//    }
    
   public void generateReports(List<ArrayList<User>> user1ReportList) throws DocumentException, FileNotFoundException {
        Date date;
        String dateForFolder = String.valueOf(new Date());
        for (ArrayList<User> users : user1ReportList) {
            date = new Date();
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
//            String path = System.getProperty("user.dir") + "\\reports\\";
            String path = System.getProperty("user.dir") + "//";
            String fileName = "ITextTest" + date.getTime() + ".pdf";
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "ITextTest" + date.getTime() + ".pdf"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + fileName));
            document.open();
            Table table = new Table(4);
            table.addCell("userID");
            table.addCell("username");
            table.addCell("password");
            table.addCell("productName");
            for (User u : users) {
                table.addCell(String.valueOf(u.getUserID()));
                table.addCell(u.getUsername());
                table.addCell(u.getPassword());
                table.addCell(u.getProductName());
            }
            document.add(table);
            document.close();
            
            uploadInS3(path, fileName, dateForFolder);
            
            File file = new File(path + fileName);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        }
    }
    
    private void uploadInS3(String uploadFilePath, String uploadFileName, String dateForFolder) {
        String bucketName = "reports-sppard";
        String keyName = "UseCase1-"+dateForFolder+"/"+uploadFileName;
        AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials("AKIAJ2E67YVFQ5PZSWQA", "xiVuejpUofGonrsiy2owvu/wgeNKq5nYjxYVC0ma"));
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(uploadFilePath+uploadFileName);
            s3client.putObject(new PutObjectRequest(
                    bucketName, keyName, file));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which "
                    + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response"
                    + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which "
                    + "means the client encountered "
                    + "an internal error while trying to "
                    + "communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}
