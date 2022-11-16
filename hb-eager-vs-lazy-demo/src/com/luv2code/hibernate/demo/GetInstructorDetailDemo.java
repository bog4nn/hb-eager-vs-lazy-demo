package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		// Create Session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		//Create session
		Session session = factory.getCurrentSession();
		
		try {
			//start transaction
			session.beginTransaction();
			
			// get the instructor detail object
			int theId= 2222;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			// print the instructor detail
			System.out.println("tempInstructorDetail: "+tempInstructorDetail);
			
			// print the associated instructor
			System.out.println("the associated instructor: "+ tempInstructorDetail.getInstructor());
			
			//commit session
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
	}

}
