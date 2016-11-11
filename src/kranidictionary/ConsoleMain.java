package kranidictionary;

import kranidictionary.fonetico.BuscadorFonetico;
import java.io.FileNotFoundException;
import java.util.Scanner;
import kranidictionary.autocompletado.Autocompletado;

/**
 * Esta clase permite hacer consultas fonéticas y de autocompletado desde la terminal.
 * @author JuanJose
 */
public class ConsoleMain {

    /**
     * Inicializa el programa con interacción desde la terminal.
     * @param args
     */
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
