
public class LinkedList {
	
	public Node front;
	public Node rear;
	
	private int nodeCount = 0;
	
	public void nodeCChange(int a) {
		nodeCount = a;
	}
	
	public void add(Node item) {
		if(nodeCount == 0) {
			front = item;
			rear = item;
			item.index = 0;
		}
		else {
			rear.next = item;
			item.prev = rear;
			rear = item;
			item.index = (item.prev).index + 1;
			rear.next = null;
		}
		nodeCount += 1;
	}
	
	public void insertAt(Node item, int index) {
		Node ptr = front;
		Node ptr2 = front;
		
		if(index != nodeCount) {
			while(ptr != null) {
				if(ptr.index == index ) {
					if(index == 0) {
						front = item;
						item.next = ptr;
						ptr.prev = item;
						item.index = 0;
						item.prev = null;
						break;
					}
					else {
						ptr2 = ptr;
						(ptr.prev).next = item;
						item.prev = ptr.prev;
						item.next = ptr;
						ptr.prev = item;
						//item.index = index;
						break;
					}
				}
				ptr = ptr.next;
			}
			ptr2.prev.index = index;
			
			while(ptr2 != null) {
				ptr2.index = ptr2.index + 1;
				ptr2 = ptr2.next;
			}
			nodeCount+=1;
		}
		else {
			add(item);
		}
	}
	
	public void remove(Node item) {
		boolean isLast = false;
		boolean isRemoved = false;
		boolean isRemovedAtIndex0 = false;
		
		Node ptr = front;
		Node ptr2 = front;

		while(ptr != null) {
			if(item.equals(ptr)) {
				isRemoved = true;
				if(ptr.index == 0) {
					ptr2 = ptr.next;
					front = front.next;
					front.prev = null;
				}
				/*else if(ptr.index == size()-1) {
					isLast = true;
					rear = ptr.prev;
					rear.next = null;
				}*/
				else {
					ptr2 = ptr.next;
					(ptr.prev).next = ptr.next;
					(ptr.next).prev = ptr.prev;
				}
				break;
			}
			ptr = ptr.next;
		}
		
		while(isRemoved == true && ptr2 != null && isLast == false) {
			ptr2.index = ptr2.index - 1;
			ptr2 = ptr2.next;
		}
		
		nodeCount -= 1;
	}
	
	public Node removeAt(int index) {
		boolean isRemoved = false;
		Node ptr = front;
		Node ptr2 = front;
		Node ret = front;
		
		while(ptr != null) {
			if(ptr.index == index && index == 0) {
				isRemoved = true;
				front = front.next;
				front.prev = null;
				nodeCount = nodeCount - 1;
				break;
			}
			else if(ptr.index == index ) {
				isRemoved = true;
				if(ptr.index == nodeCount - 1 && ptr.next == null) {
					(ptr.prev).next = null;
					nodeCount = nodeCount - 1;
				}
				else if(ptr.next != null) {
					(ptr.prev).next = ptr.next;
					(ptr.next).prev = ptr.prev;
					nodeCount = nodeCount - 1;
				}
				ret = ptr;
				break;
			}
			ptr = ptr.next;
		}
		
		while(isRemoved == true && ptr != null) {
			ptr.index = ptr.index - 1;
			ptr = ptr.next;
		}
		
		ptr = front;
		while(ptr!= null) {
			if(ptr.next == null) {
				rear = ptr;
			}
			ptr = ptr.next;
		}
		
		return ret;
	}
	
	public void removeAll(Node item) {
		Node ptr = front;
		
		while(ptr != null) {
			if(ptr.equals(item) && ptr.prev == null) {
				front = ptr.next;
				front.prev = null;
			}
			else if(ptr.equals(item)) {
				(ptr.prev).next = ptr.next;
				(ptr.next).prev = ptr.prev;
			}
			ptr = ptr.next;
		}

		ptr = front;
		int indexCount = 0;
		while(ptr != null) {
			ptr.index = indexCount;
			ptr = ptr.next;
			indexCount+=1;
		}
		nodeCount = indexCount;
	}
	
	public void clear() {
		front = null;
		rear = null;
		nodeCount = 0;
	}
	
	public void setAt(Node item, int index) {
		Node ptr = front;
		while(ptr.index != index) {
			ptr = ptr.next;
		}
		
		ptr.setBase(item.getBase());
		ptr.setExp(item.getExp());;
	}
	
	public Node getAt(int index) {
		Node ptr = front;
		while(ptr.index != index) {
			ptr = ptr.next;
		}
		
		return ptr;
	}
	
	public int indexOf(Node item) {
		Node ptr = front;
		while(ptr != null) {
			if(item.equals(ptr)) {
				return ptr.index;
			}
			ptr = ptr.next;
		}
		
		return -1;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public boolean isEmpty() {
		if(nodeCount == 0) {
			return true;
		}
		return false;
	}
	
	public LinkedList joinLinkedLists(LinkedList ll) {
		LinkedList clone = new LinkedList();
		LinkedList addOn = ll;
		Node ptr = front;
		while(ptr != null) {
			clone.add(new Node(ptr.getBase(), ptr.getExp()));
			ptr = ptr.next;
		}
		
		clone.rear.next = addOn.front;
		addOn.front.prev = clone.rear;
		clone.rear = addOn.rear;
		
		ptr = clone.front;
		int indices = 0;
		while(ptr != null) {
			ptr.index = indices;
			indices+=1;
			ptr = ptr.next;
		}
		clone.nodeCount = clone.rear.index + 1;
		return clone;
	}
	
	public LinkedList copy() {
		LinkedList copy = new LinkedList();
		Node ptr = front;
		
		while(ptr != null) {
			copy.add(new Node(ptr.getBase(), ptr.getExp()));
			ptr = ptr.next;
		}
		return copy;
	}
	
	public String toString() {
		String out = new String();
		if(isEmpty() == true ) {
			return null;
		}
		else {
			Node ptr = front;
			while(ptr != null) {
				if(ptr.next == null) {
					out = out + ptr.toString();
				}
				else {
					System.out.print(ptr.toString() + " + ");
				}
				ptr=ptr.next;
			}
		}
		return out;
	}
	
	public String debug(){
		String output = "";
		Node ptr = front;
		while(ptr != null) {
			output = output + "|" + ptr.index + "|" + ptr.toString() + "| prev:" + ptr.prev + ";next:" + ptr.next + "|,";
			ptr = ptr.next;
		}
		return output;
	}
}