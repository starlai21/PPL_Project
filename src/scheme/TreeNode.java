package scheme;

public interface TreeNode<E> {
	public int getNumChildren();
	
	public E getChild(int i);
	
	public void addChild(E child);
}