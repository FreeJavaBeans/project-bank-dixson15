package model_entity;

public class BankUser extends Customer{

    private String userName;
    private String password;

    public BankUser() {
        super();
    }

    public BankUser(int id, String firstName, String lastName, String userName, String password) {
        super(id, firstName, lastName);
        setUserName(userName);
        setPassword(password);
    }

    public BankUser(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    private void setUserName(String userName) {

        try {
            if (userName.length() >= 5)
                this.userName = userName;
            else
                System.out.println("user name must be at least 5 characters!");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void setPassword(String password) {

        try{
            if(password.length() >= 8 )
                this.password = password;
            else
                System.out.println("Password not secure.Denied !");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
