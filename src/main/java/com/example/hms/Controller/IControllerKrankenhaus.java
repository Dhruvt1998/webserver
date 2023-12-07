package com.example.hms.Controller;

public interface IControllerKrankenhaus {

    public void handleGetTotalBeds();
    public void handleSetTotalBeds(int totalBeds);
    public void handleGetAvailableBeds();
    public void handleSetAvailableBeds(int availableBeds);
}
