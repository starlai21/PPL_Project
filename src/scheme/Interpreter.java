package scheme;

import java.util.ArrayList;
import java.util.List;

import scheme.value.Cons;
import scheme.value.PrimitiveFunction;
import scheme.value.Value;
import scheme.value.ValueFactory;

/**
 * An Interpreter object evaluates the Abstract Syntax Trees (ASTs) of Scheme
 * expressions and definitions.
 */
public class Interpreter {
	private Environment globalEnv;

	/**
	 * Constructor.
	 */
	public Interpreter() {
		this.globalEnv = new Environment(null);

		// add builtin functions to the global environment
		BuiltinFunctions.addAll(globalEnv);
	}

	/**
	 * Interpret given Abstract Syntax Tree in the global environment.
	 * 
	 * @param ast
	 *            the Abstract Syntax Tree (AST) of a expression or definition.
	 * @return the Value which is the result of evaluating the AST
	 * @throws InterpreterException
	 *             if an error occurs
	 */
	public Value interpret(ASTNode ast) throws InterpreterException {
		return evaluate(ast, globalEnv);
	}

	/**
	 * Evaluate the AST of a Scheme expression or definition in the given
	 * Environment.
	 * 
	 * @param ast
	 *            the AST of a Scheme expression or definition
	 * @param env
	 *            an Environment binding variables to their Values
	 * @return the Value which is the result of evaluating the AST
	 * @throws InterpreterException
	 *             if an error occurs
	 */
	private Value evaluate(ASTNode ast, Environment env) throws InterpreterException {
		switch (ast.getASTNodeType()) {
		case LITERAL:
			return ValueFactory.javaToSchemeValue(ast.getValue());
		// TODO: evaluate other types of AST nodes
		case PROGRAM:
			return evaluateProgram(ast, env);
		case DEFINITION:
			Value v = evaluate(ast.getChild(0), env);
			env.bind((String) ast.getValue(), v);
			return null;
		case VAR_REF:
			return env.lookup((String) ast.getValue());
		case FUNCTION_APPLICATION:
			return evaluateFunctionApplication(ast, env);
		case QUOTE:
			return evaluateQuote(ast, env);

		default:
			throw new UnsupportedOperationException("evaluation of " + ast.getASTNodeType() + " not supported yet");
		}
	}

	private Value evaluateQuote(ASTNode ast, Environment env) throws InterpreterException {
		if (ast.getASTNodeType() == ASTNodeType.QUOTE) {
			if (ast.getNumChildren() == 0) {
				return ValueFactory.getNil();
			}
			Value v = null;
			ASTNode child = ast.getChild(0);
			if (!ast.getValue().equals("List")) {
				if (child.getASTNodeType() == ASTNodeType.LITERAL) {
					v = ValueFactory.javaToSchemeValue(child.getValue());
				} else {
					v = ValueFactory.getSymbol((String) child.getValue());
				}
			} else {
				ast.removeChild(0);
				v = new Cons(evaluateQuote(child, env), evaluateQuote(ast, env));
			}
			return v;
		} else {
			Value v = null;
			if (ast.getASTNodeType() == ASTNodeType.LITERAL) {
				v = ValueFactory.javaToSchemeValue(ast.getValue());
			} else {
				v = ValueFactory.getSymbol((String) ast.getValue());
			}
			return v;
		}
	}

	private Value evaluateProgram(ASTNode ast, Environment env) throws InterpreterException {
		int i;
		Value v = null;
		for (i = 0; i < ast.getNumChildren(); i++) {
			v = evaluate(ast.getChild(i), env);
		}
		return v;
	}

	private Value evaluateFunctionApplication(ASTNode ast, Environment env) throws InterpreterException {
		int i;
		PrimitiveFunction fn = (PrimitiveFunction) evaluate(ast.getChild(0), env);
		List<Value> args = new ArrayList<Value>();
		Value v = null;
		for (i = 1; i < ast.getNumChildren(); i++) {
			args.add(evaluate(ast.getChild(i), env));
		}

		v = fn.applyPrimitive(args);
		return v;
	}
}
