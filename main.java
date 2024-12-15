import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class main {
    JFrame frame;
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    main window = new main();
                    window.frame.setVisible(true);
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
        });
    }

    public main(){
        initialize();
    }

    public void initialize(){
        frame = new JFrame();
        frame.setBounds(300,100, 600, 400);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Title for Page
        JLabel title = new JLabel("Welcome to the Bank");
        title.setBounds(220,20,150,50);
        frame.getContentPane().add(title);

        //Sign in Button
        JButton signIn = new JButton("Sign In");
        signIn.setBounds(220, 100, 125, 20);
        signIn.setBackground(Color.lightGray);
        signIn.setForeground(Color.red);
        frame.getContentPane().add(signIn);

        //Create Account Button
        JButton create = new JButton("Create Account");
        create.setBounds(220, 150, 125, 20);
        create.setBackground(Color.lightGray);
        create.setForeground(Color.red);
        frame.getContentPane().add(create);

        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                signIn s = new signIn();
                s.signInFrame.setVisible(true);
            }
        });
    }
}
