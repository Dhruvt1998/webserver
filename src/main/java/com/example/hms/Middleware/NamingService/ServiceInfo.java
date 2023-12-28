package com.example.hms.Middleware.NamingService;



public class ServiceInfo {

    private String host;
    private int port;
    private IService service; // Interface für Ihren Service


    public ServiceInfo(String host, int port, IService service) {
        this.host = host;
        this.port = port;
        this.service = service;
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