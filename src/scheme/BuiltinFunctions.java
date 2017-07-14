package scheme;

import java.util.List;

import scheme.value.Cons;
import scheme.value.PrimitiveFunction;
import scheme.value.Value;
import scheme.value.ValueFactory;
import scheme.value.ValueType;

/**
 * The BuiltinFunctions class defines all of the "built-in" primitive functions
 * (e.g., +, -, *, /, cons, car, cdr, etc.)
 */
public class BuiltinFunctions {
	/**
	 * Variant of PrimitiveFunction for functions with the type int x int ->
	 * int.
	 */
	private static abstract class BinaryIntegerFunction extends PrimitiveFunction {
		protected BinaryIntegerFunction(String name) {
			super(name, new ValueType[] { ValueType.INTEGER, ValueType.INTEGER });
		}

		public Value applyPrimitive(List<Value> argList) throws InterpreterException {
			// checkArgs(argList);
			Integer result = evaluate(argList);
			return ValueFactory.javaToSchemeValue(result);
		}

		protected abstract Integer evaluate(List<Value> argList);
	}

	/**
	 * Variant of PrimitiveFunction for functions with the type int x int ->
	 * boolean.
	 */
	private static abstract class IntegerComparisonFunction extends PrimitiveFunction {
		protected IntegerComparisonFunction(String name) {
			super(name, new ValueType[] { ValueType.INTEGER, ValueType.INTEGER });
		}

		public Value applyPrimitive(List<Value> argList) throws InterpreterException {
			checkArgs(argList);
			Boolean result = evaluate(argList.get(0).getInteger(), argList.get(1).getInteger());
			return ValueFactory.javaToSchemeValue(result);
		}

		protected abstract Boolean evaluate(Integer a, Integer b);
	}

	/**
	 * Variant of PrimitiveFunction for functions with the type Cons -> ANY.
	 */
	private static abstract class NonemptyListFunction extends PrimitiveFunction {
		protected NonemptyListFunction(String name) {
			super(name, new ValueType[] { ValueType.CONS });
		}

		@Override
		public Value applyPrimitive(List<Value> argList) throws InterpreterException {
			checkArgs(argList);
			return evaluate((Cons) argList.get(0));
		}

		protected abstract Value evaluate(Cons list);
	}

	private static PrimitiveFunction[] allBuiltins = { new BinaryIntegerFunction("+") {
		@Override
		public Integer evaluate(List<Value> argList) {
			return argList.stream().map(Value::getInteger).reduce(0, Math::addExact);
		}
	}, new BinaryIntegerFunction("-") {
		@Override
		public Integer evaluate(List<Value> argList) {
			Integer sum = 0;
			int count = 0;
			for (Value i : argList) {
				if (count == 0)
					sum = i.getInteger();
				else
					sum -= i.getInteger();
				count++;
			}
			return sum;
		}
	}, new BinaryIntegerFunction("*") {
		@Override
		public Integer evaluate(List<Value> argList) {
			return argList.stream().map(Value::getInteger).reduce(1, Math::multiplyExact);
		}
	}, new BinaryIntegerFunction("/") {
		@Override
		public Integer evaluate(List<Value> argList) {
			Integer sum = 0;
			int count = 0;
			for (Value i : argList) {
				if (count == 0)
					sum = i.getInteger();
				else
					sum /= i.getInteger();
				count++;
			}
			return sum;
		}
	},

			new IntegerComparisonFunction("=") {
				@Override
				public Boolean evaluate(Integer a, Integer b) {
					return a == b;
				}
			}, new IntegerComparisonFunction("<") {
				@Override
				public Boolean evaluate(Integer a, Integer b) {
					return a < b;
				}
			}, new IntegerComparisonFunction("<=") {
				@Override
				public Boolean evaluate(Integer a, Integer b) {
					return a <= b;
				}
			}, new IntegerComparisonFunction(">") {
				@Override
				public Boolean evaluate(Integer a, Integer b) {
					return a > b;
				}
			}, new IntegerComparisonFunction(">=") {
				@Override
				public Boolean evaluate(Integer a, Integer b) {
					return a >= b;
				}
			},

			new NonemptyListFunction("car") {
				@Override
				public Value evaluate(Cons list) {
					return list.getCar();
				}
			}, new NonemptyListFunction("cdr") {
				@Override
				public Value evaluate(Cons list) {
					return list.getCdr();
				}
			}, new PrimitiveFunction("cons", new ValueType[] { ValueType.ANY, ValueType.ANY }) {
				public Value applyPrimitive(List<Value> argList) throws InterpreterException {
					checkArgs(argList);
					return new Cons(argList.get(0), argList.get(1));
				}
			}, new PrimitiveFunction("list", new ValueType[] { ValueType.ANY, ValueType.ANY }) {
				public Value applyPrimitive(List<Value> argList) throws InterpreterException {
					// checkArgs(argList);
					if (argList.isEmpty())
						return ValueFactory.getNil();
					else {
						Value child = argList.get(0);
						argList.remove(0);
						return new Cons(child, applyPrimitive(argList));
					}
				}
			}, new PrimitiveFunction("null?", new ValueType[] { ValueType.ANY }) {
				@Override
				public Value applyPrimitive(List<Value> argList) throws InterpreterException {
					checkArgs(argList);
					return ValueFactory.javaToSchemeValue(argList.get(0).getValueType() == ValueType.NIL);
				}
			} };

	/**
	 * Add all built-in primitive functions to given Environment.
	 * 
	 * @param env
	 *            an Environment
	 */
	public static void addAll(Environment env) {
		for (PrimitiveFunction f : allBuiltins) {
			env.bind(f.getPrimitiveFunctionName(), f);
		}
	}

}