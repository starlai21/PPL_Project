package scheme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
	public static void main(String[] args) throws IOException, LexicalAnalysisException {
		try {
			File file = new File("/Users/jj/Downloads/YCP_Scheme-1/src/scheme/test.txt");
			FileInputStream in=new FileInputStream (file); 
			Reader reader = new InputStreamReader(in);
			//Reader reader = new InputStreamReader(System.in);
			LexicalAnalyzer lexer = new LexicalAnalyzerImpl(reader, new TokenRecognizerImpl());
//			while (lexer.peek() != null) {
//				System.out.println(lexer.next());
//			}
			Parser parser = new Parser(lexer);
			
			Symbol program = parser.parse();
			
			TreePrinter tp = new TreePrinter();
			tp.print(program);
		} catch (Exception e) {
			System.err.println("Error!");
			e.printStackTrace();
		}
	}
}
