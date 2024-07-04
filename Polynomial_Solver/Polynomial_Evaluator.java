import java.util.Scanner;

public class Polynomial_Evaluator {
	// 1 9 1 8 1 10 1 1 1 3
	// 9 8 2 2 7 3 8 7 1 9
	// 3 10 2 7 9 12 1 13 4 19 3 3 1 1 3 10 8 12 5 19 2 10
	// 3 1 2 2 1 1 2 3 3 1 2 0

	public static void main(String args[]) {
		
		Scanner input = new Scanner(System.in);
		int choice = -1;
		LinkedList poly1 = new LinkedList();
		LinkedList poly2 = new LinkedList();
		LinkedList polyR = new LinkedList();
		
		while(choice != 0) {
			System.out.println("POLYNOMIAL EVALUATOR");
			divider();
			System.out.print("Polynomial 1: \t");
			System.out.println(poly1.toString());
			//System.out.println("\t" + poly1.debug());
			System.out.print("Polynomial 2: \t");
			System.out.println(poly2.toString());
			//System.out.println("\t" + poly2.debug());
			System.out.print("Result: \t");
			System.out.println(polyR.toString());
			//System.out.println("\t" + polyR.debug());
			divider();
			System.out.println("(1) \t Enter First Polynomial");
			System.out.println("(2) \t Enter Second Polynomial");
			System.out.println("(3) \t Re-order Polynomials terms into Ascending Degrees");
			System.out.println("(4) \t Re-order Polynomials terms into Descending Degrees");
			System.out.println("(5) \t Combine Like Terms");
			System.out.println("(6) \t Add Polynomials together");
			System.out.println("(7) \t Multiply Polynomials together");
			System.out.println("(8) \t Add Both Polynomials together (condense)");
			System.out.println("(9) \t Multiply Polynomials together (condense)");
			System.out.println("(10) \t Evaluate");
			System.out.println("(0) \t Exit");
			divider();
			System.out.println("Enter Option: ");
			
			choice = input.nextInt();
			
			if(choice == 1) {
				input.nextLine();
				cls();
				int base = 0;
				int exp = 0;
				poly1.clear();
				
				System.out.println("Format: base1 exp1 base2 exp2\nEnter 1st Polynomial:");

				
				String polyInput = input.nextLine();
				Scanner polyScanner = new Scanner(polyInput);
				
				while(polyScanner.hasNext()) {
					try {
						base = polyScanner.nextInt();
						exp = polyScanner.nextInt();
						poly1.add(new Node(base,exp));
					}catch(java.util.InputMismatchException e){
						System.out.println("Numerical input only");
						break;
					}
				}
				polyScanner.close();
				cls();
			}
			else if(choice == 2) {
				input.nextLine();
				cls();
				int base = 0;
				int exp = 0;
				poly2.clear();
				
				System.out.println("Format: base1 exp1 base2 exp2\nEnter 2nd Polynomial:");

				
				String polyInput = input.nextLine();
				Scanner polyScanner = new Scanner(polyInput);
				
				while(polyScanner.hasNext()) {
					try {
						base = polyScanner.nextInt();
						exp = polyScanner.nextInt();
						poly2.add(new Node(base,exp));
					}catch(java.util.InputMismatchException e){
						System.out.println("Numerical input only");
						break;
					}
				}
				polyScanner.close();
				cls();
			}
			else if(choice == 3) {
				cls();
				ascend(poly1);
				ascend(poly2);
				ascend(polyR);
			}
			else if(choice == 4) {
				cls();
				descend(poly1);
				descend(poly2);
				descend(polyR);
			}
			else if(choice == 5) {
				cls();
				condense(poly1);
				condense(poly2);
				condense(polyR);
			}
			else if(choice == 6) {
				cls();
				polyR.clear();
				LinkedList temp1 = poly1.copy();
				LinkedList temp2 = poly2.copy();
				temp1 = temp1.joinLinkedLists(temp2);
				polyR = temp1;
			}
			else if(choice == 7) {
				cls();
				Node terms1;
				Node terms2;
				
				if(poly2.size()>=poly1.size()) {
					terms1 = poly1.front;
					terms2 = poly2.front;
				}
				else {
					terms2 = poly1.front;
					terms1 = poly2.front;
				}
				
				LinkedList multiply = new LinkedList();
				while(terms1 != null) {
					while(terms2 != null) {
						multiply.add(new Node(terms1.getBase()*terms2.getBase(), terms1.getExp()+terms2.getExp()));
						terms2 = terms2.next;
					}
					terms1 = terms1.next;
				}
				
				polyR = multiply;
			}
			else if(choice == 8) {
				cls();
				polyR.clear();
				LinkedList temp1 = poly1.copy();
				LinkedList temp2 = poly2.copy();
				temp1 = temp1.joinLinkedLists(temp2);
				condense(temp1);
				polyR = temp1;
			}
			else if(choice == 9) {
				cls();
				Node terms1;
				Node terms2;
				
				if(poly2.size()>=poly1.size()) {
					terms1 = poly1.front;
					terms2 = poly2.front;
				}
				else {
					terms2 = poly1.front;
					terms1 = poly2.front;
				}
				
				LinkedList multiply = new LinkedList();
				while(terms1 != null) {
					while(terms2 != null) {
						multiply.add(new Node(terms1.getBase()*terms2.getBase(), terms1.getExp()+terms2.getExp()));
						terms2 = terms2.next;
					}
					terms1 = terms1.next;
				}
				
				condense(multiply);
				polyR = multiply;
			}
			else if(choice == 10) {
				input.nextLine();
				cls();
				
				System.out.println("x = ");
				int num = input.nextInt();
				
				Node ptr1 = poly1.front;
				Node ptr2 = poly2.front;
				Node ptrR = polyR.front;
				
				int ans1 = 0;
				int ans2 = 0;
				int ansR = 0;
				
				while(ptr1 != null) {
					ans1 = (int) (ans1 + ptr1.getBase()*(Math.pow(num, ptr1.getExp())));
					ptr1 = ptr1.next;
				}
				while(ptr2 != null) {
					ans2 = (int) (ans2 + ptr2.getBase()*(Math.pow(num, ptr2.getExp())));
					ptr2 = ptr2.next;
				}
				while(ptrR != null) {
					ansR = (int) (ansR + ptrR.getBase()*(Math.pow(num, ptrR.getExp())));
					ptrR = ptrR.next;
				}
				
				cls();
				System.out.println("Polynomial 1: " + ans1);
				System.out.println("Polynomial 2: " + ans2);
				System.out.println("Result: " + ansR);
				input.next();
				cls();
			}
		}
	}
	
