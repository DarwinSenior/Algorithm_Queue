import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
//	public Deque()                     // construct an empty deque
//	public boolean isEmpty()           // is the deque empty?
//	public int size()                  // return the number of items on the deque
//	public void addFirst(Item item)    // insert the item at the front
//	public void addLast(Item item)     // insert the item at the end
//	public Item removeFirst()          // delete and return the item at the front
//	public Item removeLast()           // delete and return the item at the end
//	public Iterator<Item> iterator()   // return an iterator over items in order from front to end
	private class Node{
		Node next=null;
		Node previous=null;
		Item item=null;
	}
	private Node firstNode=new Node();
	private Node lastNode=new Node();
	private int size;
	public Deque(){
		size=0;
		
		firstNode.next=lastNode;
		firstNode.previous=null;
		firstNode.item=null;
		lastNode.next=null;
		lastNode.previous=firstNode;
		lastNode.item=null;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public int size() {
		return size;
	}
	public void addFirst(Item item){
		if (item==null) throw new NullPointerException("Item");
		Node thisNode=new Node();
		thisNode.item=item;
		Node nextNode=firstNode.next;
		firstNode.next=thisNode;
		nextNode.previous=thisNode;
		thisNode.previous=firstNode;
		thisNode.next=nextNode;
		size++;
	}
	public void addLast(Item item){
		if (item==null) throw new NullPointerException("Item");
		Node thisNode=new Node();
		thisNode.item=item;
		Node previousNode=lastNode.previous;
		previousNode.next=thisNode;
		lastNode.previous=thisNode;
		thisNode.previous=previousNode;
		thisNode.next=lastNode;
		size++;
	}
	public Item removeFirst(){
		if (isEmpty()) throw new NoSuchElementException("Empty Queue");
		Node thisNode=firstNode.next;
		Node nextNode=thisNode.next;
		firstNode.next=nextNode;
		nextNode.previous=firstNode;
		size--;
		return thisNode.item;
	}
	public Item removeLast(){
		if (isEmpty()) throw new NoSuchElementException("Empty Queue");
		Node thisNode=lastNode.previous;
		Node previousNode=thisNode.previous;
		lastNode.previous=previousNode;
		previousNode.next=lastNode;
		size--;
		return thisNode.item;
	}
	@Override
	public Iterator<Item> iterator() {
		
		return new DequeIterator();
	}
	private class DequeIterator implements Iterator<Item>{
		
		private Node currentNode=firstNode;
		@Override
		public boolean hasNext() {
			return currentNode.next!=lastNode;
		}

		@Override
		public Item next() {
			if (currentNode.next==lastNode) throw new NoSuchElementException("!hasNext");  
			currentNode=currentNode.next;
			return currentNode.item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}
}
