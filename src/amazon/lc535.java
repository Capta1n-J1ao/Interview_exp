package amazon;

/*
比较简单，直接参考官解即可：
自己想出来的方法一，如果想要更好的性能参考方法6，原理基于方法2
https://leetcode-cn.com/problems/encode-and-decode-tinyurl/
* */

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Random;

public class lc535 {
//    方法一比较简单，但是并不是非常巧妙，并且对加密的数据的上限是int.maxvalue
//    private HashMap<Integer, String> hashMap = new HashMap<>();
//    private int count = 0;
//    // Encodes a URL to a shortened URL.
//    public String encode(String longUrl) {
//        hashMap.putIfAbsent(count, longUrl);
//        return "http://tinyurl.com/" + count++;
//    }
//
//    // Decodes a shortened URL to its original URL.
//    public String decode(String shortUrl) {
//        int index = Integer.valueOf(shortUrl.substring(19));
//        return hashMap.get(index);
//    }

//  基于方法2的方法6实现
    private HashMap<String, String> hashMap = new HashMap<>();
    private String encodeDic = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random random = new Random();
    private String key = keyGen();
    private final int keyLen = 6;

//    密钥生成,固定每个密钥均为6位，当然这个长度可以自己设
    private String keyGen(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < keyLen; i++){
            sb.append(random.nextInt(62));
        }
        return new String(sb);
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (hashMap.containsKey(key)) key = keyGen();
        hashMap.putIfAbsent(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return hashMap.get(shortUrl.substring(19));
    }
}
