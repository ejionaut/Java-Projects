package control;

import model.Accounts;

public class UserManagement {
    private Accounts account;
    private final static UserManagement INSTANCE = new UserManagement();

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
}
