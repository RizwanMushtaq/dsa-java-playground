package clientServerTcpServer;

import java.net.*;
import java.io.*;

class SimpleClient {
  public static void main(String[] args) throws Exception {
    System.out.println("Simple Client App");
    Socket socket = new Socket("localhost", 5000);

    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(socket.getInputStream()));

    printWriter.println("Hello Server");
    String message = bufferedReader.readLine();

    System.out.println("Response: " + message);

    socket.close();
  }
}
