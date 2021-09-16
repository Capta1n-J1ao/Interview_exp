package amazon;

/*
这道题目很繁琐，主要是先要想到思路，很重要，这个视频说的很清楚：
https://www.bilibili.com/video/BV1mK4y1v7xk?from=search&seid=14492285447262678370

然后注意以下几点：
1. 对于直接生成List需要用Arrays.asList命令
2. 要注意String是没有reverse这个函数的，只有StringBuilder有，这个必须掌握
3. 对于划分区间的方法，方法一和方法二花了大量的时间在这个debug上面，因为真的非常难
   但是只要想清楚了，用范围括号清晰地表达出来的话就问题不大了。
4. 方法一用两个for循环容易过的核心原因就是没有出现k - 1这个情况，k指的是当前分析的字符串的位置
   而方法二问题多也正是因为这个情况，比如k = 0的时候，如果是k - 1，以第一个test case为例
   以其中的第一项"ab"为例，在判断的程序看来是"" + "ab" + ""，那么k - 1相当于检查了"ab"最左边的空字符串，
   发现是回文串，所以在hashMap里面找"ba",找到了，然后添加结果。同理，然后在k = 2找到最左边的空字符串也是回文串
   这样也会添加结果。到了第二项"ba"，也是同样的过程，但是重复添加了结果。所以出了问题。
   那么总结下来就是：
   在一项字符串中顶多找一个空字符串作为的回文串！！比如对于"ab"来说，你要么判断"" + "ab"，要么判断"ab" + "",
   不能把他判断成"" + "ab" + ""，因为如果有了其他例如"ba"，这样也会变成"" + "ba" + "",造成重复计算
   这也是对方法二debug了很久找到的问题，需要牢记！
   反观方法一，通篇都没有k - 1这个情况，也就是没有检查最左边的空字符串，所以就没有问题。
   所以还是用方法一，方法二不推荐，不要写了
5. 关于回文判断函数的定义也要仔细看一下，特别是corner case
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lc336 {
//    方法一，看完思路以后自己写的，但是在从左往右和从右往左判断的时候有两个for循环
//    经过实际测试，方法一和方法二效率差不多，分析原因应该就是都是对字符串进行两边palindrome查找
//    在测试样本不大的情况下差距不明显，但是如果一旦测试样本大了，方法一相当于遍历了两边字符串
//    一定比方法二只遍历一遍的字符串效率要低。综合下来看虽然自己写的方法一效率稍低，但是代码一致性好
//    不用多余修改，方法二的代码还需要考虑corner case，很不方便，巨多坑
//    但是下面对于区间分类的思想的注释还是非常值得看的
    private List<List<Integer>> res = new LinkedList<>();
    private HashMap<String, Integer> hashMap;
    public List<List<Integer>> palindromePairs(String[] words) {
        if(words == null) return res;
        int wLen = words.length;
        hashMap = new HashMap<>();
        for(int i = 0; i < wLen; i++) hashMap.putIfAbsent(words[i], i);
        for(int i = 0; i < wLen; i++){
            String curWord = words[i];
            int sLen = curWord.length();
//            按照下面第一个test case的情况，“abcd"要在最后
//            把 ”” 即空字符串认为palindrome才能生效
//            for(int k = 0; k < sLen; k++){
            for(int k = 0; k <= sLen; k++){
//                注意这个isPalindrome的函数，如果碰到""即空字符的时候是则么判断的
//                可以用第二个test case辅助着看
//                注意这里isPalindrome的判断区间是[k, sLen - 1]
//                toFind判断区间则是[0,k)
                if(isPalindrome(curWord, k, sLen - 1)){
                    int leftFindIndex = toFind(curWord,0, k);
//                    这个判断很关键
                    if(leftFindIndex == -1 || leftFindIndex == i) continue;
                    res.add(Arrays.asList(i, leftFindIndex));
                }
            }
//                注意这里isPalindrome的判断区间是[0,m]
//                toFind判断区间则是[m + 1,sLen)，即 [m + 1,sLen - 1]
            for(int m = sLen - 1; m >= 0; m--){
                if(isPalindrome(curWord, 0, m)){
                    int rightFindIndex = toFind(curWord, m + 1, sLen);
                    if(rightFindIndex == -1 || rightFindIndex == i) continue;
                    res.add(Arrays.asList(rightFindIndex, i));
                }
            }
        }
        return res;
    }

//    方法二，参考视频写的，一开始以为就是把上面的代码改一下就行
//    后面发现不行，原因会在for循环里面讲，综合下来看虽然自己写的代码效率稍低，但是代码一致性好
//    不用多余修改，下面的代码还需要考虑corner case，不停修改，不建议
//    private List<List<Integer>> res = new LinkedList<>();
//    private HashMap<String, Integer> hashMap;
//    public List<List<Integer>> palindromePairs(String[] words) {
//        if(words == null) return res;
//        int wLen = words.length;
//        hashMap = new HashMap<>();
//        for(int i = 0; i < wLen; i++) hashMap.putIfAbsent(words[i], i);
//        for(int i = 0; i < wLen; i++){
//            String curWord = words[i];
//            int sLen = curWord.length();
////            按照下面第一个test case的情况，“abcd"要在最后
////            把 ”” 即空字符串认为palindrome才能生效
////            for(int k = 0; k < sLen; k++){
//            for(int k = 0; k <= sLen; k++){
////                注意这个isPalindrome的函数，如果碰到""即空字符的时候是则么判断的
////                可以用第二个test case辅助着看
//                if(isPalindrome(curWord, k, sLen - 1)){
//                    int leftFindIndex = toFind(curWord,0, k);
////                    这个判断很关键
//                    if(leftFindIndex != -1 && leftFindIndex != i) {
//                        res.add(Arrays.asList(i, leftFindIndex));
//                    }
//                }
///*
//                题解里面的写法，感觉效率会高，因为省掉了一个遍历字符串的循环
//                这里要注意的就是如果按照方法一的写法，有了重复情况会被continue从而跳出循环
//                所以这里要反过来写，当然如果改了方法一的写法进行统一的话也就没问题了
//                但是为了保证思路连贯性，我在方法一保留了自己的写法，在这里提出
//                下面这个if语句的核心在于是不是把k给包含进去，比如isPalindrome这个函数
//                是指在[0,k - 1]是不是回文数，那么在下面的rightFindIndex就必须包含k
//                否则k就被遗漏了。那么换一种想法如果isPalindrome这个函数
//                是指在[0,k]是不是回文数，那么下面的rightFindIndex的范围是不是可以变成[k + 1, sLen]？
//                答案是不行的，因为k最高取到sLen,s+1会超范围，所以不对
//*/
////                视频中的题解本来是 k != 0，但是我觉得改为k > 0更加容易懂，
////                不能单单只判断isPalindrome(curWord, 0, k - 1)，具体原理看最顶上的第三、四点
////                而且错误并不是必现，而是当两个字符串长度相同，并且是相互颠倒的不能完全重复的字符串，比如test case3
////                如果是"aa"和"aa"的话是不会错的，
////                因为有if(rightFindIndex != -1 && rightFindIndex != i)来保证不会有重复答案
////                if (isPalindrome(curWord, 0, k - 1)) {
//                if (k > 0 && isPalindrome(curWord, 0, k - 1)) {
////                    这里也和上面的代码不一样，比较tricky，注意是k不能是k+1，原因如第二个testcase
//                    int rightFindIndex = toFind(curWord, k, sLen);
////                    这个是方法一的写法
////                    if(rightFindIndex == -1 || rightFindIndex == i) continue;
//                    if(rightFindIndex != -1 && rightFindIndex != i) {
//                        res.add(Arrays.asList(rightFindIndex, i));
//                    }
//                }
//            }
//        }
//        return res;
//    }

//    这个函数的作用是计算str的[left,right]区间是否是回文串，
//    如果出现left > right的情况那么就一定是在判断空字符串，直接返回true
//    而且判断空字符串顶多就是left = right + 1，这一种情况
    private boolean isPalindrome(String str, int left, int right){
        char[] sChar = str.toCharArray();
        int sLen = (right - left + 1) / 2;
        for(int i = 0; i < sLen; i++){
            if(sChar[left + i] != sChar[right - i]) return false;
        }
        return true;
    }

    private int toFind(String str, int left, int right){
        String toFind = new StringBuilder(str.substring(left, right)).reverse().toString();
        return hashMap.getOrDefault(toFind, -1);
    }

    public static void main(String[] args) {
        String[] test = {"ab","ba"};
        System.out.println(new lc336().palindromePairs(test));

//        String[] test = {"a",""};
//        System.out.println(new lc336().palindromePairs(test));

//        String[] test = {"abcdefg","gfedcba"};
//        System.out.println(new lc336().palindromePairs(test));
    }
}
