//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma.enset.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public SimpleServer() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1634);
        System.out.println("i m waiting new cnx");
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        System.out.println("i am wating data");
        System.out.println("i am waiting respond");
        int nb = is.read();
        int res = nb * 23;
        os.write(res);
        socket.close();
    }
}
