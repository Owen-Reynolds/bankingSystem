import javax.swing.*;
import java.awt.*;

public class main {

    private static CardLayout cardLayout;
    private static JPanel cardsPanel;


    public static void main(String[] args){
        //Use Swing and CardLayout to to manage different panels for one screen.
        JFrame frame = new JFrame("Banking System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel mainMenuPanel = createMainMenuPanel();
        JPanel transactions = createTransactionsPanel();

        cardsPanel.add(loginPanel, "Login Screen");
        cardsPanel.add(mainMenuPanel, "Main Menu Screen");
        cardsPanel.add(transactions, "Transaction Screen");

        frame.add(cardsPanel);
        frame.setVisible(true);
    }

    private static JPanel createLoginPanel(){
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e ->{
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if(username.equals("user") && password.equals("password")){
                cardLayout.show(cardsPanel, "Main Menu Screen");
            }else{
                JOptionPane.showMessageDialog(null, "Incorrect Login Information");
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        return loginPanel;
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
            JOptionPane.showMessageDialog(null, "Balance: ");
        });
        depositButton.addActionListener(e ->{
            JOptionPane.showMessageDialog(null,"Deposited: ");
        });
        withdrawButton.addActionListener(e ->{
            JOptionPane.showMessageDialog(null, "Withdrew: ");
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
}