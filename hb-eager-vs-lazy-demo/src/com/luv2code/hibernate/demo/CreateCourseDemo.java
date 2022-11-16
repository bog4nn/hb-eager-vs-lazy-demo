 package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCourseDemo {

	public static void main(String[] args) {
		// Create Session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)

									.buildSessionFactory();
		//Create session
		Session session = factory.getCurrentSession();
		
		try {
	
			//begin transaction
			session.beginTransaction();
			
			// get the instructor form db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			// create some course
			Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
			Course tempCourse2 = new Course("The Pinball Masterclass");
			
			// add course to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			// save the course
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			//commit session
			session.getTransaction().commit();
			System.out.println("Done!");
		}finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
