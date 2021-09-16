package amazon;

/*
139/140/472这三道题目有相似点，做这道题目之前可以先做139/140，然后再做这个
还有就是说如果下次来还是做不懂的话跳掉即可。

这道题目有几个难点
1. 要熟练写实现Trie的代码，否则完全做不出
2. 这道题目是DFS + Trie，而且我这个写法现在是通过不了最后一个case的，会超时
3. 然后再最新评论里面参考了一个方法，就是先把words按照长度排序，然后再进行操作，
   这样可以OK，但是具体的原因还是要二刷的时候搞懂，或者到时看看有没有新的题解说明
   搞不懂也无所谓，反正第一种也差不了太多

首先先给出一个我参考的题解，过不了最后一个case，但是和我的思路一模一样：
https://leetcode-cn.com/problems/concatenated-words/solution/java-trie-solution-easy-to-understand-by-handsomer/
* */

import java.util.*;

public class lc472 {
//    方法一： 常规版本，但是最后一个case过不了
//    private List<String> res = new LinkedList<>();
//    private Trie trie;
//    public List<String> findAllConcatenatedWordsInADict(String[] words) {
//        trie = new Trie();
//        for(String word : words){
//            trie.add(word);
//        }
//
//        List<String> res = new LinkedList<>();
//        for(String word : words){
//            if(DFS(word, trie.root, 0)) {
//                res.add(word);
//            }
//        }
//        return res;
//    }
//
//    private boolean DFS(String str, Trie.TrieNode root, int count){
//        if(str.length() == 0) return count > 1;
//        Trie.TrieNode node = root;
//        char[] sChar = str.toCharArray();
//        for(int i = 0; i < sChar.length; i++){
//            if(!node.hashMap.containsKey(sChar[i])) return false;
//            node = node.hashMap.get(sChar[i]);
//            if(node.end > 0 && DFS(str.substring(i + 1), root, count + 1)){
////                不能这么写，调试很久，因为这样就会让第一个testcase出问题！就是由于cat和cats这种导致的
////                return DFS(str.substring(i + 1), root, count + 1);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public class Trie{
//        public class TrieNode{
//            int path;
//            int end;
//            HashMap<Character, TrieNode> hashMap;
//
//            public TrieNode(){
//                path = 0;
//                end = 0;
//                hashMap = new HashMap<>();
//            }
//        }
//
//        TrieNode root;
//        public Trie(){
//            root = new TrieNode();
//        }
//
//        public void add(String str){
//            TrieNode node = root;
//            char[] sChar = str.toCharArray();
//            for(char ch : sChar){
//                if(!node.hashMap.containsKey(ch)){
//                    node.hashMap.computeIfAbsent(ch, v -> new TrieNode());
//                }
//                node.path++;
//                node = node.hashMap.get(ch);
//            }
//            node.end++;
//        }
//    }


//    方法二：优化版本，专门针对最后一个case,可通过
//    private List<String> res = new LinkedList<>();
//    private Trie trie;
//    public List<String> findAllConcatenatedWordsInADict(String[] words) {
//        trie = new Trie();
////        主要的修改点就是先对words进行排序，string长度从小到大，
////        这样可以不用把所有word都放入Trie中
//        Arrays.sort(words, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });
//
//        List<String> res = new LinkedList<>();
//        for(String word : words){
//            if(DFS(word, trie.root, 0)) {
//                res.add(word);
//            }else trie.add(word);
//        }
//        return res;
//    }
//
//    private boolean DFS(String str, Trie.TrieNode root, int count){
//        if(str.length() == 0) return count > 1;
//        Trie.TrieNode node = root;
//        char[] sChar = str.toCharArray();
//        for(int i = 0; i < sChar.length; i++){
//            if(!node.hashMap.containsKey(sChar[i])) return false;
//            node = node.hashMap.get(sChar[i]);
//            if(node.end > 0 && DFS(str.substring(i + 1), root, count + 1)){
////                不能这么写，调试很久，因为这样就会让第一个testcase出问题！就是由于cat和cats这种导致的
////                return DFS(str.substring(i + 1), root, count + 1);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public class Trie{
//        public class TrieNode{
//            int path;
//            int end;
//            HashMap<Character, TrieNode> hashMap;
//
//            public TrieNode(){
//                path = 0;
//                end = 0;
//                hashMap = new HashMap<>();
//            }
//        }
//
//        TrieNode root;
//        public Trie(){
//            root = new TrieNode();
//        }
//
//        public void add(String str){
//            TrieNode node = root;
//            char[] sChar = str.toCharArray();
//            for(char ch : sChar){
//                if(!node.hashMap.containsKey(ch)){
//                    node.hashMap.computeIfAbsent(ch, v -> new TrieNode());
//                }
//                node.path++;
//                node = node.hashMap.get(ch);
//            }
//            node.end++;
//        }
//    }


//    方法三： 在昨晚lc139以及lc140以后，回过头来做的,大幅度提高了速度
//    参考题解：
//    https://leetcode-cn.com/problems/concatenated-words/solution/di-gui-bu-shi-yong-trie-by-mattycheng/
    private HashSet<String> hashSet = new HashSet<>();
    private List<String> res = new LinkedList<>();;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        for(String word : words) {
            if(!word.equals("")) hashSet.add(word);
        }
        for(String word : words){
            if(helper(word, 0)) res.add(word);
        }
        return res;
    }

    private boolean helper(String str, int index){
        if(hashSet.contains(str) && index > 0) return true;
        int sLen = str.length();
//        这个从后到前的思路是参考liweiwei的lc140，
//        非常好用,如果从头开始就会超时，但是从后面开始则不会
        for(int i = sLen; i >= 0; i--){
            String temp = str.substring(i,sLen);
            if(hashSet.contains(temp)){
//                这个错误已经犯了好几次了，一定要注意
//                return helper(str.substring(i), index + 1);
                if(helper(str.substring(0, i), index + 1)) return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
//        String[] test = {"catsdogcats","cat","cats","dog"};
//        String[] test = {"cats","dog","catsdogcats"};
        String[] test = {"a","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"};
        System.out.println(new lc472().findAllConcatenatedWordsInADict(test));
    }
}
