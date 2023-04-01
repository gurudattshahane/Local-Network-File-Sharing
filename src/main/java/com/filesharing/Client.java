package com.filesharing;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        String ip = "localhost";
        int port = 5000;
        try(Socket socket = new Socket(ip, port)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Scanner s = new Scanner(System.in);
            System.out.println("Enter path of file (ex. path/to/file) : ");
            String filepath = s.nextLine();
            Util.sendFile(dataOutputStream, filepath);
            
            s.close();
            dataInputStream.close();
            dataOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}