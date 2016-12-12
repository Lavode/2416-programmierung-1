import java.util.List;
import java.util.ArrayList;

public class Queue
{
	private List<Object> queue = new ArrayList<Object>();

	public Object dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Empty");
		}
		return this.queue.remove(0);
	}

	public void enqueue(Object obj) {
		queue.add(obj);
	}

	public Boolean isEmpty() {
		return this.queue.size() == 0;
	}
}
