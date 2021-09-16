package amazon;
/*
12/13建议一起做
* */

public class lc13 {
    int[] numRef = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    String[] romanRef =  {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public int romanToInt(String s) {
        int sLen = s.length();
        char[] sChar = s.toCharArray();
        int index = 0, res = 0;
        while (index < sLen){
            for (int k = 0; k < romanRef.length; k++){
                String ref = romanRef[k];
                int rLen = ref.length();
                if(index + rLen > sLen) continue;
                if(s.substring(index, index + rLen).equals(ref)){
                    res += numRef[k];
                    index += rLen;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc13().romanToInt("LVIII"));
        System.out.println(new lc13().romanToInt("MCMXCIV"));
    }
}
