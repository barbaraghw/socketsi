package sockk;

import java.io.*;
import java.net.*;

public class Sockett {
    public static void main(String[] args) {
        int port = 8082;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream is = socket.getInputStream();
                     BufferedInputStream bis = new BufferedInputStream(is);
                     FileOutputStream fos = new FileOutputStream("received_file.bin");
                     BufferedOutputStream bos = new BufferedOutputStream(fos)) {

                    System.out.println("Cliente conectado: " + socket.getInetAddress());

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Archivo recibido y guardado.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
