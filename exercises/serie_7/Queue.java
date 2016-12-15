public class Queue
{
	private QueueNode firstNode;
	// Keeping a reference to the last node to accelerate enqueueing.
	// This way we can enqueue new objects in constant time, instead of
	// having to traverse the queue.
	private QueueNode lastNode;

	/**
	 * Add an arbitrary object to the end of the queue.
	 */
	public void enqueue(Object input) {
		QueueNode nextNode = new QueueNode(input);
		if (isEmpty()) {
			this.firstNode = nextNode;
			this.lastNode = this.firstNode;
		} else {
			// Link new node to end of queue, and update our
			// reference to the last node.
			this.lastNode.setNextNode(nextNode);
			this.lastNode = nextNode;
		}
	}

	/**
	 * Get the first object from the queue.
	 *
	 * This will raise an EmptyQueueException if the queue is empty.
	 */
	public Object dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is empty!");
		}

		QueueNode outputNode = this.firstNode;
		// The to-be-dequeued node's successor is now our first node.
		this.firstNode = outputNode.getNextNode();

		if (isEmpty()) {
			// If we just dequeued the final item, also purge the reference to the lastNode.
			// Not strictly needed, but this allows GC to kick in - and is also cleaner.
			this.lastNode = null;
		}

		return outputNode.getObject();
	}

	/** 
	 * Check whether the queue is empty.
	 */
	public Boolean isEmpty() {
		return this.firstNode == null;
	}

	/** 
	 * Return a string representation of the queue and its objects.
	 */
	public String toString() {
		String out = "Queue: ";

		if (this.firstNode != null) {
			out += this.firstNode.toString();
		}

		return out;
	}
}
