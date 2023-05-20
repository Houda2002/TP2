//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ma.enset.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public SimpleClient() {
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1634);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number :");
        int nb = scanner.nextInt();
        os.write(nb);
        int response = is.read();
        System.out.println("Response=" + response);
    }
}
