package Client;

import java.net.*;
import java.io.*;

public class Client {
    private int serverPort = 1488, intCommand;
    private String address = "127.0.0.1";
    private InputStream sin;
    private OutputStream sout;
    private DataInputStream in;
    private DataOutputStream out;
    private String stringCommand = "";



    public Client() {
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public String ReadUTF() {
        try {
            stringCommand = in.readUTF();
            System.out.println(stringCommand);
            return stringCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int ReadInt() {
        try {
            intCommand = in.readInt();
            System.out.println(intCommand);
            return intCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void SendUTF(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendInt(int i) {
        try {
            out.writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}