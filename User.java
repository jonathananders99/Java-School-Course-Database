import java.io.*;

public class User implements Serializable{//start class

  protected String userID;
  protected String password;
  protected String firstName;
  protected String lastName;


  public static void main(String[] args){//start main
    User u1 = new User();
    System.out.println(u1.getUserID());
    System.out.println(u1.getPassword());
    System.out.println(u1.getFirstName());
    System.out.println(u1.getLastName());
    u1.setLastName("Ree");
    System.out.println(u1.getLastName());
    User u2 = new User("07","1672","Steve","Jobs");
    System.out.println(u2.getUserID());
    System.out.println(u2.getPassword());
    System.out.println(u2.getFirstName());
    System.out.println(u2.getLastName());

  }//end main


  public User(){//start null constructor
    this.userID = "N/A";
    this.password = "N/A";
    this.firstName = "N/A";
    this.lastName = "N/A";
  }//end null constructor


  public User(String userID, String password, String firstName, String lastName){//start constructor
    this.userID = userID;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }//end constructor


  public void setUserID(String userID){//start setUserID
    this.userID = userID;
  }//end setUserID

  
  public void setPassword(String password){//start setPassword
    this.password = password;
  }//end setPassword


  public void setFirstName(String firstName){//start setFirstName
    this.firstName = firstName;
  }//end setFirstName


  public void setLastName(String lastName){//start setLastName
    this.lastName = lastName;
  }//end setLastName


  public String getUserID(){//start getUserID
    return this.userID;
  }//end getUserID

  
  public String getPassword(){//start getPassword
    return this.password;
  }//end getPassword


  public String getFirstName(){//start getFirstName
    return this.firstName;
  }//end getFirstName


  public String getLastName(){//start getLastName
    return this.lastName;
  }//end getLastName


}//end class