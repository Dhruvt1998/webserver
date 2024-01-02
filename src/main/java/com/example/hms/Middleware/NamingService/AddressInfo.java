package com.example.hms.Middleware.NamingService;



public class AddressInfo {

    private String host;
    private int port;



    public AddressInfo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
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