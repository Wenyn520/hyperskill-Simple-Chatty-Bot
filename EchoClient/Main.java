package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

//client application (TCP/IP)
public class Main {

    public static void main(String[] args) {
        //Socket() create a new Socket and connect it to specified host on specified port number
        //this port number 5000 is same with the ServerSocket in EchoServer project
        //pass in localhost which means both host and client are on same this machine,
        //or pass in IP address "127.0.0.1"
        try (Socket socket = new Socket("localhost", 5000)) {
            //set timeout which Socket will be blocked, if timeout expires, will throw SocketTimeoutException
            //so client will not be blocked, and not hanging forever if server not responding back
            socket.setSoTimeout(5000);
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);
            //prompt user input
            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;
            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();
                //write the string to socket
                stringToEcho.println(echoString);
                if (!echoString.equals("exit")) {
                    //read from Socket, which ServerSocket has written to Socket
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while (!echoString.equals("exit"));
        } catch (SocketTimeoutException e) { //if timeout will throw this exception
            //when timeout will print out message: Socket timeout: Read timed out
            System.out.println("Socket timeout: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
