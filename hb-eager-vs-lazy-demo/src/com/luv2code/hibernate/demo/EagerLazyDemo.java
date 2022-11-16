 package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			System.out.println("lovetocode: Instructor"+tempInstructor);
			// get course for the instructor
			
			System.out.println("lovetocode: Course"+tempInstructor.getCourse());
			
			//commit session
			session.getTransaction().commit();	
			System.out.println(" lovetocode Done!");
		}finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
