package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

	private static List<Person> db = new ArrayList<Person>();
	@Override
	public int insertPerson(UUID id, Person person) {
		db.add(new Person(id, person.getName()));
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public List<Person> selectAllPeople() {
		// TODO Auto-generated method stub
		return db;
	}
	@Override
	public Optional<Person> selectPersonById(UUID id) {
		// TODO Auto-generated method stub
		
		return db.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}
	@Override
	public int deletePersonById(UUID id) {
		// TODO Auto-generated method stub
		Optional<Person> personMaybe = selectPersonById(id);
		System.out.println("System" + personMaybe.toString());
		if (personMaybe.empty() == null) {
			return 0;
		}
		db.remove(personMaybe.get());
		return 1;
	}
	@Override
	public int updatePersonById(UUID id, Person person) {
		return selectPersonById(id)
				.map(p -> {
					int index = db.indexOf(p);
					if (index >= 0) {
						db.set(index, new Person(id, person.getName()));
						return 1;
					}
					else {
						return 0;
					}
				})
				.orElse(0);
	}
	

}
