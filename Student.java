import java.util.*;

public class Student extends User{//start class

  protected Exam[] exams = new Exam[10];

  protected Homework[] allHomework = new Homework[25];

  protected Quiz[] quizzes = new Quiz[20];

  protected float curGrade;

  protected int numOfExams;

  protected int numOfHomework;

  protected int numOfQuizzes;


  public static void main(String[] args){//start main
    Student s1 = new Student();

    System.out.println(s1.getExams()[0].getTotPoints());
    s1.getExams()[0].setTotPoints(20f);
    System.out.println(s1.getExams()[0].getTotPoints());

    System.out.println(s1.getCurGrade());
    
    s1.getExams()[0].setCurPoints(19f);
    s1.calcCurGrade();
    System.out.println(s1.getCurGrade());

    s1.getAllHomework()[0].setCurPoints(10f);
    s1.getAllHomework()[0].setTotPoints(10f);
    s1.calcCurGrade();
    System.out.println(s1.getCurGrade());

    s1.getQuizzes()[5].setCurPoints(3f);
    s1.getQuizzes()[5].setTotPoints(5f);
    s1.calcCurGrade();
    System.out.println(s1.getCurGrade());

  }//end main


  public Student(){//start null constructor
    this.userID = "N/A";
    this.password = "N/A";
    this.firstName = "N/A";
    this.lastName = "N/A";
    
    initiateExams(3.0f);
    initiateHomework(1.0f);
    initiateQuizzes(2.0f);

    this.curGrade = -1f;
    this.numOfExams = 0;
    this.numOfHomework = 0;
    this.numOfQuizzes = 0;
  }//end null constructor


  public Student(float examWeight, float hwWeight, float quizWeight){//start constructor
    this.userID = "N/A";
    this.password = "N/A";
    this.firstName = "N/A";
    this.lastName = "N/A";
    
    initiateExams(examWeight);
    initiateHomework(hwWeight);
    initiateQuizzes(quizWeight);

    this.curGrade = -1f;
    this.numOfExams = 0;
    this.numOfHomework = 0;
    this.numOfQuizzes = 0;
  }//end constructor

  
  public void calcCurGrade(){//start calcCurGrade
    /*
    Will need to have a for loop that goes thru each homework, quiz, and exam and totals the points
    and adds the points using the current grade the current points on the assignment is -1
    and also has a totPoints over 0 and adding the points based on the grade weight
    Grade weight is calculated by curPoints * weight grade and add to total.
    */
    float curPoints = 0f;
    float totPoints = 0f;
    
    //start loops here
    //exams first
    for(int i=0; i<numOfExams; i+=1){//start 1st for
      if(exams[i].getCurPoints() > -1f  &&  exams[i].getTotPoints() > -1f  &&  exams[i].getName() != "N/A"){//start 1st if
        curPoints = curPoints + (exams[i].getCurPoints()*exams[i].getGradeWeight());
        totPoints = totPoints + (exams[i].getTotPoints()*exams[i].getGradeWeight());
      }//end 1st if
    }//end 1st for

    //homework next
    for(int i=0; i<numOfHomework; i+=1){//start 1st for
      if(allHomework[i].getCurPoints() > -1f  &&  allHomework[i].getTotPoints() > -1f  &&  allHomework[i].getName() != "N/A"){//start 1st if
        curPoints = curPoints + (allHomework[i].getCurPoints()*allHomework[i].getGradeWeight());
        totPoints = totPoints + (allHomework[i].getTotPoints()*allHomework[i].getGradeWeight());
      }//end 1st if
    }//end 1st for

    //quizzes next
    for(int i=0; i<numOfQuizzes; i+=1){//start 1st for
      if(quizzes[i].getCurPoints() > -1f  &&  quizzes[i].getTotPoints() > -1f  &&  quizzes[i].getName() != "N/A"){//start 1st if
        curPoints = curPoints + (quizzes[i].getCurPoints()*quizzes[i].getGradeWeight());
        totPoints = totPoints + (quizzes[i].getTotPoints()*quizzes[i].getGradeWeight());
      }//end 1st if
    }//end 1st for


    if(totPoints>0f){//start 1st if
      this.curGrade = curPoints/totPoints;
    }//end 1st if
    else{//start 1st else
      this.curGrade = -1f;
    }//end 1st else
    
  }//end calcCurGrade
  
  
  public float getCurGrade(){//start getName
    //calcs grade first
    calcCurGrade();

    return this.curGrade;
  }//end getName

  
  public void initiateExams(float examWeight){//start initiateExams
    for(int i=0; i<10; i+=1){//start 1st for
      exams[i] = new Exam(-1f, -1f, examWeight, "N/A");
    }//end 1st for
  }//end initiateExams

  
  public void initiateHomework(float hwWeight){//start initiateHomework
    for(int i=0; i<25; i+=1){//start 1st for
      allHomework[i] = new Homework(-1f, -1f, hwWeight, "N/A");
    }//end 1st for
  }//end initiateHomework

  
  public void initiateQuizzes(float quizWeight){//start initiateQuizzes
    for(int i=0; i<20; i+=1){//start 1st for
      quizzes[i] = new Quiz(-1f, -1f, quizWeight, "N/A");
    }//end 1st for
  }//end initiateQuizzes


