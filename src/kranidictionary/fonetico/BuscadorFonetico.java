package kranidictionary.fonetico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import kranidictionary.ProcesarPalabras;
import org.apache.commons.codec.language.DoubleMetaphone;

/**
 * Esta clase permite hacer consultas de palabras similares fonéticamente según un diccionario dado
 * @author sbusta16, jsuare32
 */
public class BuscadorFonetico extends ProcesarPalabras{
    private HashMap<String, ArrayList> map;
    private DoubleMetaphone dm;
    
    /**
     * Carga todas las palabras en un diccionario desde un archivo de texto.
     * @throws FileNotFoundException Es arrojada cuando el archivo de texto no es encontrado
     */
    @Override
    public void generarDiccionario() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader("src/kranidictionary/words.txt"));
        map = new HashMap<>(0);
        dm = new DoubleMetaphone();
        String linea;
        try{
            while((linea = br.readLine()) != null){
                String code = dm.encode(linea);
                if (!map.containsKey(code)){
                    map.put(code, new ArrayList<>());
                }
                map.get(code).add(linea);
            }
        }
        catch(IOException e){}
    }
    
    /**
     * Devuelve palabras similares fonéticamente al parámetro palabra
     * @param palabra La palabra de la cual se quiere hacer la consulta
     * @return Un ArrayList de cadenas de caracteres que contiene todas las palabras similares encontradas
     */
    @Override
    public ArrayList<String> consulta(String palabra){
        return map.get(dm.encode(palabra));
    }
}
