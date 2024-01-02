package com.example.hms.Controller;

import com.example.hms.Service.KrankenhausService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;


@RestController
public class ControllerKrankenhaus implements IControllerKrankenhaus{

    @Autowired
    private KrankenhausService krankenhausService;

    @GetMapping("/availableBeds")
    @Override
    public DeferredResult<ResponseEntity<?>> handleGetAvailableBeds() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        // Asynchronen Service aufrufen
        CompletableFuture<Integer> availableBedsFuture = krankenhausService.getAvailableBeds();

        // Setze das Ergebnis des DeferredResult, sobald es verfügbar ist
        availableBedsFuture.thenAccept(availableBeds ->
                deferredResult.setResult(ResponseEntity.ok(availableBeds))
        ).exceptionally(ex -> {
            // Fehlerbehandlung
            deferredResult.setErrorResult(
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Fehler bei der Abfrage der verfügbaren Betten")
            );
            return null;
        });

        return deferredResult;
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/setAvailableBeds")
    @Override
    public DeferredResult<ResponseEntity<?>> handleSetAvailableBeds(@RequestParam int availableBeds) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        // Asynchronen Service aufrufen
        CompletableFuture<Void> setAvailableBedsFuture = krankenhausService.setAvailableBeds(availableBeds);

        // Setze das Ergebnis des DeferredResult, sobald es verfügbar ist
        setAvailableBedsFuture.thenRun(() ->
                deferredResult.setResult(ResponseEntity.ok("Verfügbare Betten erfolgreich aktualisiert"))
        ).exceptionally(ex -> {
            // Fehlerbehandlung
            deferredResult.setErrorResult(
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Fehler bei der Aktualisierung der verfügbaren Betten")
            );
            return null;
        });

        return deferredResult;
    }

    @GetMapping("/totalBeds")
    @Override
    public DeferredResult<ResponseEntity<?>> handleGetTotalBeds() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        // Asynchronen Service aufrufen
        CompletableFuture<Integer> totalBedsFuture = krankenhausService.getTotalBeds();

        // Setze das Ergebnis des DeferredResult, sobald es verfügbar ist
        totalBedsFuture.thenAccept(totalBeds ->
                deferredResult.setResult(ResponseEntity.ok(totalBeds))
        ).exceptionally(ex -> {
            // Fehlerbehandlung
            deferredResult.setErrorResult(
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Fehler bei der Abfrage der total Betten")
            );
            return null;
        });

        return deferredResult;
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/setTotalBeds")
    @Override
    public DeferredResult<ResponseEntity<?>> handleSetTotalBeds(@RequestParam int totalBeds) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        // Asynchronen Service aufrufen
        CompletableFuture<Void> setTotalBedsFuture = krankenhausService.setTotalBeds(totalBeds);

        // Setze das Ergebnis des DeferredResult, sobald es verfügbar ist
        setTotalBedsFuture.thenRun(() ->
                deferredResult.setResult(ResponseEntity.ok("Totale Betten erfolgreich aktualisiert"))
        ).exceptionally(ex -> {
            // Fehlerbehandlung
            deferredResult.setErrorResult(
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Fehler bei der Aktualisierung der totalen Betten")
            );
            return null;
        });

        return deferredResult;
    }


}
