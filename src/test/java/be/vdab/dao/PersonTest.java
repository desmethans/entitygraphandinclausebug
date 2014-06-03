package be.vdab.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.vdab.entities.Person;

public class PersonTest {
	private static EntityManagerFactory factory;
	private EntityManager entityManager;

	@BeforeClass
	public static void beforeClass() {
		factory = Persistence.createEntityManagerFactory("persons");
	}

	@AfterClass
	public static void afterClass() {
		factory.close();
	}
	
	
	@Before
	public void before() {
		entityManager = factory.createEntityManager();
	}
	
	@After
	public void after() {
		entityManager.close();
	}
	
	
	@Test
	public void testInClause() {
		Set<Long> ids=new HashSet<>();
		ids.add(1L);
		ids.add(2L);
		TypedQuery<Person> query= entityManager.createQuery("select p from Person p where p.id  in :ids", Person.class);
		query.setParameter("ids", ids);
		query.getResultList();		
	}
	@Test
	public void testEntityGraph() {
		TypedQuery<Person> query=entityManager.createQuery("select p from Person p", Person.class);
		query.setHint("javax.persistence.loadgraph", entityManager.createEntityGraph("withBoss"));
		query.getResultList();		
	}
	@Test
	public void testEntityGraphAndInClause() {
		Set<Long> ids=new HashSet<>();
		ids.add(1L);
		ids.add(2L);
		TypedQuery<Person> query= entityManager.createQuery("select p from Person p where p.id  in :ids", Person.class);
		query.setHint("javax.persistence.loadgraph", entityManager.createEntityGraph("withBoss"));
		query.setParameter("ids", ids);
		query.getResultList();		
	}
	
	
	

}
