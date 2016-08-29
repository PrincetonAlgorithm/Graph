package mjq.cwru;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    public class Bag<Item> implements Iterable<Item> {
        int N;

        public Bag() {
        }

        public void add(Item x) {
        }

        public int size() {
            return N;
        }

        @Override
        public Iterator<Item> iterator() {
            return null;
        }
    }


    public class Graph {
        private final int V;
        private Bag<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            adj = new Bag[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new Bag<Integer>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }

    public class DepthFirstPaths {
        //marked is used to mark if this vertex is visited
        private boolean[] marked;
        //edgeTo[w] = v is used to record that w is visited from v.
        //it points from child to parent. It points in opposite direction.
        private int[] edgeTo;
        private int s;

        public DepthFirstPaths(Graph G, int s) {
            marked = new boolean[G.V];
            edgeTo = new int[G.V];
            this.s = s;
            dfs(G, s);
        }

        private void dfs(Graph G, int v) {
            marked[v] = true;
            for (int w : G.adj[v]) {
                if (!marked[w]) {
                    dfs(G, w);
                    edgeTo[w] = v;
                }
            }
        }

        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public Iterable<Integer> pathTo(int v) {
            if (!hasPathTo(v))
                return null;
            Stack<Integer> path = new Stack<Integer>();
            for (int w = v; w != s; w = edgeTo[w]) {
                path.push(w);
            }
            //because for loop will jump out if w == s
            path.push(s);
            return path;
        }

        public class BreadthFirstPaths {
            private boolean[] marked;
            private int[] edgeTo;

            public BreadthFirstPaths(Graph G, int s) {
                marked = new boolean[G.V];
                edgeTo = new int[G.V];

                bfs(G, s);
            }

            private void bfs(Graph G, int s) {
                Queue<Integer> q = new LinkedList<Integer>();
                q.offer(s);
                marked[s] = true;
                while (!q.isEmpty()) {
                    int v = q.poll();
                    for (int w : G.adj(v)) {
                        if (!marked[w]) {
                            q.offer(w);
                            marked[w] = true;
                            edgeTo[w] = v;
                        }
                    }
                }
            }
        }

        public class ConnectedComponent {
            private boolean[] marked;
            private int[] id;
            private int count;

            public ConnectedComponent(Graph G) {
                marked = new boolean[G.V];
                id = new int[G.V];
                for (int v = 0; v < G.V; v++) {
                    if (!marked[v]) {
                        dfs(G, v);
                        count++;
                    }
                }
            }

            private void dfs(Graph G, int v) {
                marked[v] = true;
                id[v] = count;
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        dfs(G, w);
                    }
                }
            }
        }
    }
}
