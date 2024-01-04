package com.example.hms.Middleware.ClientStub;

import java.lang.reflect.Method;

public interface IClientStub {
    String invoke (Method methodenAufruf, Object... args);
    void marshall(Method methodenAufruf, Object... args);
    String sendAndReceive();

}
