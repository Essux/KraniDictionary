package kranidictionary.autocompletado;

import java.util.HashMap;

/**
 *
 * @author JuanJose
 */
public class TrieNode {
    private final HashMap<Character, TrieNode> sons;
    private final boolean word;
    private final char letter;
    
    /**
     * Inicializa una instancia de la clase especificando el caracter que contiene y si representa una palabra
     * @param letter
     * @param word
     */
    public TrieNode (char letter, boolean word){
        this.letter = letter;
        this.word = word;
        this.sons = new HashMap<>();
    }

    /**
     * Devuelve el HashMap asociado con los hijos del nodo
     * @return the sons
     */
    public HashMap<Character, TrieNode> getSons() {
        return sons;
    }

    /**
     * Retorna el caracter que representa el nodo
     * @return the letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Retorna si el nodo representa una palabra
     * @return the word
     */
    public boolean isWord() {
        return word;
    }
}
