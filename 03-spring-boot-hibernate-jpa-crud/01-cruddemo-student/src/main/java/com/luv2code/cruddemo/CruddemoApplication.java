package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    // allows us to run program from command line
    // CommandLineRunner is from the springboot framework
    // Executed after Spring Beans have been loaded
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner -> {
            //createStudent(studentDAO);
            createMultipleStudents(studentDAO);
            //readStudent(studentDAO);
            //queryForStudents(studentDAO);
            //queryForStudentsByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students . . .");

        // deletes all students from database and returns number of rows deleted
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentID = 5;
        System.out.println("Deleting student id: " + studentID);
        studentDAO.delete(studentID);
    }

    private void updateStudent(StudentDAO studentDAO) {
        // retrieve student based on the id: primary key
        System.out.println("Getting student with id: " + 1);
        Student theStudent = studentDAO.findByID(1);

        // change first name to "Kyle"
        System.out.println("Updating student . . . ");
        theStudent.setFirstName("Kyle");

        // update the student in the database
        studentDAO.update(theStudent);

        // display the updated student
        System.out.println("Updated student: " + theStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        // get a list of students
        List<Student> theStudents = studentDAO.findByLastName("Harbaugh");

        // display list of students
        for(Student s: theStudents){
            System.out.println(s);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        // get a list of students
        List<Student> theStudents = studentDAO.findAll();

        // display list of students
        for (Student s: theStudents){
            System.out.println(s);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        // create a student object
        System.out.println("Creating a new student object . . .");
        Student tempStudent = new Student("AJ", "Brown", "AJB@gmail.com");

        // save the student to the database
        System.out.println("Saving the student . . .");
        studentDAO.save(tempStudent);

        // display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generated id: " + theId);

        // retrieve student based on the id: primary key
        System.out.println("Retrieving student with id: " + theId);
        Student myStudent = studentDAO.findByID(theId);

        // display student
        System.out.println("Found the student: " + myStudent);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        // create multiple students
        System.out.println("Creating 3 student objects . . .");
        Student tempStudent1 = new Student("Jim", "Harbaugh", "JimHarbaugh@gmail.com");
        Student tempStudent2 = new Student("Mike", "Vrabel", "MikeVrabel@gmail.com");
        Student tempStudent3 = new Student("Kevin", "Occonnel", "KOC@gmail.com");

        // save the student objects to the database
        System.out.println("Saving the students . . .");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        // create the student object
        System.out.println("Creating new student object . . .");
        Student tempStudent = new Student("John", "Harbaugh", "JohnHarbaugh@gmail.com");

        // save the student object to the database
        System.out.println("Saving the student . . .");
        studentDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }

}
