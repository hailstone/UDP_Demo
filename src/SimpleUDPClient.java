import java.io.IOException;
import java.net.*;
import java.util.Date;

/**
 * Created by hailstone01 on 12/29/15.
 */
public class SimpleUDPClient {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket sendDp;
        DatagramPacket receiveDp;
        String serverHost = "127.0.0.1";
        int serverPort = 10010;

        try {
            ds = new DatagramSocket();
            Date d = new Date();
            String content = d.toString();
            byte[] data = content.getBytes();
            InetAddress address = InetAddress.getByName(serverHost);
            sendDp = new DatagramPacket(data, data.length, address, serverPort);
            ds.send(sendDp);
            System.out.println("just sent the data to server");

            byte[] b = new byte[1024];
            receiveDp = new DatagramPacket(b, b.length);
            ds.receive(receiveDp);
            byte[] response = receiveDp.getData();
            int len = receiveDp.getLength();
            String s = new String(response, 0, len);
            System.out.println("the feedback from server is: " + s);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert ds != null;
            ds.close();
        }
    }
}
