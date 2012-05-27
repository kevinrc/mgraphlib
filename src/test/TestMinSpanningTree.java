package test;

import core.Graph;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 24/5/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMinSpanningTree {
    public static void main(String[] args) {
        MockNode a = new MockNode("a");
        MockNode b = new MockNode("b");
        MockNode c = new MockNode("c");
        MockNode d = new MockNode("d");
        Graph<MockNode> g = new Graph<MockNode>();
        g.insertEdge(a, b, 1);
        g.insertEdge(a, c, 100);
        g.insertEdge(a, d, 200);
        g.insertEdge(b, c, 10);
        g.insertEdge(c, d, 20);
        Graph<MockNode> st = g.getMinSpanningTree();
        System.out.println("st = " + st);


    }
}