	private static void ascend(LinkedList ll) {
		for(int i = 0; i < ll.size() - 1; i++) {
			Node ptr1 = ll.getAt(i);
			Node ptr2 = ptr1.next;
			
			
			int lowest = ptr1.index;
			boolean lowestDetect = false;
			while(ptr2 != null) {
				if(ptr2.getExp() <= ll.getAt(lowest).getExp()) {
					lowest = ptr2.index;
					lowestDetect = true;
				}
				ptr2 = ptr2.next;
			}
			
			if(lowestDetect == true && ll.size() != 2) {
				Node swap1 = ll.removeAt(lowest);
				Node swap2 = ll.removeAt(i);
				ll.insertAt(swap1, i);
				ll.insertAt(swap2, lowest);
			}
			else if(lowestDetect == true) {
				ll.insertAt(ll.removeAt(lowest) , 0);
			}
		}
	}
	
	private static void descend(LinkedList ll) {
		for(int i = 0; i < ll.size() - 1; i++) {
			Node ptr1 = ll.getAt(i);
			Node ptr2 = ptr1.next;
			
			
			int highest = ptr1.index;
			boolean highestDetect = false;
			while(ptr2 != null) {
				if(ptr2.getExp() >= ll.getAt(highest).getExp()) {
					highest = ptr2.index;
					highestDetect = true;
				}
				ptr2 = ptr2.next;
			}
			
			if(highestDetect == true && ll.size() != 2) {
				Node swap1 = ll.removeAt(highest);
				Node swap2 = ll.removeAt(i);
				ll.insertAt(swap1, i);
				ll.insertAt(swap2, highest);
			}
			else if(highestDetect == true) {
				ll.insertAt(ll.removeAt(highest) , 0);
			}
		}
	}
	
	private static void condense(LinkedList ll) {
		descend(ll);
		Node ptr = ll.front;
		while(ptr.next != null && ptr.next != null) {
			while(ptr.next.getExp() == ptr.getExp() && ptr.next != null) {
				ptr.setBase(ptr.getBase() + ptr.next.getBase());
				ll.remove(ptr.next);
			}
			ptr = ptr.next;
		}
	}
	
	private static void cls() {
		for(int i = 0; i < 20; i++) 
			System.out.println();
	}
	
	private static void divider() {
		for(int i = 0; i < 80; i++)
			System.out.print("-");
		System.out.println();
	}
}
