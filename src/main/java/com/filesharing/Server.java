package com.filesharing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        int port = 5000;
        // Localhost ip address
        String ip;  
        try(ServerSocket serverSocket = new ServerSocket(port)){
            ip = InetAddress.getLocalHost().getHostAddress();
            
            System.out.println("Listening on "+ ip + ":" + port);
            
            Socket clientSocket = serverSocket.accept();
            String clientIpAdress = clientSocket.getRemoteSocketAddress().toString().replace("/","");
            System.out.println(clientIpAdress+" connected.");
            
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            Util.receiveFile(dataInputStream);

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    
}