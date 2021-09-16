package amazon;

/*
这道题是目前搜索里面最难的题目，没有之一，个人认为需要预处理方法留一个小时，Trie留一个小时比较合适，建议做此题前先做一下lc46找找感觉，
看看纯BackTracking怎么做，然后再看添加DFS怎么做。

这道题目需要结合DFS和回溯才能够做出来，一开始只用了DFS，但是会有test case过不去，所以想到还要用回溯。
但是回溯的时候还要结合DFS，所以debug了很久

这个方法是自己想的，没有看题解，最容易想到,但是效率偏低，所以建议还会一种Trie的方法，并且还可以当做复习。

当然我这个方法还可以先预处理下数据，这样会快很多，但是根据二刷的结果也过不了了：
https://leetcode-cn.com/problems/word-search-ii/solution/yu-chu-li-shen-du-you-xian-sou-suo-by-zh-3upr/

然后就是使用最多的用Trie的方法，这个题解和我的几乎一模一样，我也是在他帮助下才debug好的，但是其实效率并不高，改成数组会有改善，
但是还是不好，权当复习Trie，而且由于这道题目的原因，对Trie还做了些小改动，方便代码：
https://leetcode-cn.com/problems/word-search-ii/solution/java-zi-dian-shu-jie-fa-by-heidi-1/
下面这个是剪枝版本，快挺多，但是和前两个方法还是有差距，有空可以搞搞,这次没搞出来，差一点点：
https://leetcode-cn.com/problems/word-search-ii/solution/java-jian-ji-ming-liao-qian-zhui-shu-dfssi-xiang-b/

综上所述，根据效率来说是 方法二 > 方法一 > 方法三

二刷更新，用预处理的方法也已经超时，没法通过了。二刷的时候尝试了剪枝，就是搞了个maxLength,但是效率并没有明显提升，
但是从代码来看思路几乎一样，所以应该是用的某个数据结构不一样导致的，试了一下和题解一样用hashSet,但是没什么变化
然后经过思考和试验，发现是因为题解里面有个deleteLeaf的方法导致速度快了很多，原理的话可以参考这个题解，
经过多次试验，终于找到原因了！其实本质就是对于一开始的root要一直保留，如果有一个答案是经过root中的一个trieNode然后到底，
并且整个path只有这一路的时候，那就要删掉这个trieNode之后所有的，所以要保留这个root，具体件方法三代码！！！debug很久！！！
但是我感觉其实还有更加优化的办法，可以之后再试试看，主要是方法不知道怎么进一步实现
https://leetcode-cn.com/problems/word-search-ii/solution/qian-zhui-shu-di-gui-jian-zhi-4xing-dai-ma-chao-97/
其实这道题目有两种思路：
1. 一种是从board考虑，这种方法由于要走很多弯路，所以时间会长
2. 另一种是从words考虑，用words本身去减少多余的判断
* */

import java.util.*;

