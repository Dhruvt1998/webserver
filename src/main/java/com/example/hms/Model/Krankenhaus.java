package com.example.hms.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
//import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.net.InetAddress;
//import org.springframework.data.annotation.Id;

@Entity
@Table(name = "Krankenhaus")
public  class Krankenhaus implements IModelKrankenhaus{

    private static Krankenhaus instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int totalBeds;
    private int availableBeds;
    private int port;
    private InetAddress ipAddress;

    private Krankenhaus() {
        // Private Konstruktor, um Instanzierung außerhalb dieser Klasse zu verhindern
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static Krankenhaus getInstance() {
        if (instance == null) {
            instance = new Krankenhaus();
        }
        return instance;
    }



    @Override
    public int getTotalBeds() {
        return totalBeds;
    }

    @Override
    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    @Override
    public int getAvailableBeds() {
        return availableBeds;
    }

    @Override
    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setName(String name) {
        this.name = name;
    }
}




