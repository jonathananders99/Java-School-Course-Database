import java.io.*;

public abstract class AssignmentWeight implements Serializable{//start class

  protected float examWeight;
  protected float hwWeight;
  protected float quizWeight;


  public AssignmentWeight(){//start null constructor

  }//end null constructor


  public void setExamWeight(float examWeight){//start setExamWeight
    this.examWeight = examWeight;
  }//end setExamWeight

  
  public void setHwWeight(float hwWeight){//start setHwWeight
    this.hwWeight = hwWeight;
  }//end setHwWeight


  public void setQuizWeight(float quizWeight){//start setQuizWeight
    this.quizWeight = quizWeight;
  }//end setQuizWeight


  public float getExamWeight(){//start getExamWeight
    return this.examWeight;
  }//end getExamWeight

  
  public float getHwWeight(){//start getHwWeight
    return this.hwWeight;
  }//end getHwWeight


  public float getQuizWeight(){//start getQuizWeight
    return this.quizWeight;
  }//end getQuizWeight


}//end class