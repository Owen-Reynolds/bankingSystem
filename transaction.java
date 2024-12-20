import javax.swing.JOptionPane;

public class transaction {
    //Attributes: Transaction ID, date, type (deposit/withdrawal/transfer), amount, account number.
    //Methods: GenerateTransactionID(), PrintTransactionDetails().

    public double viewBalance(double balance){
        return balance;
    }

    public double deposit(double balance, double amount){
        balance += amount;
        return balance;
    }

    public double withdraw(double balance, double amount){
        balance -= amount;
        if(balance < 0){
            JOptionPane.showMessageDialog(null, "Insufficient funds");
            return balance + amount;
        }
        return balance;
    }
}
