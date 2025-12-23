package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    // this creates a command line application
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        return runner -> {
            //createInstructor(appDAO);

            //findInstructor(appDAO);

            //deleteInstructor(appDAO);

            //findInstructorDetail(appDAO);

            //deleteInstructorDetail(appDAO);

            createInstructorWithCourses(appDAO);
        };
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "tennis");

        // associate objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Tennis - the Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        System.out.println("Saving the instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);
        System.out.println("Done!");

    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId=3;
        System.out.println("Deleting instructorDetail with id: " + theId);

        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        //get the instructor detail object
        int theId = 2;
        System.out.println("Finding Instructor Detail id: " + theId);

        // print instructor detail
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);
        System.out.println("tempInstructorDetail: " + instructorDetail);

        System.out.println("the associated instructor: " + instructorDetail.getInstructor());

        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 4;
        System.out.println("Deleting instructor id: " + theId);
        appDAO.deleteInstructorById(theId);

        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId= 2;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associate instructorDetail only : " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        /*
        // create the instructor
        Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!");
        */

        // create the instructor
        Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "great guitar player!");

        // associate objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        //
         // NOTE: this will ALSO save the details objects
        // because of CascadeType.ALL
        //

        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }
}
