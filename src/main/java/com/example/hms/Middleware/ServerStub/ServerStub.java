package com.example.hms.Middleware.ServerStub;

import com.example.hms.Middleware.ClientStub.ClientStub;
import com.example.hms.Service.KrankenhausService;
import com.example.hms.repository.KrankenhausRepository;
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
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class ServerStub implements IServerStub{
    private JSONObject jsonObject;
    private ServerSocket serverSocket;
    private int requestId;


    public ServerStub(@Value("${serverstub.port}") int port, ApplicationStubCallee applicationStubCallee) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.applicationStubCallee = applicationStubCallee;
    }

    @Override
    public void register(String methode){
        System.out.println("Methode registriert: " + methode);

    }

    @Override
    public void unmarshall (String json){
        this.jsonObject = new JSONObject(json);
        this.requestId = (int) jsonObject.opt("id"); // Extrahieren der ID
    }



    public void recieve() {
        try (Socket client = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            String inputLine = in.readLine();
            //while ((inputLine = in.readLine()) != null) {
            unmarshall(inputLine);
            handleCall(client, jsonObject.getString("methodName"), jsonObject.getJSONArray("parameters"));
            System.out.println("Client ist close?: "+client.isClosed());
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void handleCall(Socket client, String methodName, JSONArray parameters) {
        System.out.println("Handling method call: " + methodName + " with parameter: " + parameters);

        try {
            // Die Logik zum Aufrufen der entsprechenden Methode im ApplicationStubCallee
            switch (methodName) {
                case "getAvailableBeds":
                    // Aufruf der Methode getAvailableBedsAsync und Behandlung des CompletableFuture-Ergebnisses
                    applicationStubCallee.getAvailableBedsAsync().thenAccept(availableBeds -> {
                        sendResponse(client, availableBeds, requestId);
                    }).exceptionally(e -> {
                        sendErrorResponse(client, e.getMessage(), requestId);
                        return null;
                    });
                    break;
                case "setAvailableBeds":
                    // Aufruf der Methode setAvailableBedsAsync und Behandlung des CompletableFuture-Ergebnisses
                    int beds = parameters.getInt(0); // Annahme, dass das erste Element ein Integer ist
                    applicationStubCallee.setAvailableBedsAsync(beds).thenRun(() -> {
                        sendResponse(client, "Verfuegbare Bettenanzahl aktualisiert", requestId);
                    }).exceptionally(e -> {
                        sendErrorResponse(client, e.getMessage(), requestId);
                        return null;
                    });
                    break;
                case "getTotalBeds":
                    // Aufruf der Methode getAvailableBedsAsync und Behandlung des CompletableFuture-Ergebnisses
                    applicationStubCallee.getTotalBedsAsync().thenAccept(totalBeds -> {
                        sendResponse(client, totalBeds, requestId);
                    }).exceptionally(e -> {
                        sendErrorResponse(client, e.getMessage(), requestId);
                        return null;
                    });
                    break;
                case "setTotalBeds":
                    // Aufruf der Methode setAvailableBedsAsync und Behandlung des CompletableFuture-Ergebnisses
                    int totalBeds = parameters.getInt(0); // Annahme, dass das erste Element ein Integer ist
                    applicationStubCallee.setTotalBedsAsync(totalBeds).thenRun(() -> {
                        sendResponse(client, "Totale Bettenanzahl aktualisiert", requestId);
                    }).exceptionally(e -> {
                        sendErrorResponse(client, e.getMessage(), requestId);
                        return null;
                    });
                    break;

                // Fügen Sie hier weitere Fälle für andere Methoden hinzu
                default:
                    sendErrorResponse(client, "Unbekannte Methode", requestId);
            }
        } catch (Exception e) {
            // Fehlerbehandlung und Senden einer Fehlerantwort
            sendErrorResponse(client, e.getMessage(), requestId);
        }
    }

    private void sendErrorResponse(Socket client, String errorMessage, Object id) {
        try (PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("jsonrpc", "2.0");
            errorResponse.put("id", id);
            errorResponse.put("error", errorMessage); // Fehlermeldung

            out.println(errorResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(Socket client, Object result, Object id) {
        try (PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("jsonrpc", "2.0");
            jsonResponse.put("id", id);
            jsonResponse.put("result", result); // oder "error" im Fehlerfall

            out.println(jsonResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) throws IOException, NoSuchMethodException {
        final KrankenhausRepository krankenhausRepository;
        // Starten des ServerStubs in einem separaten Thread
        new Thread(() -> {
            try {

                ServerStub serverStub = new ServerStub(12346);
                serverStub.recieve();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Erstellen und Verwenden des ClientStubs
        ClientStub clientStub = new ClientStub("localhost", 12345);
        Method method = ServerStub.class.getMethod("testMethod", int.class, String.class);
        clientStub.invoke(method, 123, "www");
    }


    public static int testMethod(int a, String s) {
        System.out.println("Hier bin ich");
        System.out.println("a: "+ a + s);
        return 1;
    }

}
