package Model;

import java.util.UUID;

public class Task {
    public int ID;
    public int arrivalTime;
    public int serviceTime;

    @Override
    public String toString() {
        return  "(" + ID + ", " + arrivalTime + ", " + serviceTime + ");";
    }
}
