
public class Subset {
	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		int n=Integer.parseInt(args[0]);
		for (int i = 0; i < n; i++) {
			queue.enqueue(StdIn.readString());
		}
		for (String string : queue) {
			StdOut.println(string);
		}
	}
}
