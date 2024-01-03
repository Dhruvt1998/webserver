package com.example.hms.Middleware.ServerStub;

import org.json.JSONArray;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;

public interface IServerStub {
void register();
void unmarshall (String json);
void recieve();
void handleCall (Socket client, String methodenName, JSONArray parameters);
}
