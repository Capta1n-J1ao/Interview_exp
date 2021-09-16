package amazon;

/*
这个参考题解最好懂，而且代码性能也不错：
https://leetcode-cn.com/problems/reconstruct-itinerary/solution/java-bu-yong-ou-la-zhi-yong-hui-su-si-lu-4v83/

关于那个boolean的用法是参考这个题解，很巧妙，要当成模板记住
https://leetcode-cn.com/problems/reconstruct-itinerary/solution/java-hui-su-fa-yong-shi-8mschao-guo-7415-by-wang-k/
* */

import java.util.*;

public class lc332 {
//    private List<String> tempRes = new LinkedList<>();
    private List<String> res = new LinkedList<>();
    private HashMap<String, TreeMap<String, Integer>> hashMap = new HashMap<>();
    private List<List<String>> tickets;
    private boolean isValid;
    public List<String> findItinerary(List<List<String>> tickets) {
        this.tickets = tickets;
        for(List<String> ticket : tickets){
            String start = ticket.get(0);
            String dest = ticket.get(1);
            /*
            这个写法结合lc212，解决了我的一个很大的困惑，可以两道题目一起看
            但是这里结合332可以进一步确认时，
            虽然不能写成node = node.hashMap.putIfAbsent(ch, new TrieNode());这样，原因可以看excel
            但是却可以写成node.hashMap.putIfAbsent(ch, new TrieNode());
            然后在下一句给node赋值，改成node = node.get(ch),
            这样就可以得到那个TrieNode。
            一定要记得！不能忘，要分开赋值！
            * */
            hashMap.putIfAbsent(start, new TreeMap<>());
            TreeMap<String, Integer> curMap = hashMap.get(start);
            curMap.put(dest, curMap.getOrDefault(dest, 0) + 1);
//            System.out.println(curMap.size());
        }
        res.add("JFK");
        BackTracking(0);
        return res;
    }

//    注意这里BackTracking不能像之前的方法一样仅仅用void判断
//    如果是void的话，那么可能res会被最后一种情况给替代，所以要用一个方法能够给主函数一个判断
//    这里给出两种方法，我选择了第二种保证模板的一致性：
//    1. 和题解一样，BackTracking返回值为boolean
//    2. 增加一个boolean位，判断第一次满足条件的情况，并且及时返回
    private void BackTracking(int index){
        if(index == tickets.size()) {
            isValid = true;
            return;
        }
        String pre = res.get(res.size() - 1);
        TreeMap<String, Integer> nextStart = hashMap.get(pre);
        if(nextStart == null || nextStart.size() == 0) return;
        for(String str : nextStart.keySet()){
            if(nextStart.get(str) == 0) continue;
            res.add(str);
            nextStart.put(str, nextStart.get(str) - 1);
            BackTracking(index + 1);
            if(isValid) return;
            res.remove(res.size() - 1);
            nextStart.put(str, nextStart.get(str) + 1);
        }
    }

    public static void main(String[] args) {
        List<String> l1 = new LinkedList<>();
        l1.add("JFK");
        l1.add("SFO");
        List<String> l2 = new LinkedList<>();
        l2.add("JFK");
        l2.add("ATL");
        List<String> l3 = new LinkedList<>();
        l3.add("SFO");
        l3.add("ATL");
        List<String> l4 = new LinkedList<>();
        l4.add("ATL");
        l4.add("JFK");
        List<String> l5 = new LinkedList<>();
        l5.add("ATL");
        l5.add("SFO");
        List<List<String>> lists = new LinkedList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);
        lists.add(l5);
        System.out.println(new lc332().findItinerary(lists));

//        还可以用这种写法
//        List<List<String>> ticketsLst = new ArrayList<>();
//        String[][] tickets = new String[][]{{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}};
//        for (String[] ticket : tickets) {
//            List list = new ArrayList();
//            list.add(ticket[0]);
//            list.add(ticket[1]);
//            ticketsLst.add(list);
//        }
    }
}
