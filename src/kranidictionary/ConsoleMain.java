/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kranidictionary;

import kranidictionary.fonetico.BuscadorFonetico;
import java.io.FileNotFoundException;
import java.util.Scanner;
import kranidictionary.autocompletado.Autocompletado;

/**
 *
 * @author USUARIO
 */
public class ConsoleMain {
    public static void main(String[] args){
        BuscadorFonetico fonetico = new BuscadorFonetico();
        Autocompletado auto = new Autocompletado();
        try{
            fonetico.generarDiccionario();
            auto.generarDiccionario();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Búsqueda Fonética");
        System.out.println("2. Autocompletado");
        ProcesarPalabras opcion = auto;
        if (sc.nextInt() == 1){
            opcion = fonetico;
        }
        sc.nextLine();
        while (true){
            opcion.consulta(sc.nextLine()).stream().forEach((e) -> {
                System.out.println(e);
            });
            System.out.println();
        }
    }
}
