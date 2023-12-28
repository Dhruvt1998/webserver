package com.example.hms.Middleware.NamingService;

public class TestService implements IService {
    @Override
    public Object invoke(Object... args) {
        int a = (Integer) args[0];
        String s = (String) args[1];
        System.out.println("Hier bin ich");
        System.out.println("a: " + a + s);
        return 1;
    }
}