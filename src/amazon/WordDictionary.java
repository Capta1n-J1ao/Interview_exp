package amazon;

/*
这个题解和我的思路最像，所以参考他的
https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/solution/qian-zhui-shu-dui-lie-chu-li-tong-pei-fu-cha-zhao-/
* */

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class WordDictionary {
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


    /** Initialize your data structure here. */
    private TrieNode trieNode;
    public WordDictionary() {
        trieNode = new TrieNode();
    }

    public void addWord(String word) {
        char[] wChar = word.toCharArray();
//        这句话很关键
        TrieNode node = trieNode;
        for(char ch : wChar){
            if(!node.hashMap.containsKey(ch)){
                node.hashMap.computeIfAbsent(ch, v -> new TrieNode());
            }
            node.path++;
            node = node.hashMap.get(ch);
        }
        node.end++;
    }

    public boolean search(String word) {
        char[] wChar = word.toCharArray();
        TrieNode node = trieNode;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(node);
//        这道题不能用常规的BFS模板，而是要用这个模板才可以
//        入股硬要用模板，那要引入index，二刷的时候可以试一下
//        用下面这个可以试下
//        while (!queue.isEmpty() && index < wChar.length)
        for(int i = 0; i < word.length(); i++){
            int qLen = queue.size();
            for(int k = 0; k < qLen; k++){
                TrieNode temp = queue.poll();
//                分类就是按照是不是'.'分的
                if(wChar[i] == '.'){
                    if(temp.hashMap.size() > 0){
                        for(char ch : temp.hashMap.keySet()){
                            queue.add(temp.hashMap.get(ch));
                        }
                    }

                }else {
                    if(temp.hashMap.containsKey(wChar[i])){
                        queue.add(temp.hashMap.get(wChar[i]));
                    }
                }
            }
            if(queue.isEmpty()) return false;
        }
//        这句话其实是精髓！一直没加这句话，所以错了
        while (!queue.isEmpty()){
            if(queue.poll().end > 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary test = new WordDictionary();
        test.addWord("dad");
        System.out.println(test.search("dad"));
        System.out.println(test.search("mad"));
        System.out.println(test.search(".ad"));

//        test.addWord("a");
//        System.out.println(test.search("."));

//        test.addWord("a");
//        System.out.println(test.search("a"));
//        System.out.println(test.search("b"));
//        System.out.println(test.search("aa"));
    }
}