  public Exam[] getExams(){//start getExams
    return this.exams;
  }//end getExams


  public Homework[] getAllHomework(){//start getAllHomework
    return this.allHomework;
  }//end getAllHomework


  public Quiz[] getQuizzes(){//start getQuizzes
    return this.quizzes;
  }//end getQuizzes


  public void addExam(float totPoints, float curPoints, float gradeWeight, String name){//start addExam
    //the name will already be checked to see if it already exists in the student's current courses exams
    //and will also already be checked to see if they already have the max amount of exams
    //floats will already be checked to see if they are >= 0
    exams[this.numOfExams] = new Exam(totPoints, curPoints, gradeWeight, name);

    this.numOfExams = this.numOfExams+1;
    //System.out.println("Exam named "+name+" successfully added for student "+this.firstName+" "+this.lastName+".");
  }//end addExam


  public void addHomework(float totPoints, float curPoints, float gradeWeight, String name){//start addHomework
    //the name will already be checked to see if it already exists in the student's current courses homework
    //and will also be checked to see if they already have the max amount of homework
    //floats will already be checked to see if they are >= 0
    allHomework[this.numOfHomework] = new Homework(totPoints, curPoints, gradeWeight, name);

    this.numOfHomework = this.numOfHomework+1;
    //System.out.println("Homework named "+name+" successfully added for student "+this.firstName+" "+this.lastName+".");
  }//end addHomework


  public void addQuiz(float totPoints, float curPoints, float gradeWeight, String name){//start addQuiz
    //the name will already be checked to see if it already exists in the student's current courses quizzes
    //and will also be checked to see if they already have the max amount of quizzes
    //floats will already be checked to see if they are >= 0
    quizzes[this.numOfQuizzes] = new Quiz(totPoints, curPoints, gradeWeight, name);

    this.numOfQuizzes = this.numOfQuizzes+1;
    //System.out.println("Quiz named "+name+" successfully added for student "+this.firstName+" "+this.lastName+".");
  }//end addQuiz


  public void removeExam(String name, int loc, float examWeight){//start removeExam
    //the name will already be checked to see if the name actually exists
    //and will also already be checked to see if numOfExams is bigger than 0
    if(Objects.equals(exams[loc].getName(), name)  &&  loc != this.numOfExams-1){//start 1st if
      exams[loc] = exams[this.numOfExams-1];
      exams[this.numOfExams-1] = new Exam(-1f, -1f, examWeight, "N/A");
    }//end 1st if
    else if(Objects.equals(exams[loc].getName(), name)  &&  loc == this.numOfExams-1){//start 1st if
      exams[loc] = new Exam(-1f, -1f, examWeight, "N/A");
    }//end 1st if

    this.numOfExams = this.numOfExams-1;
    //System.out.println("Exam named "+name+" successfully removed for student "+this.firstName+" "+this.lastName+".");
  }//end removeExam


  public void removeHomework(String name, int loc, float hwWeight){//start removeHomework
    //the name will already be checked to see if the name actually exists
    //and will also already be checked to see if this.numOfHomework is bigger than 0
    if(Objects.equals(allHomework[loc].getName(), name)  &&  loc != this.numOfHomework-1){//start 1st if
      allHomework[loc] = allHomework[this.numOfHomework-1];
      allHomework[this.numOfHomework-1] = new Homework(-1f, -1f, hwWeight, "N/A");
    }//end 1st if
    else if(Objects.equals(allHomework[loc].getName(), name)  &&  loc == this.numOfHomework-1){//start 1st if
      allHomework[loc] = new Homework(-1f, -1f, hwWeight, "N/A");
    }//end 1st if

    this.numOfHomework = this.numOfHomework-1;
    //System.out.println("Homework named "+name+" successfully removed for student "+this.firstName+" "+this.lastName+".");
  }//end removeHomework


  public void removeQuiz(String name, int loc, float quizWeight){//start removeQuiz
    //the name will already be checked to see if the name actually exists
    //and will also already be checked to see if this.numOfQuizzes is bigger than 0
    if(Objects.equals(quizzes[loc].getName(), name)  &&  loc != this.numOfQuizzes-1){//start 1st if
      quizzes[loc] = quizzes[this.numOfQuizzes-1];
      quizzes[this.numOfQuizzes-1] = new Quiz(-1f, -1f, quizWeight, "N/A");
    }//end 1st if
    else if(Objects.equals(quizzes[loc].getName(), name)  &&  loc == this.numOfQuizzes-1){//start 1st if
      quizzes[loc] = new Quiz(-1f, -1f, quizWeight, "N/A");
    }//end 1st if

    this.numOfQuizzes = this.numOfQuizzes-1;
    //System.out.println("Quiz named "+name+" successfully removed for student "+this.firstName+" "+this.lastName+".");
  }//end removeQuiz

  public int getNumOfExams(){
  return this.numOfExams;
  }//end getNumOfExams


  public int getNumOfHomework(){
  return this.numOfHomework;
  }//end getNumOfHomework


  public int getNumOfQuizzes(){
  return this.numOfQuizzes;
  }//end getNumOfQuizzes
  
  
}//end class