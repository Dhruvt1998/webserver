package com.example.hms.Middleware.NamingService;

public interface INamingService {
    AddressInfo lookUp(String serviceName);
    void register(String serviceName, AddressInfo serviceInfo);

    void unregister(String serviceName);
}
