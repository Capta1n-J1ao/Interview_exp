package amazon;

import java.util.HashMap;

public class Trie {
    public class TrieNode{
        int path;
        int end;
        HashMap<Character, TrieNode> hashMap;

        public TrieNode(){
            path = 0;
            end = 0;
            hashMap = new HashMap<>();
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
//    public Trie() {
//        root = new TrieNode();
//    }

    public void Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] wChar = word.toCharArray();
        TrieNode node = root;
        for(char ch :wChar){
            if(!node.hashMap.containsKey(ch)){
                node.hashMap.computeIfAbsent(ch, v -> new TrieNode());
            }
            node.path++;
            node = node.hashMap.get(ch);
        }
        node.end++;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        char[] wChar = word.toCharArray();
        for(char ch : wChar){
            if(!node.hashMap.containsKey(ch)) return false;
            node = node.hashMap.get(ch);
        }
        return node.end > 0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        char[] wChar = prefix.toCharArray();
        for(char ch : wChar){
            if(!node.hashMap.containsKey(ch)) return false;
            node = node.hashMap.get(ch);
        }
        return true;
    }
}
