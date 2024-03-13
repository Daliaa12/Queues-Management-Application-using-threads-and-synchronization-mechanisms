package BusinessLogic;

import GUI.SimulationFrame;
import Model.SelectionPolicy;
import Model.Server;
import Model.Task;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.UUID.randomUUID;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxPorcessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numbersOfServers;
    public SelectionPolicy simulationPolicy;
    public int numbersOfClients;
    public String chosenStrategy;

    private Scheduler scheduler;
    private List<Task> generatedTask;

    public SimulationManager(SelectionPolicy simulationPolicy, int numbersOfClients, int maxPorcessingTime, int minProcessingTime, int numbersOfServers,int maxArrivalTime, int minArrivalTime) {
        this.simulationPolicy = simulationPolicy;
        this.numbersOfClients = numbersOfClients;
        this.maxPorcessingTime=maxPorcessingTime;
        this.minProcessingTime=minProcessingTime;
        this.numbersOfServers=numbersOfServers;
        this.maxArrivalTime=maxArrivalTime;
        this.minArrivalTime=minArrivalTime;

        timeLimit = 200;

        chosenStrategy = "SHORTEST_TIME";

        scheduler = new Scheduler(numbersOfServers,numbersOfClients);
        generatedTask = generateRandomTask(numbersOfClients);
        scheduler.changeStrategy(simulationPolicy);
    }
    private List<Task> generateRandomTask(int n) {
        List<Task> generatedTask = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Random r = new Random();
            Task task = new Task();
            task.serviceTime = r.nextInt(maxPorcessingTime + 1 - minProcessingTime) + minProcessingTime;
            task.arrivalTime = r.nextInt(maxArrivalTime + 1 - minArrivalTime) + minArrivalTime;
            task.ID = i;

            generatedTask.add(task);
            System.out.println(task);
            Log.print(task.toString());
        }
        return generatedTask;
    }
    @Override
    public synchronized void run() {
        int currenTime = 0;
        while (currenTime < timeLimit) {
            Iterator<Task> iterator = generatedTask.iterator();
            while (iterator.hasNext()) {
                Task client = iterator.next();
                if (client.arrivalTime == currenTime) {
                    scheduler.dispatchTask(client);
                    iterator.remove();
                }
            }
            currenTime ++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
