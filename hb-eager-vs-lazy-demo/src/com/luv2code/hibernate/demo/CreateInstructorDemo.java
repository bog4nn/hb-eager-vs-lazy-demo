package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			// create the object
			Instructor tempInstructor = new Instructor ("Cona","Wale","holo@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail (
					"http://www.gamingcona.com/youtube",
					"Duall!!!");
			
			// associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//begin transaction
			session.beginTransaction();
			
			//save the instructor
			//
			//Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: "+ tempInstructor);
			session.save(tempInstructor);
			
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
