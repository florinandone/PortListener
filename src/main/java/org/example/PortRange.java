package org.example;

class PortRange {
    private final int startPort;
    private final int endPort;

    public PortRange(int startPort, int endPort) {
        this.startPort = startPort;
        this.endPort = endPort;
    }

    public int getStartPort() {
        return startPort;
    }

    public int getEndPort() {
        return endPort;
    }
}