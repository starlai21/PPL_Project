package scheme;
import java.util.ArrayList;
import java.util.List;

public class ASTNode implements TreeNode<ASTNode> {
	private ASTNodeType type;
	private List<ASTNode> childList;
	private Object value; // if a literal, or a node which has an associated identifier
	
	public ASTNode(ASTNodeType type) {
		this(type, null);
	}
	
	public ASTNode(ASTNodeType type, Object value) {
		this.type = type;
		this.childList = new ArrayList<ASTNode>();
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public ASTNodeType getASTNodeType() {
		return type;
	}
	
	public int getNumChildren() {
		return childList.size();
	}
	
	public ASTNode getChild(int index) {
		return childList.get(index);
	}
	public void removeChild(int index){
		childList.remove(index);
	}
	
	public void addChild(ASTNode child) {
		childList.add(child);
	}
	
	public String getIdentifier() {
		if (!type.hasIdentifier()) {
			throw new IllegalStateException(type + " ast nodes do not have identifiers");
		}
		return (String) value;
	}
	
	public String getStringValue() {
		return (String) value;
	}
	
	public Integer getIntegerValue() {
		return (Integer) value;
	}
	
	public Boolean getBooleanValue() {
		return (Boolean) value;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(type.toString());
		if (value != null) {
			buf.append("(");
			if (value instanceof String) {
				buf.append("\"");
				buf.append(value.toString());
				buf.append("\"");
			} else {
				buf.append(value.toString());
			}
			buf.append(")");
		}
		
		return buf.toString();
	}
}