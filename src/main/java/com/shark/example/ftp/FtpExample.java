package com.shark.example.ftp;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FtpExample {

    public static void main(String[] argv) {
        System.out.println("start ftp connection");
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("localhost", 21);
            System.out.println("ftp connect");
            boolean login = ftpClient.login("user", "password");
            System.out.println("ftp login: " + login);
            //上傳
            ftpClient.makeDirectory("/destination");
            ftpClient.changeWorkingDirectory("/destination");
            String[] fileNames = new String[]{"Model_Version.txt", "Model_Version.txt"};
            String path = "file";
            for(String fileName: fileNames) {
                String fileResPath = path + fileName;
                File file = new File(fileResPath);
                FileInputStream fileInputStream = new FileInputStream(file);
                String fileDesPath = "/destination/" + fileName;
                ftpClient.storeFile(fileDesPath, fileInputStream);
                fileInputStream.close();
            }


            //下載
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            System.out.println("ftpFileSize: " + ftpFiles.length);
            for(FTPFile ftpFile: ftpFiles) {
                System.out.println("ftpFile: " + ftpFile.getName());
                File localFile = new File("download/"  + ftpFile.getName());
                OutputStream fileOutputStream = new FileOutputStream(localFile);
                System.out.println("download ftp file");
                ftpClient.retrieveFile(ftpFile.getName(), fileOutputStream);
                fileOutputStream.close();
            }
            System.out.println("ftp logout");
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void uploadFile() {

    }
}
