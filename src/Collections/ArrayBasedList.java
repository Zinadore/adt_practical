package Collections;

import java.util.Comparator;
import java.util.function.Predicate;

// ********************************************************
// Array-based implementation of the ADT list.
// *********************************************************
@SuppressWarnings("unchecked")
public class ArrayBasedList<T> implements ListInterface<T>
{
	private int MAX_SIZE = 2;
	private Object dataItems[];
	private int numItems;

	public ArrayBasedList()
	{
		dataItems = new Object[MAX_SIZE];
		numItems = 0;
	}
	
	public boolean isEmpty()
	{
		return (numItems == 0);
	}
   
	public int size()
	{
		return numItems;
	}

	
	public T get( int index ) 
					  throws ListIndexOutOfBoundsException
	{
		if (index >= 1 && index <= numItems)
			return (T)dataItems[translate(index)];
		else
		{
			throw new ListIndexOutOfBoundsException(
			"ListIndexOutOfBoundsException on get");
		}
	}

	public void add( int index, T newDataItem )
					throws  ListIndexOutOfBoundsException
	{
		if (index < 1 || index > numItems+1)
			throw new ListIndexOutOfBoundsException("ListIndexOutOfBoundsException on add");

		if (numItems == MAX_SIZE)
			resize("expand");
		
		for (int pos = numItems; pos >= index; pos--)
			dataItems[translate(pos+1)] = dataItems[translate(pos)];

		dataItems[translate(index)] = newDataItem;
		numItems++;
	}


	public void add(T newDataItem, Comparator<T> comperator) throws ListException {
		if(isEmpty()) {
			add(1, newDataItem);
			return;
		}
		for (int i = 1; i <= size(); i++) {
			if(comperator.compare(newDataItem, get(i)) < 0) {
				add(i, newDataItem);
				return;
			}
		}
		add((numItems + 1),newDataItem);
	}
	
	public void remove( int index ) 
					   throws ListIndexOutOfBoundsException
	{
		if (index<1 || index>numItems)
		{
			throw new ListIndexOutOfBoundsException(
			"ListIndexOutOfBoundsException on remove");
		}
		
		for (int pos = index+1; pos <= size(); pos++)
		    dataItems[translate(pos-1)] = dataItems[translate(pos)];
		numItems--;

		if (numItems == (MAX_SIZE/2))
			resize("shrink");
	}
   
	private int translate( int position )
	{
		return position - 1;
	}

	private void resize(String allocation)
	{
		if (allocation == "expand")
			MAX_SIZE = MAX_SIZE * 2;
		else
			MAX_SIZE = MAX_SIZE / 2;

		Object [] newDataItems = new Object[MAX_SIZE];

			for (int i=0; i<numItems; i++)
				newDataItems[i] = dataItems[i];
			dataItems = newDataItems;
	}

	@Override
	public void removeAll() {
		dataItems = new Object[MAX_SIZE];
		numItems = 0;
	}

	@Override
	public void insert(T newDataItem) {
		this.add(1, newDataItem);
	}

	@Override
	public void append(T newDataItem) {
		this.add(numItems+1,newDataItem);
	}

	@Override
	public T showFront() {
		return this.get(1);
	}

	@Override
	public T showLast() {
		return this.get(numItems);
	}

	@Override
	public boolean exists(T newDataItem) {
		for(int i = 1; i <= this.size(); i++) {
			if(this.get(i).equals(newDataItem)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean exists(Predicate<T> predicate) {
		for(int i = 1; i <= this.size(); i++) {
			if(predicate.test(this.get(i))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T findSingle(Predicate<T> predicate) throws ListException {
		for(int i = 1; i <= this.size(); i++) {
			if(predicate.test(this.get(i))){
				return this.get(i);
			}
		}
		throw new ListException("No matching object found");
	}

	@Override
	public void removeSingle(Predicate<T> predicate) throws ListException {
		for(int i = 1; i <= this.size(); i++) {
			if(predicate.test(this.get(i))){
				this.remove(i);
			}
		}
		throw new ListException("No matching object found");
	}

}