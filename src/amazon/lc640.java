package amazon;

/*
典型的String操作考试题，囊括了很多种类型的String操作，非常好的一道锻炼命令的题目，
比如string.replace, string.contains等等

算法方面还是比较简单的

参考题解：
https://leetcode-cn.com/problems/solve-the-equation/solution/javajian-dan-chu-li-zuo-you-fen-bie-chu-li-main640/
* */

public class lc640 {
    public String solveEquation(String equation) {
        String[] equalSplit = equation.split("=");
        int[] leftRes = helper(equalSplit[0]);
        int[] rightRes = helper(equalSplit[1]);
        int xRes = leftRes[0] - rightRes[0];
        int numRes = rightRes[1] - leftRes[1];
        if(xRes == 0){
            if(numRes == 0) return "Infinite solutions";
            else return "No solution";
        }else return "x=" + numRes/xRes;
    }

    private int[] helper(String str){
//        注意“+”的特殊性，要写成下面这样
        String[] plusSplit = str.replace("-", "+-").split("\\+");
        int xPlus = 0, numPlus = 0;
        for(String st : plusSplit){
            if(st.equals("x")) xPlus++;
            else if(st.equals("-x")) xPlus--;
            else if(st.contains("x")) xPlus += Integer.valueOf(st.substring(0, st.length() - 1));
//            这个是针对下面第二个case的
            else if(!st.equals(""))numPlus += Integer.valueOf(st);
            else continue;
        }
        return new int[]{xPlus, numPlus};
    }

    public static void main(String[] args) {
//        System.out.println(new lc640().solveEquation("x+5-3+x=6+x-2"));
        System.out.println(new lc640().solveEquation("-x=-1"));
    }
}
