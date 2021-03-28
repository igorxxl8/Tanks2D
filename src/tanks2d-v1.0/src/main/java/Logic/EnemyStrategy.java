package Logic;


import java.util.LinkedList;
import java.util.Random;

interface IStrategy {
    LinkedList<Integer> findNewPath(Graph graph, int position);
}

class Damaged implements IStrategy {
    public LinkedList<Integer> findNewPath(Graph graph, int position){
        return null;
    }
}


class Patrol implements IStrategy {
    public LinkedList<Integer> findNewPath(Graph graph, int position){
        Random rand = new Random();
        return graph.getPath(position, rand.nextInt(144));
    }
}

class Defense implements IStrategy {
    public LinkedList<Integer> findNewPath(Graph graph, int position){
        Random rand = new Random();
        return graph.getPath(position, rand.nextInt(3) + 130);

    }
}

class EnemyStrategy {
    private IStrategy strategy;
    EnemyStrategy(){
        strategy = new Patrol();
    }

    IStrategy getStrategy(){return strategy;}

    void setStrategy(IStrategy strategy){
        this.strategy = strategy;
    }

    LinkedList<Integer> executeStrategy(Graph graph, int position){
        return strategy.findNewPath(graph, position);
    }
}
