package accounts.accounts;

// Account class
public class Account {

    // Private variables
    private String accountNumber;
    private String name;
    private String row;

    //Constructor
    public Account (String n, String a){
        accountNumber = a;
        name = n;
        row = name + System.getProperty ("line.separator") + accountNumber;
    }

    // Function that returns a data row to be saved to account list
    public String generateRow(){
        return row;
    }
}
