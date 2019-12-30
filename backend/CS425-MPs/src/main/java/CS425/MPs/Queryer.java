package CS425.MPs;

// Java libraries
import java.io.*;
import java.net.*;
import java.util.*;

public class Queryer {
  public static void main(String[] args) throws IOException, FileNotFoundException, SocketException {
    if(args.length != 4) {
      System.err.println("Usage: java Queryer <process_number> <queries_filename> <output_filename>");
      System.exit(1);
    }

    Map<String, String> env = System.getenv();

    int processNumber = Integer.parseInt(args[1]);
    int numProcesses = Integer.parseInt(env.get("NUM_PROCESSES"));
    String queriesFilename = args[2];
    String outputFilename = args[3];
    String logFilename = String.format("machine.%d.log", processNumber);

    String ipv4_address = env.get(String.format("PROCESS_NUMBER_%d_IPV4_ADDRESS", processNumber));
    int portNumber = Integer.parseInt(env.get(String.format("PROCESS_NUMBER_%d_PORT_NUMBER", processNumber)));

    DatagramSocket socket = new DatagramSocket(portNumber);
    BufferedReader queryFileReader = new BufferedReader(new FileReader(queriesFilename));
    BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFilename));
    String query;
    
    /*
    while((query = queryFileReader.readLine()) != null) {
      // Run query within query process
      // TODO: Specify log filename to query
      Runtime.exec(query);

      // Send query to every other process
      for(int i=0; i<numProcesses; i++) {
        int curProcessNumber = i + 1;

        if(i != processNumber) {
          String cur_ipv4_address = env.get(String.format("PROCESS_NUMBER_%d_IPV4_ADDRESS", curProcessNumber));
          int curPortNumber = Integer.parseInt(env.get(String.format("PROCESS_NUMBER_%d_PORT_NUMBER", curProcessNumber)));
          socket.send(query.getBytes(), query.length(), cur_ipv4_address, curPortNumber);
        }
      }
    }
    */

    queryFileReader.close();
    outputFileWriter.close();
  }
}
