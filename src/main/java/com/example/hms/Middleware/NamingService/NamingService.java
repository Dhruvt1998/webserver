package com.example.hms.Middleware.NamingService;

import java.util.concurrent.ConcurrentHashMap;

public class NamingService {
    private ConcurrentHashMap<String, ServiceInfo> services;

    public NamingService() {
        this.services = new ConcurrentHashMap<>();
    }

    public void register(String serviceName, ServiceInfo serviceInfo) {
        services.put(serviceName, serviceInfo);
    }

    public ServiceInfo lookup(String serviceName) {
        return services.get(serviceName);
    }

    public void unregister(String serviceName) {
        services.remove(serviceName);
    }


    // Test method to demonstrate usage
    public static void main(String[] args) {
        NamingService namingService = new NamingService();
        IService TestService = null;
        IService service = TestService;
        // Register a service
        namingService.register("myService", new ServiceInfo("localhost", 12345, TestService));

        // Lookup the service
        ServiceInfo address = namingService.lookup("myService");
        System.out.println("Service Address: " + address);

        // Unregister the service
        namingService.unregister("myService");
    }
}