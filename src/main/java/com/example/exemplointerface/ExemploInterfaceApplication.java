package com.example.exemplointerface;

import com.example.exemplointerface.entities.CarRental;
import com.example.exemplointerface.entities.Vehicle;
import com.example.exemplointerface.service.BrazilTaxService;
import com.example.exemplointerface.service.RentalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

/*Problema exemplo
* Uma locadora brasileira de carros cobra um valor por hora para locações de até 12 horas. Porém, se a duraçaõ
* da locação ultrapassar 12 horas, a locação será cobrada com base no valor diário. Além do valor da locação, é
* acrescido no preço o valor do imposto ocnforme regras do país que, no caso do Brasil, ´3 20% para valores até
* 100.00, ou 15% para valores acima de 100.00. Fazer um programa que lê os dados da locação (modelo do carro, instante
* incial e fianl da locaçaõ), bem como o valor por hora eo valor diário de locaçõa. O programa deve então gerar
* a nota de pagamento (contendo valor da locação, valor do imposto e o valor total do pagamento) e informar
* os dados na tela.
* */
@SpringBootApplication
public class ExemploInterfaceApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Return (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        //Como o nome do veiculo é uma associação entre Vehcicle com CarRental, e no CarRental ele é um objeto.
        //temos que inicial o Veihcle para poder pasar o nome do veiculo
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        /* testando os serviços do BrazilTaxService
        BrazilTaxService taxService = new BrazilTaxService();

        System.out.println(taxService.tax(50.0));
        */

        //Testando RentalService

        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

        rentalService.processInvoice(cr);
        System.out.println("Fatura:");
        System.out.println("Pagamento basico: " + String.format("%.2f",cr.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("%.2f",cr.getInvoice().getTax()));
        System.out.println("Pagamento total: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));



        sc.close();
    }

}
