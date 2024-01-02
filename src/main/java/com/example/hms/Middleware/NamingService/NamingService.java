package com.example.hms.Middleware.NamingService;

import java.util.concurrent.ConcurrentHashMap;

public class NamingService implements INamingService{
    private ConcurrentHashMap<String,AddressInfo> services;

    //TODO: ist das eine Loesung für Key-Problem
    //private ConcurrentHashMap<String,ConcurrentHashMap<String,AddressInfo>> hospitalGlobalServices;


    public NamingService() {
        this.services = new ConcurrentHashMap<>();
    }

    public void register(String serviceName, ServiceInfo serviceInfo) {
        services.put(serviceName, serviceInfo);
    }

    @Override
    public AddressInfo lookUp(String serviceName) {
        return services.get(serviceName);
    }

    @Override
    public void unregister(String serviceName) {
        services.remove(serviceName);
    }


    // Test method to demonstrate usage
    public static void main(String[] args) {
        INamingService namingService = new NamingService();
        // Register a service
        namingService.register("myService", new AddressInfo("localhost", 12345));

        // Lookup the service
        ServiceInfo address = namingService.lookup("myService");
        System.out.println("Service Address: " + address);

        // Unregister the service
        namingService.unregister("myService");
    }
}