import javax.swing.*;
import java.awt.*;

JFrame signInFrame;
public class signIn {

    public signIn(){
        loadSignIn();
    }

    public void loadSignIn(){
        signInFrame = new JFrame();
        signInFrame.setBounds(300,100, 600, 400);
        signInFrame.getContentPane().setLayout(null);
        signInFrame.getContentPane().setBackground(Color.white);
        signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
