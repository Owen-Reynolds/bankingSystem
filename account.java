import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class account extends bank {
    //Attributes: Account number, balance.
    //Methods: Deposit(), Withdraw(), Transfer(), PrintStatement() (for transaction history).


    //Checks existance of user in json file
    public String checkExistence(JSONArray array, String username, String password) throws Exception{
        for(Object obj: array){
            JSONObject user = (JSONObject) obj;
            if(username.equals(user.get("username"))){

                String storedSalt = (String) user.get("salt");
                String storedHashedPassword = (String) user.get("password");

                String hashedPassword = hashPassword(password, storedSalt);

                String userId = (String) user.get("ID");

                if(storedHashedPassword.equals(hashedPassword)){
                    //User exists, return user ID
                    return userId;
                }   
                else{
                    System.out.println("Incorrect password");
                    return null;
                }
            }
        }
        return null;
    }


}
