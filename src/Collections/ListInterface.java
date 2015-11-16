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
	public void add(T newDataItem, Comparator<T> comperator);
	public void append(T newDataItem);
	public T showFront();
	public T showLast();
	public void remove(int index) 
					   throws ListIndexOutOfBoundsException;
	public boolean exists(T newDataItem);
	public boolean exists(Predicate<T> predicate);
	public T findSingle(Predicate<T> predicate) throws ListException;
	public void removeSingle(Predicate<T> predicate) throws ListException;
}
