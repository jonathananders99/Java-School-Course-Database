import java.io.*;
import java.util.*;

public class ALP{//start class

  public static int maxCourses = 10;
  
  public Course[] courses = new Course[maxCourses];

  public int numOfCourses = 0;

  
  public static void main(String[] args){//start main
    new ALP();
  }//end main


  public ALP(){//start null constructor
/*
    initiateCourses();
    addCourse("Statistics 101", "MATH");
    System.out.println(this.numOfCourses);
    courses[0].addStudent("SmithE1", "password", "Evan", "Smith");
    courses[0].addStudent("RoberC1", "password", "Chadeous", "Roberts");
    courses[0].addStudent("SteveC1", "password", "Crazy", "Steve");
    addCourse("Physical Science I", "SCIENCE");
    System.out.println(this.numOfCourses);
    courses[1].addStudent("SmithE1", "password", "Evan", "Smith");
    courses[1].addStudent("BrownR1", "password", "Reba", "Brown");
    float num = 0f;
    for(int i=0; i<25; i+=1){//start 1st for
      courses[0].getStudents()[i].addExam(100, -1f, courses[0].examWeight, "Exam 1");
    }//end 1st for
    num = 85f;
    for(int i=0; i<courses[0].getNumOfStudents(); i+=1){//start 1st for
      courses[0].getStudents()[i].getExams()[0].setCurPoints(num);
      num = num + 5f;
    }//end 1st for
    for(int i=0; i<25; i+=1){//start 1st for
      courses[0].getStudents()[i].addHomework(20, -1f, courses[0].hwWeight, "HW 1");
    }//end 1st for
    for(int i=0; i<20; i+=1){//start 1st for
      courses[0].getStudents()[i].addQuiz(5, -1f, courses[0].quizWeight, "Quiz 1");
    }//end 1st for
    num = 1f;
    for(int i=0; i<courses[0].getNumOfStudents(); i+=1){//start 1st for
      courses[0].getStudents()[i].getQuizzes()[0].setCurPoints(num);
      num = num + 1f;
    }//end 1st for
    for(int i=0; i<25; i+=1){//start 1st for
      courses[0].getStudents()[i].addExam(100, -1f, courses[0].examWeight, "Exam 2");
    }//end 1st for
    for(int i=0; i<25; i+=1){//start 1st for
      courses[0].getStudents()[i].addHomework(20, -1f, courses[0].hwWeight, "HW 2");
    }//end 1st for
    for(int i=0; i<25; i+=1){//start 1st for
      courses[1].getStudents()[i].addExam(100, -1f, courses[1].examWeight, "Exam 1");
    }//end 1st for
    for(int i=0; i<25; i+=1){//start 1st for
      courses[1].getStudents()[i].addHomework(20, -1f, courses[1].hwWeight, "HW 1");
    }//end 1st for
    for(int i=0; i<20; i+=1){//start 1st for
      courses[1].getStudents()[i].addQuiz(5, -1f, courses[1].quizWeight, "Quiz 1");
    }//end 1st for


    saveCourses();
*/
    loadCourses();

    //this is where menu will be called and the program will actually have output for the user to see
    loginMenu();

  }//end null constructor

  public void initiateCourses(){//start initiateCourses
    for(int i=0; i<maxCourses; i+=1){//start 1st for
      courses[i] = new Course();
    }//end 1st for
  }//end initiateCourses


  public void saveCourses(){//start saveCourses

    try{//start 1st try
      FileOutputStream fo = new FileOutputStream("courseArray.dat");
      ObjectOutputStream obOut = new ObjectOutputStream(fo);
      obOut.writeObject(this.courses);
      obOut.close();
    }//end 1st try

    catch(Exception e){//start 1st catch
      System.out.println(e.getMessage());
    }//end 1st catch

  }//end saveCourses


  public void loadCourses(){//start loadCourses

    try{//start 1st try
      FileInputStream fIn = new FileInputStream("courseArray.dat");
      ObjectInputStream obIn = new ObjectInputStream(fIn);
      this.courses = (Course[])obIn.readObject();
      obIn.close();
    }//end 1st try

    catch(Exception e){//start 1st catch
      System.out.println(e.getMessage());
    }//end 1st catch
    
    //gets number of courses
    numOfCourses = 0;
    for(int i=0; i<maxCourses; i+=1){//start 1st for
      if(!Objects.equals(courses[i].getCourseName(), "N/A")){//start 1st if
        numOfCourses = numOfCourses + 1;
      }//end 1st if
    }//end 1st for

  }//end loadCourses


  public void addCourse(String name, String subject){//start addCourse
    //subject will already be checked to see if it matches one in the enum Subject
    
    courses[this.numOfCourses].setCourseName(name);
    courses[this.numOfCourses].setSubject(subject);

    this.numOfCourses = this.numOfCourses + 1;
    System.out.println("Course named "+name+" with a subject "+subject+" of successfully added.");
  }//end addCourse


  public void removeCourse(String name, int loc){//start removeCourse
    //the name will already be checked to see if the name actually exists
    //and will also already be checked to see if exam[0].numOfCourses is bigger than 0
    if(Objects.equals(courses[loc].getCourseName(), name)  &&  loc != this.numOfCourses-1){//start 1st if
      courses[loc] = courses[this.numOfCourses-1];
      courses[this.numOfCourses-1] = new Course();
    }//end 1st if
    else if(Objects.equals(courses[loc].getCourseName(), name)  &&  loc == this.numOfCourses-1){//start 1st if
      courses[loc] = new Course();
    }//end 1st if

    this.numOfCourses = this.numOfCourses-1;

  }//end removeCourse


  public void loginMenu(){//start loginMenu
    Scanner userInput = new Scanner(System.in);
    System.out.println("\nWelcome to ALP!\nPlease enter your User ID:");
    String inputID = userInput.nextLine();
    System.out.println("Please enter your Password:");
    String inputPass = userInput.nextLine();

    //checks to see if its a teacher/admin
    if(Objects.equals("Teacher", inputID)  &&  Objects.equals("Teacher", inputPass)){//start 1st if
      teacherMenu();
    }//end 1st if
    
    //if its a student or someone not in the system
    else{//start 1st if/else
      int[] courseLocs = new int[this.numOfCourses];
      String fName = "";
      String lName = "";

      //cycles thru all active courses then active students and if their password and userID are = it marks it down
      for(int i=0; i<this.numOfCourses; i+=1){//start 1st for
        //puts courseLocs as -1
        courseLocs[i] = -1;
        for(int x=0; x<courses[i].getNumOfStudents(); x+=1){//start 2nd for

          String curID = courses[i].getStudents()[x].getUserID();
          String curPass = courses[i].getStudents()[x].getPassword();

          if(Objects.equals(curID, inputID)  &&  Objects.equals(curPass, inputPass)){//start 2nd if
            courseLocs[i] = x;
            fName = courses[i].getStudents()[x].getFirstName();
            lName = courses[i].getStudents()[x].getLastName();
          }//end 2nd if

        }//end 2nd for
      }//end 1st for

      if(Objects.equals(fName, "")  &&  Objects.equals(lName, "")){//start 2nd if
        System.out.println("Credentials not recognized, goodbye.");
      }//end 2nd if

      else{//start 2nd if/else
        studentMenu(courseLocs, fName, lName, inputID);
      }//end 2nd if/else

    }//end 1st if/else
    userInput.close();
  }//end loginMenu


