package amazon;

import com.sun.jdi.ArrayReference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class lc56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int iLen = intervals.length;
        List<int[]> tempRes = new LinkedList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for(int i = 1; i < iLen; i++){
            if(intervals[i][0] <= end){
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
            }else {
                tempRes.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        tempRes.add(new int[]{start, end});

        int[][] res = new int[tempRes.size()][2];
        int index = 0;
        for(int[] curRes : tempRes){
            res[index] = curRes;
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] test = {{1,3},{2,6},{8,10},{15,18}};
        int[][] testRes = new lc56().merge(test);
        for(int[] res : testRes){
            System.out.println(Arrays.toString(res));
        }
    }
}
