package amazon;

/*
这道题目主要就是在判断数字的时候细节很多，要注意两种情况，就是corner case下面的两种，以正数为例
首先需要确认一个事情那就是Integer.max/min都是10位数
1. 数字位数 > 10位，那么这个数无论前九位怎么样，铁定返回Integer.max
2. 数字位数 = 10位，那么需要判断前九位和Integer.max前九位的关系，其中分两种，
        （1）一种是数字前九位 > Integer.max前九位，也是铁定返回Integer.max
        （2）一种是数字前九位 = Integer.max前九位，那么要通过判断最后一位来确定返回哪种
*/

public class lc8 {
    public int myAtoi(String str){
        char[] sChar = str.toCharArray();
        int index = 0, sLen = str.length();

        while (index < sLen && sChar[index] == ' '){
            index++;
        }
//        Corner Case,防止str全是空格
        if(index == sLen) return 0;
//        看数字正负
        int flag = 1;
        if(sChar[index] == '+') index++;
        else if(sChar[index] == '-'){
            flag = -1;
            index++;
        }

//        整道题目最难的地方就在这里
        int res = 0;
        while (index < sLen){
            if(sChar[index] < '0' || sChar[index] > '9') break;
            if(flag == 1 && (Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE / 10 == res && sChar[index] >= '7'))) return Integer.MAX_VALUE;
            else if(flag == -1  && (Integer.MIN_VALUE / 10 > res || (Integer.MIN_VALUE / 10 == res && sChar[index] > '8'))) return Integer.MIN_VALUE;
            int curBit = (sChar[index] - '0');
            res = ((res * 10) + (flag * curBit));
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc8().myAtoi("-91283472332"));
        System.out.println(new lc8().myAtoi("91283472332"));
        System.out.println(new lc8().myAtoi("2147483648"));
        System.out.println(new lc8().myAtoi("4193 with words"));
        System.out.println(new lc8().myAtoi(""));
    }
}
