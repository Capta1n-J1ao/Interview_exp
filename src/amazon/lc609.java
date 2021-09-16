package amazon;

/*
这道题目其实思路听好想，已经直接想出来了，代码是结合了官解和官解下面jaya的代码
https://leetcode-cn.com/problems/find-duplicate-file-in-system/solution/zai-xi-tong-zhong-cha-zhao-zhong-fu-wen-jian-by-le/
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lc609 {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for(String str : paths){
            String[] splitStr = str.split(" ");
            String dir = splitStr[0];
            for(int i = 1; i < splitStr.length; i++){
                String content = splitStr[i].substring(splitStr[i].indexOf("(") + 1, splitStr[i].indexOf(")"));
                List<String> curRes = hashMap.getOrDefault(content, new ArrayList<>());
                curRes.add(dir + "/" + splitStr[i].substring(0, splitStr[i].indexOf("(")));
                hashMap.put(content, curRes);
            }
        }
        List<List<String>> res = new LinkedList<>();
        for(String dir : hashMap.keySet()){
            if(hashMap.get(dir).size() > 1){
                res.add(hashMap.get(dir));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] test = {"root/a 1.txt(abcd) 2.txt(efgh","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
        System.out.println(new lc609().findDuplicate(test));
    }
}
