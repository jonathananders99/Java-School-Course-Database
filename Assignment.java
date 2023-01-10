import java.io.*;

public abstract class Assignment implements Serializable{//start class

  protected float totPoints;
  protected float curPoints;
  protected float gradeWeight;


  public Assignment(){//start null constructor

  }//end null constructor


  public void setTotPoints(float totPoints){//start setTotPoints
    this.totPoints = totPoints;
  }//end setTotPoints

  
  public void setCurPoints(float curPoints){//start setCurPoints
    this.curPoints = curPoints;
  }//end setCurPoints


  public void setGradeWeight(float gradeWeight){//start setGradeWeight
    this.gradeWeight = gradeWeight;
  }//end setGradeWeight


  public float getTotPoints(){//start getTotPoints
    return this.totPoints;
  }//end getTotPoints

  
  public float getCurPoints(){//start getCurPoints
    return this.curPoints;
  }//end getCurPoints


  public float getGradeWeight(){//start getGradeWeight
    return this.gradeWeight;
  }//end getGradeWeight


}//end class