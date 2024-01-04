package com.example.hms.Middleware.ApplicationStub;

import java.net.InetAddress;

public class HospitalInfo {

    private int port;

    private InetAddress host;

    private String name;

    private int availableBeds;

    private int totalBeds;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getHost() {
        return host;
    }

    public void setHost(InetAddress host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public HospitalInfo(int port, InetAddress host, String name, int availableBeds, int totalBeds) {
        this.port = port;
        this.host = host;
        this.name = name;
        this.availableBeds = availableBeds;
        this.totalBeds = totalBeds;
    }


}
