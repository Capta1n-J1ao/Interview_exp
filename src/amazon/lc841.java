package amazon;

/*
常规BFS，很简单，直接AC
* */

import edu.princeton.cs.algs4.In;

import java.util.*;

public class lc841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int rLen = rooms.size();
        boolean[] visited = new boolean[rLen];
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int curRoom = queue.poll();
                List<Integer> curKey = rooms.get(curRoom);
                for(int key : curKey){
//                    if(visited[rLen - 1]) return true;
                    if(visited[key]) continue;
                    visited[key] = true;
                    queue.add(key);
                }
            }
        }
        for(boolean isVisited : visited) if(!isVisited) return false;
        return true;
    }

    public static void main(String[] args) {
        List<Integer> l1 = new LinkedList<>(){
            {
                this.add(1);
            }
        };

        List<Integer> l2 = new LinkedList<>(){
            {
                this.add(2);
            }
        };

        List<Integer> l3 = new LinkedList<>(){
            {
                this.add(3);
            }
        };

        List<Integer> l4 = new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>(){{
           this.add(l1);
           add(l2);
           add((l3));
           add(l4);
        }};
        System.out.println(new lc841().canVisitAllRooms(list));
    }
}
