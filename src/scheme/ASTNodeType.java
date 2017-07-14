package scheme;

public enum ASTNodeType {
	/** AST node representing the overall program */
	PROGRAM,
	
	/** AST node representing a variable reference (an expression which is an identifier) */
	VAR_REF,
	
	/** AST node representing a literal integer, string, or boolean value */
	LITERAL,
	
	/** AST node representing a function (lambda expression) */
	//FUNCTION,
	QUOTE,
	
	/** AST node representing a formal parameter to a function */
	FORMAL_PARAM,
	
	/** AST node representing a function application expression */
	FUNCTION_APPLICATION,
	
	/** AST node representing a definition */
	DEFINITION,
	
	/** AST node representing an if expression */
	//IF,
	
	/** AST node representing a let expression */
	LET,
	
	/** AST node representing a let pair */
	LET_PAIR;
	
	/** AST node representing an and expression */
	//AND,

	/** AST node representing an or expression */
	//OR,
	
	/** AST node representing a not expression */
	//NOT;
	
	/**
	 * @return true if this type of ASTNode should contain an
	 *         identifier as a value
	 */
	public boolean hasIdentifier() {
		return this == VAR_REF || this == DEFINITION || this == LET_PAIR || this == FORMAL_PARAM; 
	}
}