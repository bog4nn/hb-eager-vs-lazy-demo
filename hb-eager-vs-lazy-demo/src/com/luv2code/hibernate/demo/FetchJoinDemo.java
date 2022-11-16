 package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			//Option 2 : Hibernate query with HQL i.id=:theInstructorId set argument for parameter
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i " 
								+ "JOIN FETCH i.course "
								+ "where i.id=:theInstructorId",
								Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor , Load instructor and courses all at once
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("lovetocode: Instructor"+tempInstructor);
			
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
