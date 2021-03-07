package Logic;


import java.util.LinkedList;
import java.util.Random;

interface Strategy{
    LinkedList<Integer> findNewPath(Graph graph, int position);
}

class Damaged implements Strategy{
    public LinkedList<Integer> findNewPath(Graph graph, int position){
        return null;
    }
}


class Patrol implements Strategy{
    public LinkedList<Integer> findNewPath(Graph graph, int position){
        Random rand = new Random();
        return graph.getPath(position, rand.nextInt(144));
    }
}

class Defense implements Strategy{
    public LinkedList<Integer> findNewPath(Graph graph, int position){
        Random rand = new Random();
        return graph.getPath(position, rand.nextInt(3) + 130);

    }
}

class EnemyStrategy {
    private Strategy strategy;
    EnemyStrategy(){
        strategy = new Patrol();
    }

    Strategy getStrategy(){return strategy;}

    void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    LinkedList<Integer> executeStrategy(Graph graph, int position){
        return strategy.findNewPath(graph, position);
    }
}
