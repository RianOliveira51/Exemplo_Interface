package com.example.exemplointerface.service;

import com.example.exemplointerface.entities.CarRental;
import com.example.exemplointerface.entities.Invoice;

import java.time.Duration;

//Serviço responsavel por instanciar a invoice no momento da execução, após instanciar o CarRental.
public class RentalService {

    private double pricePerHour;
    private double pricePerDay;

    //associação
    private BrazilTaxService taxService;

    public RentalService(double pricePerHour, double pricePerDay, BrazilTaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }

    //processando a fatura, responsavel por instanciar a fatura, a partir da entidade CarRental
    public void processInvoice(CarRental carRental){
        double minutes = Duration.between(carRental.getStart(),carRental.getFisish()).toMinutes();
        double hours = minutes / 60.0;
        double basicPayment = 0;
        if (hours <= 12) {
            //calculando por hora, Math.ceil arredonda um numero flaxinoda pra cima, transformando em inteiro
            basicPayment = pricePerHour * Math.ceil(hours);
        }else{
            //calculando por dia caso passe de 12 horas
            basicPayment = pricePerDay * Math.ceil(hours / 24);
        }
        //calculando imposto
        double tax = taxService.tax(basicPayment);
        //criando a invoice
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
