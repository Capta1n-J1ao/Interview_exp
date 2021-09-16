package amazon;

/*
这道题目挺傻的，其实就是硬做，参考郭郭的题解，就能有最简单的写法，而且效率比他高
因为他是用list进行删减判断的
https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/java-chang-gui-si-lu-qing-xi-shi-pin-jiang-jie-by-/

下面这个优化在上面的基础上就能看到了：
https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/30-chuan-lian-suo-you-dan-ci-de-zi-chuan-bvy9/
* */

import java.util.*;

public class lc30 {
//    private List<Integer> res;
////    private HashMap<String, Integer> hashMap;
//    public List<Integer> findSubstring(String s, String[] words) {
//        res = new LinkedList<>();
////        hashMap = new HashMap<>();
//        int sLen = s.length(),wLen = words.length, singleWLen = words[0].length();
//        int expectedLen = wLen * singleWLen;
//        if(expectedLen > sLen) return res;
//
//        for(int i = 0; i < sLen; i++){
//            int end = i + expectedLen;
//            if(end > sLen) break;
//            HashMap<String, Integer> hashMap = new HashMap<>();
//            for(String word : words){
//                hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
//            }
//            for(int k = i + singleWLen; k <= i + expectedLen; k += singleWLen){
////                System.out.println(k - singleWLen);
////                System.out.println(k);
//                String curStr = s.substring(k - singleWLen, k);
////              根据解法二的经验，加了下面这句话，性能有明显改善，但是还是不及解法二
////                个人分析主要原因还是因为hashMap的remove方法效率较低，直接用第二个HashMap进行比较则更快
//                if(!hashMap.keySet().contains(curStr) || hashMap.get(curStr) <= 0) break;
//                if(hashMap.keySet().contains(curStr)){
//                    if(hashMap.get(curStr) - 1 == 0) hashMap.remove(curStr);
//                    else hashMap.put(curStr, hashMap.get(curStr) - 1);
//                }
//            }
//            if(hashMap.keySet().size() == 0) res.add(i);
//        }
//        return res;
//    }


//    方法二，个人认为使用新建一个hashMap来替代map的remove操作导致了速度的增快
    private List<Integer> res;
    private HashMap<String, Integer> require;
    public List<Integer> findSubstring(String s, String[] words) {
        res = new LinkedList<>();
        require = new HashMap<>();
        int sLen = s.length(),wLen = words.length, singleWLen = words[0].length();
        int expectedLen = wLen * singleWLen;
        if(expectedLen > sLen) return res;
        for(String word : words){
            require.put(word, require.getOrDefault(word, 0) + 1);
        }
        for(int i = 0; i < sLen; i++){
            int end = i + expectedLen;
            if(end > sLen) break;
            HashMap<String, Integer> have = new HashMap<>();
//            这里要用while，不能用for，时间不够可以不用管，记住即可
            int index = i;
            while (index < i + expectedLen){
                String curStr = s.substring(index, index + singleWLen);
                if(!require.keySet().contains(curStr) || require.get(curStr) == have.get(curStr))break;
                have.put(curStr, have.getOrDefault(curStr, 0) + 1);
                index += singleWLen;
            }
            if(index == i + expectedLen) res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] test = {"bar", "foo"};
        System.out.println(new lc30().findSubstring("barfoothefoobarman", test));
    }
}