  public void teacherMenu(){//start teacherMenu
    Scanner userInput = new Scanner(System.in);

    boolean keepGoing = true;

    //starts teacher menu
    while(keepGoing){//start 1st while
      //get all course names in a nice looking order
      String courseChoices = "";
      for(int i=0; i<this.numOfCourses; i+=1){//start 1st for
        courseChoices = courseChoices+"\n"+i+") "+courses[i].getCourseName();
      }//end 1st for
      
      //adds exit option
      courseChoices = courseChoices+"\n"+this.numOfCourses+") Add course";

      //adds exit option
      courseChoices = courseChoices+"\n"+(this.numOfCourses+1)+") Exit";

      //next thing is to choose the course
      System.out.println("\nWelcome Teacher! Please choose a course from the list (input number of course listed below)"+courseChoices);
      String menuChoice1 = userInput.nextLine();
      int menuChoice1Num = -1;

      //converts choice into an int
      try{//start 1st try
        menuChoice1Num = Integer.parseInt(menuChoice1);
      }//end 1st try
      catch(Exception e){//start 1st catch
        //does nothing
      }//end 1st catch


      //exit
      if(Objects.equals(menuChoice1Num, this.numOfCourses+1)){//start 1st if
        keepGoing = false;
        System.out.println("Thanks for using ALP, goodbye.");
      }//end 1st if


      //add course
      else if(Objects.equals(menuChoice1Num, this.numOfCourses)){//start 1st if/else
        
        //course name
        System.out.println("\nPlease input the name of the new course:");
        String courseName = userInput.nextLine();
        boolean keepGoing2 = true;

        //course subject
        String courseSubject = "";

        while(keepGoing2){//start 2nd while
          System.out.println("Please choose the number of the correcponding course subject:\n0) Science\n1) Math\n2) Social Studies\n3) English\n4) Art\n5) Physical Education");
          String subjectInput = userInput.nextLine();
          //science
          if(Objects.equals(subjectInput, "0")){//start 2nd if
            courseSubject = "SCIENCE";
            keepGoing2 = false;
          }//end 2nd if

          //math
          else if(Objects.equals(subjectInput, "1")){//start 2nd if/else
            courseSubject = "MATH";
            keepGoing2 = false;
          }//end 2nd if/else

          //SOCIAL_STUDIES
          else if(Objects.equals(subjectInput, "2")){//start 2nd if/else
            courseSubject = "SOCIAL_STUDIES";
            keepGoing2 = false;
          }//end 2nd if/else

          //ENGLISH
          else if(Objects.equals(subjectInput, "3")){//start 2nd if/else
            courseSubject = "ENGLISH";
            keepGoing2 = false;
          }//end 2nd if/else

          //ART
          else if(Objects.equals(subjectInput, "4")){//start 2nd if/else
            courseSubject = "ART";
            keepGoing2 = false;
          }//end 2nd if/else

          //PHYSICAL_EDUCATION
          else if(Objects.equals(subjectInput, "5")){//start 2nd if/else
            courseSubject = "PHYSICAL_EDUCATION";
            keepGoing2 = false;
          }//end 2nd if/else

          //not one of the options
          else{//start 2nd if/else
            System.out.println("Input not recognized, please input a number listed in the list (0-5)");
          }//end 2nd if/else

        }//end 2nd while

        //adds course here
        addCourse(courseName, courseSubject);
      }//end 1st if/else


      //choose a course
      else if(menuChoice1Num >= 0  &&  menuChoice1Num < this.numOfCourses){//start 1st if/else
        //teacher course submenu
        boolean keepGoing2 = true;

        while(keepGoing2){//start 2nd while
          System.out.println("\nPlease input a number from the following options:\n0) Add something\n1) Check something\n2) Change something\n3) Back");
          String menuChoice2 = userInput.nextLine();
          

          //back
          if(Objects.equals(menuChoice2, "3")){//start 2nd if
            keepGoing2 = false;
          }//end 2nd if


          //add something
          else if(Objects.equals(menuChoice2, "0")){//start 2nd if/else
            //teacher course add something submenu
            boolean keepGoing3 = true;
  
            while(keepGoing3){//start 3rd while
              System.out.println("\nPlease input a number from the following options:\n0) Add exam\n1) Add homework\n2) Add quiz\n3) Add student\n4) Back");
              String menuChoice3 = userInput.nextLine();
            
              //back
              if(Objects.equals(menuChoice3, "4")){//start 3rd if
                keepGoing3 = false;
              }//end 3rd if


              //add exam
              else if(Objects.equals(menuChoice3, "0")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //asks for name
                  System.out.println("\nPlease input the desired name of the exam:");
                  String inputExamName = userInput.nextLine();

                  //asks for grade weight
                  System.out.println("\nPlease input the desired amount of points for the exam:");
                  String inputExamPoints = userInput.nextLine();
  
                  //checks to see if name matches another one or if it = "N/A"
                  String matches = "n";
                  for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfExams(); i+=1){//start 1st for
                    if(Objects.equals(courses[menuChoice1Num].getStudents()[0].getExams()[i].getName(), inputExamName )  ||  Objects.equals("N/A", inputExamName)){//start 4th if
                      matches = "y";
                    }//end 4th if
                  }//end 1st for

                  //tries to make the points into float
                  try{//start 1st try
                    float examPoints = Float.parseFloat(inputExamPoints);
                    
                    //checks to see if name is taken
                    if(matches == "y"){//start 4th if
                      System.out.println("Inputed name is either already taken or is 'N/A' which isn't allowed.");
                    }//end 4th if

                    //checks to see if the input is between 0-1000
                    else if(examPoints >= 0  &&  examPoints <= 1000){//start 4th if/else
                      //adds exam in for loop for max num of exams (10)
                      for(int i=0; i<25; i+=1){//start 1st for
                        courses[menuChoice1Num].getStudents()[i].addExam(examPoints, -1f, courses[menuChoice1Num].examWeight, inputExamName);
                      }//end 1st for
                      keepGoing4 = false;
                      System.out.println("Successfully added exam, there are now a total of "+courses[menuChoice1Num].getStudents()[0].getNumOfExams()+" exams.");
                    }//end 4th if/else
  
                    else if(examPoints < 0  ||  examPoints > 1000){//start 4th if/else
                      //not a valid input
                      System.out.println("Input needs to be a number between 0-1000");
                    }//end 4th if/else
  
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //not a valid input
                    System.out.println("Input needs to be a number between 0-1000");
                  }//end 1st catch

                }//end 4th while
              }//end 3rd if/else
  

              //add hw
              else if(Objects.equals(menuChoice3, "1")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //asks for name
                  System.out.println("\nPlease input the desired name of the homework:");
                  String inputHomeworkName = userInput.nextLine();

                  //asks for grade weight
                  System.out.println("\nPlease input the desired amount of points for the homework:");
                  String inputHomeworkPoints = userInput.nextLine();
  
                  //checks to see if name matches another one or if it = "N/A"
                  String matches = "n";
                  for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfHomework(); i+=1){//start 1st for
                    if(Objects.equals(courses[menuChoice1Num].getStudents()[0].getAllHomework()[i].getName(), inputHomeworkName )  ||  Objects.equals("N/A", inputHomeworkName)){//start 4th if
                      matches = "y";
                    }//end 4th if
                  }//end 1st for

                  //tries to make the points into float
                  try{//start 1st try
                    float homeworkPoints = Float.parseFloat(inputHomeworkPoints);
                    
                    //checks to see if name is taken
                    if(matches == "y"){//start 4th if
                      System.out.println("Inputed name is either already taken or is 'N/A' which isn't allowed.");
                    }//end 4th if

                    //checks to see if the input is between 0-1000
                    else if(homeworkPoints >= 0  &&  homeworkPoints <= 1000){//start 4th if/else
                      //adds homework in for loop for max num of homework(25)
                      for(int i=0; i<25; i+=1){//start 1st for
                        courses[menuChoice1Num].getStudents()[i].addHomework(homeworkPoints, -1f, courses[menuChoice1Num].hwWeight, inputHomeworkName);
                      }//end 1st for
                      keepGoing4 = false;
                      System.out.println("Successfully added homework, there are now a total of "+courses[menuChoice1Num].getStudents()[0].getNumOfHomework()+" homework.");
                    }//end 4th if/else
  
                    else if(homeworkPoints < 0  ||  homeworkPoints > 1000){//start 4th if/else
                      //not a valid input
                      System.out.println("Input needs to be a number between 0-1000");
                    }//end 4th if/else
  
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //not a valid input
                    System.out.println("Input needs to be a number between 0-1000");
                  }//end 1st catch

                }//end 4th while
              }//end 3rd if/else
    

              //add quiz
              else if(Objects.equals(menuChoice3, "2")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //asks for name
                  System.out.println("\nPlease input the desired name of the quiz:");
                  String inputQuizName = userInput.nextLine();

                  //asks for grade weight
                  System.out.println("\nPlease input the desired amount of points for the quiz:");
                  String inputQuizPoints = userInput.nextLine();
  
                  //checks to see if name matches another one or if it = "N/A"
                  String matches = "n";
                  for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes(); i+=1){//start 1st for
                    if(Objects.equals(courses[menuChoice1Num].getStudents()[0].getQuizzes()[i].getName(), inputQuizName )  ||  Objects.equals("N/A", inputQuizName)){//start 4th if
                      matches = "y";
                    }//end 4th if
                  }//end 1st for

                  //tries to make the points into float
                  try{//start 1st try
                    float quizPoints = Float.parseFloat(inputQuizPoints);
                    
                    //checks to see if name is taken
                    if(matches == "y"){//start 4th if
                      System.out.println("Inputed name is either already taken or is 'N/A' which isn't allowed.");
                    }//end 4th if

                    //checks to see if the input is between 0-1000
                    else if(quizPoints >= 0  &&  quizPoints <= 1000){//start 4th if/else
                      //adds quiz in for loop for max num of quizzes (20)
                      for(int i=0; i<20; i+=1){//start 1st for
                        courses[menuChoice1Num].getStudents()[i].addQuiz(quizPoints, -1f, courses[menuChoice1Num].quizWeight, inputQuizName);
                      }//end 1st for
                      keepGoing4 = false;
                      System.out.println("Successfully added quiz, there are now a total of "+courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes()+" quizzes.");
                    }//end 4th if/else
  
                    else if(quizPoints < 0  ||  quizPoints > 1000){//start 4th if/else
                      //not a valid input
                      System.out.println("Input needs to be a number between 0-1000");
                    }//end 4th if/else
  
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //not a valid input
                    System.out.println("Input needs to be a number between 0-1000");
                  }//end 1st catch

                }//end 4th while
              }//end 3rd if/else
    

              //add student
              else if(Objects.equals(menuChoice3, "3")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //asks for first name
                  System.out.println("\nPlease input the first name of the student:");
                  String inputFirstName = userInput.nextLine();

                  //asks for last name
                  System.out.println("\nPlease input the last name of the student:");
                  String inputLastName = userInput.nextLine();
  
                  //asks for userId
                  System.out.println("\nPlease input the desired userID of the student:");
                  String inputUserID = userInput.nextLine();
                
                  //searches to see if there is a matching student
                  for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                    String curID = courses[menuChoice1Num].getStudents()[i].getUserID();

                    //does the entered info match this student already in the course?
                    if(Objects.equals(curID, inputUserID)){//start 4th if
                      System.out.println("\nStudent is already apart of the course or at least the userID is taken in the course");
                      i = 30;
                    }//end 4th if

                    else if(courses[menuChoice1Num].getNumOfStudents() == 25){//start 4th if/else
                      //too many students/ reached student cap already
                      System.out.println("\nThe max number of students in this course has been reached. Try removing a student before adding another.");
                      keepGoing4 = false;
                      i = 30;
                    }//end 4th if/else


                    else if(i == courses[menuChoice1Num].getNumOfStudents()-1){//start 4th if/else
                      //adds student
                      courses[menuChoice1Num].addStudent(inputUserID, "password", inputFirstName, inputLastName);
                      i = 30;
                      keepGoing4 = false;
                    }//end 4th if/else

                  }//end 1st for

