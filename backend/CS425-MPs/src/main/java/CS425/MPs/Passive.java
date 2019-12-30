package CS425.MPs;

// Java libraries
import java.net.*;

public class Passive {
  public static void main(String[] args) throws SocketException {
    if(args.length != 2) {
      System.err.println("Usage: java Passive <process_number>");
      System.exit(1);
    }

    int process_number = Integer.parseInt(args[1]);
    String ipv4_address = System.getenv(String.format("PROCESS_NUMBER_%d_IPV4_ADDRESS", process_number));
    int portNumber = Integer.parseInt(System.getenv(String.format("PROCESS_NUMBER_%d_PORT_NUMBER", process_number)));

    DatagramSocket socket = new DatagramSocket(portNumber);
    /*
    while(true) {
      byte[] buffer = new byte[1024];
      DatagramPacket packet = new DatagramPacket(buffer, 1024);
      socket.receive(packet);
      String query = new String(packet.getData(), 0, packet.getLength());

      Runtime.exec(query);
    }
    */
  }
}
