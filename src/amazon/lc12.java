package amazon;

/*
这道题用常规思路很好做，不用再写了，一次就AC了，主要是想一下用贪心怎么做，
两个方法其实时间复杂度都是O(1),因为定死了最大3999，所以性能上几乎一样
liweiwei题解很漂亮：
https://leetcode-cn.com/problems/integer-to-roman/solution/tan-xin-suan-fa-by-liweiwei1419/
* */

public class lc12 {
//    public String intToRoman(int num) {
//        int thousand = num / 1000;
//        num %= 1000;
//        int hundred = num / 100;
//        num %= 100;
//        int tens = num / 10;
//        num %= 10;
//        int digit = num;
//        StringBuilder res = new StringBuilder();
//
//        if(thousand != 0){
//            if(thousand < 4){
//                for (int i = 0;i < thousand; i++){
//                    res.append('M');
//                }
//            }
//        }
//
//        if(hundred != 0){
//            if(hundred == 4) res.append("CD");
//            else if(hundred == 9) res.append("CM");
//            else if(hundred < 5){
//                for(int i = 0; i < hundred; i++){
//                    res.append('C');
//                }
//            }
//            else if(hundred >= 5){
//                res.append('D');
//                for(int i = 0; i < hundred - 5; i++){
//                    res.append('C');
//                }
//            }
//        }
//
//        if(tens != 0){
//            if(tens == 4) res.append("XL");
//            else if(tens == 9) res.append("XC");
//            else if(tens < 5){
//                for(int i = 0; i < tens; i++){
//                    res.append('X');
//                }
//            }
//            else if(tens >= 5){
//                res.append('L');
//                for(int i = 0; i < tens - 5; i++){
//                    res.append('X');
//                }
//            }
//        }
//
//        if(digit != 0){
//            if(digit == 4) res.append("IV");
//            else if(digit == 9) res.append("IX");
//            else if(digit < 5){
//                for(int i = 0; i < digit; i++){
//                    res.append('I');
//                }
//            }
//            else if(digit >= 5){
//                res.append('V');
//                for(int i = 0; i < digit - 5; i++){
//                    res.append('I');
//                }
//            }
//        }
//        return res.toString();
//    }


//    方法二：liweiwei方法
    int[] numRef = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    String[] romanRef =  {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < 13){
            while (num >= numRef[index]){
                res.append(romanRef[index]);
                num -= numRef[index];
            }
            index++;
        }
        return res.toString();
    }


    public static void main(String[] args) {
        System.out.println(new lc12().intToRoman(1994));
    }
}
