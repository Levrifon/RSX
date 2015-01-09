import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ReceiveUDP extends Thread{
	MulticastSocket ds;
	DatagramPacket dp;

	public ReceiveUDP(int port) throws IOException {
		this.ds = new MulticastSocket(port);
		ds.joinGroup(InetAddress.getByName("224.0.0.2"));

		

	}
	@Override
	public void run() {
		try {
			receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void receive() throws IOException {
		byte[] buff;
		buff = new byte[1024];
		dp = new DatagramPacket(buff, buff.length);
		ds.receive(dp);
		System.out.println("Message reçu : " + new String(buff) + " de la part de : " + dp.getSocketAddress());
	}

	public static void main(String[] args) throws NumberFormatException,IOException {
		ReceiveUDP ru;
		int port;
		port = Integer.parseInt(args[0]);
		ru = new ReceiveUDP(port);
		while (true) {
			ru.receive();
		}
	}
}
