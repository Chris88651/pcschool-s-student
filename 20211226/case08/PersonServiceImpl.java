package com.study.springcore.case08;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	public boolean append(String name, Date birth) {
		Person person = new Person();
		person.setName(name);
		person.setBirth(birth);
		return append(person);
	}

	@Override
	public boolean append(Person person) {
		return personDao.create(person);
	}

	@Override
	public List<Person> findAllPersons() {
		return personDao.readAll();
	}

	@Override
	public Person getPerson(String name) {
		Optional<Person> optPerson = findAllPersons()
				.stream()
				.filter(p -> p.getName().equals(name))
				.findFirst();
		return optPerson.isPresent() ? optPerson.get() : null;
	}
	
}
