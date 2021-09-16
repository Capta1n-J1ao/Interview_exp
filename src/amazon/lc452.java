package amazon;

import java.util.Arrays;
import java.util.Comparator;

public class lc452 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
//          [[-2147483646,-2147483645],[2147483646,2147483647]] 会过不了
//              return o1[1] - o2[1];  按照每个区间的结尾进行升序排序
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int bound = points[0][1], res = 1, pLen = points.length;
        for(int i = 1; i < pLen; i++){
            if(points[i][0] <= bound) continue;
            res++;
            bound = points[i][1];
        }
        return res;
    }
}
