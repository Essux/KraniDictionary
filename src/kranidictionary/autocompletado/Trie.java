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