package amazon;

/*
看了下题解，本质就是轮盘赌算法,下面的思路说的听清楚：
https://leetcode-cn.com/problems/random-pick-with-weight/solution/lun-pan-du-suan-fa-by-joyboy/
看完以后可以看下下面这个题解的代码，更加清晰：
https://leetcode-cn.com/problems/random-pick-with-weight/solution/java-qian-zhui-he-er-fen-cha-zhao-zhu-xi-v6u2/
然后可以参考下官解下面的第一个评论，有具体的应用场景：
https://leetcode-cn.com/problems/random-pick-with-weight/solution/an-quan-zhong-sui-ji-xuan-ze-by-leetcode/

补充一些实战场景，以加深记忆（面试会加分吗 XD）

1. Spring Cloud Ribbon （客户端负载均衡）策略中的 WeightedResponseTimeRule
    （1） 此题可简述为「按权重，看作多个区间，按区间宽度越大，概率越大」
    （2） 在 Ribbon 相关架构中，服务端给客户端一个服务列表，类似 Map<String, Set<String>> 结构。
        若客户端想调用 key = serviceA，可选的具体服务端实例有 Set<String> 的
        ["/svc/a1", "/svc/a2", "/svc/a3"]，由客户端自行决定
    （3） Ribbon 作为客户端负载均衡来帮助客户端选择去哪个具体服务实例（a1 / a2 / a3），
        希望雨露均沾，又希望别运气不好抽到响应慢的服务器，故有了一种根据权重的均衡策略
    （4） 权重是通过定时统计最近一段时间内，a1 / a2 / a3 各自的访问响应时间如何，如 a1: 10ms，a2: 20ms，a3: 40ms
    （5） 通过算法（不赘述，有兴趣可留言喔）计算得 a1: [0, 60]，a2: (60, 110]，a3: (110, 140] 的区间对应
    （6） 下次再需要访问 serviceA 时，随机一个数 [0, 140]，看落在哪个区间，就选那个实例
2. RabbitMQ 的 Topic 交换器使用 Trie 匹配
3. MySQL 中的 IN 语法涉及二分算法
* */

import java.util.Arrays;
import java.util.Random;

public class lc528 {
//    public Solution(int[] w) {
    private int[] res;
    private int wLen, count;
    private Random random = new Random();
    public lc528(int[] w) {
        this.wLen = w.length;
        this.res = new int[wLen];
//        初始化
        res[0] = w[0];
        for(int i = 1; i < wLen; i++){
            res[i] = res[i - 1] + w[i];
        }
        count = res[wLen - 1];
    }

    public int pickIndex() {
        int target = random.nextInt(count);
        int left = 0, right = wLen - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
//            这里二分的思路就是找到刚好大于target的最小的前缀和，
//            比如前缀和是[1,4]，那么如果target = 3那就要找到4而不是1
            if(res[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        lc528 test = new lc528(new int[]{1});
        int rest = test.pickIndex();
        System.out.println(rest);
    }
}
