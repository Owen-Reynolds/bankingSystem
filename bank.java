import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.UUID;

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
        String userId = generateUser();
        double balance = 0;
        
        JSONArray users = loadArray();

        JSONObject user = new JSONObject();

        user.put("username", username);
        user.put("password", hashedPassword);
        user.put("salt", salt);
        user.put("ID", userId);
        user.put("balance", balance);
        
        users.add(user);  

        saveData(users);
    }

    private void saveData(JSONArray array) {
        try (PrintWriter pw = new PrintWriter("accounts.json")) {
            pw.println(array.toJSONString());
            System.out.println("Data successfully saved to accounts.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private JSONArray loadArray() throws Exception {
        Path path = Path.of("accounts.json");

        String content = Files.readString(path).trim();
    
        JSONParser parser = new JSONParser();
        try {
            Object parsedContent = parser.parse(content);
    
            //Ensure the parsed content is a JSONArray
            if (parsedContent instanceof JSONArray) {
                return (JSONArray) parsedContent;
            } else {
                System.out.println("File does not contain a JSON array. Overwriting with a new array.");
                return new JSONArray();
            }
        } catch (Exception e) {
            throw new Exception("Error parsing JSON data from accounts.json: " + e.getMessage());
        }
    }
    
    private void initializeFile() throws IOException {
        Path path = Path.of("accounts.json");
        if (!Files.exists(path) || Files.readString(path).trim().isEmpty()) {
            System.out.println("Creating or fixing accounts.json file...");
            Files.writeString(path, "[]"); //Write an empty JSON array to the file
        }
    }
    
    public bank() {
        try {
            initializeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String generateUser(){
        UUID uuid = UUID.randomUUID();

        String userId = uuid.toString();

        return userId;
    }
}
