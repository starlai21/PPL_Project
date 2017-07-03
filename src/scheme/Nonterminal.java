package scheme;

import java.util.ArrayList;
import java.util.List;

/**
 * A Nonterminal object represents a nonterminal symbol in the parse tree.
 */
public class Nonterminal implements Symbol {
	private SymbolType symbolType;
	private List<Symbol> childList;
	
	public Nonterminal(SymbolType symbolType) {
		if (symbolType.isTerminal()) {
			throw new IllegalArgumentException(
					"Cannot create a Nonterminal object using SymbolType " + symbolType);
		}
		
		this.symbolType = symbolType;
		this.childList = new ArrayList<Symbol>();
	}
	
	public void addChild(Symbol child) {
		childList.add(child);
	}

	@Override
	public Symbol getChild(int index) {
		return childList.get(index);
	}

	@Override
	public int getNumChildren() {
		return childList.size();
	}

	@Override
	public SymbolType getSymbolType() {
		return symbolType;
	}

}
