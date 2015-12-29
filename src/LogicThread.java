import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by hailstone01 on 12/29/15.
 */
public class LogicThread extends Thread {
    DatagramSocket ds;
    DatagramPacket dp;

    public LogicThread(DatagramSocket ds, DatagramPacket dp) {
        this.ds = ds;
        this.dp = dp;
        start();
    }

    @Override
    public void run() {
        byte[] data = dp.getData();
        int len = dp.getLength();
        InetAddress clientAddress = dp.getAddress();
        int clientPort = dp.getPort();
        System.out.println("the client IP: " + clientAddress.getHostAddress());
        System.out.println("the client port: " + clientPort);
        System.out.println("the content from client side: " + new String(data, 0, len));
        byte[] b = "OK".getBytes();
        DatagramPacket sendDp = new DatagramPacket(b, b.length, clientAddress, clientPort);

        try {
            ds.send(sendDp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
