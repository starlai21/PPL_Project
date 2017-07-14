package scheme.value;

/**
 * The ValueType enumeration describes the various
 * types of Scheme values.
 */
public enum ValueType {
	/** A Scheme integer value. */
	INTEGER,
	
	/** A Scheme string value. */
	STRING,
	
	/** A Scheme boolean value. */
	BOOLEAN,
	
	/** A Scheme symbol. */
	SYMBOL,
	
	/** A Scheme cons cell (list node). */
	CONS,
	
	/** A Scheme empty list. */
	NIL,
	
	/** A Scheme function (evaluated lambda expression). */
	FUNCTION,
	
	/** A Scheme primitive function. */
	PRIMITIVE,
	
	/** Special type used to indicate, for a primitive function,
	 *  that an argument with any type is allowed for
	 *  a particular parameter of the primitive function.
	 */
	ANY;

	public boolean checkArgument(ValueType valueType) {
		if (this == ANY) {
			return true;
		} else {
			return valueType == this;
		}
	}

	public boolean isList() {
		return this == NIL || this == CONS;
	}
	
}