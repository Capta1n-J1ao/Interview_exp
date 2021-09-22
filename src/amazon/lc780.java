package amazon;

/*
这道题目乍一看很简单，用BFS能做出来，但是一定是超时的，所以要想其他办法
常规郭郭的视频，讲的很清楚，本质就是从底往上面求：
https://www.bilibili.com/video/BV1P54y157Ak?spm_id_from=333.999.0.0
* */

public class lc780 {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if(sx == tx && sy == ty) return true;
//        因为sx/sy都是>0的，所以不用考虑等于的情况,还有这里不能用<=,case2会有问题
        while (sx < tx && sy < ty){
            if(tx > ty) tx %= ty;
            else ty %= tx;
        }
//        System.out.println(tx);
//        System.out.println(ty);
//        这样写的话case2是过不了的
//        if(tx > ty) return tx - ty == sx;
//        else return ty - tx == sy;
//        下面这么写的原因看下视频的代码部分，一下子就懂了，
//        其实到了这步相当于tx和ty里面至少有一个等于sx或者sy了，那么相当于后面只可以减另外一个了
        if(tx == sx && ty > sy && (ty - sy) % sx == 0) return true;
        else if(tx > sx && ty == sy && (tx - sx) % sy == 0) return true;
        else return false;
    }

    public static void main(String[] args) {
//        System.out.println(new lc780().reachingPoints(1,1,3,5));
        System.out.println(new lc780().reachingPoints(3,3,12,9));
    }
}
