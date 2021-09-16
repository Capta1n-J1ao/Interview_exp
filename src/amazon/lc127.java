package amazon;

/*
示例2的endWord不在字典中无法进行转换，那么beginword不在字典中可以吗？ 回答：可以的
* */

import java.util.*;

public class lc127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> hashSet = new HashSet<>(wordList);
//      注意这里不能用stack! 这个结构是后进先出，会造成BFS出问题，一定要用Queue或者其他先进先出的结构
//        Stack<String> stack = new Stack<>();
//        还有要注意queue的初始化，new 后面是没有Queue的，要用List代替！
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int res = 0;
        while (!queue.isEmpty()){
            res++;
            int sLen = queue.size();
            for(int i = 0; i < sLen; i++){
                String temp = queue.poll();
                if(temp.equals(endWord)) return res;
                char[] chTemp = temp.toCharArray();
                for (int k = 0; k < temp.length(); k++){
//                    下面两句很关键！
                    char chBackup = temp.charAt(k);
                    for(char m = 'a'; m <= 'z'; m++){
                        chTemp[k] = m;
                        String subWord = new String(chTemp);
//                        这个错误反复范，用toString不行的原因excel里面讲过了
//                        if(hashSet.contains(chTemp.toString())){
                        if(hashSet.contains(subWord)){
                            queue.add(subWord);
                            hashSet.remove(subWord);
                        }else continue;
                    }
                    chTemp[k] = chBackup;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> dic = new ArrayList<>();
        //String[] dic1 = {"hot","dot","dog","lot","log","cog"};
        dic.add("hot");
        dic.add("dot");
        dic.add("dog");
        dic.add("lot");
        dic.add("log");
        dic.add("cog");
        String beginWord = "hit";
        String endWord = "cog";
        System.out.println(new lc127().ladderLength(beginWord, endWord,dic));
    }
}
