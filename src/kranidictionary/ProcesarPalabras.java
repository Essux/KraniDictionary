/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kranidictionary;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author JuanJose
 */
public abstract class ProcesarPalabras {
    public abstract void generarDiccionario() throws FileNotFoundException;
    public abstract ArrayList<String> consulta(String palabra);
}
