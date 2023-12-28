package com.example.hms.Middleware.ClientStub;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import java.lang.reflect.Parameter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class ClientStub implements IClientStub {
    private JSONObject json;
    private Socket clientSocket;

    public ClientStub(String host, int port) throws IOException {
        this.clientSocket = new Socket(host,port);
    }


    public void invoke(Method methodenAufruf, Object... args) {
        marshall(methodenAufruf, args);
        send();
    }


//    public void marshall2(Method methodAufruf) {
//        this.json = new JSONObject();
//        json.put("methodName",methodAufruf.getName());
//        JSONArray jsonArray = new JSONArray(methodAufruf.getParameters());
//        json.put("parameter",jsonArray);
//    }

    @Override
    public void marshall(Method methodAufruf, Object... args) {
        this.json = new JSONObject();
        json.put("methodName", methodAufruf.getName());
        JSONArray jsonArgs = new JSONArray();
        for (Object arg : args) {
            jsonArgs.put(arg.toString()); // Hier müssen Sie eventuell eine bessere Serialisierung implementieren
        }
        json.put("parameters", jsonArgs);
    }

    @Override
    public void send() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
