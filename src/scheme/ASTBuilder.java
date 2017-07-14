package scheme;

public class ASTBuilder {
	public ASTNode build(Symbol parseTree) {
		// TODO: return the AST for given parse tree

		switch (parseTree.getSymbolType()) {
		case S:
			return buildProgram(parseTree);
		case EXPRS:
			return buildEXPRS(parseTree);
		case EXPR:
			return buildEXPR(parseTree);
		case LIST:
			return buildLIST(parseTree);
		case PRIME:
		case IDENTIFIER:
			return buildIDENTIFIER((Token) parseTree);
		case INTEGER_LITERAL:
		case STRING_LITERAL:
			return buildLITERAL((Token) parseTree);

		default:
			throw new IllegalStateException(
					"conversion of " + parseTree.getSymbolType() + " parse nodes is not supported yet");
		}
	}

	private ASTNode buildProgram(Symbol parseTree) {
		ASTNode program = new ASTNode(ASTNodeType.PROGRAM);

		// Convert each top level item into an AST,
		// add them as children of the program AST node
		while (parseTree != null) {
			if (parseTree.getNumChildren() > 0)
				program.addChild(build(parseTree.getChild(0)));
			if (parseTree.getNumChildren() > 1) {
				parseTree = parseTree.getChild(1);
			} else {
				parseTree = null;
			}
		}

		return program;
	}

	private ASTNode buildEXPRS(Symbol s) {
		ASTNode n = null;
		if (s.getNumChildren() > 0)
			n = build(s.getChild(0));
		if (s.getNumChildren() > 1)
			build(s.getChild(1));
		return n;
	}

	private ASTNode buildEXPR(Symbol s) {
		return build(s.getChild(0));
	}

	private ASTNode buildQUOTE(Symbol s) {
		return null;
	}

	private ASTNode buildLIST(Symbol s) {
		Symbol ss = s.getChild(1);
		ASTNode n = build(ss);
		ASTNode node = null;
		if (n != null) {
			if (n.getValue().equals("define")) {
				node = new ASTNode(ASTNodeType.DEFINITION);
				node.setValue(build(ss.getChild(1)).getValue());
				node.addChild(build(ss.getChild(1).getChild(1)));
			} else if (n.getASTNodeType() == ASTNodeType.VAR_REF) {
				node = new ASTNode(ASTNodeType.FUNCTION_APPLICATION);
				node.addChild(n);
				ss = ss.getChild(1);
				while (ss.getNumChildren() > 0) {
					node.addChild(build(ss.getChild(0)));
					ss = ss.getChild(1);
				}
			} else {

			}
		} else {

		}
		return node;
	}

	private ASTNode buildIDENTIFIER(Token s) {
		ASTNode identifier = new ASTNode(ASTNodeType.VAR_REF);
		identifier.setValue(s.getLexeme());
		return identifier;
	}

	private ASTNode buildLITERAL(Token s) {
		ASTNode literal = new ASTNode(ASTNodeType.LITERAL);
		if (s.getSymbolType() == SymbolType.INTEGER_LITERAL) {
			literal.setValue(Integer.parseInt(s.getLexeme()));
		} else {
			literal.setValue(s.getLexeme());
		}
		return literal;
	}
}
