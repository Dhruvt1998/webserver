package com.example.hms.Middleware.ServerStub;

import org.json.JSONArray;

import java.lang.reflect.Method;

public interface IServerStub {
void register(String methode);
void unmarshall (String json);
void recieve();
void handleCall (String methodenName, JSONArray parameters);
}