public class lc212 {
//    private List<String> res = new LinkedList<>();
//    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
//    private int row, col;
//    char[][] board;
//    String[] words;
//    private HashSet<String> hashSet = new HashSet<>();
//    方法一： 自己想的方法，效率勉强
//    public List<String> findWords(char[][] board, String[] words) {
//        this.board = board;
//        this.words = words;
//        row = board.length;
//        col = board[0].length;
//        if(board == null || row == 0) return res;
//        for(String word : words){
//            for(int i = 0; i < row; i++){
//                for(int k = 0; k < col; k++){
//                    boolean[][] visited = new boolean[row][col];
//                    if(!hashSet.contains(word)) BackTracking(word, 0, i, k, visited);
//                }
//            }
//        }
//        return res;
//    }

//    方法二：带预处理，效率高很多，原理就是先遍历board，把每个格子里面的字母进行排序和编号，
//    然后对照words里面每个word的首字母进行寻找，
//    这样可以省掉三个for循环,很巧妙，其实速度也已经和Trie差不多了
//    public List<String> findWords(char[][] board, String[] words) {
//        this.board = board;
//        this.words = words;
//        row = board.length;
//        col = board[0].length;
//        List[] preTreatment = new List[26];
//        if(board == null || row == 0) return res;
//        for(int i = 0; i < row; i++){
//            for(int k = 0; k < col; k++){
//                int index = board[i][k] - 'a';
//                if(preTreatment[index] == null){
//                    preTreatment[index] = new ArrayList<>();
//                }
//                int[] pos = new int[2];
//                pos[0] = i;
//                pos[1] = k;
//                preTreatment[index].add(pos);
//            }
//        }
//        for(String word : words){
//            int head = word.charAt(0) - 'a';
//            if(preTreatment[head] == null) continue;
//            int headSize = preTreatment[head].size();
//            for(int i = 0; i < headSize; i++){
//                int[] pos = (int[]) preTreatment[head].get(i);
//                boolean[][] visited = new boolean[row][col];
//                if(!hashSet.contains(word)) BackTracking(word, 0, pos[0], pos[1], visited);
//            }
//        }
//        return res;
//    }

//    private void BackTracking(String word, int index, int row1, int col1, boolean[][] visited){
//        char[] wChar = word.toCharArray();
//        int wLen = wChar.length;
//        if(row1 < 0 || row1 >= row || col1 < 0 || col1 >= col || visited[row1][col1] || index >= wLen) return;
//        if(board[row1][col1] == wChar[index] && !visited[row1][col1]){
//            index++;
//            if(index == wLen && !hashSet.contains(word)) {
//                hashSet.add(word);
//                res.add(word);
//                return;
//            }
//            visited[row1][col1] = true;
////            System.out.println(visited[2][0]);
//            for(int[] dir : dirs){
//                int x = row1 + dir[0];
//                int y = col1 + dir[1];
//                if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y]) continue;
//                BackTracking(word, index, x, y, visited);
//
//            }
//            visited[row1][col1] = false;
//        }
//    }

//    方法三：使用Trie，经过验证没有上面两个方法效率高，但是由于Trie也是一个比较常见的结构，权当复习，
//    而且TrieNode里面还多了个word变量，这样可以让整体代码方便很多。但是整体来说还是很绕的
//    public class TrieNode{
//        int end;
//        int path;
//        String word;
//        HashMap<Character, TrieNode> hashMap;
//        public TrieNode(){
//            end = 0;
//            path = 0;
//            hashMap = new HashMap<>();
//            word = null;
//        }
//    }
//
//    public class Trie{
//        TrieNode root;
//        public Trie(){
//            root = new TrieNode();
//        }
//        public void insert(String word){
////            这句非常重要
//            TrieNode node = root;
//            char[] wChar = word.toCharArray();
//            for(char ch : wChar){
//                if(!node.hashMap.containsKey(ch)) node.hashMap.put(ch, new TrieNode());
//                node.path++;
//                node = node.hashMap.get(ch);
//            }
//            node.end++;
//            node.word = word;
//        }
//        public boolean Search(String word){
//            TrieNode node = root;
//            char[] wChar = word.toCharArray();
//            for(char ch : wChar){
//                if(!node.hashMap.containsKey(ch)) return false;
//                node = node.hashMap.get(ch);
//            }
//            return node.end > 0;
//        }
//    }
//
//    Trie node = new Trie();
//    public List<String> findWords(char[][] board, java.lang.String[] words) {
//        this.board = board;
//        this.words = words;
//        row = board.length;
//        col = board[0].length;
//        if(board == null || row == 0) return res;
////        Trie node = new Trie();
//        for(String word : words){
//            node.insert(word);
//        }
//        for(int i = 0; i < row; i++){
//            for(int k = 0; k < col; k++){
//                char ch = board[i][k];
//                if(node.root.hashMap.containsKey(ch)){
//                    boolean[][] visited = new boolean[row][col];
//                    BackTracking(i, k, visited, node.root);
//                }
//            }
//        }
//        return res;
//    }
//
//    private void BackTracking(int row1, int col1, boolean[][] visited, TrieNode node){
//        if(row1 < 0 || row1 >= row || col1 < 0 || col1 >= col || visited[row1][col1] ) return;
//        char ch = board[row1][col1];
////        这句话很关键，如果不这么写就会问题非常多！很巧妙
//        TrieNode curNode = node.hashMap.get(ch);
//        if(curNode != null && curNode.word != null){
//            res.add(curNode.word);
////            这句也很重要
//            curNode.word = null;
//        }
//        visited[row1][col1] = true;
////            System.out.println(visited[2][0]);
//        for(int[] dir : dirs){
//            int x = row1 + dir[0];
//            int y = col1 + dir[1];
//            if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || curNode.hashMap.get(board[x][y]) == null) continue;
//            BackTracking(x, y, visited, curNode);
//
//        }
//        visited[row1][col1] = false;
//    }



//    二刷，2021/06/27，上海美领馆面签前一天
//    2021/7/1更新，面试vo秒过，已经ISSUED
//    二刷的时候发现这个方法已经会超时了
//    private List<String> res = new LinkedList<>();
//    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
//    private int row, col;
//    private char[][] board;
//    private String[] words;
//    private HashSet<String> hashSet = new HashSet<>();
//    private List<int[]>[] preTreatment = new LinkedList[26];
//    public List<String> findWords(char[][] board, String[] words) {
//        this.board = board;
//        this.words = words;
//        if(board == null || words == null) return res;
//        this.row = board.length;
//        this.col = board[0].length;
//        for(int i = 0; i < row; i++){
//            for(int k = 0; k < col; k++){
//                int index = board[i][k] - 'a';
//                if(preTreatment[index] == null) preTreatment[index] = new LinkedList();
//                int[] curPos = new int[]{i, k};
//                preTreatment[index].add(curPos);
//            }
//        }
//        for(String word : words){
//            char[] wChar = word.toCharArray();
//            int target = wChar[0] - 'a';
//            if(preTreatment[target] == null) continue;
////            注意这句话要这么写的话，在一开始对于preTreatment的初始化要在list后面加上类型
//            for(int[] pos : preTreatment[target]){
//                boolean[][] visited = new boolean[row][col];
//                BackTracking(0, pos[0], pos[1], word, visited);
//            }
//        }
//        return res;
//    }
//
//    private void BackTracking(int index, int row1, int col1, String word, boolean[][] visited){
//        int wLen = word.length();
//        char[] wChar = word.toCharArray();
//        if(!isValid(row1, col1) || visited[row1][col1] || index > wLen) return;
//        if(index == wLen - 1){
//            if(!hashSet.contains(word)){
//                hashSet.add(word);
//                res.add(word);
//                return;
//            }
//        }
//        visited[row1][col1] = true;
//        for(int[] dir : dirs){
//            int x = row1 + dir[0];
//            int y = col1 + dir[1];
////            System.out.println("word = " + word + " index = " + index);
//            if(index < wLen - 1 && isValid(x, y) && board[x][y] == wChar[index + 1]) BackTracking(index + 1, x, y, word, visited);
//        }
//        visited[row1][col1] = false;
//    }
//
    private boolean isValid(int a, int b){
        return 0 <= a && a < row && 0 <= b && b < col;
    }

//    方法二，使用Trie来解，共用isValid方法
    public class TrieNode{
        int end;
        int path;
        String word;
        HashMap<Character, TrieNode> hashMap;
        public TrieNode(){
            end = 0;
            path = 0;
            word = null;
            hashMap = new HashMap<>();
        }

//        针对方法三额外加的TrieNode方法，功能：如果该字符是字典树叶子结点，已匹配完，可删除
        public void deleteLeaf(){
            HashMap<Character, TrieNode> map = this.hashMap;
            if(map.size() == 1){
                for(char ch : map.keySet()){
                    if(map.get(ch) == null) map.remove(ch);
                    break;
                }
            }
        }
    }

