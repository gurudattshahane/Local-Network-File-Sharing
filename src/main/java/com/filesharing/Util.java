package com.filesharing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// File sending and receiving utility 
public class Util {
    
    public static void sendFile(DataOutputStream dataOutputStream ,String path) throws Exception{
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        // send file size
        dataOutputStream.writeLong(file.length());  
        // send file name
        dataOutputStream.writeUTF(file.getName());

        // break file into chunks
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
        System.out.println(file.getName()+" sent successfully.");
    }

    public static void receiveFile(DataInputStream dataInputStream) throws Exception{
        int bytes = 0;
        // read file size
        long size = dataInputStream.readLong();    
        // read file name
        String filename = dataInputStream.readUTF();

        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
        System.out.println(filename+" received successfully.");
    }
    
}
