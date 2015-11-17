package Collections;

import java.util.Comparator;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class ReferenceBasedList<T> implements ListInterface<T>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4506294312024144993L;
	private ListNode<T> head;
	private ListNode<T> tail;
	int numItems;

	public ReferenceBasedList()
	{
		head = tail = null;
		numItems = 0;
	}

	public int size()
	{
		return numItems;
	}
	
	public boolean isEmpty()
	{
		return (numItems == 0);
	}

	public void removeAll()
	{
		head = tail = null;
		numItems = 0;
	}

	private ListNode<T> find(int index)
	{
		ListNode<T> curr = head;
		for (int skip = 1; skip < index; skip++)
			curr = curr.getNext();
		return curr;
	}

	
	public T get(int index) 
					  throws ListIndexOutOfBoundsException
	{
		if (index >= 1 && index <= numItems)
		{
			ListNode<T> curr = find(index);
			return curr.getItem();
		} 
		else
		{
			throw new ListIndexOutOfBoundsException(
                     "List index out of bounds exception on get");
		}
	}

	public void add(int index, T newDataItem)
					throws ListIndexOutOfBoundsException
	{
		if (index >= 1 && index <= numItems+1)
		{
			if ( index == 1 )
			{
				ListNode<T> newNode = new ListNode<T>(newDataItem, head);
				head = newNode;

				if (tail==null)
					tail = head;
			} 
			else if ( index==numItems+1 )
			{
				ListNode<T> newNode = new ListNode<T>(newDataItem);
				tail.setNext(newNode);
				tail = newNode;
			}
			else
			{
				ListNode<T> prev = find(index-1);
				ListNode<T> newNode = new ListNode<T>(newDataItem, prev.getNext());
				prev.setNext(newNode);
			}
			numItems++;
		} 
		else
		{
			throw new ListIndexOutOfBoundsException(
                    "List index out of bounds exception on add");
		}
	}

	public void add(T newDataItem, Comparator<T> comperator) {
		ListNode<T> item = new ListNode<T>(newDataItem, null);
		if(isEmpty()) {
			head = tail = item;
			numItems++;
			return;
		}
		if (comperator.compare(newDataItem, head.getItem()) < 0) {
			item.setNext(head);
			head = item;
			numItems++;
		} else if (comperator.compare(newDataItem, tail.getItem()) > 0) {
			tail.setNext(item);
			tail = item;
			numItems++;
		} else {
			ListNode<T> tmp = head;
			for(int i = 0; i < (size() - 1); i++) {
				ListNode<T> next = tmp.getNext();
				if (comperator.compare(newDataItem, next.getItem()) < 0) {
					item.setNext(next);
					tmp.setNext(item);
					numItems++;
					return;
				}
				tmp = next;
			}
		}
	}
	
	public void insert(T newDataItem)
	{
		this.add(1,newDataItem);
	}

	public void append(T newDataItem)
	{
		this.add(numItems+1,newDataItem);
	}	

	public T showFront()
	{
		return this.get(1);
	}

	public T showLast()
	{
		return this.get(numItems);
	}

	public void remove(int index) 
					   throws ListIndexOutOfBoundsException
	{
		if (index >= 1 && index <= numItems)
		{
			if (index == 1)
			{
				head = head.getNext();
				if (head == null)
					tail = null;
			}
			else
			{
				ListNode<T> prev = find(index-1);
				ListNode<T> curr = prev.getNext(); 
				prev.setNext(curr.getNext());
				if (index == numItems)
					tail = prev;
			}
			numItems--;
		}
		else
		{
			throw new ListIndexOutOfBoundsException(
                   "List index out of bounds exception on remove");
		}
	}

	public boolean exists(T dataItem)
	{
		for (ListNode<T> tmp=head; tmp!=null; tmp=tmp.getNext())
			if (tmp.getItem().equals(dataItem))
				return true;
		return false;
	}

	public boolean exists(Predicate<T> predicate) {
		for (ListNode<T> tmp=head; tmp!=null; tmp=tmp.getNext())
			if (predicate.test(tmp.getItem()))
				return true;
		return false;
	}

	@Override
	public T findSingle(Predicate<T> predicate) throws ListException {
		for (ListNode<T> tmp=head; tmp!=null; tmp=tmp.getNext()) {
			T item = tmp.getItem();
			if (predicate.test(item)) {
				return item;
			}
		}
		throw new ListException("Error on find single, no matching element found");
	}

	
	@Override
	public void removeSingle(Predicate<T> predicate) throws ListException {
		if(isEmpty()) {
			throw new ListException("Error at removeSingle, list is already empty");
		}
		if(predicate.test(head.getItem())) {
			head = head.getNext();
			numItems--;
			if(head == null) {
				head = tail = null;
			}
		} else {
			ListNode<T> temp = head;
			for(int i = 1; i <= size() ; i++) {
				if(predicate.test(temp.getNext().getItem())) {
					ListNode<T> curr = temp.getNext();
					temp.setNext(curr.getNext());
					numItems--;
					if(temp.getNext() == null) {
						tail = temp;
					}
					break;
				} else {
					temp = temp.getNext();
				}
			}
			throw new ListException("No matching element found.");
		}
	}

}