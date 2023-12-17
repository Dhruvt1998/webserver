package com.example.hms.Middleware.ServerStub;

import com.example.hms.Middleware.ClientStub.ClientStub;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStub implements IServerStub{
    private JSONObject jsonObject;
    private ServerSocket serverSocket;


    public ServerStub(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void register(String methode){
        System.out.println("Methode registriert: " + methode);

    }

    @Override
    public void unmarshall (String json){
        this.jsonObject = new JSONObject(json);
    }



    public void recieve() {
        try (Socket client = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                unmarshall(inputLine);
                handleCall(jsonObject.getString("methodName"), jsonObject.getJSONArray("parameters"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void handleCall(String methodenName, JSONArray parameters) {
        System.out.println("Handling method call: " + methodenName + " with parameter: " + parameters);

        try {
            // Annahme: es gibt nur einen int-Parameter
            // Extrahieren Sie den ersten Parameter aus dem JSONArray und wandeln Sie ihn in einen int um
            int paramValue = parameters.getInt(0); // Geändert, um das erste Element des JSONArray zu verwenden
            String paramValue2 = parameters.getString(1);

            // Suchen Sie die Methode mit dem gegebenen Namen und Parametertypen
            Method method = this.getClass().getDeclaredMethod(methodenName, int.class, String.class);
            // Rufen Sie die Methode mit dem extrahierten Parameterwert auf
            method.invoke(this, paramValue, paramValue2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, NoSuchMethodException {
        // Starten des ServerStubs in einem separaten Thread
        new Thread(() -> {
            try {
                ServerStub serverStub = new ServerStub(12345);
                serverStub.recieve();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Warten, um sicherzustellen, dass der Server gestartet ist
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Erstellen und Verwenden des ClientStubs
        ClientStub clientStub = new ClientStub("localhost", 12345);
        Method method = ServerStub.class.getMethod("testMethod", int.class, String.class); // Änderung hier
        clientStub.invoke(method, 123, "www"); // Beispielswert für den int-Parameter
    }


    public static void testMethod(int a, String s) {
        System.out.println("Hier bin ich");
        System.out.println("a: "+ a + s);
    }

}
