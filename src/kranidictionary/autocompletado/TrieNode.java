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
    
    public TrieNode (char letter, boolean word){
        this.letter = letter;
        this.word = word;
        this.sons = new HashMap<>();
    }

    /**
     * @return the sons
     */
    public HashMap<Character, TrieNode> getSons() {
        return sons;
    }

    /**
     * @return the letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * @return the word
     */
    public boolean isWord() {
        return word;
    }
}
