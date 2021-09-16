package amazon;

/*
一开始想用StringBuilder来偷懒，但是有几个问题：
1. Integer.valueof并不能把二进制String改为int
2. int必须是32位的，所以不能用while，而是要用for来固定23位补齐后面的0
* */

public class lc190 {
//    这个方法不行，原因已经说了
//    public int reverseBits(int n) {
//        int temp = n;
//        StringBuilder sb = new StringBuilder();
//        while (temp != 0){
//            int curBit = temp & 1;
//            sb.append(String.valueOf(curBit));
//            temp >>= 1;
//        }
//        return Integer.valueOf(sb.toString());
//    }

    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            //如果下面这句不写就是错的，
            // 为什么第一种方法需要res先左移一位呢？
            // res不是已经是0了吗?
            // 感谢一位答主的帮助，我知道了原因，他的原话是：
            // 保证最后一次循环的时候如果是1能加上去，
            // 放在后面左移，那么每次循环后r最后一位都是0
            //接下来就是用下面main里面的test case来辅助讲解:
            // 首先43261596的二进制如下，一共26位：
            //10100101000001111010011100
            //用正确的方法得到的二进制如下：
            //111001011110000010100101000000
            //用不正确的方法得到的二进制如下：
            //1110010111100000101001010000000
            // 可以发现多了一个零，这个就是那个人所说的在最后一个元素加完以后
            // 他额外多移了一位，这就是问题的根源
            res <<= 1;
            res += (n & 1);
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc190().reverseBits(43261596));
    }
}
