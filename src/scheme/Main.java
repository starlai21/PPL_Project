//package scheme;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
//
//public class Main {
//	public static void main(String[] args) throws IOException, LexicalAnalysisException {
//		try {
//			File file = new File("test.txt");
//			FileInputStream in=new FileInputStream (file); 
//			Reader reader = new InputStreamReader(in);
//			//Reader reader = new InputStreamReader(System.in);
//			LexicalAnalyzer lexer = new LexicalAnalyzerImpl(reader, new TokenRecognizerImpl());
////			while (lexer.peek() != null) {
////				System.out.println(lexer.next());
////			}
//			Parser parser = new Parser(lexer);
//			
//			Symbol program = parser.parse();
//			
//			TreePrinter tp = new TreePrinter();
//			tp.print(program);
//		} catch (Exception e) {
//			System.err.println("Error!");
//			e.printStackTrace();
//		}
//	}
//}
package scheme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import scheme.value.Value;

public class Main {
	private static final boolean PRINT_PARSE_TREE = Boolean.getBoolean("scheme.printparsetree");

	public static void main(String[] args) throws IOException, LexicalAnalysisException {
		try {
			File file = new File("test.txt");
			FileInputStream in = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			List<String> lines = new ArrayList<String>();
			String line = null;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			FileInputStream fin = new FileInputStream(file);
			Reader reader = new InputStreamReader(fin);
			LexicalAnalyzer lexer = new LexicalAnalyzerImpl(reader, new TokenRecognizerImpl());

			Parser parser = new Parser(lexer);

			Symbol program = parser.parse();

			ASTBuilder astBuilder = new ASTBuilder();
			ASTNode ast = astBuilder.build(program);

			Interpreter interpreter = new Interpreter();
			try {
				int i;
				for (i = 0; i < ast.getNumChildren(); i++) {
					System.out.println(lines.get(i));
					Value result = interpreter.interpret(ast.getChild(i));
					if (result != null)
						System.out.println("=> " + result.toString());
				}

			} catch (InterpreterException e) {
				System.out.println("Evaluation error: " + e.getMessage());

			}
		} catch (Exception e) {
			System.err.println("Error!");
			e.printStackTrace();
		}
	}
}
