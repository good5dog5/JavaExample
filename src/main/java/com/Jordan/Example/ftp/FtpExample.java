package com.Jordan.Example.ftp;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FtpExample {

    public static void main(String[] argv) {
        System.out.println("start ftp connection");
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("192.168.66.241", 21);

            boolean login = ftpClient.login("ftpuser", "synergiesai");
            System.out.println("ftp login: " + login);
            //上傳
            ftpClient.makeDirectory("/hk_esar/");
            String[] fileNames = new String[]{"OBS_HK_SCORE_2019-12-01.dat", "OBS_HK_SCORE_2019-12-02.dat", "OBS_HK_SCORE_2019-12-03.dat"};
            ftpClient.changeWorkingDirectory("/hk_esar/");
            String path = "/Users/shiyongzhe/Documents/Shark/java/JavaExample/file/";
            for(String fileName: fileNames) {
                String fileResPath = path + fileName;
                File file = new File(fileResPath);
                FileInputStream fileInputStream = new FileInputStream(file);
                String fileDesPath = "/hk_esar/" + fileName;
                ftpClient.storeFile(fileDesPath, fileInputStream);
                fileInputStream.close();
            }


            //下載
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            FTPFile[] ftpFiles = ftpClient.listFiles();

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -5);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            long before5DaysTime = calendar.getTimeInMillis();
            System.out.println("before5DaysTime: " + before5DaysTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for(FTPFile file: ftpFiles) {
                try {
                    String name = file.getName();
                    System.out.println("name: " + name);
                    name = name.replace("OBS_HK_SCORE_", "");
                    System.out.println("name: " + name);
                    Date date = simpleDateFormat.parse(name);
                    long dataUploadTime = date.getTime();
                    System.out.println("dataUploadTime: " + dataUploadTime);
                    if(dataUploadTime < before5DaysTime) {
                        ftpClient.deleteFile("/hk_esar/" + file.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ftpFiles = ftpClient.listFiles();
            for(FTPFile file: ftpFiles) {
                String name = file.getName();
                System.out.println("name: " + name);
            }

//            System.out.println("ftpFileSize: " + ftpFiles.length);
//            for(FTPFile ftpFile: ftpFiles) {
//                System.out.println("ftpFile: " + ftpFile.getName());
//                File localFile = new File("download/"  + ftpFile.getName());
//                OutputStream fileOutputStream = new FileOutputStream(localFile);
//                System.out.println("download ftp file");
//                ftpClient.retrieveFile(ftpFile.getName(), fileOutputStream);
//                fileOutputStream.close();
//            }
//            System.out.println("ftp logout");
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void uploadFile() {

    }
}
