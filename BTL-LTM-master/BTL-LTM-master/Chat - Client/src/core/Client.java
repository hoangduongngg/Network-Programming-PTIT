package core;

import controller.UserController;
import entity.Response;
import flag.ActionFlags;
import flag.ResultFlags;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Observable {

    private static final String serverIp = "26.139.8.48";   //pass:12345678
    private static final int port = 11000;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    public UserController userController;
    private Thread thread;

    public Client(Observer obs) throws UnknownHostException {
        this.addObserver(obs);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }


    public void dispose() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (thread != null) {
            thread.stop();
        }
    }

    public boolean startConnect() {
        try {
            socket = new Socket(serverIp, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            userController = new UserController(objectOutputStream);
            startThreadWaitResult();
            return true;
        } catch (IOException ex) {
            Response response = new Response(ActionFlags.ERROR, ResultFlags.ERROR ,"Không thể kết nối tới server" , ex);
            notifyObservers(response);
            return false;
        }
    }

    private void startThreadWaitResult() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        read();
                    }
                } catch (IOException ex) {
                    Response response = new Response(ActionFlags.ERROR, ResultFlags.ERROR ,"Kết nối tới server có lỗi" , ex);
                    notifyObservers(response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    private void read() throws IOException, ClassNotFoundException {
        Response response = (Response) objectInputStream.readObject();
        notifyObservers(response);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }

}
