package be.vdab.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

@Entity
@NamedEntityGraph(name="withBoss", attributeNodes=@NamedAttributeNode("boss"))
public class Person {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@ManyToOne
	@JoinColumn
	private Person boss;	
}
