package com.example.hms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class Config {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.json"; // Pfad zur JSON-Datei

//    @Value("${json.file.path}")
//    private static Resource jsonFilePath;
    private int id;
    private String name;
    private int port;
    private int totalBeds;

    private InetAddress ipAddress;



    public int getTotalBeds() {
            return totalBeds;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getPort() {
        return port;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }



    public void setBedCount(int bedCount) {
        this.totalBeds = bedCount;
    }


    public static Config  readConfigFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON-Datei einlesen und in ein entsprechendes Objekt mappen
            return objectMapper.readValue(new File(CONFIG_FILE_PATH), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Config();
        }
    }
}



