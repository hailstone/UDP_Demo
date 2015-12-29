import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by hailstone01 on 12/29/15.
 */
public class SimpleUDPServer {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket sendDp;
        DatagramPacket receiveDp;
        final int PORT = 10010;

        try {
            ds = new DatagramSocket(PORT);
            System.out.println("server is launched");
            byte[] b = new byte[1024];
            receiveDp = new DatagramPacket(b, b.length);
            ds.receive(receiveDp);
            InetAddress clientIP = receiveDp.getAddress();
            int clientPort = receiveDp.getPort();
            byte[] data = receiveDp.getData();
            int len = receiveDp.getLength();
            System.out.println("the client IP: " + clientIP.getHostAddress());
            System.out.println("the client port: " + clientPort);
            System.out.println("the content from client: " + new String(data, 0, len));

            String response = "OK";
            byte[] bData = response.getBytes();
            sendDp = new DatagramPacket(bData, bData.length, clientIP, clientPort);
            ds.send(sendDp);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert ds != null;
            ds.close();
        }
    }
}
