package com.example.hms.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

public interface IControllerKrankenhaus {

    public DeferredResult<ResponseEntity<?>> handleGetTotalBeds();
    public DeferredResult<ResponseEntity<?>> handleSetTotalBeds(int totalBeds);
    public DeferredResult<ResponseEntity<?>> handleGetAvailableBeds();
    public DeferredResult<ResponseEntity<?>> handleSetAvailableBeds(int availableBeds);
}
