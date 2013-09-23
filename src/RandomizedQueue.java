import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class RandomizedQueue<Item> implements Iterable<Item> {

//   public RandomizedQueue()           // construct an empty randomized queue
//   public boolean isEmpty()           // is the queue empty?
//   public int size()                  // return the number of items on the queue
//   public void enqueue(Item item)     // add the item
//   public Item dequeue()              // delete and return a random item
//   public Item sample()               // return (but do not delete) a random item
//   public Iterator<Item> iterator()   // return an independent iterator over items in random order
	private int size;
	private int capacity;
	private Item[] items;
	@SuppressWarnings("unchecked")
	public RandomizedQueue(){
		size=0;
		capacity=4;
		items=(Item[]) new Object[capacity];
	}
	public boolean isEmpty(){
		return size==0;
	}
	public int size(){
		return size;
	}
	public void enqueue(Item item){
		if (item==null) throw new NullPointerException();
		size++;
		items[size-1]=item;
		if (size>=capacity) {
			resize(capacity*2);
		}
	}
	public Item dequeue(){
		if (isEmpty()) throw new NoSuchElementException();
		int rand=StdRandom.uniform(size);
		Item dItem=items[rand];
		items[rand]=items[size-1];
		size--;
		if (size<capacity/4) {
			resize(capacity/4);
		}
		return dItem;
	}
	public Item sample(){
		if (isEmpty()) throw new NoSuchElementException();
		int rand=StdRandom.uniform(size);
		return items[rand];
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new randomQueueIterator();
	}
	@SuppressWarnings("unchecked")
	private void resize(int newCapacity){
		Item[] newItems=(Item[])new Object[newCapacity];
		for (int i = 0; i < size&& i< newCapacity; i++) {
			newItems[i]=items[i];
		}
		items=newItems;
		newItems=null;
		capacity=newCapacity;
	}
	private class randomQueueIterator implements Iterator<Item>{
		int iter=0;
		int[] id;
		public randomQueueIterator() {
			id=new int[size];
			for (int i = 0; i < id.length; i++) {
				id[i]=i;
			}
			StdRandom.shuffle(id);
		}
		@Override
		public boolean hasNext() {
			return iter<size;
		}

		@Override
		public Item next() {
			if (iter>=size) throw new NoSuchElementException();
			iter++;
			return items[id[iter-1]];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
//	public static void main(String[] args) {
//		RandomizedQueue<Integer> randomizedQueue=new RandomizedQueue<Integer>();
//		for (int i = 0; i < 100; i++) {
//			randomizedQueue.enqueue(i);
//		}
//		for (int i = 0; i < 100; i++) {
//			randomizedQueue.dequeue();
//			System.out.println(randomizedQueue.getCapacity());
//		}
//		for (Integer integer : randomizedQueue) {
//			System.out.print(integer+" ");
//			if (integer%10==0) {
//				System.out.println();
//			}
//		}
//	}
}