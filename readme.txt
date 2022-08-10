=== JAVA SCHOOL TRAINING SIMULATION ===

Author: Alberto Tamajo
Project Date: November 2019
Project Purpose: University of Southampton, Programming I CourseWork
Parts attempted:  All parts of the coursework have been attempted

Extensions implemented:  a) Implementation of the prerequisites for the subjects so that students can only enrol in a course only if
                            they have all the prerequisites for it.
                         
                         b) saving out the current state of a simulation to a text file so that it can be reloaded and restarted at another time

##TECHNOLOGIES##

Project is created with:
* Java 13

##DESCRIPTION##

This project aim is to build a simulation of a Java Training School that teaches Java-related topics to some students.
In order to achieve this, the following classes have been built:

  1) Student: the Student class represents a student. A student can enrol to at most one course at a time and receives
              a certificate for the course completion. Once a student has got all certificates, he/she leaves the school.

  2) Subject: the Subject class represents a subject. A subject consists of a Java-related topic and is associated with a specialism.
              A subject can have prerequisites in order to let only students with some certifications enroll in a course that teaches that certain subject.
              If the prerequistes are not provided, then every student can enroll in that subject.

  3) Course:  the Course class represents a course. Courses are needed in order to teach the subjects offered by the School.
              Each course can enrol a maximum of 3 students and must have an instructor. Each course is associated with only
              one subject.

  4) Instructor: the Instructor class represents an instructor. An instructor can teach at most one subject and only if he/she possesses
                 the right specialism. In the simulation, there will be the following types of instructors:
               
                 a) Teacher: a teacher can teach subjects with specialism 1,2
                 b) Demonstrator: a demonstrator can teach subjects with specialism 2
                 c) OOTrainer: an OOTrainer can teach subjects with specialisms 1,2,3
                 d) GUITrainer: a GUITrainer can teach subjects with specialisms 1,2,4

  5) School: the School class represents a school. A school is responsible for managing subjects, courses, instructors and students

  6) Administrator: the Administrator class represents the administrator of the school. It is responsible of registering/deregistering the
                    students and the instructors to the school.

When running the simulation of the school, the program will print out a pretty-printed string containing the recap of what happened the previous day
in the school listing information about subjects, courses, instructors, students and the relationship between them.
(HOW TO RUN THE SIMULATION WILL BE EXPLAINED IN THE NEXT SECTION).

After this, the program will print out the major facts happening during the day. These occurrences are:

  1) Every day a random number of students ranging from 0 to 2 will join the school.

  2) Every day there is a possibility that an instructor joins the school. These are the probabilities for each type of instructor:

                 a) Teacher: 20% probability
                 b) Demonstrator: 10% probability
                 c) OOTrainer: 5% probability
                 d) GUITrainer: 5% probability

  3) Any topic that does not have an open-for-registration course will be matched to a new course that will start in 2 days.

  4) Courses need instructors, thus available and suitable (right specialism) instructors will be assigned to a course.

  5) Students that are not enrolled in a course will be assigned to a course only if the course is not currently running or is not full and the students possesses the prerequisites for it.

  6) At the end of the day, courses that have finished or have been cancelled will be deleted from the School's courses set.

  7) If a course finishes, the students enrolled in it will receive a certification.

  8) At the end of the day, an instructor not currently teaching will leave the school with a probability of 20%.

  9) At the end of the day, if a student has got all certificates from all subjects, he/she will leave the school.

  10) At the end of the day, a student not enrolled in a course has a probability of 5% to leave the school.


##HOW TO RUN THE SIMULATION##

To run the simulation, any IDE can be used and it is enough to run Administrator.main().
Once the program has been compiled and has started, the user will be asked to write in the command line 'SF' if he/she wants to enter a simulation file
or 'C' if he/she wants to continue a simulation.

  1) In the case the user decides to enter a simulation file, he/she will have to write the source path of the simulation file.
     If the simulation file is in the same folder as the source code, then it is enough to write the name of the simulation file.
     It is important that the simulation file name is followed by ".txt"
     The simulation file must be a normal text file and must be formatted as shown below:
     
     school:ECS Java Training School
     subject:Basics,1,1,5
     subject:Lab 1,2,2,2
     subject:Arrays,3,1,4
     student:Peter,M,60
     student:John,M,22
     student:Annabelle,F,31
     student:Maggie,F,58
     student:Alex,M,23
     Teacher:Yvonne,F,55
     Demonstrator:Beth,F,45
     OOTrainer:Chris,M,62
     GUITrainer:Sarah,F,48

     It is not necessary that the order of the lines is the same as in the above simulation file example.
     For instance, the first line may describe a GUITrainer and the last line may describe the school.

     Since, this program also implements the first extension (Prerequisites for the subjects), it is possible to set the prerequisites from the simulation file.
     Example of how to set the prerequisites of a subject:

     school:ECS Java Training School
     subject:Basics,1,1,5
     subject:Lab 1,2,2,2
     subject:Arrays,3,1,4,1,2
     student:Peter,M,60
     student:John,M,22
     student:Annabelle,F,31
     student:Maggie,F,58
     student:Alex,M,23
     Teacher:Yvonne,F,55
     Demonstrator:Beth,F,45
     OOTrainer:Chris,M,62
     GUITrainer:Sarah,F,48

     In the example above, some prerequisites (1,2) are set to the subject "Arrays".Thus, the prerequistes must be separated by a come and must follow the duration of the subject.
     The program will accept only prerequistes of lower level, indeed prerequistes of higher level will be removed. As an example, the subject "Array" with id "3" cannot have "4"
     as prerequisite as it represents an higher level subject. 


     In the case the source path of the simulation file does not exist, the program will throw an exception and the user will be asked to enter
     a new source path. If the file path exists and the file text can be read, the user will be asked to provide the name of the school if it was
     not provided by the simulation file. The simulation will fail to start in the case no subjects have been provided by the simulation file. If this
     happens, a customised exception "SubjectsNotFoundException" will be thrown and the user will need to enter another simulation file.
     If the previous stages don't go wrong, the user will be asked to write the number of days of the simulation and consequently, the school simulation will
     run unless the number of days is less than or equal to 0. In the case that happens, the user will be asked to write the number of days again.

  2) In the case the user decides to continue a simulation already started, then he/she must write in the command line the source path of the saved simulation
     file (HOW TO SAVE THE SIMULATION WILL BE DESCRIBED IN THE NEXT CHAPTER). As described for starting a simulation through a simulation file before, if the
     source path of the file does not exist, the user will be asked to enter a new source path again. If the process of loading the saved simulation file succeeds,
     the simulation will continue running for a certain number of days decided by the user.

After the previous set up steps, the simulation will run automatically. No user inputs will be needed.

##HOW TO SAVE THE STATE OF A SIMULATION##

Once the simulation terminates, the user will be asked to enter 'S' to save the simulation or 'T' to terminate without saving.

  1) If the user decides to terminate the simulation without saving its state, the programm will terminate successfully.

  2) If saving the state of the simulation is the option chosen by the user then he/she will need to provide the name of the text file that will be generated.
     If the user needs to save the file in a specific destination, then the destination path and the file name of the text file must be provided.
     It is important that the file name is followed by ".txt"
    






