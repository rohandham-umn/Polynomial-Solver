
public class Node {
	
	private int base;
	private int exp;
	
	public int index;
	public Node next;
	public Node prev;
	
	
	Node(int base, int exp){
		this.base = base;
		this.exp = exp;
		index = -1;
		next = null;
		prev = null;
	}
	
	public void setBase(int base) {
		this.base = base;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public int getBase() {
		return base;
	}
	
	public int getExp() {
		return exp;
	}
	
	public boolean equals(Node anotherNode) {
		return (this.base == anotherNode.base && this.exp == anotherNode.exp);
	}
	
	public String toString() {
		return base + "x^" + exp;
	}
}
