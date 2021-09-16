package amazon;

/*
官解最清晰：
https://leetcode-cn.com/problems/clone-graph/solution/ke-long-tu-by-leetcode-solution/
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class lc133 {
    private HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        if(visited.containsKey(node)) return visited.get(node);
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        for(Node neighbour : node.neighbors){
            Node newNeighbour = cloneGraph(neighbour);
            cloneNode.neighbors.add(newNeighbour);
        }
        return cloneNode;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
