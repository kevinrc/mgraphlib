package core;

import core.util.BinaryHeap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <E>
 */
public class SpanningTreeGenerator<E> extends Traversal<E> {
    List<Graph.Vertex<E>> inTree = new ArrayList<Graph.Vertex<E>>();
    Map<Graph.Vertex<E>, BinaryHeap<Graph.Vertex<E>>> v2EdgeMinHeap =
            new HashMap<Graph.Vertex<E>, BinaryHeap<Graph.Vertex<E>>>();
    Graph<E> spanningTree = new Graph<E>();

    public SpanningTreeGenerator(Graph<E> eGraph) {
        super(eGraph);
    }

    @Override
    public void traverse() {
        inTree.add(g.getVertices().iterator().next());
        generateTree();


    }

    private void generateTree() {
        Graph.Vertex<E> vMin = null;
        Graph.Vertex<E> edgeFrom = null;
        if (inTree.size() == g.getVertices().size()) return;
        for (Graph.Vertex<E> v : inTree) {
            Graph.Vertex<E> vMin2 = getFringeMinimum(v);
            vMin = vMin == null ? vMin2
                    : vMin2 == null ? vMin
                    : vMin.compareTo(vMin2) > 0 ? vMin2
                    : vMin;
            edgeFrom = v;
        }
        spanningTree.insertEdge(edgeFrom.e, vMin.e, vMin.edgeWeight);
        markDiscovered(vMin);
        inTree.add(vMin);
        generateTree();
    }

    public List<Graph.Vertex<E>> getMinimumSpanningTree() {
        return inTree;
    }

    private Graph.Vertex<E> getFringeMinimum(Graph.Vertex<E> v) {
        BinaryHeap<Graph.Vertex<E>> adjListHeap = getMinHeap(v);
        Graph.Vertex<E> top = adjListHeap.extractTop();
        while (top != null && discovered(top)) {
            top = adjListHeap.extractTop();
        }
        return top;
    }

    private BinaryHeap<Graph.Vertex<E>> getMinHeap(Graph.Vertex<E> v) {
        BinaryHeap<Graph.Vertex<E>> h = v2EdgeMinHeap.get(v);
        if (h == null) {
            h = buildHeap(v);
            v2EdgeMinHeap.put(v, h);
        }
        return h;
    }

    private BinaryHeap<Graph.Vertex<E>> buildHeap(Graph.Vertex<E> v) {
        BinaryHeap<Graph.Vertex<E>> h = new BinaryHeap<Graph.Vertex<E>>();
        for (Graph.Vertex<E> eVertex : g.getAdjList(v)) {
            h.insert(eVertex);
        }
        ;
        return h;
    }


}
                  