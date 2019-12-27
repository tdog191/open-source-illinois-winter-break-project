// Java libraries
import java.util.*;
import java.io.*;

public class Wrapper {
  public static void main(String[] args) throws Exception {
    if(args.length != 3) {
      System.err.println("Usage: java Wrapper <num_processes> <queries_filename> <output_filename>");
      System.exit(1);
    }

    int numProcesses = Integer.parseInt(args[1]);

    if(numProcesses < 2) {
      System.err.println("ERROR: There must be at least 2 processes in the distributed system.");
      System.exit(1);
    }

    String queriesFilename = args[2];
    String outputFilename = args[3];

    Process[] processes = new Process[numProcesses];
    int queryProcessIndex = new Random().nextInt(numProcesses);

    String ipv4_address = "127.0.0.1";
    int basePortNumber = 2000;
    ProcessBuilder pb = new ProcessBuilder();
    Map<String, String> env = pb.environment();
    env.put("NUM_PROCESSES", String.valueOf(numProcesses));

    // Initialize query and passive processes
    for(int i=0; i<numProcesses; i++) {
      int curProcessNumber = i + 1;
      int portNumber = basePortNumber + curProcessNumber;
      env.put(String.format("PROCESS_NUMBER_%d_IPV4_ADDRESS", curProcessNumber), ipv4_address);
      env.put(String.format("PROCESS_NUMBER_%d_PORT_NUMBER", curProcessNumber), String.valueOf(portNumber));

      if(i == queryProcessIndex) {
        //pb.command(queryCommand, ipv4_address, String.valueOf(portNumber), String.valueOf(curProcessNumber));
        //pb.command(queryCommand, String.valueOf(curProcessNumber));
        pb.command("java", "Queryer", String.valueOf(curProcessNumber), queriesFilename, outputFilename);
        processes[i] = pb.start();
      } else {
        //pb.command(passiveCommand, ipv4_address, String.valueOf(portNumber), String.valueOf(curProcessNumber));
        //pb.command(passiveCommand, String.valueOf(curProcessNumber));
        pb.command("java", "Passive", String.valueOf(curProcessNumber));
        processes[i] = pb.start();
      }
    }

    /*for(int i=0; i<numProcesses; i++) {
      if(i == queryProcessIndex) {
        continue;
      }

      // Confirm passive process is set up with pipe
      try(BufferedReader process_output_reader = new BufferedReader(new InputStreamReader(processes[i].getInputStream()))) {
        while((String line = process_output_reader.readLine()) != null) {
          if(line == String.valueOf(i)) {

          }
        }
      }
    }*/

    // Wait for queries to complete
    int queryExitStatus = processes[queryProcessIndex].waitFor();
    if(queryExitStatus != 0) {
      System.err.println("ERROR: The query process did not terminate successfully.");
    }

    // Cleanup the distributed system (namely all passive processes)
    for(int i=0; i<numProcesses; i++) {
      if(i == queryProcessIndex) {
        continue;
      } else {
        processes[i].destroy();
        int exitStatus = processes[i].waitFor();
        if(exitStatus != 0) {
          System.err.println(String.format("ERROR: Process #%d did not terminate successfully.", i));
        }
      }
    }
  }
}
