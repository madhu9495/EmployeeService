package com.rest.EmpApp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class SessionUtil {
	private static SessionUtil instance=new SessionUtil();
    private SessionFactory sessionFactory;
   
    
    public static SessionUtil getInstance(){
        return instance;
    }
    
    private SessionUtil(){
        try {
        	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        	Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
        	sessionFactory = metaData.getSessionFactoryBuilder().build();
           } catch (Throwable th) {
        	System.err.println("Enitial SessionFactory creation failed" + th);
        	throw new ExceptionInInitializerError(th);
          }
    }
    
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        
        return session;
    }

}
