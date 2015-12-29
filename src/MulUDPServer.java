import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by hailstone01 on 12/29/15.
 */
public class MulUDPServer {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket receiveDp;
        final int PORT = 10012;
        byte[] b = new byte[1024];
        receiveDp = new DatagramPacket(b, b.length);

        try {
            ds = new DatagramSocket(PORT);
            System.out.println("server side is launched");
            while (true) {
                ds.receive(receiveDp);
                new LogicThread(ds, receiveDp);
            }
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
