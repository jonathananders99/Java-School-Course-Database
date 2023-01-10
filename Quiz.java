public class Quiz extends Assignment{//start class

  protected String name;
  

  public static void main(String[] args){//start main
    Quiz e1 = new Quiz();
    System.out.println(e1.getTotPoints());
    System.out.println(e1.getCurPoints());
    System.out.println(e1.getGradeWeight());
    System.out.println(e1.getName());
  }//end main


  public Quiz(){//start null constructor
    this.totPoints = -1f;
    this.curPoints = -1f;
    this.gradeWeight = 2.0f;
    this.name = "Quiz 0";
  }//end null constructor


  public Quiz(float totPoints, float curPoints, float gradeWeight, String name){//start constructor
    this.totPoints = totPoints;
    this.curPoints = curPoints;
    this.gradeWeight = gradeWeight;
    this.name = name;
  }//end constructor

  
  public void setName(String name){//start setName
    this.name = name;
  }//end setName
  
  
  public String getName(){//start getName
    return this.name;
  }//end getName
  
  
}//end class