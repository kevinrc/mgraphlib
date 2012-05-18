package core;


import java.util.*;

/**
 * Represents the Graph object. Uses Adjacency List
 * @param <E>
 */

public class Graph<E> {
    protected Map<Vertex<E>, LinkedList<Vertex<E>>> graphAdjMap = new HashMap<Vertex<E>, LinkedList<Vertex<E>>>();
    boolean directed = false;

    public Graph<E> insertEdge(E e1, E e2, Object edgeWeight ) {
        Vertex<E> v1=new Vertex<E>(e1);
        Vertex<E> v2=new Vertex<E>(e2);
        v2.edgeWeight=edgeWeight;
        insert(v1, v2);
        if (!directed && e2!=null) {
            insert(v2, v1);
        }
        return this;
    }
    public Graph<E> insertEdge(E e1,E e2){
        return insertEdge(e1,e2,null);
    }
    private void insert(Vertex<E> v1, Vertex<E> v2) {
        LinkedList<Vertex<E>> adjList = graphAdjMap.get(v1);
        if (adjList == null) {
            adjList = new LinkedList<Vertex<E>>();
        }
        if (v2!=null && !adjList.contains(v2)) {
            adjList.add(v2);
            ++v1.degree;
        }
        graphAdjMap.put(v1, adjList);

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Vertex<E>, LinkedList<Vertex<E>>> adjLists : graphAdjMap.entrySet()) {
            sb.append("Adjacency list for ").append(adjLists.getKey()).append("\n");
            for (Vertex<E> v : adjLists.getValue()) {
                sb.append(v).append(",");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void breadthFirstSearch() {
        new BFSTraversal<E>(this).traverse();
    }


    public void depthFirstSearch() {
        new DFSTraversal<E>(this).traverse();
    }

    public boolean hasCycles() {
        DFSTraversal<E> tr = new DFSTraversal<E>(this);
        tr.traverse();
        return tr.hasCycles;
    }


    public LinkedList<Vertex<E>> findShortestPath(E e1, E e2) {
        BFSTraversal<E> bfs = new BFSTraversal<E>(this);
        Vertex<E> v1=new Vertex<E>(e1);
        bfs.traverse(v1);
        return bfs.findShortestPath(v1, new Vertex<E>(e2));

    }
    
    

    protected LinkedList<Vertex<E>> getAdjList(Vertex<E> v) {
        LinkedList<Vertex<E>> adjList = graphAdjMap.get(v);
        return (adjList == null) ? new LinkedList<Vertex<E>>() : adjList;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public Set<Vertex<E>> getVertices() {
        return graphAdjMap.keySet();
    }

    public static class Vertex<E> {
        E e=null;
        int degree = 0;
        Object edgeWeight=null;

        public Vertex(E e) {
            this.e = e;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            if (e != null ? !e.equals(vertex.e) : vertex.e != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return e != null ? e.hashCode() : 0;
        }

        @Override
        public String toString() {
            return e!=null?e.toString():"";
        }
    }


}
