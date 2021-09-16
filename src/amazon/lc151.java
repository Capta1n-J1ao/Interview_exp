package amazon;

/*
没难度，秒杀
* */

public class lc151 {
    public String reverseWords(String s) {
        s = s.trim();
        String[] sArray = s.split(" ");
        int sLen = sArray.length;
        StringBuilder res = new StringBuilder();
        for(int i = sLen - 1; i >= 0; i--){
            String curRes = sArray[i];
            if(curRes.equals("")) continue;
            res.append(curRes);
            res.append(" ");
        }
        return res.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(new lc151().reverseWords("  damon is  god "));
    }
}
