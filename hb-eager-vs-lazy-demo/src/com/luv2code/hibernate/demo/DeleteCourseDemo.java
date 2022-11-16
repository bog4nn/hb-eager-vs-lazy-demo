 package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

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
			
			// get a course
			int theId= 1;
			Course tempCourse = session.get(Course.class, theId);
			
			//delete course
			System.out.println("Deleting course"+ tempCourse);
			session.delete(tempCourse);
			
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
