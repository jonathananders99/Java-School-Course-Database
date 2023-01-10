import java.util.*;

public class Course extends AssignmentWeight{//start class

  protected Subject subject;

  protected Student[] students = new Student[100];

  protected String courseName;

  //number of active students in a single instance of the class
  protected int numOfStudents;
  
  public static void main(String[] args){//start main
    Course c1 = new Course();
    
    System.out.println(c1.getSubject());
    c1.setSubject("SCIENCE");
    System.out.println(c1.getSubject());

    System.out.println(c1.getStudents()[0].getFirstName());
    c1.getStudents()[0].setFirstName("Joe");
    System.out.println(c1.getStudents()[0].getFirstName());

    System.out.println(c1.getStudents()[0].getQuizzes()[0].getCurPoints());
    c1.getStudents()[0].getQuizzes()[0].setCurPoints(5f);
    System.out.println(c1.getStudents()[0].getQuizzes()[0].getCurPoints());
    
  }//end main


  public Course(){//start null constructor
    this.examWeight = 3.0f;
    this.hwWeight = 1.0f;
    this.quizWeight = 2.0f;

    for(int i=0; i<25; i+=1){//start 1st for
      students[i] = new Student(this.examWeight, this.hwWeight, this.quizWeight);
    }//end 1st for

    this.courseName = "N/A";
    this.numOfStudents = 0;
  }//end null constructor


  public void setSubject(String subject){//start setSubject
    //search here for subject name in enum
    //sub will already be in upper case before it reaches this point
    //sub also will be rejected if it doesnt equal one of the subjects before this point too

    if(subject=="SCIENCE"){//start 1st if
      this.subject = Subject.SCIENCE;
    }//end 1st if

    else if(subject=="MATH"){//start 1st else/if
      this.subject = Subject.MATH;
    }//end 1st else/if

    else if(subject=="SOCIAL_STUDIES"){//start 1st else/if
      this.subject = Subject.SOCIAL_STUDIES;
    }//end 1st else/if

    else if(subject=="ENGLISH"){//start 1st else/if
      this.subject = Subject.ENGLISH;
    }//end 1st else/if

    else if(subject=="ART"){//start 1st else/if
      this.subject = Subject.ART;
    }//end 1st else/if

    else if(subject=="PHYSICAL_EDUCATION"){//start 1st else/if
      this.subject = Subject.PHYSICAL_EDUCATION;
    }//end 1st else/if

  }//end setSubject


  public Subject getSubject(){//start getSubject
    return this.subject;
  }//end getSubject


  public Student[] getStudents(){//start getStudents
    return students;
  }//end getStudents

  
  public void setCourseName(String name){//start setCourseName
    this.courseName = name;
  }//end setCourseName


  public String getCourseName(){//start getCourseName
    return this.courseName;
  }//end getCourseName


  public void addStudent(String userID, String password, String fName, String lName){//start addStudent
    //userid will already be checked to see if it matches another in the same course (can have one student in more than one course)
    //userid is the first 4 of the last name, 1st letts of first name and a number based on if there are doubles
    //it will also check to see if they are trying to add a 26th student to a single class 
    students[this.numOfStudents].setUserID(userID);
    students[this.numOfStudents].setPassword(password);
    students[this.numOfStudents].setFirstName(fName);
    students[this.numOfStudents].setLastName(lName);
    
    this.numOfStudents = this.numOfStudents + 1;
    System.out.println("Student named "+fName+" "+lName+" has been added to the course.");
  }//end addStudent


  public void removeStudent(String userID, int loc){//start removeStudent
    //the user id will already be checked to see if the user is in the course
    //and will also already be checked to see if this.numOfStudents is bigger than 0
    if(Objects.equals(students[loc].getUserID(), userID)  &&  loc != this.numOfStudents-1){//start 1st if
      students[loc] = students[this.numOfStudents-1];
      students[this.numOfStudents-1] = new Student();
    }//end 1st if
    else if(Objects.equals(students[loc].getUserID(), userID)  &&  loc == this.numOfStudents-1){//start 1st if
      students[loc] = new Student();
    }//end 1st if

    this.numOfStudents = this.numOfStudents-1;
    System.out.println("Student with the id "+userID+" has been removed from the course.");
  }//end removeStudent


  public int getNumOfStudents(){//start getNumOfStudents
    return this.numOfStudents;
  }//end getNumOfStudents


}//end class