package com.example.exemplointerface.service;

//Pacote de serviço responsavel por processamento de serviços na hora da execução.

//Responsavel por aplicar a tax do serviços (imposto)
public class BrazilTaxService {

    public double tax(double amount){
        if ( amount <= 100) {
            return amount * 0.2;
        }else{
            return amount * 0.15;
        }
    }
}
