package com.example.exemplointerface.entities;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CarRental {
    public LocalDateTime start;
    public LocalDateTime finish;

    //associaçaõ
    private Vehicle vehicle;
    private Invoice invoice;

    public CarRental(){

    }
    //Retirado a fatura, pois não ainda não existe ainda
    public CarRental(LocalDateTime start, LocalDateTime finish, Vehicle vehicle){
        this.start = start;
        this.finish = finish;
        this.vehicle = vehicle;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFisish() {
        return finish;
    }

    public void setFisish(LocalDateTime fisish) {
        this.finish = fisish;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
