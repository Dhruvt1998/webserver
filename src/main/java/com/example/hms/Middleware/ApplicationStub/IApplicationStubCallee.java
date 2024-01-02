package com.example.hms.Middleware.ApplicationStub;

import java.util.concurrent.CompletableFuture;

public interface IApplicationStubCallee {

    public CompletableFuture<Integer> getAvailableBeds();

    public CompletableFuture<Void> setAvailableBeds(int availableBeds);

    public CompletableFuture<Integer> getTotalBeds();
    public CompletableFuture<Void> setTotalBeds(int totalBeds);
}