                  if(courses[menuChoice1Num].getNumOfStudents() == 0){//start 4th if
                    //adds student
                    courses[menuChoice1Num].addStudent(inputUserID, "password", inputFirstName, inputLastName);
                    keepGoing4 = false;
                  }//end 4th if

                }//end 4th while
              }//end 3rd if/else
    

              //not one of the options
              else{//start 3rd if/else
                System.out.println("Input not recognized, please input a number listed in the list (0-3)");
              }//end 3rd if/else

            }//end 3rd while
          }//end 2nd if/else


          //check something
          else if(Objects.equals(menuChoice2, "1")){//start 2nd if/else
            //teacher course check something submenu
            boolean keepGoing3 = true;
  
            while(keepGoing3){//start 3rd while
              System.out.println("\nPlease input a number from the following options:\n0) Check exam\n1) Check homework\n2) Check quiz\n3) Check course info\n4) Check student\n5) Check assignment type grade weights\n6) Back");
              String menuChoice3 = userInput.nextLine();
            
              //back
              if(Objects.equals(menuChoice3, "6")){//start 3rd if
                keepGoing3 = false;
              }//end 3rd if
  

              //check exam
              else if(Objects.equals(menuChoice3, "0")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //gets all exams to have user select from
                  String examChoices = "";
                  for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfExams(); i+=1){//start 1st for
                    examChoices = examChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getExams()[i].getName();
                  }//end 1st for

                  //adds back selection
                  examChoices = examChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfExams() + ") Back";

                  //asks selection of which exam
                  System.out.println("\nPlease input a number from the following options:"+examChoices);
                  String menuChoice4 = userInput.nextLine();
                  int menuChoice4Num = -1;

                  //converts choice into an int
                  try{//start 1st try
                    menuChoice4Num = Integer.parseInt(menuChoice4);
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //does nothing
                  }//end 1st catch

                  //back
                  if(Objects.equals(menuChoice4Num, courses[menuChoice1Num].getStudents()[0].getNumOfExams())){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if

                  else if(menuChoice4Num >= 0  && menuChoice4Num < courses[menuChoice1Num].getStudents()[0].getNumOfExams()){//start 4th if/else
                    //gather data all data of exam
                    /*info to be shown:
                      
                      Exam Name: |Name|
                      Total Points: |points|
                      Grade Weight: |weight|
                      Total Number Graded: |number| out of |number of students in course|
                      Average Score: |number|%
                    */
                    //num graded and average score first
                    int numGraded = 0;
                    float avgExamCurPoints = 0f;
                    for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                      float curExamCurPoints = courses[menuChoice1Num].getStudents()[i].getExams()[menuChoice4Num].getCurPoints();
                      //if exam has been graded/isnt -1
                      if(!Objects.equals(curExamCurPoints, -1f)){//start 5th if
                        numGraded = numGraded + 1;
                        avgExamCurPoints = avgExamCurPoints + curExamCurPoints;
                      }//end 5th if
                    }//end 1st for

                    //make avgExamCurPoints into its correct form
                    avgExamCurPoints = avgExamCurPoints / numGraded;
                    avgExamCurPoints = avgExamCurPoints / courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice4Num].getTotPoints();
                    avgExamCurPoints = avgExamCurPoints * 100f;

                    //shows results
                    System.out.println("\nExam Name: " + courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice4Num].getName() + "\nTotal Points: " + courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice4Num].getTotPoints() + "\nGrade Weight: " + courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice4Num].getGradeWeight() + "\nTotal Number Graded: " + numGraded + " out of " + courses[menuChoice1Num].getNumOfStudents() + "\nAverage Score: " + avgExamCurPoints + "%");

                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
  

              //check hw
              else if(Objects.equals(menuChoice3, "1")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //gets all homework to have user select from
                  String homeworkChoices = "";
                  for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfHomework(); i+=1){//start 1st for
                    homeworkChoices = homeworkChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[i].getName();
                  }//end 1st for

                  //adds back selection
                  homeworkChoices = homeworkChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfHomework() + ") Back";

                  //asks selection of which homework
                  System.out.println("\nPlease input a number from the following options:"+homeworkChoices);
                  String menuChoice4 = userInput.nextLine();
                  int menuChoice4Num = -1;

                  //converts choice into an int
                  try{//start 1st try
                    menuChoice4Num = Integer.parseInt(menuChoice4);
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //does nothing
                  }//end 1st catch

                  //back
                  if(Objects.equals(menuChoice4Num, courses[menuChoice1Num].getStudents()[0].getNumOfHomework())){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if

                  else if(menuChoice4Num >= 0  && menuChoice4Num < courses[menuChoice1Num].getStudents()[0].getNumOfHomework()){//start 4th if/else
                    //gather data all data of homework
                    /*info to be shown:
                      
                      Homework Name: |Name|
                      Total Points: |points|
                      Grade Weight: |weight|
                      Total Number Graded: |number| out of |number of students in course|
                      Average Score: |number|%
                    */
                    //num graded and average score first
                    int numGraded = 0;
                    float avgHomeworkCurPoints = 0f;
                    for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                      float curHomeworkCurPoints = courses[menuChoice1Num].getStudents()[i].getAllHomework()[menuChoice4Num].getCurPoints();
                      //if homework has been graded/isnt -1
                      if(!Objects.equals(curHomeworkCurPoints, -1f)){//start 5th if
                        numGraded = numGraded + 1;
                        avgHomeworkCurPoints = avgHomeworkCurPoints + curHomeworkCurPoints;
                      }//end 5th if
                    }//end 1st for

                    //make avgHomeworkCurPoints into its correct form
                    avgHomeworkCurPoints = avgHomeworkCurPoints / numGraded;
                    avgHomeworkCurPoints = avgHomeworkCurPoints / courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice4Num].getTotPoints();
                    avgHomeworkCurPoints = avgHomeworkCurPoints * 100f;

                    //shows results
                    System.out.println("\nHomework Name: " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice4Num].getName() + "\nTotal Points: " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice4Num].getTotPoints() + "\nGrade Weight: " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice4Num].getGradeWeight() + "\nTotal Number Graded: " + numGraded + " out of " + courses[menuChoice1Num].getNumOfStudents() + "\nAverage Score: " + avgHomeworkCurPoints + "%");

                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
    

              //check quiz
              else if(Objects.equals(menuChoice3, "2")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //gets all quizzes to have user select from
                  String quizChoices = "";
                  for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes(); i+=1){//start 1st for
                    quizChoices = quizChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[i].getName();
                  }//end 1st for

                  //adds back selection
                  quizChoices = quizChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes() + ") Back";

                  //asks selection of which quiz
                  System.out.println("\nPlease input a number from the following options:"+quizChoices);
                  String menuChoice4 = userInput.nextLine();
                  int menuChoice4Num = -1;

                  //converts choice into an int
                  try{//start 1st try
                    menuChoice4Num = Integer.parseInt(menuChoice4);
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //does nothing
                  }//end 1st catch

                  //back
                  if(Objects.equals(menuChoice4Num, courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes())){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if

                  else if(menuChoice4Num >= 0  && menuChoice4Num < courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes()){//start 4th if/else
                    //gather data all data of quiz
                    /*info to be shown:
                      
                      Quiz Name: |Name|
                      Total Points: |points|
                      Grade Weight: |weight|
                      Total Number Graded: |number| out of |number of students in course|
                      Average Score: |number|%
                    */
                    //num graded and average score first
                    int numGraded = 0;
                    float avgQuizCurPoints = 0f;
                    for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                      float curQuizCurPoints = courses[menuChoice1Num].getStudents()[i].getQuizzes()[menuChoice4Num].getCurPoints();
                      //if quiz has been graded/isnt -1
                      if(!Objects.equals(curQuizCurPoints, -1f)){//start 5th if
                        numGraded = numGraded + 1;
                        avgQuizCurPoints = avgQuizCurPoints + curQuizCurPoints;
                      }//end 5th if
                    }//end 1st for

                    //make avgQuizCurPoints into its correct form
                    avgQuizCurPoints = avgQuizCurPoints / numGraded;
                    avgQuizCurPoints = avgQuizCurPoints / courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice4Num].getTotPoints();
                    avgQuizCurPoints = avgQuizCurPoints * 100f;

                    //shows results
                    System.out.println("\nQuiz Name: " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice4Num].getName() + "\nTotal Points: " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice4Num].getTotPoints() + "\nGrade Weight: " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice4Num].getGradeWeight() + "\nTotal Number Graded: " + numGraded + " out of " + courses[menuChoice1Num].getNumOfStudents() + "\nAverage Score: " + avgQuizCurPoints + "%");

                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
    

              //check course info
              else if(Objects.equals(menuChoice3, "3")){//start 3rd if/else
                //gathers average overall grade per student then divides by num of students
                /*info to be shown:
                  Course Name: |Name|
                  Course Subject: |subject|
                  Number of Students: |points|
                  Number of Exams: |number|
                  Number of Homework: |number|
                  Number of Quizzes: |number|
                  Average Grade: |number|%
                */

                //average score first
                float avgGrade = 0f;
                for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                  float curGrade = courses[menuChoice1Num].getStudents()[i].getCurGrade();
                  //if quiz has been graded/isnt -1
                  if(!Objects.equals(curGrade, -1f)){//start 4th if
                    avgGrade = avgGrade + curGrade;
                  }//end 4th if
                }//end 1st for

                //finalizes grades
                avgGrade = avgGrade / courses[menuChoice1Num].getNumOfStudents();
                avgGrade = avgGrade * 100f;

                //next is to get subject if its needed
                //here

                //shows results
                System.out.println("\nCourse Name: " + courses[menuChoice1Num].getCourseName() + "\nCourse Subject: " + courses[menuChoice1Num].getSubject() + "\nNumber of Students: " + courses[menuChoice1Num].getNumOfStudents() + "\nNumber of Exams: " + courses[menuChoice1Num].getStudents()[0].getNumOfExams() + "\nNumber of Homework: " + courses[menuChoice1Num].getStudents()[0].getNumOfHomework() + "\nNumber of Quizzes: " + courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes() + "\nAverage Grade: " + avgGrade + "%");

              }//end 3rd if/else
    

              //check student
              else if(Objects.equals(menuChoice3, "4")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //gets all studentzes to have user select from
                  String studentChoices = "";
                  for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                    studentChoices = studentChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[i].getFirstName() + " " + courses[menuChoice1Num].getStudents()[i].getLastName() + " ~ User ID: " + courses[menuChoice1Num].getStudents()[i].getUserID();
                  }//end 1st for

                  //adds back selection
                  studentChoices = studentChoices + "\n" + courses[menuChoice1Num].getNumOfStudents() + ") Back";

                  //asks selection of which student
                  System.out.println("\nPlease input a number from the following options:"+studentChoices);
                  String menuChoice4 = userInput.nextLine();
                  int menuChoice4Num = -1;

                  //converts choice into an int
                  try{//start 1st try
                    menuChoice4Num = Integer.parseInt(menuChoice4);
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //does nothing
                  }//end 1st catch

                  //back
                  if(Objects.equals(menuChoice4Num, courses[menuChoice1Num].getNumOfStudents())){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if

                  else if(menuChoice4Num >= 0  && menuChoice4Num < courses[menuChoice1Num].getNumOfStudents()){//start 4th if/else
                    //gather data all data of student
                    /*info to be shown:
                      
                      Student's Full Name: |Name|
                      User ID: |ID|
                      Current Grade: |number|%
                      Exams: 
                       - |Exam Name:| ~ |curPoints|/|totPoints|
                       - |Exam Name:| ~ |curPoints|/|totPoints|
                      Homework: 
                       - |Homework Name:| ~ |curPoints|/|totPoints|
                       - |Homework Name:| ~ |curPoints|/|totPoints|
                      Quizzes: 
                       - |Quiz Name:| ~ |curPoints|/|totPoints|
                       - |Quiz Name:| ~ |curPoints|/|totPoints|
                    */

                    //first is to get exam info
                    String allExamsInfo = "\nExams:";
                    for(int i=0; i<courses[menuChoice1Num].getStudents()[menuChoice4Num].getNumOfExams(); i+=1){//start 1st for

                      //gets cur exam
                      Exam curExam = courses[menuChoice1Num].getStudents()[menuChoice4Num].getExams()[i];

                      //if name isnt "N/A" and isnt -1 score
                      if(!Objects.equals(curExam.getName(), "N/A")  &&  !Objects.equals(curExam.getCurPoints(), -1f)){//start 5th if
                        allExamsInfo = allExamsInfo + "\n - " + curExam.getName() + " ~ Score: " + curExam.getCurPoints() + "/" + curExam.getTotPoints();
                      }//end 5th if

                      //if name isnt "N/A" and isnt -1 score
                      else if(!Objects.equals(curExam.getName(), "N/A")  &&  Objects.equals(curExam.getCurPoints(), -1f)){//start 5th if/else
                        allExamsInfo = allExamsInfo + "\n - " + curExam.getName() + " ~ Score: Not Graded/" + curExam.getTotPoints();
                      }//end 5th if/else

                    }//end 1st for


                    //next is to get hw info
                    String allHomeworkInfo = "\nHomework:";
                    for(int i=0; i<courses[menuChoice1Num].getStudents()[menuChoice4Num].getNumOfHomework(); i+=1){//start 1st for

                      //gets cur homework
                      Homework curHomework = courses[menuChoice1Num].getStudents()[menuChoice4Num].getAllHomework()[i];

                      //if name isnt "N/A" and isnt -1 score
                      if(!Objects.equals(curHomework.getName(), "N/A")  &&  !Objects.equals(curHomework.getCurPoints(), -1f)){//start 5th if
                        allHomeworkInfo = allHomeworkInfo + "\n - " + curHomework.getName() + " ~ Score: " + curHomework.getCurPoints() + "/" + curHomework.getTotPoints();
                      }//end 5th if

                      //if name isnt "N/A" and isnt -1 score
                      else if(!Objects.equals(curHomework.getName(), "N/A")  &&  Objects.equals(curHomework.getCurPoints(), -1f)){//start 5th if/else
                        allHomeworkInfo = allHomeworkInfo + "\n - " + curHomework.getName() + " ~ Score: Not Graded/" + curHomework.getTotPoints();
                      }//end 5th if/else

                    }//end 1st for


                    //next is to get quiz info
                    String allQuizzesInfo = "\nQuizzes:";
                    for(int i=0; i<courses[menuChoice1Num].getStudents()[menuChoice4Num].getNumOfQuizzes(); i+=1){//start 1st for

                      //gets cur quiz
                      Quiz curQuiz = courses[menuChoice1Num].getStudents()[menuChoice4Num].getQuizzes()[i];

                      //if name isnt "N/A" and isnt -1 score
                      if(!Objects.equals(curQuiz.getName(), "N/A")  &&  !Objects.equals(curQuiz.getCurPoints(), -1f)){//start 5th if
                        allQuizzesInfo = allQuizzesInfo + "\n - " + curQuiz.getName() + " ~ Score: " + curQuiz.getCurPoints() + "/" + curQuiz.getTotPoints();
                      }//end 5th if

                      //if name isnt "N/A" and isnt -1 score
                      else if(!Objects.equals(curQuiz.getName(), "N/A")  &&  Objects.equals(curQuiz.getCurPoints(), -1f)){//start 5th if/else
                        allQuizzesInfo = allQuizzesInfo + "\n - " + curQuiz.getName() + " ~ Score: Not Graded/" + curQuiz.getTotPoints();
                      }//end 5th if/else

                    }//end 1st for


                    //make avgStudentCurPoints into its correct form
                    float studentCurGrade = courses[menuChoice1Num].getStudents()[menuChoice4Num].getCurGrade() * 100f;
                    //checks to see if curGrade is not a negative number
                    if(studentCurGrade < 0f){//start 5th if
                      studentCurGrade = 0f;
                    }//end 5th if

                    //shows results
                    System.out.println("\nStudent's Full Name: " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getFirstName() + " " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getLastName() + "\nUser ID: " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getUserID() + "\nCurrent Grade: " + studentCurGrade + "%" + allExamsInfo + allHomeworkInfo + allQuizzesInfo);

                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
    


              //assignment type grade weights
              if(Objects.equals(menuChoice3, "5")){//start 3rd if
                System.out.println("Grade Weights:\nExams: " + courses[menuChoice1Num].getExamWeight() + "\nHomework: " + courses[menuChoice1Num].getHwWeight() + "\nQuizzes: " + courses[menuChoice1Num].getQuizWeight());
              }//end 3rd if


              //not one of the options
              else{//start 3rd if/else
                System.out.println("Input not recognized, please input a number listed in the list (0-3)");
              }//end 3rd if/else

            }//end 3rd while

          }//end 2nd if/else


          //change something
          else if(Objects.equals(menuChoice2, "2")){//start 2nd if/else
            //teacher course change something submenu
            boolean keepGoing3 = true;
  
            while(keepGoing3){//start 3rd while
              System.out.println("\nPlease input a number from the following options:\n0) Change student's grade for an assignment\n1) Change assignment type grade weight\n2) Change something from an assignment\n3) Change something else from this course\n4) Back");
              String menuChoice3 = userInput.nextLine();
            

              //back
              if(Objects.equals(menuChoice3, "4")){//start 3rd if
                keepGoing3 = false;
              }//end 3rd if
  

              //Change student's grade for an assignment
              else if(Objects.equals(menuChoice3, "0")){//start 3rd if/else
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while

                  //gets all students to have user select from
                  String studentChoices = "";
                  for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                    studentChoices = studentChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[i].getFirstName() + " " + courses[menuChoice1Num].getStudents()[i].getLastName() + " ID: " + courses[menuChoice1Num].getStudents()[i].getUserID();
                  }//end 1st for

                  //adds back selection
                  studentChoices = studentChoices + "\n" + courses[menuChoice1Num].getNumOfStudents() + ") Back";

                  //asks selection of which student
                  System.out.println("\nPlease input a number from the following options:"+studentChoices);
                  String menuChoice4 = userInput.nextLine();
                  int menuChoice4Num = -1;

                  //converts choice into an int
                  try{//start 1st try
                    menuChoice4Num = Integer.parseInt(menuChoice4);
                  }//end 1st try
                  catch(Exception e){//start 1st catch
                    //does nothing
                  }//end 1st catch


                  //back
                  if(Objects.equals(menuChoice4Num, courses[menuChoice1Num].getNumOfStudents())){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if


                  //choose a assignment type
                  else if(menuChoice4Num >= 0  && menuChoice4Num < courses[menuChoice1Num].getNumOfStudents()){//start 4th if/else
                    boolean keepGoing5 = true;
          
                    while(keepGoing5){//start 5th while
                      //asks for which assignment type
                      System.out.println("Please input a number from the following options:\n0) Exam\n1) Homework\n2) Quiz\n3) Back");
                      String menuChoice5 = userInput.nextLine();        
        
                      //back
                      if(Objects.equals(menuChoice5, "3")){//start 5th if
                        keepGoing5 = false;
                      }//end 5th if


                      //exam
                      else if(Objects.equals(menuChoice5, "0")){//start 5th if/else
                        //chooses exam from list
                        boolean keepGoing6 = true;
              
                        while(keepGoing6){//start 6th while
                          //gets all exams to have user select from
                          String examChoices = "";
                          for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfExams(); i+=1){//start 1st for  
                            examChoices = examChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getExams()[i].getName();
                          }//end 1st for
  
                          //adds back selection
                          examChoices = examChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfExams() + ") Back";

                          //asks for which exam
                          System.out.println("\nPlease input a number from the following options:"+examChoices);
                          String menuChoice6 = userInput.nextLine();
                          int menuChoice6Num = -1;
            
                          //converts choice into an int
                          try{//start 1st try
                            menuChoice6Num = Integer.parseInt(menuChoice6);
                          }//end 1st try
                          catch(Exception e){//start 1st catch
                            //does nothing
                          }//end 1st catch
            
            
                          //back
                          if(Objects.equals(menuChoice6Num, courses[menuChoice1Num].getStudents()[0].getNumOfExams())){//start 6th if
                            keepGoing6 = false;
                          }//end 6th if
              
            
                          //if user inputs number of an assignment
                          else if(menuChoice6Num >= 0  &&  menuChoice6Num < courses[menuChoice1Num].getStudents()[0].getNumOfExams()){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for score
                              System.out.println("\nPlease input the desired number of points. The exam is out of " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getExams()[menuChoice6Num].getTotPoints() +  " points (input -1 for the exam not to be graded)");
                              String inputGrade = userInput.nextLine();
                              float inputGradeNum = -2f;
                              
                              //converts choice into a float
                              try{//start 1st try
                                inputGradeNum = Float.parseFloat(inputGrade);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch
                              
                              //if number is between -1 and 1000
                              if(inputGradeNum >= -1f  &&  inputGradeNum <= 1000f){//start 7th if
                                courses[menuChoice1Num].getStudents()[menuChoice4Num].getExams()[menuChoice6Num].setCurPoints(inputGradeNum);
                                System.out.println("A score of " + inputGradeNum + " out of " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getExams()[menuChoice6Num].getTotPoints() + " has been added to the exam " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getExams()[menuChoice6Num].getName() + ".");
                                keepGoing7 = false;
                              }//end 7th if

                              //if number isnt allowed
                              else{//start 7th else
                                System.out.println("Please input a number between -1 and 1000");
                              }//end 7th if/else
                            
                            }//end 7th while
                          }//end 6th if/else
            
              
                          //not one of the options
                          else{//start 6th if/else
                            System.out.println("Input not recognized, please input a number listed in the list");
                          }//end 6th if/else
        
                        }//end 6th while
                        
                      }//end 5th if/else


                      //hw
                      else if(Objects.equals(menuChoice5, "1")){//start 5th if/else
                        //choose which hw
                        boolean keepGoing6 = true;
              
                        while(keepGoing6){//start 6th while
                          //gets all homework to have user select from
                          String homeworkChoices = "";
                          for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfHomework(); i+=1){//start 1st for  
                            homeworkChoices = homeworkChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[i].getName();
                          }//end 1st for
  
                          //adds back selection
                          homeworkChoices = homeworkChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfHomework() + ") Back";

                          //asks for which homework
                          System.out.println("\nPlease input a number from the following options:" + homeworkChoices);
                          String menuChoice6 = userInput.nextLine();
                          int menuChoice6Num = -1;
            
                          //converts choice into an int
                          try{//start 1st try
                            menuChoice6Num = Integer.parseInt(menuChoice6);
                          }//end 1st try
                          catch(Exception e){//start 1st catch
                            //does nothing
                          }//end 1st catch
            
            
                          //back
                          if(Objects.equals(menuChoice6Num, courses[menuChoice1Num].getStudents()[0].getNumOfHomework())){//start 6th if
                            keepGoing6 = false;
                          }//end 6th if
              
            
                          //if user inputs number of an assignment
                          else if(menuChoice6Num >= 0  &&  menuChoice6Num < courses[menuChoice1Num].getStudents()[0].getNumOfHomework()){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for score
                              System.out.println("\nPlease input the desired number of points. The homework is out of " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getAllHomework()[menuChoice6Num].getTotPoints() +  " points (input -1 for the homework not to be graded)");
                              String inputGrade = userInput.nextLine();
                              float inputGradeNum = -2f;
                              
                              //converts choice into a float
                              try{//start 1st try
                                inputGradeNum = Float.parseFloat(inputGrade);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch
                              
                              //if number is between -1 and 1000
                              if(inputGradeNum >= -1f  &&  inputGradeNum <= 1000f){//start 7th if
                                courses[menuChoice1Num].getStudents()[menuChoice4Num].getAllHomework()[menuChoice6Num].setCurPoints(inputGradeNum);
                                System.out.println("A score of " + inputGradeNum + " out of " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getAllHomework()[menuChoice6Num].getTotPoints() + " has been added to the homework " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getAllHomework()[menuChoice6Num].getName() + ".");
                                keepGoing7 = false;
                              }//end 7th if

                              //if number isnt allowed
                              else{//start 7th else
                                System.out.println("Please input a number between -1 and 1000");
                              }//end 7th if/else
                            
                            }//end 7th while
                          }//end 6th if/else
            
              
                          //not one of the options
                          else{//start 6th if/else
                            System.out.println("Input not recognized, please input a number listed in the list");
                        }//end 6th if/else
        
                        }//end 6th while
                      }//end 5th if/else


                      //quiz
                      else if(Objects.equals(menuChoice5, "2")){//start 5th if/else
                        //choose which quiz
                        boolean keepGoing6 = true;
              
                        while(keepGoing6){//start 6th while
                          //gets all quizzes to have user select from
                          String quizChoices = "";
                          for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes(); i+=1){//start 1st for  
                            quizChoices = quizChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[i].getName();
                          }//end 1st for
  
                          //adds back selection
                          quizChoices = quizChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes() + ") Back";

                          //asks for which quiz
                          System.out.println("\nPlease input a number from the following options:" + quizChoices);
                          String menuChoice6 = userInput.nextLine();
                          int menuChoice6Num = -1;
            
                          //converts choice into an int
                          try{//start 1st try
                            menuChoice6Num = Integer.parseInt(menuChoice6);
                          }//end 1st try
                          catch(Exception e){//start 1st catch
                            //does nothing
                          }//end 1st catch
            
            
                          //back
                          if(Objects.equals(menuChoice6Num, courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes())){//start 6th if
                            keepGoing6 = false;
                          }//end 6th if
              
            
                          //if user inputs number of an assignment
                          else if(menuChoice6Num >= 0  &&  menuChoice6Num < courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes()){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for score
                              System.out.println("\nPlease input the desired number of points. The quiz is out of " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getQuizzes()[menuChoice6Num].getTotPoints() +  " points (input -1 for the quiz not to be graded)");
                              String inputGrade = userInput.nextLine();
                              float inputGradeNum = -2f;
                              
                              //converts choice into a float
                              try{//start 1st try
                                inputGradeNum = Float.parseFloat(inputGrade);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch
                              
                              //if number is between -1 and 1000
                              if(inputGradeNum >= -1f  &&  inputGradeNum <= 1000f){//start 7th if
                                courses[menuChoice1Num].getStudents()[menuChoice4Num].getQuizzes()[menuChoice6Num].setCurPoints(inputGradeNum);
                                System.out.println("A score of " + inputGradeNum + " out of " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getQuizzes()[menuChoice6Num].getTotPoints() +  " has been added to the quiz " + courses[menuChoice1Num].getStudents()[menuChoice4Num].getQuizzes()[menuChoice6Num].getName() + ".");
                                keepGoing7 = false;
                              }//end 7th if

                              //if number isnt allowed
                              else{//start 7th else
                                System.out.println("Please input a number between -1 and 1000");
                              }//end 7th if/else
                            
                            }//end 7th while
                          }//end 6th if/else
            
              
                          //not one of the options
                          else{//start 6th if/else
                            System.out.println("Input not recognized, please input a number listed in the list");
                        }//end 6th if/else
        
                        }//end 6th while

                      }//end 5th if/else
        
            
                      //not one of the options
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number listed in the list (0-3)");
                      }//end 5th if/else
        
                    }//end 5th while

                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
  

              //Change assignment type grade weight
              else if(Objects.equals(menuChoice3, "1")){//start 3rd if/else
                //choose assignment type
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while
                  //asks selection of which student
                  System.out.println("\nPlease input a number from the following options:\n0) Exams\n1) Homework\n2) Quizzes\n3) Back");
                  String menuChoice4 = userInput.nextLine();


                  //back
                  if(Objects.equals(menuChoice4, "3")){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if


                  //exam
                  else if(Objects.equals(menuChoice4, "0")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      //asks for new weight of grade for all exams
                      System.out.println("\nPlease input a number bewteen 0 and 10 for the new intended weight of exams. The current weight for exams is: "+ courses[menuChoice1Num].getStudents()[0].getExams()[0].getGradeWeight() +".");
                      String inputWeight = userInput.nextLine();
                      float inputWeightNum = -1f;

                      //converts choice into a float
                      try{//start 1st try
                        inputWeightNum = Float.parseFloat(inputWeight);
                      }//end 1st try
                      catch(Exception e){//start 1st catch
                        //does nothing
                      }//end 1st catch


                      //correct input / input within 0-10
                      if(inputWeightNum >= 0  &&  inputWeightNum <= 10){//start 5th if
                        //cycles through each exam (even the inactive ones) and puts new grade weight
                        for(int i=0; i<10; i+=1){//start 1st for
                          courses[menuChoice1Num].getStudents()[0].getExams()[i].setGradeWeight(inputWeightNum);
                        }//end 1st for

                        //changes weight type in course attribute
                        courses[menuChoice1Num].setExamWeight(inputWeightNum);

                        System.out.println("Exams now have a grade weight of: "+ courses[menuChoice1Num].getStudents()[0].getExams()[0].getGradeWeight() +".");
                        keepGoing5 = false;
                      }//end 5th if


                      //it isnt bewteen 0-10
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number bewteen 0 and 10");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //hw
                  else if(Objects.equals(menuChoice4, "1")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      //asks for new weight of grade for all homework
                      System.out.println("\nPlease input a number bewteen 0 and 10 for the new intended weight of homework. The current weight for homework is: "+ courses[menuChoice1Num].getStudents()[0].getAllHomework()[0].getGradeWeight() +".");
                      String inputWeight = userInput.nextLine();
                      float inputWeightNum = -1f;

                      //converts choice into a float
                      try{//start 1st try
                        inputWeightNum = Float.parseFloat(inputWeight);
                      }//end 1st try
                      catch(Exception e){//start 1st catch
                        //does nothing
                      }//end 1st catch


                      //correct input / input within 0-10
                      if(inputWeightNum >= 0  &&  inputWeightNum <= 10){//start 5th if
                        //cycles through each homework (even the inactive ones) and puts new grade weight
                        for(int i=0; i<25; i+=1){//start 1st for
                          courses[menuChoice1Num].getStudents()[0].getAllHomework()[i].setGradeWeight(inputWeightNum);
                        }//end 1st for

                        //changes weight type in course attribute
                        courses[menuChoice1Num].setHwWeight(inputWeightNum);

                        System.out.println("Homework now have a grade weight of: "+ courses[menuChoice1Num].getStudents()[0].getAllHomework()[0].getGradeWeight() +".");
                        keepGoing5 = false;
                      }//end 5th if


                      //it isnt bewteen 0-10
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number bewteen 0 and 10");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //quiz
                  else if(Objects.equals(menuChoice4, "2")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      //asks for new weight of grade for all quizzes
                      System.out.println("\nPlease input a number bewteen 0 and 10 for the new intended weight of quizzes. The current weight for quizzes is: "+ courses[menuChoice1Num].getStudents()[0].getQuizzes()[0].getGradeWeight() +".");
                      String inputWeight = userInput.nextLine();
                      float inputWeightNum = -1f;

                      //converts choice into a float
                      try{//start 1st try
                        inputWeightNum = Float.parseFloat(inputWeight);
                      }//end 1st try
                      catch(Exception e){//start 1st catch
                        //does nothing
                      }//end 1st catch


                      //correct input / input within 0-10
                      if(inputWeightNum >= 0  &&  inputWeightNum <= 10){//start 5th if
                        //cycles through each quiz (even the inactive ones) and puts new grade weight
                        for(int i=0; i<20; i+=1){//start 1st for
                          courses[menuChoice1Num].getStudents()[0].getQuizzes()[i].setGradeWeight(inputWeightNum);
                        }//end 1st for

                        //changes weight type in course attribute
                        courses[menuChoice1Num].setQuizWeight(inputWeightNum);

                        System.out.println("Quizzes now have a grade weight of: "+ courses[menuChoice1Num].getStudents()[0].getQuizzes()[0].getGradeWeight() +".");
                        keepGoing5 = false;
                      }//end 5th if


                      //it isnt bewteen 0-10
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number bewteen 0 and 10");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
    

              //Change something from an assignment
              else if(Objects.equals(menuChoice3, "2")){//start 3rd if/else
                //choose assignment type
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while
                  //asks selection of which student
                  System.out.println("\nPlease input a number from the following options:\n0) Exams\n1) Homework\n2) Quizzes\n3) Back");
                  String menuChoice4 = userInput.nextLine();


                  //back
                  if(Objects.equals(menuChoice4, "3")){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if


                  //exam
                  else if(Objects.equals(menuChoice4, "0")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      //gets all exams to have user select from
                      String examChoices = "";
                      for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfExams(); i+=1){//start 1st for
                        examChoices = examChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getExams()[i].getName();
                      }//end 1st for
    
                      //adds back selection
                      examChoices = examChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfExams() + ") Back";

                      //asks selection of which exam
                      System.out.println("\nPlease input a number from the following options:"+examChoices);
                      String menuChoice5 = userInput.nextLine();
                      int menuChoice5Num = -1;
    
                      //converts choice into an int
                      try{//start 1st try
                        menuChoice5Num = Integer.parseInt(menuChoice5);
                      }//end 1st try
                      catch(Exception e){//start 1st catch
                        //does nothing
                      }//end 1st catch


                      //back
                      if(Objects.equals(menuChoice5Num, courses[menuChoice1Num].getStudents()[0].getNumOfExams())){//start 5th if
                        keepGoing5 = false;
                      }//end 5th if


                      //input is an exam number
                      else if(menuChoice5Num >= 0  &&  menuChoice5Num < courses[menuChoice1Num].getStudents()[0].getNumOfExams()){//start 5th if/else
                        //choose which operation to do to the assignment
                        boolean keepGoing6 = true;
              
                        while(keepGoing6){//start 6th while
                          //asks for which operation
                          System.out.println("\nPlease input a number from the following options:\n0) Delete exam\n1) Change exam name\n2) Change total points\n3) Change individual grade weight\n4) Back");
                          String menuChoice6 = userInput.nextLine();
            
            
                          //back
                          if(Objects.equals(menuChoice6, "4")){//start 6th if
                            keepGoing6 = false;
                          }//end 6th if
              
            
                          //delete exam
                          else if(Objects.equals(menuChoice6, "0")){//start 6th if/else
                            String examName = courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice5Num].getName();
                            courses[menuChoice1Num].getStudents()[0].removeExam(examName, menuChoice5Num, courses[menuChoice1Num].getExamWeight());
                            System.out.println("The exam named: " + examName + " has been removed.");
                          }//end 6th if/else
              
            
                          //change exam name
                          else if(Objects.equals(menuChoice6, "1")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new name
                              System.out.println("\nPlease input the desired name of the exam.");
                              String inputExamName = userInput.nextLine();

                              //checks to see if name matches another one or if it = "N/A"
                              String matches = "n";
                              for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfExams(); i+=1){//start 1st for
                                if(Objects.equals(courses[menuChoice1Num].getStudents()[0].getExams()[i].getName(), inputExamName )  ||  Objects.equals("N/A", inputExamName)){//start 8th if
                                  matches = "y";
                                }//end 8th if
                              }//end 1st for

                              //if name is good to go
                              if(matches == "n"){//start 8th if
                                //changes exam name
                                courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice5Num].setName(inputExamName);
                                System.out.println("Exam name changed to: " + inputExamName + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //if the name is taken or N/A
                              else if(matches == "y"){//start 8th if
                                System.out.println("The exam name is taken or is 'N/A', which isn't allowed");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
              
            
                          //change total points
                          else if(Objects.equals(menuChoice6, "2")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new tot points
                              System.out.println("\nPlease input the desired amount of points. The current amount of points possible for this exam is: " + courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice5Num].getTotPoints() + ".");
                              String inputPoints = userInput.nextLine();
                              float inputPointsNum = -1;

                              //tries to make the points into float
                              try{//start 1st try
                                inputPointsNum = Float.parseFloat(inputPoints);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch

                              
                              //if its a number between 0 and 1000
                              if(inputPointsNum >= 0  &&  inputPointsNum <= 1000){//start 8th if
                                //changes exam tot points
                                courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice5Num].setTotPoints(inputPointsNum);
                                System.out.println("The total points of the exam name is now " + inputPointsNum + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //if the tot points inputed isnt 0-1000
                              else{//start 8th if
                                System.out.println("Input not recognized, please input a number between 0 and 1000.");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
              
            
                          //change individual grade weight
                          else if(Objects.equals(menuChoice6, "3")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new grade weight
                              System.out.println("\nPlease input the desired grade weight. The current grade weight is: " + courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice5Num].getGradeWeight() + ".");
                              String inputGradeWeight = userInput.nextLine();
                              float inputGradeWeightNum = -1;

                              //tries to make the points into float
                              try{//start 1st try
                                inputGradeWeightNum = Float.parseFloat(inputGradeWeight);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch

                              
                              //if its a number between 0 and 10
                              if(inputGradeWeightNum >= 0  &&  inputGradeWeightNum <= 10){//start 8th if
                                //changes exam grade weight
                                courses[menuChoice1Num].getStudents()[0].getExams()[menuChoice5Num].setGradeWeight(inputGradeWeightNum);
                                System.out.println("The grade weight of the exam name is now " + inputGradeWeightNum + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //grade weight isnt 0-10
                              else{//start 8th if
                                System.out.println("Input not recognized, please input a number between 0 and 10.");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
            
              
                          //not one of the options
                          else{//start 6th if/else
                            System.out.println("Input not recognized, please input a number listed in the list");
                          }//end 6th if/else
        
                        }//end 6th while
                      }//end 5th if/else


                      //it isnt between a listed exam number
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number listed");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //hw
                  else if(Objects.equals(menuChoice4, "1")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      //gets all homework to have user select from
                      String homeworkChoices = "";
                      for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfHomework(); i+=1){//start 1st for
                        homeworkChoices = homeworkChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[i].getName();
                      }//end 1st for
    
                      //adds back selection
                      homeworkChoices = homeworkChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfHomework() + ") Back";

                      //asks selection of which homework
                      System.out.println("\nPlease input a number from the following options:"+homeworkChoices);
                      String menuChoice5 = userInput.nextLine();
                      int menuChoice5Num = -1;
    
                      //converts choice into an int
                      try{//start 1st try
                        menuChoice5Num = Integer.parseInt(menuChoice5);
                      }//end 1st try
                      catch(Exception e){//start 1st catch
                        //does nothing
                      }//end 1st catch


                      //back
                      if(Objects.equals(menuChoice5Num, courses[menuChoice1Num].getStudents()[0].getNumOfHomework())){//start 5th if
                        keepGoing5 = false;
                      }//end 5th if


                      //input is an homework number
                      else if(menuChoice5Num >= 0  &&  menuChoice5Num < courses[menuChoice1Num].getStudents()[0].getNumOfHomework()){//start 5th if/else
                        //choose which operation to do to the assignment
                        boolean keepGoing6 = true;
              
                        while(keepGoing6){//start 6th while
                          //asks for which operation
                          System.out.println("\nPlease input a number from the following options:\n0) Delete homework\n1) Change homework name\n2) Change total points\n3) Change individual grade weight\n4) Back");
                          String menuChoice6 = userInput.nextLine();
            
            
                          //back
                          if(Objects.equals(menuChoice6, "4")){//start 6th if
                            keepGoing6 = false;
                          }//end 6th if
              
            
                          //delete homework
                          else if(Objects.equals(menuChoice6, "0")){//start 6th if/else
                            String homeworkName = courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice5Num].getName();
                            courses[menuChoice1Num].getStudents()[0].removeHomework(homeworkName, menuChoice5Num, courses[menuChoice1Num].getHwWeight());
                            System.out.println("The homework named: " + homeworkName + " has been removed.");
                          }//end 6th if/else
              
            
                          //change homework name
                          else if(Objects.equals(menuChoice6, "1")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new name
                              System.out.println("\nPlease input the desired name of the homework.");
                              String inputHomeworkName = userInput.nextLine();

                              //checks to see if name matches another one or if it = "N/A"
                              String matches = "n";
                              for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfHomework(); i+=1){//start 1st for
                                if(Objects.equals(courses[menuChoice1Num].getStudents()[0].getAllHomework()[i].getName(), inputHomeworkName )  ||  Objects.equals("N/A", inputHomeworkName)){//start 8th if
                                  matches = "y";
                                }//end 8th if
                              }//end 1st for

                              //if name is good to go
                              if(matches == "n"){//start 8th if
                                //changes homework name
                                courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice5Num].setName(inputHomeworkName);
                                System.out.println("Homework name changed to: " + inputHomeworkName + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //if the name is taken or N/A
                              else if(matches == "y"){//start 8th if
                                System.out.println("The homework name is taken or is 'N/A', which isn't allowed");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
              
            
                          //change total points
                          else if(Objects.equals(menuChoice6, "2")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new tot points
                              System.out.println("\nPlease input the desired amount of points. The current amount of points possible for this homework is: " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice5Num].getTotPoints() + ".");
                              String inputPoints = userInput.nextLine();
                              float inputPointsNum = -1;

                              //tries to make the points into float
                              try{//start 1st try
                                inputPointsNum = Float.parseFloat(inputPoints);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch

                              
                              //if its a number between 0 and 1000
                              if(inputPointsNum >= 0  &&  inputPointsNum <= 1000){//start 8th if
                                //changes homework tot points
                                courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice5Num].setTotPoints(inputPointsNum);
                                System.out.println("The total points of the homework name is now " + inputPointsNum + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //if the tot points inputed isnt 0-1000
                              else{//start 8th if
                                System.out.println("Input not recognized, please input a number between 0 and 1000.");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
              
            
                          //change individual grade weight
                          else if(Objects.equals(menuChoice6, "3")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new grade weight
                              System.out.println("\nPlease input the desired grade weight. The current grade weight is: " + courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice5Num].getGradeWeight() + ".");
                              String inputGradeWeight = userInput.nextLine();
                              float inputGradeWeightNum = -1;

                              //tries to make the points into float
                              try{//start 1st try
                                inputGradeWeightNum = Float.parseFloat(inputGradeWeight);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch

                              
                              //if its a number between 0 and 10
                              if(inputGradeWeightNum >= 0  &&  inputGradeWeightNum <= 10){//start 8th if
                                //changes homework grade weight
                                courses[menuChoice1Num].getStudents()[0].getAllHomework()[menuChoice5Num].setGradeWeight(inputGradeWeightNum);
                                System.out.println("The grade weight of the homework name is now " + inputGradeWeightNum + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //grade weight isnt 0-10
                              else{//start 8th if
                                System.out.println("Input not recognized, please input a number between 0 and 10.");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
            
              
                          //not one of the options
                          else{//start 6th if/else
                            System.out.println("Input not recognized, please input a number listed in the list");
                          }//end 6th if/else
        
                        }//end 6th while
                      }//end 5th if/else


                      //it isnt between a listed homework number
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number listed");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //quiz
                  else if(Objects.equals(menuChoice4, "2")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      //gets all quizzes to have user select from
                      String quizChoices = "";
                      for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes(); i+=1){//start 1st for
                        quizChoices = quizChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[i].getName();
                      }//end 1st for
    
                      //adds back selection
                      quizChoices = quizChoices + "\n" + courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes() + ") Back";

                      //asks selection of which quiz
                      System.out.println("\nPlease input a number from the following options:"+quizChoices);
                      String menuChoice5 = userInput.nextLine();
                      int menuChoice5Num = -1;
    
                      //converts choice into an int
                      try{//start 1st try
                        menuChoice5Num = Integer.parseInt(menuChoice5);
                      }//end 1st try
                      catch(Exception e){//start 1st catch
                        //does nothing
                      }//end 1st catch


                      //back
                      if(Objects.equals(menuChoice5Num, courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes())){//start 5th if
                        keepGoing5 = false;
                      }//end 5th if


                      //input is an quiz number
                      else if(menuChoice5Num >= 0  &&  menuChoice5Num < courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes()){//start 5th if/else
                        //choose which operation to do to the assignment
                        boolean keepGoing6 = true;
              
                        while(keepGoing6){//start 6th while
                          //asks for which operation
                          System.out.println("\nPlease input a number from the following options:\n0) Delete quiz\n1) Change quiz name\n2) Change total points\n3) Change individual grade weight\n4) Back");
                          String menuChoice6 = userInput.nextLine();
            
            
                          //back
                          if(Objects.equals(menuChoice6, "4")){//start 6th if
                            keepGoing6 = false;
                          }//end 6th if
              
            
                          //delete quiz
                          else if(Objects.equals(menuChoice6, "0")){//start 6th if/else
                            String quizName = courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice5Num].getName();
                            courses[menuChoice1Num].getStudents()[0].removeQuiz(quizName, menuChoice5Num, courses[menuChoice1Num].getQuizWeight());
                            System.out.println("The quiz named: " + quizName + " has been removed.");
                          }//end 6th if/else
              
            
                          //change quiz name
                          else if(Objects.equals(menuChoice6, "1")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new name
                              System.out.println("\nPlease input the desired name of the quiz.");
                              String inputQuizName = userInput.nextLine();

                              //checks to see if name matches another one or if it = "N/A"
                              String matches = "n";
                              for(int i=0; i<courses[menuChoice1Num].getStudents()[0].getNumOfQuizzes(); i+=1){//start 1st for
                                if(Objects.equals(courses[menuChoice1Num].getStudents()[0].getQuizzes()[i].getName(), inputQuizName )  ||  Objects.equals("N/A", inputQuizName)){//start 8th if
                                  matches = "y";
                                }//end 8th if
                              }//end 1st for

                              //if name is good to go
                              if(matches == "n"){//start 8th if
                                //changes quiz name
                                courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice5Num].setName(inputQuizName);
                                System.out.println("Quiz name changed to: " + inputQuizName + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //if the name is taken or N/A
                              else if(matches == "y"){//start 8th if
                                System.out.println("The quiz name is taken or is 'N/A', which isn't allowed");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
              
            
                          //change total points
                          else if(Objects.equals(menuChoice6, "2")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new tot points
                              System.out.println("\nPlease input the desired amount of points. The current amount of points possible for this quiz is: " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice5Num].getTotPoints() + ".");
                              String inputPoints = userInput.nextLine();
                              float inputPointsNum = -1;

                              //tries to make the points into float
                              try{//start 1st try
                                inputPointsNum = Float.parseFloat(inputPoints);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch

                              
                              //if its a number between 0 and 1000
                              if(inputPointsNum >= 0  &&  inputPointsNum <= 1000){//start 8th if
                                //changes quiz tot points
                                courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice5Num].setTotPoints(inputPointsNum);
                                System.out.println("The total points of the quiz name is now " + inputPointsNum + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //if the tot points inputed isnt 0-1000
                              else{//start 8th if
                                System.out.println("Input not recognized, please input a number between 0 and 1000.");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
              
            
                          //change individual grade weight
                          else if(Objects.equals(menuChoice6, "3")){//start 6th if/else
                            boolean keepGoing7 = true;
                            
                            while(keepGoing7){//start 7th while

                              //asks for new grade weight
                              System.out.println("\nPlease input the desired grade weight. The current grade weight is: " + courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice5Num].getGradeWeight() + ".");
                              String inputGradeWeight = userInput.nextLine();
                              float inputGradeWeightNum = -1;

                              //tries to make the points into float
                              try{//start 1st try
                                inputGradeWeightNum = Float.parseFloat(inputGradeWeight);
                              }//end 1st try
                              catch(Exception e){//start 1st catch
                                //does nothing
                              }//end 1st catch

                              
                              //if its a number between 0 and 10
                              if(inputGradeWeightNum >= 0  &&  inputGradeWeightNum <= 10){//start 8th if
                                //changes quiz grade weight
                                courses[menuChoice1Num].getStudents()[0].getQuizzes()[menuChoice5Num].setGradeWeight(inputGradeWeightNum);
                                System.out.println("The grade weight of the quiz name is now " + inputGradeWeightNum + ".");
                                keepGoing7 = false;
                              }//end 8th if

                              //grade weight isnt 0-10
                              else{//start 8th if
                                System.out.println("Input not recognized, please input a number between 0 and 10.");
                              }//end 8th if

                            }//end 7th while
                          }//end 6th if/else
            
              
                          //not one of the options
                          else{//start 6th if/else
                            System.out.println("Input not recognized, please input a number listed in the list");
                          }//end 6th if/else
        
                        }//end 6th while
                      }//end 5th if/else


                      //it isnt between a listed quiz number
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number listed");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
    

              //Change something else from this course
              else if(Objects.equals(menuChoice3, "3")){//start 3rd if/else
                //chooses from next list
                //choose assignment type
                boolean keepGoing4 = true;
  
                while(keepGoing4){//start 4th while
                  //asks selection of which student
                  System.out.println("\nPlease input a number from the following options:\n0) Delete course\n1) Change course name\n2) Change course subject\n3) Remove Student\n4) Back");
                  String menuChoice4 = userInput.nextLine();


                  //back
                  if(Objects.equals(menuChoice4, "4")){//start 4th if
                    keepGoing4 = false;
                  }//end 4th if


                  //Delete course
                  else if(Objects.equals(menuChoice4, "0")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      String courseName = courses[menuChoice1Num].getCourseName();
                      System.out.println("\nAre you sure you would like to delete the course " + courseName + "? This cannot be undone.\n0) Yes\n1) No");
                      String menuChoice5 = userInput.nextLine();

                      //no
                      if(Objects.equals(menuChoice5, "1")){//start 5th if
                        keepGoing5 = false;
                      }//end 5th if

                      //yes
                      else if(Objects.equals(menuChoice5, "0")){//start 5th if
                        removeCourse(courseName, menuChoice1Num);
                        System.out.println("The course has been removed.");
                        keepGoing5 = false;
                        keepGoing4 = false;
                        keepGoing3 = false;
                        keepGoing2 = false;
                      }//end 5th if


                      //not one of the options
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number listed in the list");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //Change course name
                  else if(Objects.equals(menuChoice4, "1")){//start 4th if/else
                    //asks for new name
                    String oldCourseName = courses[menuChoice1Num].getCourseName();
                    System.out.println("\nWhat would you like the new name of the course to be? The current course name is " + oldCourseName + ".");
                    String inputCourseName = userInput.nextLine();

                    //changes name
                    courses[menuChoice1Num].setCourseName(inputCourseName);
                    System.out.println("The course name has been changed to: " + inputCourseName + ".");
                  }//end 4th if/else


                  //Change course subject
                  else if(Objects.equals(menuChoice4, "2")){//start 4th if/else
                    boolean keepGoing5 = true;
                    String courseSubject = "N/A";

                    while(keepGoing5){//start 5th while
                      //asks for new subject
                      System.out.println("\nWhat would you like the new subject of the course to be? The current course subject is " + courses[menuChoice1Num].getSubject() + ".\n0) Science\n1) Math\n2) Social Studies\n3) English\n4) Art\n5) Physical Education");
                      String subjectInput = userInput.nextLine();

                      //science
                      if(Objects.equals(subjectInput, "0")){//start 5th if
                        courseSubject = "SCIENCE";
                        keepGoing5 = false;
                      }//end 5th if
            
                      //math
                      else if(Objects.equals(subjectInput, "1")){//start 5th if/else
                        courseSubject = "MATH";
                        keepGoing5 = false;
                      }//end 5th if/else
            
                      //SOCIAL_STUDIES
                      else if(Objects.equals(subjectInput, "2")){//start 5th if/else
                        courseSubject = "SOCIAL_STUDIES";
                        keepGoing5 = false;
                      }//end 5th if/else
            
                      //ENGLISH
                      else if(Objects.equals(subjectInput, "3")){//start 5th if/else
                        courseSubject = "ENGLISH";
                        keepGoing5 = false;
                      }//end 5th if/else
            
                      //ART
                      else if(Objects.equals(subjectInput, "4")){//start 5th if/else
                        courseSubject = "ART";
                        keepGoing5 = false;
                      }//end 5th if/else
            
                      //PHYSICAL_EDUCATION
                      else if(Objects.equals(subjectInput, "5")){//start 5th if/else
                        courseSubject = "PHYSICAL_EDUCATION";
                        keepGoing5 = false;
                      }//end 5th if/else
            
                      //not one of the options
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number listed in the list (0-5)");
                      }//end 5th if/else

                    }//end 5th while
                    //changes subject
                    courses[menuChoice1Num].setSubject(courseSubject);
                    System.out.println("Course subject had been changed to: " + courseSubject);

                  }//end 4th if/else


                  //Remove Student
                  else if(Objects.equals(menuChoice4, "3")){//start 4th if/else
                    boolean keepGoing5 = true;

                    while(keepGoing5){//start 5th while
                      //gets all students to have user select from
                      String studentChoices = "";
                      for(int i=0; i<courses[menuChoice1Num].getNumOfStudents(); i+=1){//start 1st for
                        studentChoices = studentChoices + "\n" + i + ") " + courses[menuChoice1Num].getStudents()[i].getFirstName() + " " + courses[menuChoice1Num].getStudents()[i].getLastName() + " ~ User ID: " + courses[menuChoice1Num].getStudents()[i].getUserID();
                      }//end 1st for
    
                      //adds back selection
                      studentChoices = studentChoices + "\n" + courses[menuChoice1Num].getNumOfStudents() + ") Back";
    
                      //asks selection of which student
                      System.out.println("\nPlease input a number from the following options:"+studentChoices);
                      String menuChoice5 = userInput.nextLine();
                      int menuChoice5Num = -1;
    
                      //converts choice into an int
                      try{//start 1st try
                        menuChoice5Num = Integer.parseInt(menuChoice5);
                      }//end 1st try
                      catch(Exception e){//start 1st catch
                        //does nothing
                      }//end 1st catch
    

                      //back
                      if(Objects.equals(menuChoice5Num, courses[menuChoice1Num].getNumOfStudents())){//start 5th if
                        keepGoing4 = false;
                      }//end 5th if
    

                      //if choice is actual student
                      else if(menuChoice5Num >= 0  && menuChoice5Num < courses[menuChoice1Num].getNumOfStudents()){//start 5th if/else
                        //removes student
                        courses[menuChoice1Num].removeStudent(courses[menuChoice1Num].getStudents()[menuChoice5Num].getUserID(), menuChoice5Num);
                        keepGoing4 = false;
                      }//end 5th if/else


                      //not one of the options
                      else{//start 5th if/else
                        System.out.println("Input not recognized, please input a number listed in the list");
                      }//end 5th if/else

                    }//end 5th while
                  }//end 4th if/else


                  //not one of the options
                  else{//start 4th if/else
                    System.out.println("Input not recognized, please input a number listed in the list");
                  }//end 4th if/else

                }//end 4th while
              }//end 3rd if/else
    
              //not one of the options
              else{//start 3rd if/else
                System.out.println("Input not recognized, please input a number listed in the list (0-3)");
              }//end 3rd if/else

            }//end 3rd while
          }//end 2nd if/else


          //not one of the options
          else{//start 2nd if/else
            System.out.println("Input not recognized, please input a number listed in the list (0-3)");
          }//end 2nd if/else

        }//end 2nd while

      }//end 1st if/else

      else{//start 1st if/else
        //not num 0-maxNum
        System.out.println("Please choose a number listed");
      }//end 1st if/else
      

    }//end 1st while
    
    saveCourses();
    userInput.close();
  }//end teacherMenu


  public void studentMenu(int[] courseLocs, String fName, String lName, String inputID){//start studentMenu
    Scanner userInput = new Scanner(System.in);
    boolean keepGoing = true;

    //starts student menu
    while(keepGoing){//start 1st while
      //Asks student whether they want to change password or to see their current course grades
      System.out.println("\nWelcome, "+fName+" "+lName+". Please input a number from the list for what you would like to do:\n0) Check all courses info\n1) Change password\n2) Exit");
      String menuChoice1 = userInput.nextLine();

      //Exit
      if(Objects.equals(menuChoice1, "2")){//start 1st if
        keepGoing = false;
      }//end 1st if


      //shows all course info for this student
      else if(Objects.equals(menuChoice1, "0")){//start 1st if/else
        //gets all info for each course
        String courseInfo = "";
        for(int i=0; i<this.numOfCourses; i+=1){//start 1st for
          if(!Objects.equals(courseLocs[i], -1)){//start 1st if
            //gets grade to see if its negative
            float curGrade = courses[i].getStudents()[courseLocs[i]].getCurGrade();
            //less than 0
            if(curGrade < 0){//start 2nd if
              //adds everything but assignment info
              courseInfo = courseInfo + "\n\nCourse: " + courses[i].getCourseName() + "\nCourse Subject: " + courses[i].getSubject() + "\nOverall Grade: " + "No Grade" + "%\nExams:";
            }//end 2nd if
            //0 or more
            else if(curGrade >= 0){//start 2nd if
              //adds everything but assignment info
              courseInfo = courseInfo + "\n\nCourse: " + courses[i].getCourseName() + "\nCourse Subject: " + courses[i].getSubject() + "\nOverall Grade: " + curGrade + "%\nExams:";
            }//end 2nd if


            //adds exam info
            for(int x=0; x<courses[i].getStudents()[courseLocs[i]].getNumOfExams(); x+=1){//start 2nd for
              //gets cur exam
              Exam curExam = courses[i].getStudents()[courseLocs[i]].getExams()[x];

              //if name isnt "N/A" and isnt -1 score
              if(!Objects.equals(curExam.getName(), "N/A")  &&  !Objects.equals(curExam.getCurPoints(), -1f)){//start 2nd if
                courseInfo = courseInfo + "\n - " + curExam.getName() + " ~ Score: " + curExam.getCurPoints() + "/" + curExam.getTotPoints();
              }//end 2nd if

              //if name isnt "N/A" and isnt -1 score
              else if(!Objects.equals(curExam.getName(), "N/A")  &&  Objects.equals(curExam.getCurPoints(), -1f)){//start 2nd if/else
                courseInfo = courseInfo + "\n - " + curExam.getName() + " ~ Score: Not Graded/" + curExam.getTotPoints();
              }//end 2nd if/else

            }//end 2nd for

            courseInfo  = courseInfo + "\nHomework:";
            //adds homework info
            for(int x=0; x<courses[i].getStudents()[courseLocs[i]].getNumOfHomework(); x+=1){//start 2nd for
              //gets cur homework
              Homework curHomework = courses[i].getStudents()[courseLocs[i]].getAllHomework()[x];

              //if name isnt "N/A" and isnt -1 score
              if(!Objects.equals(curHomework.getName(), "N/A")  &&  !Objects.equals(curHomework.getCurPoints(), -1f)){//start 2nd if
                courseInfo = courseInfo + "\n - " + curHomework.getName() + " ~ Score: " + curHomework.getCurPoints() + "/" + curHomework.getTotPoints();
              }//end 2nd if

              //if name isnt "N/A" and isnt -1 score
              else if(!Objects.equals(curHomework.getName(), "N/A")  &&  Objects.equals(curHomework.getCurPoints(), -1f)){//start 2nd if/else
                courseInfo = courseInfo + "\n - " + curHomework.getName() + " ~ Score: Not Graded/" + curHomework.getTotPoints();
              }//end 2nd if/else

            }//end 2nd for


            courseInfo  = courseInfo + "\nQuizzes:";
            //adds quiz info
            for(int x=0; x<courses[i].getStudents()[courseLocs[i]].getNumOfQuizzes(); x+=1){//start 2nd for
              //gets cur quiz
              Quiz curQuiz = courses[i].getStudents()[courseLocs[i]].getQuizzes()[x];

              //if name isnt "N/A" and isnt -1 score
              if(!Objects.equals(curQuiz.getName(), "N/A")  &&  !Objects.equals(curQuiz.getCurPoints(), -1f)){//start 2nd if
                courseInfo = courseInfo + "\n - " + curQuiz.getName() + " ~ Score: " + curQuiz.getCurPoints() + "/" + curQuiz.getTotPoints();
              }//end 2nd if

              //if name isnt "N/A" and isnt -1 score
              else if(!Objects.equals(curQuiz.getName(), "N/A")  &&  Objects.equals(curQuiz.getCurPoints(), -1f)){//start 2nd if/else
                courseInfo = courseInfo + "\n - " + curQuiz.getName() + " ~ Score: Not Graded/" + curQuiz.getTotPoints();
              }//end 2nd if/else

            }//end 2nd for
          }//end 1st if
        }//end 1st for
  
        //outputs info
        System.out.println(courseInfo);
        
      }//end 1st if/else


      //Change password
      else if(Objects.equals(menuChoice1, "1")){//start 1st if/else
        boolean keepGoing2 = true;
        while(keepGoing2){//start 2nd while
          System.out.println("\nPlease input your desired password: ");
          String newPassword = userInput.nextLine();

          //illegal input
          if(Objects.equals(newPassword, "N/A")  ||  Objects.equals(newPassword, "")){//start 2nd if
            System.out.println("New password cannot be 'N/A' or blank");
          }//end 2nd if
          //correct input
          else{//start 2nd if/else
            //changes password in all their courses
            for(int i=0; i<this.numOfCourses; i+=1){//start 1st for
              if(!Objects.equals(courseLocs[i], -1)){//start 3rd if
                courses[i].getStudents()[courseLocs[i]].setPassword(newPassword);
              }//end 3rd if
            }//end 1st for

            //message of success
            System.out.println("Password has been changed");
            keepGoing2 = false;
          }//end 2nd if/else
        }//end 2nd while
      }//end 1st if/else
  
  
      //not a valid input
      else{//start 1st if/else
        System.out.println("Please input a number listed in the choices (0-2)");
      }//end 1st if/else

    }//end 1st while

    saveCourses();
    userInput.close();
  }//end studentMenu

}//end class