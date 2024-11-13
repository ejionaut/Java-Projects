package control;

import model.Accounts;
import model.Task;

public class UserManagement {
    private Accounts account;
    private final static UserManagement INSTANCE = new UserManagement();
    private Task task;

    private UserManagement(){}

    public static UserManagement getInstance(){
        return INSTANCE;
    }

    public void setAccount(Accounts acc){
        this.account = acc;
    }

    public Accounts getAccount(){
        return this.account;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
