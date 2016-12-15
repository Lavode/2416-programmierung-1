public class QueueNode
{
	private Object obj;
	private QueueNode nextNode;

	public QueueNode(Object obj) {
		this.obj = obj;
	}

	public QueueNode getNextNode() {
		return this.nextNode;
	}

	public void setNextNode(QueueNode nextNode) {
		this.nextNode = nextNode;
	}

	public Object getObject() {
		return this.obj;
	}

	public String toString() {
		String out = this.obj.toString();
		if (this.nextNode != null) {
			out += " - " + this.nextNode.toString();
		}

		return out;
	}
}
