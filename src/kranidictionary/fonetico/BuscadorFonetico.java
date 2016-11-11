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
 * @version Alpha 0.0.1
 * @author sbusta16, jsuare32
 */
public class BuscadorFonetico extends ProcesarPalabras{
    private HashMap<String, ArrayList> map;
    private DoubleMetaphone dm;
    
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
    
    @Override
    public ArrayList<String> consulta(String palabra){
        return map.get(dm.encode(palabra));
    }
}
