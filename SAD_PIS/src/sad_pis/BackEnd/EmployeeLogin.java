
package sad_pis.BackEnd;


public class EmployeeLogin {
    
    private static String username="";
    private static String password=""; //needs encryption
    private static String firstname = "";
    private static String lastname = "";
    
    //SETTER
    public static void setUsernamePassword(String usr,String pass){
        username = usr;
        password = pass;
    }
    //GETTER
    public static String getUsername(){
        return username;
    }
    public static String getPassword(){
        return password;
    }
    
    
    
}
