package dk.itu.gsd.lms.dao;

import java.util.List;

/**
 * A generic Hibernate interface for all Hibernate objects
 * The interfaces exposes the basic CRUD operations available
 * on all Hibernate objects 
 *
 * @param <T> where T is object name
 */
public interface GenericDao<T> {
	
	/**
	 * Saves an object with type T
	 * @param obj
	 * @return The updated object
	 * with type T
	 */
	public T save(T obj);

	/**
	 * Saves an object with type T
	 * @param obj
	 * @return The updated object
	 * with type T
	 */
	public T update(T obj);
	
	/**
	 * Deletes an object of type T
	 * from the database 
	 * @param obj
	 */
	public void deleteById(Long id);

	/**
	 * Finds an occurences of T
	 * by T's ID
	 * @param obj
	 * @return The found object 
	 * of type T
	 */
	public T find(Long id);
	
	/**
	 * Finds all occurences of T 
	 * @param obj
	 * @return A list with T objects
	 */
	public List<T> findAll();
	
	/**
	 * Find id for all occurrences of T
	 * @return A list with the ids of all T objects
	 */
	public List<Long> findAllIds();

}