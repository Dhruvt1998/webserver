package com.example.hms.Middleware.ApplicationStub;

import com.example.hms.Middleware.NamingService.NamingService;
import com.example.hms.Model.IModelKrankenhaus;
import com.example.hms.Service.KrankenhausService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

public interface IApplicationStubCallee {

    public CompletableFuture<Integer> getAvailableBedsAsync();

    public CompletableFuture<Void> setAvailableBedsAsync(int availableBeds);

    public CompletableFuture<Integer> getTotalBedsAsync();
    public CompletableFuture<Void> setTotalBedsAsync(int totalBeds);
}
