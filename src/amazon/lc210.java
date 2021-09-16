package amazon;

/*
题目的解释：
注意是可以允许课程没有先修课的

把HashMap 改为HashSet[]会快一点，看一刷的写法就行
根据lc210 - lc207 - lc997的顺序来做

五刷的时候是因为lc743过来的，这里有个需要注意的点就是：
像这种出度入度的问题，其实是用来检测有向图是不是有环的问题
而像lc743这种题目有环是无所谓的，所以要区分开来，但是两种题目的本质其实是一样的
那就是BFS
* */

import java.util.*;

public class lc210 {
//    private int[] res;
//    private HashMap<Integer, List<Integer>> hashMap;
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        res = new int[numCourses];
//        if(numCourses <= 0 ) return res;
//        int[] inDegree = new int[numCourses];
//        hashMap = new HashMap<>();
//        for(int [] pre : prerequisites){
//            if(!hashMap.containsKey(pre[1])){
//                hashMap.put(pre[1], new LinkedList<>());
//            }
//            hashMap.get(pre[1]).add(pre[0]);
//            inDegree[pre[0]]++;
//        }
//        Deque<Integer> stack = new ArrayDeque<>();
//        int index = 0;
//        for(int i = 0; i < res.length; i++){
//            if(inDegree[i] == 0) stack.addFirst(i);
//        }
//        while (!stack.isEmpty()){
//            int sLen = stack.size();
//            for(int i = 0; i < sLen; i++){
//                int temp = stack.pollFirst();
//                res[index] = temp;
//                index++;
//                List<Integer> list = hashMap.get(temp);
//                if(list == null){
//                    if(index == numCourses) return res;
//                    break;
//                }
//                for(int num : list){
//                    inDegree[num]--;
//                    if(inDegree[num] == 0) {
//                        stack.addFirst(num);
//                    }
//                }
//
//            }
//        }
//        return new int[0];
//    }

//    四刷，2021/04/22
//    private int[] res;
//    private HashMap<Integer, List<Integer>> hashMap;
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        res = new int[numCourses];
//        if(numCourses <= 0 ) return res;
//        int[] inDegree = new int[numCourses];
//        hashMap = new HashMap<>();
//        for(int [] course : prerequisites){
//            inDegree[course[0]]++;
//            hashMap.putIfAbsent(course[1], new LinkedList<>());
//            hashMap.get(course[1]).add(course[0]);
//        }
//        Queue<Integer> queue = new LinkedList<>();
//        for(int i = 0; i < numCourses; i++){
//            if(inDegree[i] == 0) queue.add(i);
//        }
//        int count = 0;
//        while (!queue.isEmpty()){
//            int qLen = queue.size();
//            for(int i = 0; i < qLen; i++){
//                int temp = queue.poll();
//                res[count++] = temp;
//                if(count == numCourses) return res;
//                List<Integer> nextCourse = hashMap.get(temp);
////                这句话很重要，否则就会错，参考第二个test case
//                if(nextCourse == null) break;
//                for(int course : nextCourse){
//                    inDegree[course]--;
//                    if(inDegree[course] == 0) queue.add(course);
//                }
//            }
//        }
//        return new int[0];
//    }

//    五刷，2021/5/16，这次写的比较顺利，如果有什么疑问看四刷
    private int[] res;
    private HashMap<Integer, List<Integer>> hashMap;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        hashMap = new HashMap<>();
        res = new int[numCourses];
        for(int[] pre : prerequisites){
            inDegree[pre[0]]++;
            hashMap.putIfAbsent(pre[1], new LinkedList<>());
            hashMap.get(pre[1]).add(pre[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) if(inDegree[i] == 0) queue.add(i);
        int index = 0;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int curCourse = queue.poll();
                res[index++] = curCourse;
                if(index == numCourses) return res;
                List<Integer> next = hashMap.get(curCourse);
//                参考第二个test case
                if(next == null) break;
                for(int course : next){
                    inDegree[course]--;
                    if(inDegree[course] == 0) queue.add(course);
                }
            }
        }
        return new int[]{};
    }
    public static void main(String[] args) {
        int[][] test = {{1,0}};
//        正确答案是[0,1]
//        System.out.println(Arrays.toString(new lc210().findOrder(2,test)));

//        int[][] test = {};
//        System.out.println(Arrays.toString(new lc210().findOrder(2,test)));
    }
}
