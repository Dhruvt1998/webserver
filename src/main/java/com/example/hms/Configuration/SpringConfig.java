package com.example.hms.Configuration;

import com.example.hms.Middleware.ApplicationStub.ApplicationStubCallee;
import com.example.hms.Middleware.ApplicationStub.IApplicationStubCallee;
import com.example.hms.Middleware.NamingService.INamingService;
import com.example.hms.Middleware.NamingService.NamingService;
import com.example.hms.Middleware.ServerStub.IServerStub;
import com.example.hms.Middleware.ServerStub.ServerStub;
import com.example.hms.Service.KrankenhausService;
import com.example.hms.repository.KrankenhausRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Configuration
public class SpringConfig {

    @Value("${server.port}")
    private int serverPort;

    @Bean
    public KrankenhausService krankenhausService(KrankenhausRepository krankenhausRepository) {
        // Erstellen Sie hier eine Instanz Ihres KrankenhausService
        return new KrankenhausService(krankenhausRepository);
    }

    @Bean
    public IApplicationStubCallee applicationStubCallee(KrankenhausService krankenhausService) {
        // Erstellen Sie hier eine Instanz Ihres ApplicationStubCallee
        return new ApplicationStubCallee(krankenhausService);
    }

    @Bean
    public INamingService namingService() {
        // Erstellen Sie hier eine Instanz Ihres NamingService
        return new NamingService();
    }

    @Bean
    public IServerStub serverStub() throws IOException {
        // Erstellen Sie hier eine Instanz Ihres ServerStub
        IServerStub serverStub = new ServerStub();
        // Weitere Konfigurationen können hier durchgeführt werden
        return serverStub;
    }

}
