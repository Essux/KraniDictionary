package kranidictionary.autocompletado;

import java.util.ArrayList;

/**
 *
 * @author JuanJose
 */
public class Trie {
    TrieNode root;
    
    Trie(){
        root = new TrieNode('\u0000', false);
    }
    
    /**
     * Inserta la palabra especificada en el Trie
     * @param word
     */
    public void insert(String word){
        TrieNode currNode = root;
        for(int i = 0; i < word.length(); i++){
            char currChar = word.charAt(i);
            boolean isWord = i == word.length() - 1;
            if (!currNode.getSons().containsKey(currChar)){
                currNode.getSons().put(currChar, new TrieNode(currChar, isWord));
            }
            currNode = currNode.getSons().get(currChar);
        }
    }
    
    /**
     * Encuentra el nodo que representa la palabra en el Trie. Devuelve null si no es encontrada
     * @param word La palabra a buscar
     * @return El objeto TrieNode que representa la palabra buscadoa
     */
    public TrieNode findNode(String word){
        TrieNode currNode = root;
        for(int i = 0; i < word.length(); i++){
            char currChar = word.charAt(i);
            if (!currNode.getSons().containsKey(currChar)){
                return null;
            }
            currNode = currNode.getSons().get(currChar);
        }
        return currNode;
    }
    
    /**
     * Recorre en profundidad recursivamente el Trie desde un nodo en particular retornando todas las palabras que
     * empiezan con el parámetro palabra.
     * @param start El nodo inicial desde el cual recorrer el Trie
     * @param word Se buscarán las palabras que tengan como prefijo a esta
     * @param list Se le debe pasar una lista vacía
     * @return Lista de palabras que tienen como prefijo al parámetro word
     */
    public ArrayList<String> dfs(TrieNode start, String word, ArrayList<String> list){
        TrieNode currNode = start;
        word += currNode.getLetter();
        for (TrieNode currSon : currNode.getSons().values()){
            dfs(currSon, word, list);
        }
        if (currNode.isWord()){
            list.add(word);
        }
        return list;
    }
}