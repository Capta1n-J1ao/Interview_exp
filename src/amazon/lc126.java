package amazon;

/*
这道题目可以算结合BFS和DFS最难得题目之一，搞了两天才写出代码，还是效率最低的那种，首先先看一下视频：
https://www.bilibili.com/video/BV16K4y1j7hX?t=4
然后是这个视频的源码，我的第一编代码几乎和他一样：
https://turingplanet.org/2020/11/26/126_word_ladder_ii/
搞懂了上面那个以后再看liweiwei的优化版本，其实其实两个办法最主要的差别就是在于找爹，
就是对应爹和儿子的关系，把它做成一个hashmap一一对应，然后通过对这个hashMap进行回溯来得到所有答案
https://leetcode-cn.com/problems/word-ladder-ii/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you--2/
经过总结来看的话，两个方法的区别就是找爹效率的不同，
方法一是先要把所有的单词的邻居通过HashMap来一一对应，然后在BFS里面用这个HashMap来找父子关系，
这样相当于用两个HashMap来找爹，并且当中有很多重叠操作，造成了极大的浪费
方法二的找爹方法和lc极像，就是用换字符串的方式，这样可以最大程度节省效率
其实这两个方法就这两个代码不一样，其他都一样，但是效率差十倍以上。
* */

import java.util.*;

public class lc126 {
//    方法一：
//    比较好懂的办法，其实就是找爹，
//    首先，就是先把每个word的只差一个字符的邻居找出来，存在hashset里面
//    其次，用这个hashSet从beginword开始BFS遍历，把这一层的单词和下一层的单词做一个父子对应关系。直到endword为止
//    最后用对应的父子关系用回溯得到所有结果


//    private HashMap<String, HashSet<String>> oneChangeNei = new HashMap<>();
//    private List<List<String>> res = new LinkedList<>();
//    private HashMap<String, HashSet<String>> parents = new HashMap<>();
//    private String beginWord, endWord;
//    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//        this.beginWord = beginWord;
//        this.endWord = endWord;
//        for(String str : wordList){
//            HashSet<String> hashSet = new HashSet<>();
//            for(String strNei : wordList){
//                if(oneDiff(str, strNei)){
//                    hashSet.add(strNei);
//                }
//            }
//            oneChangeNei.put(str, hashSet);
//        }
//
//        HashSet<String> temp1 = new HashSet<>();
//        for(String str : wordList){
//            if(oneDiff(beginWord, str)){
//                temp1.add(str);
//            }
//        }
//        oneChangeNei.put(beginWord, temp1);
//
//        if(!oneChangeNei.containsKey(endWord)) return res;
//
////        以上代码在做的事情就是对wordList里面的neighbour进行处理，方便后续操作
//
//        Queue<String> queue = new LinkedList<>();
////        放到全局变量
////        HashMap<String, HashSet<String>> parents = new HashMap<>();
//        queue.add(beginWord);
//        parents.put(beginWord, null);
//        while (!queue.isEmpty()){
//            int qLen = queue.size();
////            String是儿子，  HashSet是String的爹
//            HashMap<String, HashSet<String>> temp_parents = new HashMap<>();
//            for(int i = 0; i < qLen; i++){
//                String temp = queue.poll();
//                for(String str : oneChangeNei.get(temp)){
//                    if(!parents.containsKey(str)){
//                        if(!temp_parents.containsKey(str)){
//                            queue.add(str);
//                        }
////                        下面的这个if逻辑可以用这个语句直接写出来，也可以繁琐一点用if判断
////                        temp_parents.computeIfAbsent(str, key -> new HashSet<>()).add(temp);
//                        if(temp_parents.get(str) == null){
//                            temp_parents.put(str, new HashSet<>());
//                        }
//                        temp_parents.get(str).add(temp);
//                    }
//                }
//            }
//            parents.putAll(temp_parents);
////            System.out.println(parents);
//            if(parents.containsKey(endWord)){
//                List<String> curRes = new LinkedList<>();
//                curRes.add(endWord);
//                BackTracking(curRes, endWord);
//                return res;
//            }
//        }
//        return new ArrayList<>();
//    }
//
//    private void BackTracking(List<String> curRes, String findDad){
//        if(findDad.equals(beginWord)){
////            其实用题解里面的代码答案是OK的，但是leetcode不认，只能这么写
////            res.add(new LinkedList<>(curRes));
//            List<String> tempRes = new LinkedList<>(curRes);
//            Collections.reverse(tempRes);
//            res.add(tempRes);
//            return;
//        }
//
//        for(String str : parents.get(findDad)){
//            curRes.add(str);
//            BackTracking(curRes, str);
//            curRes.remove(curRes.size() - 1);
//        }
//    }
//
//    private boolean oneDiff(String str1, String str2){
//        char[] sChar1 = str1.toCharArray();
//        char[] sChar2 = str2.toCharArray();
//        int sLen = str1.length();
//        int diffNum = 0;
//        for(int i = 0; i < sLen; i++){
//            if(sChar1[i] != sChar2[i]) diffNum++;
//        }
////        下面这句话一定要这么写，否则如果写return diffNum > 1的话会把自身给弄进去，
////        这是不需要的
//        return diffNum == 1;
//    }


