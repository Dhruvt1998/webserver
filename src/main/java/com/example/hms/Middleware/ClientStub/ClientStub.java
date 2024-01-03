package com.example.hms.Middleware.ClientStub;


import com.example.hms.Middleware.NamingService.AddressInfo;
import com.example.hms.Middleware.NamingService.INamingService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import java.lang.reflect.Parameter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
@Component
public class ClientStub implements IClientStub {
    private JSONObject json;
    private Socket clientSocket;

    @Autowired
    private INamingService namingService;


    //TODO: Parameter aendern!
    public ClientStub(@Value("${serviceName}")String serviceName) throws IOException {
        AddressInfo addressInfo = namingService.lookUp(serviceName);
        this.clientSocket = new Socket(addressInfo.getHost(), addressInfo.getPort());
    }


    public void invoke(Method methodenAufruf, Object... args) {
        marshall(methodenAufruf, args);
        sendAndReceive();
    }


    @Override
    public void marshall(Method methodAufruf, Object... args) {
        this.json = new JSONObject();
        json.put("jsonrpc", "2.0"); // Hinzufügen der JSON-RPC-Version
        json.put("methodName", methodAufruf.getName());
        JSONArray jsonArgs = new JSONArray();
        for (Object arg : args) {
            jsonArgs.put(arg.toString()); // Hier müssen Sie eventuell eine bessere Serialisierung implementieren
        }
        json.put("parameters", jsonArgs);
        // Generierung einer eindeutigen ID für den Aufruf
        json.put("id", generateUniqueId());
    }

    private int generateUniqueId() {
        // Implementieren Sie hier Ihre Logik zur Generierung einer eindeutigen ID
        return new Random().nextInt(Integer.MAX_VALUE);
    }
    @Override
    public void sendAndReceive() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // Senden der Anfrage
            out.println(json.toString());

            // Warten auf die Antwort
            String response = in.readLine();
            System.out.println("Response received: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
