package BusinessLogic;

import Model.Server;
import Model.Task;


import java.util.List;
import java.util.Scanner;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int shortestQueueLength = Integer.MAX_VALUE;

        for (Server server : servers) {
            if (server.waitingPeriod.get() < shortestQueueLength) {
                shortestQueueLength = server.waitingPeriod.get();
            }
        }
        for (Server server1 : servers) {
            if (server1.waitingPeriod.get() == shortestQueueLength) {
                Scanner in = new Scanner(System.in);
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