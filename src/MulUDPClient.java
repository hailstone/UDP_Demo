import java.io.IOException;
import java.net.*;
import java.util.Date;

/**
 * Created by hailstone01 on 12/29/15.
 */
public class MulUDPClient {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket sendDp;
        DatagramPacket receiveDp;
        String serverHost = "127.0.0.1";
        int serverPort = 10012;

        try {
            ds = new DatagramSocket();
            InetAddress address = InetAddress.getByName(serverHost);
            byte[] b = new byte[1024];
            receiveDp = new DatagramPacket(b, b.length);
            System.out.println("client side is ready");

            for (int i = 0; i < 30; i++) {
                Date d = new Date();
                String content = d.toString();
                byte[] data = content.getBytes();
                sendDp = new DatagramPacket(data, data.length, address, serverPort);
                ds.send(sendDp);
                Thread.sleep(100);
                ds.receive(receiveDp);
                byte[] response = receiveDp.getData();
                int len = receiveDp.getLength();
                String s = new String(response, 0, len);
                System.out.println("the feedback from server: " + s);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            assert ds != null;
            ds.close();
        }
    }
}