    //  方法二：在方法一的基础上看了liweiwei的思路，其实两个办法最主要的差别就是在于找爹，
//  就是对应爹和儿子的关系，把它做成一个hashmap一一对应，然后通过对这个hashMap进行回溯来得到所有答案
    private List<List<String>> res = new LinkedList<>();
    private HashMap<String, HashSet<String>> parents = new HashMap<>();
    private String beginWord, endWord;
    private HashSet<String> wList;
    private int wLen;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        this.beginWord = beginWord;
        this.endWord = endWord;
        wList = new HashSet<>(wordList);
        if(!wList.contains(endWord)) return res;
        wLen = beginWord.length();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while (!queue.isEmpty()){
            int qLen = queue.size();
//            这个是Buffer，把下面sChange的所有爹都找到才可以放到正式parents里面
            HashMap<String, HashSet<String>> temp_parents = new HashMap<>();
            for(int i = 0; i < qLen; i++){
                String str = queue.poll();
                char[] sChar = str.toCharArray();
                for(int k = 0; k < wLen; k++){
                    char ch2Change = sChar[k];
                    for(char ch = 'a'; ch <= 'z'; ch++){
                        if(ch2Change == ch) continue;
                        sChar[k] = ch;
                        String sChange = new String(sChar);
                        if(wList.contains(sChange)){
                            if(!parents.containsKey(sChange)){
                                if(!temp_parents.containsKey(sChange)){
                                    queue.add(sChange);
                                }
//                                看方法一的注释
                                temp_parents.computeIfAbsent(sChange, key -> new HashSet<>()).add(str);
                            }
                        }
                    }
                    sChar[k] = ch2Change;
                }
            }
            parents.putAll(temp_parents);
            System.out.println(parents);
            if(parents.containsKey(endWord)){
                List<String> curRes = new LinkedList<>();
                curRes.add(endWord);
                BackTracking(curRes, endWord);
                return res;
            }
        }
        return res;
    }

    private void BackTracking(List<String> curRes, String findDad){
        if(findDad.equals(beginWord)){
//            其实用下面的代码答案是OK的，但是leetcode不认，只能这么写
//            res.add(new LinkedList<>(curRes));
            List<String> tempRes = new LinkedList<>(curRes);
            Collections.reverse(tempRes);
            res.add(tempRes);
            return;
        }

        for(String str : parents.get(findDad)){
            curRes.add(str);
            BackTracking(curRes, str);
            curRes.remove(curRes.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> wl = new LinkedList<>();
        wl.add("hot");
        wl.add("dot");
        wl.add("dog");
        wl.add("lot");
        wl.add("log");
        wl.add("cog");
        System.out.println(new lc126().findLadders("hit", "cog", wl));
    }
}
