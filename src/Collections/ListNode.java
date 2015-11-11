package Collections;

import java.io.Serializable;

public class ListNode<T> implements Serializable
{
	private T item;
	private ListNode<T> next;

	public ListNode()
	{
		next = null;
	}

	public ListNode(T newItem)
	{
		item = newItem;
		next = null;
	}

	public ListNode(T newItem, ListNode<T> nextNode)
	{
		item = newItem;
		next = nextNode;
	}

	public void setItem(T newItem)
	{
		item = newItem;
	}

	public T getItem()
	{
		return item;
	}

	public void setNext(ListNode<T> nextNode)
	{
		next = nextNode;
	}

	public ListNode<T> getNext()
	{
		return next;
	}
};