import javax.swing.*;
import java.awt.*;

public class main extends user{

    private static CardLayout cardLayout;
    private static JPanel cardsPanel;


    public static void main(String[] args) throws Exception{
        //Use Swing and CardLayout to to manage different panels for one screen.
        JFrame frame = new JFrame("Banking System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel newUserPanel = createNewUserPanel();
        JPanel mainMenuPanel = createMainMenuPanel();
        JPanel transactionsPanel = createTransactionsPanel();

        JPanel balance = createBalancePanel();
        JPanel deposit = createDepositPanel();
        JPanel withdraw = createWithdrawPanel();

        cardsPanel.add(loginPanel, "Login Screen");
        cardsPanel.add(newUserPanel, "New User Screen");
        cardsPanel.add(mainMenuPanel, "Main Menu Screen");
        cardsPanel.add(transactionsPanel, "Transaction Screen");
        cardsPanel.add(balance, "Balance Screen");
        cardsPanel.add(deposit, "Deposit Screen");
        cardsPanel.add(withdraw, "Withdraw Screen");

        frame.add(cardsPanel);
        frame.setVisible(true);

        bank obj = new bank();
        
    }

    private static JPanel createLoginPanel(){
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        JButton createButton = new JButton("Create Account");

        loginButton.addActionListener(e ->{
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            bank obj = new bank();
            account checkUser = new account();

            try {
                if(checkUser.checkExistence(obj.loadArray(), username, password)){
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    cardLayout.show(cardsPanel, "Main Menu Screen");
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Login Information");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        
        createButton.addActionListener(e ->{
            cardLayout.show(cardsPanel, "New User Screen");
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(createButton);

        return loginPanel;
    }

    private static JPanel createNewUserPanel(){
        JPanel newUserPanel = new JPanel();
        newUserPanel.setLayout(new BoxLayout(newUserPanel, BoxLayout.Y_AXIS));

        JLabel userTitle = new JLabel("Create Account");
        JLabel newUserLabel = new JLabel("Create Username: ");
        JLabel newPasswordLabel = new JLabel("Create Password: ");

        JTextField newUserUsername = new JTextField(20);
        JPasswordField newUserPassword = new JPasswordField(20);

        JButton newUserButton = new JButton("Create Account");

        newUserButton.addActionListener(e ->{
            //Add user info to db
            String newUsername = newUserUsername.getText();
            String newPassword = new String(newUserPassword.getPassword());

            if(newPassword.length() < 8){
                JOptionPane.showMessageDialog(null, "Password must be at least 8 Characters");
                cardLayout.show(cardsPanel, "New User Screen");
            }
            else{
                bank newUser = new bank();
                try {
                    if(newUser.checkDuplicate(newUser.loadArray(), newUsername)){
                        JOptionPane.showMessageDialog(null, "Username already exists. Please try again.");
                        return;
                    }
                    else {
                        newUser.createUser(newUsername, newPassword);
                        JOptionPane.showMessageDialog(null, "Account created successfully.");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            
            cardLayout.show(cardsPanel, "Main Menu Screen");
            }
        });

        newUserPanel.add(userTitle);
        newUserPanel.add(newUserLabel);
        newUserPanel.add(newUserUsername);
        newUserPanel.add(newPasswordLabel);
        newUserPanel.add(newUserPassword);
        newUserPanel.add(newUserButton);

        return newUserPanel;
    }

    private static JPanel createMainMenuPanel(){
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));

        JButton checkButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Depost");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transactionHistory = new JButton("Transaction History");
        JButton logoutButton = new JButton("Logout");

        checkButton.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Balance Screen");
        });
        depositButton.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Deposit Screen");
        });
        withdrawButton.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Withdraw Screen");
        });
        transactionHistory.addActionListener(e->{
            cardLayout.show(cardsPanel, "Transaction Screen");
        });
        logoutButton.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Login Screen");
        });

        mainMenuPanel.add(checkButton);
        mainMenuPanel.add(depositButton);
        mainMenuPanel.add(withdrawButton);
        mainMenuPanel.add(transactionHistory);
        mainMenuPanel.add(logoutButton);

        return mainMenuPanel;
    }

    private static JPanel createTransactionsPanel(){
        JPanel transactionsPanel = new JPanel();

        JLabel title = new JLabel("Transaction History: ");
        //Process for showing Transaction History
        JButton returntoMain = new JButton("Back to Main Menu");

        returntoMain.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Main Menu Screen");
        });

        transactionsPanel.add(title);
        transactionsPanel.add(returntoMain);

        return transactionsPanel;
    }

    private static JPanel createBalancePanel(){
        JPanel balancePanel = new JPanel();

        JLabel title = new JLabel("Balance:");
        //Process for showing Balance
        JButton returntoMain = new JButton("Back to Main Menu");

        returntoMain.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Main Menu Screen");
        });

        balancePanel.add(title);
        balancePanel.add(returntoMain);


        return balancePanel;
    }

    private static JPanel createDepositPanel(){
        JPanel depositPanel = new JPanel();

        JLabel title = new JLabel("How much to Deposit");

        JTextField entry = new JTextField(20);

        JButton entryButton = new JButton("Deposit");

        entryButton.addActionListener(e ->{
            double amount = Double.parseDouble(entry.getText());
            account obj = new account();
            obj.deposit(getUserId(), getBalance(), amount);
        });

        JButton returntoMain = new JButton("Back to Main Menu");

        returntoMain.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Main Menu Screen");
        });

        depositPanel.add(title);
        depositPanel.add(entry);
        depositPanel.add(entryButton);
        depositPanel.add(returntoMain);

        return depositPanel;

    }

    private static JPanel createWithdrawPanel(){
        JPanel withdrawPanel = new JPanel();

        JLabel title = new JLabel("How much to Withdraw");

        JTextField entry = new JTextField(20);

        JButton entryButton = new JButton("Withdraw");

        entryButton.addActionListener(e ->{
            double amount = Double.parseDouble(entry.getText());
            account obj = new account();
            obj.withdraw(getUserId(), getBalance(), amount);
        });

        JButton returntoMain = new JButton("Back to Main Menu");

        returntoMain.addActionListener(e ->{
            cardLayout.show(cardsPanel, "Main Menu Screen");
        });

        withdrawPanel.add(title);
        withdrawPanel.add(entry);
        withdrawPanel.add(entryButton);
        withdrawPanel.add(returntoMain);

        return withdrawPanel;

    }

    
}
