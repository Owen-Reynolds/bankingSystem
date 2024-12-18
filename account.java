import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class account extends bank {
    //Attributes: Account number, balance.
    //Methods: Deposit(), Withdraw(), Transfer(), PrintStatement() (for transaction history).


    //Checks existance of user in json file
    public boolean checkExistence(JSONArray array, String username, String password) throws Exception{
        for(Object obj: array){
            JSONObject user = (JSONObject) obj;
            if(username.equals(user.get("username"))){

                String storedSalt = (String) user.get("salt");
                String storedHashedPassword = (String) user.get("password");

                String hashedPassword = hashPassword(password, storedSalt);

                if(storedHashedPassword.equals(hashedPassword)){
                    //User exists
                    return true;
                }   
                else{
                    System.out.println("Incorrect password");
                    return false;
                }
            }
        }
        return false;
    }



    public void deposit(String userId, double balance, double amount){
        balance += amount;
    }

    public void withdraw(String userId, double balance, double amount){
        balance -= amount;
    }

}
