package tiktok;

/*
* */

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class isthisaTree {
    public static String sExpression(String nodes) {
        // Write your code here
        String sPattern = "\\(\\w\\,\\w\\)*";
        String str = "(a,b) (c,d) (e,f) (a,b) (a,b)";
        Pattern p = Pattern.compile(sPattern);
        Matcher matcher = p.matcher(str);
        while(matcher.find()){
            String temp = matcher.group();
            temp = temp.substring(1, temp.length() - 1);
            String[] curRes = temp.split(",");
            System.out.println(Arrays.toString(curRes));
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(new isthisaTree().sExpression(" "));
    }
}
