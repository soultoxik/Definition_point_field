package com.github.desiresdesigner;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author m-erofeev
 * @since 11.12.13
 */
public class Server {
  private final int port;
  private ServerSocket socket;

  public Server(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    socket = new ServerSocket(port);
    listener();
  }

  private void listener() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          final Socket clientSocket = socket.accept();
          final BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          final PrintWriter outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
          final String input = readToString(inputStream);
          outputStream.println("ping");
          String ret = processInput(input);
          outputStream.println(ret);
          clientSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  private String readToString(BufferedReader bufferedReader) throws IOException {
    final StringBuilder inputStringBuilder = new StringBuilder();
    String s;
    while ((s = bufferedReader.readLine()) != null) {
      inputStringBuilder.append(s).append("\n");
    }
    return inputStringBuilder.toString();
  }

  private String processInput(String input) {
    return input.toUpperCase();
  }
}