    public class Trie{
        TrieNode root;
        public Trie(){
            root  = new TrieNode();
        }

        public void insert(String word){
            char[] wChar = word.toCharArray();
            int wLen = word.length();
            TrieNode node = root;
            for(char ch : wChar){
//                System.out.println(node.hashMap.size());
//                很奇怪为什么这两个写法都不对，都会导致不能赋值进去,导致node是null
//                解答：https://blog.csdn.net/frankingly/article/details/94447360
//                所以说并不是赋值不进去，而是因为是Null所以就直接调用了，而不会执行new TrieNode
//                node = node.hashMap.putIfAbsent(ch, new TrieNode());
//                node = node.hashMap.getOrDefault(ch, new TrieNode());
//                但是根据lc332的经验，你不要这么写，而是改成下面这样，就可以了，
//                主要就是先不要给node赋值，这样就不会出错，要单独使用。下面两句话效果一样
                node.hashMap.putIfAbsent(ch, new TrieNode());
//                if(node.hashMap.get(ch) == null) node.hashMap.put(ch, new TrieNode());
                node.path++;
//                System.out.println(node.hashMap.size());
                node = node.hashMap.get(ch);
//                System.out.println(node.hashMap.size());
            }
            node.end++;
            node.word = word;
        }

        public boolean search(String word){
            char[] wChar = word.toCharArray();
            TrieNode node = root;
            for(char ch : wChar){
//                System.out.println(node.hashMap.size());
                if(!node.hashMap.containsKey(ch)) return false;
                node = node.hashMap.get(ch);
            }
            return node.end > 1;
        }
    }

//    private List<String> res = new LinkedList<>();
//    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
//    private int row, col, maxLength;
//    private char[][] board;
//    private String[] words;
//    private HashSet<String> hashSet = new HashSet<>();
//    private List<int[]>[] preTreatment = new LinkedList[26];
//    private Trie trie = new Trie();
//    public List<String> findWords(char[][] board, String[] words) {
//        this.board = board;
//        this.words = words;
//        if(board == null || words == null) return res;
//        this.row = board.length;
//        this.col = board[0].length;
//        this.maxLength = Integer.MIN_VALUE;
//        for(String word : words) {
//            trie.insert(word);
//            maxLength = Math.max(maxLength, word.length());
//        }
//        for(int i = 0; i < row; i++){
//            for(int k = 0; k < col; k++){
//                char ch = board[i][k];
//                if(!trie.root.hashMap.containsKey(ch)) continue;
//                boolean[][] visited = new boolean[row][col];
//                BackTracking(i, k, visited, trie.root, 0);
//            }
//        }
//        return res;
//    }
//
//    private void BackTracking(int row1, int col1, boolean[][] visited, TrieNode curNode, int count){
//        if(!isValid(row1, col1) || visited[row1][col1] || curNode == null || count > maxLength) return;
//        char ch = board[row1][col1];
//        curNode = curNode.hashMap.get(ch);
//        if(curNode == null) return;
//        if(curNode.end > 0 && curNode.word != null) {
//            res.add(curNode.word);
////            关键
//            curNode.word = null;
////            这个不能加，加了以后如果接下去还有的话就会识别不出
////            return;
//        }
//        visited[row1][col1] = true;
//        count++;
//        for(int[] dir : dirs){
//            int x = row1 + dir[0];
//            int y = col1 + dir[1];
//            if(isValid(x, y) && !visited[x][y]) {
//                char temp = board[x][y];
////                System.out.println("x = "+ x + " y = " + y + " temp = " + temp);
//                BackTracking(x, y, visited, curNode, count);
//            }
//        }
//        visited[row1][col1] = false;
//        count--;
//    }


//    方法三，共用上面的TrieNode函数，以及isValid函数，用了题解里面的剪枝，但是也没有快，挺奇怪的
//    https://leetcode-cn.com/problems/word-search-ii/solution/javazi-dian-shu-jian-zhi-shi-xian-by-mazw-2/
    private List<String> res = new LinkedList<>();
    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    private int row, col, maxLength;
    private char[][] board;
    private String[] words;
    private HashSet<String> hashSet = new HashSet<>();
    private List<int[]>[] preTreatment = new LinkedList[26];
    private Trie trie = new Trie();
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.words = words;
        if(board == null || words == null) return res;
        this.row = board.length;
        this.col = board[0].length;
        this.maxLength = Integer.MIN_VALUE;
        for(String word : words) {
            trie.insert(word);
            maxLength = Math.max(maxLength, word.length());
        }
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                char ch = board[i][k];
                if(!trie.root.hashMap.containsKey(ch)) continue;
                boolean[][] visited = new boolean[row][col];
                BackTracking(i, k, visited, trie.root, 0);
            }
        }
        return res;
    }

    private void BackTracking(int row1, int col1, boolean[][] visited, TrieNode curNode, int count){
        if(!isValid(row1, col1) || visited[row1][col1] || curNode == null || count > maxLength) return;
        char ch = board[row1][col1];
//        注意这里是整个优化的精髓，用tempNode来一层一层往下走
//        但是始终保留curNode，也就是一开始的root，结合下面res.add里面的语句理解一下
        TrieNode tempNode = curNode.hashMap.get(ch);
        if(tempNode == null) return;
        if(tempNode.end > 0 && tempNode.word != null) {
            res.add(tempNode.word);
//            关键
            tempNode.word = null;
//            效率有了显著提升，但是要注意是对curNode进行操作
//            而不是tempNode!! 很关键
            curNode.deleteLeaf();
//            这个不能加，加了以后如果接下去还有的话就会识别不出
//            return;
        }
        visited[row1][col1] = true;
        count++;
        for(int[] dir : dirs){
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            if(isValid(x, y) && !visited[x][y]) {
                char temp = board[x][y];
//                System.out.println("x = "+ x + " y = " + y + " temp = " + temp);
                BackTracking(x, y, visited, tempNode, count);
            }
        }
        visited[row1][col1] = false;
        count--;
    }

    public static void main(String[] args) {
//        char[][] test = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] test1 = {"oath","pea","eat", "rain"};
//        System.out.println(new lc212().findWords(test, test1));

//        char[][] test = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] test1 = {"oath","oathk"};
//        System.out.println(new lc212().findWords(test, test1));

//        char[][] test = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] test1 = {"rain","rains"};
//        System.out.println(new lc212().findWords(test, test1));

        char[][] test = {{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}};
        String[] test1 = {"oa","oaa"};
        System.out.println(new lc212().findWords(test, test1));

//        char[][] test = {{'a','b','c'},{'a','e','d'},{'a','f','g'}};
//        String[] test1 = {"eaabcdgfa"};
//        System.out.println("答案是：" + "eaabcdgfa" + " 代码答案为： " + new lc212().findWords(test, test1));
    }
}
