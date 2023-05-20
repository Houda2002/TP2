package ma.enset.blocking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiCHATSERVERServer extends Thread{
    int clientsCount;
    public static void main(String[] args) {
        new MultiCHATSERVERServer().start();


    }

    @Override
    public void run() {
        System.out.println("The server is started using port=1234");


        try{

            ServerSocket serversocket = null;
            try {
                serversocket = new ServerSocket(1234);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            while (true) {
                Socket socket = serversocket.accept();
                ++clientsCount;

                new Conversation(socket).start();


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
    class Conversation extends Thread {
        private Socket socket;
        private int clientId;
        public Conversation(Socket socket){
            this.socket=socket;
            this.clientId=clientId;
        }

        @Override
        public void run() {
            try{
                InputStream is=socket.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);
                OutputStream os=socket.getOutputStream();
                PrintWriter pw=new PrintWriter(os,true);
                String ip=socket.getRemoteSocketAddress().toString();
                System.out.println("New Client cnx =>"+clientId+"IP="+ip);
                pw.println("Wlcm,your ID is =>"+clientId);
                String request;
                while ((request=br.readLine())!=null){

                    System.out.println("New Request =>IP="+ip+"Request="+request);
                    //broadcastMessage(request,this);


                    String response ="Size="+request.length();
                    pw.println(response);
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }}}



