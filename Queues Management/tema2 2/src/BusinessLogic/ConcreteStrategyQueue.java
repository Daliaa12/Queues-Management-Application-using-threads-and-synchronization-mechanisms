package BusinessLogic;

import Model.Server;
import Model.Task;


import java.util.List;


public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int smallestQueueSize = Integer.MAX_VALUE;
        for (Server server : servers) {
            if (server.getTasks().size() < smallestQueueSize) {
                smallestQueueSize = server.getTasks().size();
            }
        }
        for (Server server1 : servers) {
            if (server1.getTasks().size() == smallestQueueSize) {
                server1.addTask(t);
                server1.names.add(t.toString());
                System.out.println(server1.getName() + ": " + server1.names);
                Log.print(server1.getName() + ": " + server1.names);
                Log.print("Client " + t.toString() +  " added in " + server1.getName());
                break;
            }
        }
    }
}