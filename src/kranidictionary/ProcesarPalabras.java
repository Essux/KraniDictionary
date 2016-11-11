package kranidictionary;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Esta clase define el esqueleto del comportamiento de las clases que realizan consultas de palabras
 * @author JuanJose
 */
public abstract class ProcesarPalabras {
    public abstract void generarDiccionario() throws FileNotFoundException;
    public abstract ArrayList<String> consulta(String palabra);
}
