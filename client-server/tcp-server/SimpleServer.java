import java.net.*;
import java.io.*;

class SimpleServer {
  public static void main(String[] args) throws IOException {
    System.out.println("Simple Server App");
    ServerSocket serverSocket = new ServerSocket(5000);
    System.out.println("Server Started, waiting for client...");

    Socket socket = serverSocket.accept();
    System.out.println("Client Connected");

    BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

    String message = bufferedReader.readLine();
    System.out.println("Received: " + message);
    printWriter.println("Hello Client! You sent: " + message);

    socket.close();
    serverSocket.close();
  }
}