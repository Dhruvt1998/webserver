package com.example.hms.Service;

import com.example.hms.Model.IModelKrankenhaus;
import com.example.hms.Model.Krankenhaus;
import com.example.hms.repository.KrankenhausRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class KrankenhausService {
    private final KrankenhausRepository krankenhausRepository;

    @Autowired
    public KrankenhausService(KrankenhausRepository krankenhausRepository) {
        this.krankenhausRepository = krankenhausRepository;
    }

    @Async
    public CompletableFuture<Integer> getAvailableBeds() {
        // Find the Krankenhaus instance asynchronously
        return CompletableFuture.supplyAsync(() -> {
            return krankenhausRepository.findById(1)
                    .map(IModelKrankenhaus::getAvailableBeds) // Use the getAvailableBeds method of the Krankenhaus class
                    .orElse(0); // Return 0 if Krankenhaus is not found
        });
    }

    @Async
    public CompletableFuture<Void> setAvailableBeds(int availableBeds) {
        return CompletableFuture.runAsync(() -> {
            Optional<Krankenhaus> optionalKrankenhaus = krankenhausRepository.findById(1);
            optionalKrankenhaus.ifPresent(krankenhaus -> {
                //TODO: Randfälle bedeken(param nicht -1 , AvailableBeds <= TotalBeds)
                krankenhaus.setAvailableBeds(availableBeds);
                krankenhausRepository.save(krankenhaus);
            });
        });
    }

    @Async
    public CompletableFuture<Integer> getTotalBeds() {
        // Find the Krankenhaus instance asynchronously
        return CompletableFuture.supplyAsync(() -> {
            return krankenhausRepository.findById(1)
                    .map(IModelKrankenhaus::getTotalBeds) // Use the getAvailableBeds method of the Krankenhaus class
                    .orElse(0); // Return 0 if Krankenhaus is not found
        });
    }


    @Async
    public CompletableFuture<Void> setTotalBeds(int totalbeds) {
        return CompletableFuture.runAsync(() -> {
            Optional<Krankenhaus> optionalKrankenhaus = krankenhausRepository.findById(1);
            optionalKrankenhaus.ifPresent(krankenhaus -> {
                krankenhaus.setTotalBeds(totalbeds);
                krankenhausRepository.save(krankenhaus);
            });
        });
    }




}
