package Model;

import BusinessLogic.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    public BlockingQueue<Task> tasks;
    public List<String> names = new ArrayList<>();
    public AtomicInteger waitingPeriod;
    public String name;


    public Server(String name, int max_clients, List<String> names) {
        waitingPeriod = new AtomicInteger(0);
        tasks = new ArrayBlockingQueue<Task>(max_clients);
        this.name = name;
        this.names = names;
    }

    public void addTask(Task newTask) {
        try {
            tasks.add(newTask);
        } catch (Exception e) {
            System.out.println("Exception while adding task to " + name + ": " + e.getMessage());
            e.printStackTrace();
        }
        waitingPeriod.addAndGet(newTask.serviceTime);
        System.out.println("Task added to " + name + ": " + newTask + ", Queue size: " + tasks.size());
        Log.print("Task added to " + name + ": " + newTask + ", Queue size: " + tasks.size());
    }

    public void removeTask(Task newTask) {
        tasks.remove(newTask);
        names.remove(newTask.toString());
        waitingPeriod.addAndGet(-newTask.serviceTime);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task nextTask = tasks.peek();
                if (nextTask != null) {
                    Thread.sleep(2000 * nextTask.serviceTime);
                    nextTask = tasks.take();
                    removeTask(nextTask);
                    Scanner in = new Scanner(System.in);
                    System.out.println("Client " + nextTask.toString() + " removed from " + name);
                    Log.print("Client " + nextTask.toString() + " removed from " + name);
                }

            } catch (InterruptedException e) {
                System.out.println("Thread " + Thread.currentThread().getId() + " was interrupted");
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
}
