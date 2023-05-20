package ma.enset.blocking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadChatServer extends Thread{
    private List<Conversation> conversations=new ArrayList<>();

    int clientsCount;
    public static void main(String[] args) {
        new MultiCHATSERVERServer().start();


    }

    @Override
    public void run() {
        System.out.println("The server is started using port=1234");




        try{

            ServerSocket serversocket = new ServerSocket(1234);

            while (true) {
                Socket socket = serversocket.accept();
                ++clientsCount;

                Conversation conversation =new Conversation(socket,clientsCount);
                conversations.add(conversation);
                conversation.start();


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

        public Conversation(Socket socket, int clientsCount) {

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


                    broadcastMessage(request);
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }}public void broadcastMessage (String message){

        try {
            for(Conversation conversation:conversations){Socket socket=conversation.socket;

                OutputStream outputStream=socket.getOutputStream();
                PrintWriter printWriter=new PrintWriter(outputStream,true);
                printWriter.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}



