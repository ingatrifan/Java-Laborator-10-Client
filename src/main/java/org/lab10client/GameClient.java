package org.lab10client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    GameClient(){
        startConexion();
    }
    public void startConexion(){
        try  {
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host.getHostName(), 6868);
            System.out.println(socket.getInetAddress());
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);
            startGame(reader,writer);
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startGame(BufferedReader reader, PrintWriter writer) throws IOException {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Waitng a command...\n");
            String command = scanner.nextLine();
            if(command.equals("exit")) break;
            writer.println(command);
            String response = reader.readLine();
            System.out.println(response);
        }
    }
}
