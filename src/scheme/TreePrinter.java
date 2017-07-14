//package scheme;
//
//import java.util.Stack;
//
//public class TreePrinter {
//	private static class Item {
//		private Symbol node;
//		private int remainingChildren;
//		private boolean visited;
//		public Item(Symbol node) {
//			this.node = node;
//			if (node instanceof Nonterminal) {
//				this.remainingChildren = ((Nonterminal) node).getNumChildren();
//			}
//			this.visited = false;
//		}
//		
//		public boolean isVisited() {
//			return visited;
//		}
//		
//		public void setVisited(boolean visited) {
//			this.visited = visited;
//		}
//		
//		public Symbol getNode() {
//			return node;
//		}
//		
//		public int getRemainingChildren() {
//			return remainingChildren;
//		}
//
//		public Symbol nextChild() {
//			if (remainingChildren <= 0) {
//				throw new IllegalStateException();
//			}
//			Nonterminal nt = (Nonterminal) node;
//			Symbol child = nt.getChild(nt.getNumChildren() - remainingChildren);
//			--remainingChildren;
//			return child;
//		}
//	}
//	
//	private Stack<Item> stack;
//	private int indent;
//	public TreePrinter() {
//		stack = new Stack<Item>();
//	}
//	
//	public void print(Symbol node) {
//		indent = 0;
//		stack.clear();
//		stack.push(new Item(node));
//		
//		while (!stack.isEmpty()) {
//			Item item = stack.pop();
//			if (!item.isVisited()) {
//				printNode(item.getNode());
//				item.setVisited(true);
//			}
//			if (item.getRemainingChildren() > 0) {
//				stack.push(item);
//				stack.push(new Item(item.nextChild()));
//			}
//		}
//	}
//	private void printSpace(){
//		for (int i = 0 ;i<indent;i++){
//			System.out.print("  ");
//		}
//	}
//	private void printNode(Symbol node) {
//
//		if (node instanceof Token) {
//			Token tpn = (Token) node;
//			if (tpn.getSymbolType() == SymbolType.LPAREN){
//				printSpace();
//				System.out.println("(");
//				indent++;
//			}
//			else if (tpn.getSymbolType() == SymbolType.RPAREN){
//				indent--;
//				printSpace();
//				System.out.println(")");
//			}
//			else{
//				printSpace();
//				System.out.println(tpn.getSymbolType()+": ["+ tpn.getLexeme() + "]");
//			}
//			
//		}
//	}
//}
package scheme;

import java.util.Stack;

public class TreePrinter<E extends TreeNode<E>> {
	private static class Item<E extends TreeNode<E>> {
		private E node;
		private int remainingChildren;
		private boolean visited;
		
		public Item(E node) {
			this.node = node;
			this.remainingChildren = node.getNumChildren();
			this.visited = false;
		}
		
		public boolean isVisited() {
			return visited;
		}
		
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		public TreeNode<E> getNode() {
			return node;
		}
		
		public int getRemainingChildren() {
			return remainingChildren;
		}

		public E nextChild() {
			if (remainingChildren <= 0) {
				throw new IllegalStateException();
			}

			E child = node.getChild(node.getNumChildren() - remainingChildren);
			
			--remainingChildren;
			return child;
		}
	}
	
	private Stack<Item<E>> stack;
	
	public TreePrinter() {
		stack = new Stack<Item<E>>();
	}
	
	public void print(E node) {
		stack.push(new Item<E>(node));
		
		while (!stack.isEmpty()) {
			Item<E> item = stack.pop();
			if (!item.isVisited()) {
				printNode(item.getNode());
				item.setVisited(true);
			}
			if (item.getRemainingChildren() > 0) {
				stack.push(item);
				stack.push(new Item<E>(item.nextChild()));
			}
		}
	}

	private void printNode(TreeNode<E> node) {
		for (int i = 0; i < stack.size(); i++) {
			boolean parentLevel = (i == stack.size() - 1);
			if (parentLevel) {
				System.out.print("+--");
			} else {
				System.out.print(stack.get(i).getRemainingChildren() > 0 ? "|  " : "   ");
			}
		}
		
		System.out.print(node.toString());
		System.out.println();
	}
}
