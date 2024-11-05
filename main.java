import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // create a server socket on port number 9090
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server is running and waiting for client connection...");
        while(true) {
            // Accept incoming client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Setup input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //String req = new String(clientSocket.getInputStream().readAllBytes(),StandardCharsets.UTF_8);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String req = in.readLine();
            // Read message from client
            String message = in.lines().parallel().collect(Collectors.joining("\n"));

            System.out.println("Client says: " + message.toString());
            String response = "HTTP/1.1 200 OK\n" +
                    "Date: Mon, 27 Jul 2009 12:28:53 GMT\n" +
                    "Server: Apache/2.2.14 (Win32)\n" +
                    "Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT\n" +
                    "Content-Length: 88\n" +
                    "Content-Type: text/html\n" +
                    "Connection: Closed\n\n" +
                    "<html>\n" +
                    "<body>\n" +
                    "<h1>Hello, World!</h1>\n" +
                    "</body>\n" +
                    "</html>";
            // Send response to the client
            out.println(response);

            // Close the client socket
            clientSocket.close();
        }
        // Close the server socket
        //serverSocket.close();
    }
}
