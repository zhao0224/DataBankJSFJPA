/*****************************************************************
 * File: PersonPojo.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;


import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import databank.model.PersonPojo;
import databank.ejb.PersonService;
/**
 * Description: Implements the C-R-U-D API for the database
 * 
 * TODO 01 - some components are managed by CDI.<br>
 * TODO 02 - methods which perform DML need @Transactional annotation.<br>
 * TODO 03 - fix the syntax errors to correct methods. <br>
 * TODO 04 - refactor this class. move all the method bodies and EntityManager to a new service class which is a
 * singleton (EJB).<br>
 * TODO 05 - inject the service class using EJB.<br>
 * TODO 06 - call all the methods of service from each appropriate method here.
 */
@Named
@ApplicationScoped
public class PersonDaoImpl implements PersonDao, Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

//	//get the log4j2 logger for this class
//	private static final Logger LOG = LogManager.getLogger();
//
//	protected EntityManager em;
	@EJB
	protected PersonService personService;

	@Override
	public List< PersonPojo> readAllPeople() {
		return personService.findAllPeople();
	}

	@Override
	@Transactional
	public PersonPojo createPerson( PersonPojo person) {
		return personService.persistePerson(person);
	}

	@Override
	public PersonPojo readPersonById( int personId) {
		return personService.findPersonById(personId);
	}

	@Override
	@Transactional
	public PersonPojo updatePerson( PersonPojo personWithUpdates) {
		return personService.mergePerson(personWithUpdates);
	}

	@Override
	@Transactional
	public void deletePersonById( int personId) {
		personService.removePersonById(personId);
	}

}