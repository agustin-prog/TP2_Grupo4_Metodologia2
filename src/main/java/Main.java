/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author agust
 */

import patrones.adapter.DemoAdapter;
import patrones.adapter.PagoException;

public class Main {

    public static void main(String[] args) {

        // 2 - Patron adapter
        System.out.println("==================================");
        System.out.println("TP2 - Metodologia de Sistemas II");
        System.out.println("Grupo 4");
        System.out.println("==================================");

        probarAdapter(args);
        // Ejecucion de los demas patrones.
    }

    private static void probarAdapter(String[] args) {
        System.out.println("\n--- PATRON ADAPTER ---");

        try {
            DemoAdapter.main(args);
        } catch (PagoException e) {
            System.out.println(
                "Error al realizar el pago: " + e.getMessage()
            );
        }
    }
}

