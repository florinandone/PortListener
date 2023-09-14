package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PortListener {
    private static List<ServerSocket> sockets = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java PortListener <intervalSec> <port-range-1> <port-range-2> ...");
            return;
        }

        int intervalSec = Integer.parseInt(args[0]);
        List<PortRange> portRanges = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            String[] rangeParts = args[i].split("-");
            if (rangeParts.length != 2) {
                System.out.println("Invalid port range format: " + args[i]);
                return;
            }

            int startPort = Integer.parseInt(rangeParts[0]);
            int endPort = Integer.parseInt(rangeParts[1]);
            portRanges.add(new PortRange(startPort, endPort));
        }

        startListening(portRanges);

        System.out.println("Wait intervalSec:" + intervalSec);
        // Schedule a timer task to stop listening after the specified interval
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopListening();
                timer.cancel();
            }
        }, intervalSec * 1000);
    }

     static void startListening(List<PortRange> portRanges) {
        int failCount=0;
        int okCount=0;
        for (PortRange range : portRanges) {
            for (int port = range.getStartPort(); port <= range.getEndPort(); port++) {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    sockets.add(serverSocket);
                    okCount++;
                } catch (IOException e) {
                    failCount++;
                }
            }
        }
        System.out.println("Listen " +okCount + ", fail:" + failCount);
    }

     static void stopListening() {
        for (ServerSocket serverSocket : sockets) {
            try {
                serverSocket.close();
                System.out.println("Stopped listening on port " + serverSocket.getLocalPort());
            } catch (IOException e) {
                System.err.println("Error stopping listening on port " + serverSocket.getLocalPort() + ": " + e.getMessage());
            }
        }
    }


}
