package Collections;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Predicate;

// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterface<T> extends Serializable
{
	public boolean isEmpty();
	public int size();
	public T get(int index) 
					  throws ListIndexOutOfBoundsException;
	public void removeAll();
	public void insert(T newDataItem);
	public void add(int index, T newDataItem) 
					throws ListIndexOutOfBoundsException,
						   ListException;
	/**
	 * Adds a new element to the list, using the {@link java.util.Comparator Comparator} to define its position.
	 * @param newDataItem the element to be added.
	 * @param comparator the Comparator used to add the element to its proper position.
	 */
	public void add(T newDataItem, Comparator<T> comparator);
	public void append(T newDataItem);
	public T showFront();
	public T showLast();
	public void remove(int index) 
					   throws ListIndexOutOfBoundsException;
	public boolean exists(T newDataItem);
	/**
	 * Returns true if an element is found  matching the given {@link java.util.function.Predicate predicate}. Otherwise it
	 * returns false.
	 * @param predicate the predicate to be evaluated.
	 * @return true if an element is found, false if not.
	 */
	public boolean exists(Predicate<T> predicate);
	
	/**
	 * Returns the first element found matching the {@link java.util.function.Predicate predicate}. If no element is found a ListException is thrown.
	 * @param predicate the predicate to be evaluated.
	 * @return the element if found.
	 * @throws ListException if no matching element is found.
	 */
	public T findSingle(Predicate<T> predicate) throws ListException;
	
	/**
	 * Removes the first element found matching the {@link java.util.function.Predicate predicate}. If no element is found a ListException is thrown.
	 * @param predicate the predicate to be evaluated.
	 * @throws ListException if no matching element is found.
	 */
	public void removeSingle(Predicate<T> predicate) throws ListException;
}
