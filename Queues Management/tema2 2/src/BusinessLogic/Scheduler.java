package BusinessLogic;

import Model.Server;
import Model.Task;
import java.util.ArrayList;
import java.util.List;
import Model.SelectionPolicy;

public class Scheduler {
     List<Server> servers = new ArrayList<Server>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for(int i = 1; i <= maxNoServers; i++) {
            Server thread = new Server("Queue " + i, maxTasksPerServer, new ArrayList<String>());
            servers.add(thread);
            Thread t = new Thread(thread);
            t.start();
        }
    }
    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }
    public void dispatchTask(Task t) {
        strategy.addTask(servers, t);
    }
}
