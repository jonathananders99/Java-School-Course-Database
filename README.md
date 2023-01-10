### Program Title: **_ALP_ (Automated Learning Pro)**
### Description: 
The goal of this project is to give an easier way (mainly for teachers) to those without access to pricey learning systems like canvas a free alternative. ALP needs to be able to do a lot of things necessary to keep tabs on students and in a specific course. The users will include the teacher and the students. I will need one file to keep all user data and class data (a .dat file for an array of course classes). The only interaction between user and system will be through three different main menus using the command line. The first menu will be the login, then depending on the user it will either bring up the student menu or a teacher menu. The data will stay persistant after exiting correctly as the data will be serialized in a .dat file.

The best way I can think of to represent the data is through a database using a single file (serialized array of classes) for each course with a .dat file that is an array of course classes that I load up into a separate class that I have all the menu’s in (ALP.java). To see a representation of the database and each classes relationship with each other you can look at the UML. There are a few things that you can do like add extra credit by adding an assignment but putting the total points as 0 or having an assignment not count towards the student’s grade but still show up on the record so they can see what score they got by putting grade weight at 0 for that assignment. Grade weight is calculated with points of item multiplied by grade weight to be able to have something worth more in the overall grade. To login into teacher menu input **_Teacher_** for both userID and password.

### How to run the project:
After downloading all files, In command line type `make run`. To clear all files and restart type `make clean` or `rm *.class`. If any problems arise from the .dat file not loading correctly you can take out the /* on line 19 and */ on line 68 from file and run it then. Just make sure to put both back after running it once, as it will reset any data back to what it puts there.

To manually run it without using the makefile type these commands in the command line: 
```
javac -g ALP.java
java ALP
```