package model;

import java.util.ArrayList;

public class Accounts {
    String username;
    String password;
    ArrayList<Task> task;

    public Accounts(String username, String password){
        this.username = username;
        this.password = password;
        this.task = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Task> getTask() {
        return task;
    }

    public void setTask(ArrayList<Task> task) {
        this.task = task;
    }

    public Accounts(String username, String password, ArrayList<Task> task){
        this.username = username;
        this.password = password;
        this.task = task;
    }
}
