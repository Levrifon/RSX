import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendUDP {
	DatagramPacket packet;
	DatagramSocket socket;

	public SendUDP(InetAddress ia, int port, String message)
			throws SocketException {
		byte[] msg = new byte[1024];
		msg = message.getBytes();
		this.packet = new DatagramPacket(msg, msg.length,ia,port);
		this.socket = new DatagramSocket();
		try {
			socket.send(packet);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws NumberFormatException,IOException {
		SendUDP su;
		InetAddress address;
		ReceiveUDP ru;
		int port;
		address = InetAddress.getByName((args[1]));
		System.out.println("Adresse : " +address);
		port = Integer.parseInt(args[0]);
		System.out.println("port : " +port);
		System.out.println("message : " + args[2]);
		su = new SendUDP(address, port, args[2]);
	}
}
