package Logic;

import java.io.Serializable;
import java.util.LinkedList;

class Graph implements Serializable {
    int n;
    int s;
    int SIZE = 145;
    private LinkedList[] g;
    private LinkedList<Integer> comp;

    LinkedList getEdge(int index){
        return g[index];
    }

    public LinkedList<Integer> getPath(int from, int to){
        LinkedList<Integer> q = new LinkedList<>();
        q.add(from);
        boolean[] used = new boolean[SIZE];
        int[] d = new int[SIZE];
        int[] p = new int[SIZE];
        used[from] = true;
        p[from] = -1;
        while (!q.isEmpty()) {
            int v = q.peek();
            q.pop();
            for (int i = 0; i < g[v].size(); ++i) {
                int to_ = (int) g[v].get(i);
                if (!used[to_]) {
                    used[to_] = true;
                    q.add(to_);
                    d[to_] = d[v] + 1;
                    p[to_] = v;
                }
            }
        }
        if (!used[to]) {
            System.out.println("No path!");
            return null;
        }
        else {
            LinkedList<Integer> path = new LinkedList<>();
            for (int v = to; v != -1; v = p[v])
                path.add(v);

            return path;
        }
    }

    Graph(){
        g = new LinkedList[SIZE];
        comp = new LinkedList<>();
        for(int i = 0; i < SIZE; i++){
            g[i] = new LinkedList<Integer>();
        }
    }

    void addEdge(Integer e1, Integer e2){
        g[e1].add(e2);
        g[e2].add(e1);
    }
}
