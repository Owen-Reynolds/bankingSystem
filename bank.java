import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;


public class bank {
    //Handles user authentication, account creation, and manages user accounts.
    //Methods: CreateUser(), AuthenticateUser(), ListAccounts(), FindAccountById().

    //Generates a Random Salt that prevents 2 users from having same hashed passwords 
    public String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    //Creates Hashed Password by combining salt with Password and hashing with the SHA-256 Algorithm
    public String hashPassword(String password, String salt) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String saltedPassword = salt + password;

        byte[] hash = digest.digest(saltedPassword.getBytes());

        return Base64.getEncoder().encodeToString(hash);
    }

    public void createUser(String username, String password) throws Exception{
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        
        
    }
}
