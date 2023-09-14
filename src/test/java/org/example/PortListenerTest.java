package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PortListenerTest {
    private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(stdout));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPortListenerStartStop() {
        // This is just a basic test to illustrate the concept.
        // You should adapt it based on your actual PortListener functionality.

        // Create an instance of PortListener (assuming you have a constructor).
        PortListener portListener = new PortListener();

        // Define a list of port ranges you want to test.
        List<PortRange> portRanges = new ArrayList<>();
        portRanges.add(new PortRange(12345, 12349));
        portRanges.add(new PortRange(23456, 23459));

        // Start listening on the list of port ranges.
        portListener.startListening(portRanges);

        // In your actual PortListener, you should check that it is listening
        // on the ports within the ranges. Here, we'll just check if it printed
        // the expected message for each port.
        String expectedOutput = "Listening on port 12345\nListening on port 12346\nListening on port 12347\nListening on port 12348\nListening on port 12349\n" +
                "Listening on port 23456\nListening on port 23457\nListening on port 23458\nListening on port 23459\n";
        assertEquals(expectedOutput, stdout.toString());

        // Stop listening (in your actual PortListener, you should implement this method).
        portListener.stopListening();

        // Check if it printed the expected message for each port.
        expectedOutput += "Stopped listening on port 12345\nStopped listening on port 12346\nStopped listening on port 12347\nStopped listening on port 12348\nStopped listening on port 12349\n" +
                "Stopped listening on port 23456\nStopped listening on port 23457\nStopped listening on port 23458\nStopped listening on port 23459\n";
        assertEquals(expectedOutput, stdout.toString());
    }
}
