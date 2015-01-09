import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MultiCastSendUDP extends Thread {
	DatagramPacket packet;
	DatagramSocket socket;

	public MultiCastSendUDP(InetAddress ia, int port, String message)
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
		/* ici l'adresse vaut 224.0.0.1*/
		address = InetAddress.getByName("224.0.0.2");
		System.out.println("Adresse : " +address);
		/* ici le port vaut 7654 */
		port = 7654;
		System.out.println("port : " +port);
		System.out.println("message : " + args[2]);
		su = new SendUDP(address, port, args[2]);
	}
}
