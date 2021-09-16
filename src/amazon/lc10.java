package amazon;

/*
建议 583/72/10/97 一起服用
这个dp的方式和lc72有点像，但是这道题目更难，至少预留两个小时，可以先看这个题解，已经算讲的最清楚的了，
但是有个问题就是他对于碰到"*"如果往前两个位置为false，需要看当前上一行的那种情况讲的不是很清楚
而这个也是整个代码里面最难得点，目前看下来几乎没有题解讲得清楚
https://leetcode-cn.com/problems/regular-expression-matching/solution/shi-pin-tu-jie-dong-tai-gui-hua-zheng-ze-biao-da-s/

对于dp[i - 1][k]的讲解，个人觉得这个题解更加好，这个是最容易懂的说法了，
dp[i-1][j]表示s的前i-1个字符串是否和现在的前k个字符串匹配（即包含*的字符串），如果匹配，并且s[i]和'*'之前即p[k - 1]的这个字符又一样（或者'*'之前的这个字符为'.'）
则dp[i][j]==True，表示s[i]被这个星号所“吸收”。
结合我在代码里面写的在理解下：
dp[i - 1][k] 这个最难理解，也是很多题解没有说清楚的，
它的作用就是让pPre复制>1次，不能等于1，因为第三种情况是等于一次的
下面这个很重要，判断pPre == sCh 和 dp[i - 1][k]其实是在判断sPre和pPre是否相等
这个不是打错，其中sPre就是sCh的前一个字符，dp[i - 1][k]其实在判断的是sPre和pCh的关系
所以换句话来说就是在判断pPre == sCh和sPre和pCh，即pPre + pCh 是否等于 sPre + sCh
这样的话其实就判断了如果sPre + sCh是想等的两个字符，
那么在sCh = '*'的情况下，sPre只要和sPre + sCh均相等，
那么他们也是可以成功匹配的，这个关系很绕，可以用第一个testcase里面ssip和s*p比较一下就懂了
用第三个test case就能知道dp[i - 1][k]的重要性，当然s=aaaaaaa的话画个表格就更清楚
可以发现后面都是靠dp[i - 1][k]来判断得到正确值的
https://leetcode-cn.com/problems/regular-expression-matching/solution/ru-guo-ni-xian-ru-jue-wang-bu-zhi-dao-wo-j0zi/
* */

public class lc10 {
    public boolean isMatch(String s, String p) {
        if(p == null) return true;
        if(s == null) return false;
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        int sLen = s.length();
        int pLen = p.length();
//        这里dp的设置其实还是和lc72一样的，那就是把0行和0列设成和空字符串比较，
//        这样会有很多优势，可以参考lc72的题解
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
//        初始化参考lc72里面的原理,其中0列可以不用设，因为s里面没有符号，
//        铁定和空字符串无法匹配
        dp[0][0] = true;
        for(int i = 1; i <= pLen; i++){
            char ch = pChar[i - 1];
            if(i > 1 && ch == '*' && dp[0][i - 2]){
                dp[0][i] = true;
            }
        }

        /*
        下面这里是参考第二个题解的，因为我觉得第一个题解里面对于pCh = ‘*’的解释并不符合我的思路
        而第二个题解里面的分类讨论更加符合我的思路，主要难点就是pCh = ‘*’这个分支的写法
        */
        for(int i = 1; i <= sLen; i++){
            for(int k = 1; k <= pLen; k++){
                char sCh = sChar[i - 1];
                char pCh = pChar[k - 1];
                if(sCh == pCh || pCh == '.') dp[i][k] = dp[i - 1][k - 1];
                else if(pCh == '*' && k > 1){
                    char pPre = pChar[k - 2];
                    /*
                    下面几句话是整个题目最难得地方，参考第二个题解里面的分类讨论：
                    1.  dp[i][k] = dp[i][k - 2] 这个最好理解，两个题解里面都说的很清楚
                    2.  dp[i - 1][k] 这个最难理解，也是很多题解没有说清楚的，
                        它的作用就是让pPre复制>1次，不能等于1，因为第三种情况是等于一次的
                        下面这个很重要，判断pPre == sCh 和 dp[i - 1][k]其实是在判断sPre和pPre是否相等
                        这个不是打错，其中sPre就是sCh的前一个字符，dp[i - 1][k]其实在判断的是sPre和pCh的关系
                        所以换句话来说就是在判断pPre == sCh和sPre和pCh，即pPre + pCh 是否等于 sPre + sCh
                        这样的话其实就判断了如果sPre + sCh是想等的两个字符，
                        那么在sCh = '*'的情况下，sPre只要和sPre + sCh均相等，
                        那么他们也是可以成功匹配的，这个关系很绕，可以用第一个testcase里面ssip和s*p比较一下就懂了
                        用第三个test case就能知道dp[i - 1][k]的重要性，当然s=aaaaaaaaa的话画个表格就更清楚
                        可以发现后面都是靠dp[i - 1][k]来判断得到正确值的
                    3.  dp[i][k - 1] 就是相当于*让pPre只重复了一次，也就是说相当于没有*这位
                    * */
                    if(sCh == pPre || pPre == '.') dp[i][k] = dp[i][k - 2] || dp[i - 1][k] || dp[i][k - 1];
//                    其实dp[i][k - 1]不一定需要，也是过得，但是这样又需要多想很久为什么，
//                    还是用上面那个可以少想
//                    if(sCh == pPre || pPre == '.') dp[i][k] = dp[i][k - 2] || dp[i - 1][k];
//                    这个情况就很简单，如果sCh != pPre，
//                    那说明*的作用就不能复制pPre，所以只能看把pPre删除的条件来看
//                    这个用举例子的形式就是下面第一个test case，
//                    核心判断就是其中ssi和s*中ssi的“i”和s*的“s”的比较，
//                    因为i != s，所以就是false
                    else dp[i][k] = dp[i][k - 2];
                }
            }
        }
        return dp[sLen][pLen];
    }

    public static void main(String[] args) {
        System.out.println(new lc10().isMatch("mississippi", "mis*is*p*."));
//        System.out.println(new lc10().isMatch("ab", ".*"));
//        System.out.println(new lc10().isMatch("aaaa", ".*"));
    }
}
