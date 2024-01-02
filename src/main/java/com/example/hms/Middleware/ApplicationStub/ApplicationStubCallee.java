package com.example.hms.Middleware.ApplicationStub;

import com.example.hms.Service.KrankenhausService;
import com.example.hms.repository.KrankenhausRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ApplicationStubCallee implements IApplicationStubCallee{
    private final KrankenhausService krankenhausService;
    //private  KrankenhausRepository krankenhausRepository;

    @Autowired
    public ApplicationStubCallee(KrankenhausService krankenhausService) {
        this.krankenhausService = krankenhausService;
    }


    @Override
    public CompletableFuture<Integer> getAvailableBedsAsync() {
        return krankenhausService.getAvailableBeds();
    }

    @Override
    public CompletableFuture<Void> setAvailableBedsAsync(int availableBeds) {
        return krankenhausService.setAvailableBeds(availableBeds);
    }

    @Override
    public CompletableFuture<Integer> getTotalBedsAsync() {
        return krankenhausService.getTotalBeds();
    }

    @Override
    public CompletableFuture<Void> setTotalBedsAsync(int totalBeds) {
        return krankenhausService.setTotalBeds(totalBeds);
    }

}
