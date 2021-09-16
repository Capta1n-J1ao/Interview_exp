package amazon;

import java.util.LinkedList;
import java.util.List;

public class lc93 {
    private List<String> res = new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
        if(s == null || s.length() == 0 || s.length() > 12) return res;
        BackTracking(s,0, new LinkedList<>());
        return res;
    }

    private void BackTracking(String str, int index, List<String> curRes){
        if(index == str.length() && curRes.size() == 4){
            res.add(String.join(".", curRes));
            return;
        }
        for(int i = 1; i <= 3; i++){
//            下面的剪枝很重要
            if(index + i > str.length()) return;
            if(str.length() - (index + i) > (4 - curRes.size()) * 3) return;
//            剪枝结束
            String curIp = str.substring(index, index + i);
            if(isValid(curIp)){
                curRes.add(curIp);
                BackTracking(str, index + i, curRes);
                curRes.remove(curRes.size() - 1);
            }else return;
        }
    }

    private boolean isValid(String str){
        int num = Integer.valueOf(str);
        if(str.length() > 1 && str.charAt(0) == '0') return false;
        if(0 <= num && num <= 255) return true;
        return false;
    }

    public static void main(String[] args) {
        String test = "010010";
        System.out.println(new lc93().restoreIpAddresses(test));
    }
}
