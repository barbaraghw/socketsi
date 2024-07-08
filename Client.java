package sockk;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8082;
        String filePath = "C:\\Users\\migue\\Pictures\\No Mans Sky\\Jun-5@13.26.27.bin"; // Reemplaza con la ruta a tu archivo binario

        try (Socket socket = new Socket(host, port);
             FileInputStream fis = new FileInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = socket.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(os)) {

            System.out.println("Conectado al servidor.");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();

            System.out.println("Archivo enviado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
