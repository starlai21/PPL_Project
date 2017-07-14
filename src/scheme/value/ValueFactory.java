package scheme.value;

import java.util.HashMap;
import java.util.Map;

/**
 * The ValueFactory class contains static methods which
 * are useful for creating and manipulating Values.
 */
public class ValueFactory {

	private static Value trueValue = new Literal(Boolean.valueOf(true));
	private static Value falseValue = new Literal(Boolean.valueOf(false));
	
	/**
	 * Convert a Java Integer, Boolean, or String value
	 * to a Literal object representing a Scheme value.
	 * 
	 * @param jvalue a Java Integer, Boolean, or String value
	 * @return a Literal object representing the same value as a Scheme value
	 */
	public static Value javaToSchemeValue(Object jvalue) {
		if (jvalue instanceof Boolean) {
			return ((Boolean)jvalue) ? trueValue : falseValue;
		} else {
			return new Literal(jvalue);
		}
	}

	/**
	 * Convert a Scheme value to a String.
	 * If the value is a list, the string will contain
	 * a representation of all of the
	 * members of the list.
	 * 
	 * @param sexp a Scheme value
	 * @return a String representing the Scheme value
	 */
	public static String toString(Value sexp) {
		StringBuilder buf = new StringBuilder();
		
		ValueFactory.formatSExp(buf, sexp);
		
		return buf.toString();
	}

	private static void formatSExp(StringBuilder buf, Value sexp) {
		if (!sexp.getValueType().isList()) {
			buf.append(sexp.toString());
		} else {
			buf.append("(");
			
			while (sexp.getValueType() != ValueType.NIL) {
				Cons cons = (Cons) sexp;
				formatSExp(buf, cons.getCar());
				
				sexp = cons.getCdr();
				
				if (!sexp.getValueType().isList()) {
					// improper list
					buf.append(" . ");
					formatSExp(buf, sexp);
					break;
				} else if (sexp.getValueType() != ValueType.NIL) {
					// list continues with another member
					buf.append(" ");
				}
			}
			
			buf.append(")");
		}
	}

	// Map to intern symbol values so that each symbol
	// is represented by a unique object.
	private static Map<String, Value> symbolMap = new HashMap<String, Value>();

	/**
	 * Get a **Value** object to represent the Scheme symbol
	 * with the given name.
	 * 
	 * @param name the name of a symbol
	 * @return a **Value** representing that symbol
	 */
	public static Value getSymbol(String name) {
		Value v = symbolMap.get(name);
		if (v == null) {
			v = new SymbolValue(name);
			symbolMap.put(name, v);
		}
		return v;
	}

	// singleton instance of the Nil class
	private static Nil value = new Nil();

	/**
	 * @return the single instance of the Nil class
	 */
	public static Nil getNil() { return value; }

}