public class account {
    //Attributes: Account number, balance.
    //Methods: Deposit(), Withdraw(), Transfer(), PrintStatement() (for transaction history).
    public void deposit(String userId, double balance, double amount){
        balance += amount;
    }

    public void withdraw(String userId, double balance, double amount){
        balance -= amount;
    }

}
