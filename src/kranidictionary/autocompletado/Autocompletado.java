package kranidictionary.autocompletado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import kranidictionary.ProcesarPalabras;

/**
 *
 * @author JuanJose
 */
public class Autocompletado extends ProcesarPalabras{
    private Trie trie;
    
    /**
     * Lee las palabras del archivo de texto y crea un trie que las contenga
     * @throws FileNotFoundException Si el archivo de texto no es encontrado
     */
    @Override
    public void generarDiccionario() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader("src/kranidictionary/words.txt"));
        String linea;
        trie = new Trie();
        try{
            while((linea = br.readLine()) != null){
                trie.insert(linea);
            }
        }
        catch(IOException e){}
    }

    /**
     * Recibe una palabra y devuelve todas las palabras que la tengan como prefijo
     * @param palabra
     * @return palabras que comiencen por el parámetro
     */
    @Override
    public ArrayList<String> consulta(String palabra) {
        try{
        ArrayList<String> list = trie.dfs(trie.findNode(palabra), 
                palabra.substring(0, palabra.length()-1),
                new ArrayList<>());
        return list;
        } catch(NullPointerException e){
            return new ArrayList<>();
        }
    }
}