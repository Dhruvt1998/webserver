package com.example.hms.Middleware.NamingService;

import java.net.InetAddress;

public class AddressInfo {

    private InetAddress host;
    private int port;



    public AddressInfo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public InetAddress getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    // toString method for easy debugging and logging
    @Override
    public String toString() {
        return "ServiceAddress{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}