package scheme;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of TokenRecognizer.
 */
public class TokenRecognizerImpl implements TokenRecognizer {
	/**
	 * A TokenPattern object is a regular expression defining the possible lexemes
	 * that result in a particular type of token.
	 */
	static class TokenPattern {
		Pattern regex;
		SymbolType SymbolType;

		// constructor
		TokenPattern(Pattern regex, SymbolType SymbolType) {
			this.regex = regex;
			this.SymbolType = SymbolType;
		}
	}
	
	// Array of all TokenPatterns.
	// These are checked in order in order to recognize a
	// token at the beginning of a string of text.
	// Note that all regular expressions should be anchored
	// at the beginning of the string using the "^" metacharacter.
	// Also, each regular expression should end with the \b
	// construct, to force the token to end on a word boundary.
	// This ensures that "lambdafoo" is not recognized as
	// "lambda" followed by "foo".
	private static final TokenPattern TOKEN_PATTERNS[] = {
		// TODO: add additional TokenPatterns to recognize all Scheme token types

		new TokenPattern(Pattern.compile("^\\("), SymbolType.LPAREN),
		new TokenPattern(Pattern.compile("^\\)"), SymbolType.RPAREN),
		new TokenPattern(Pattern.compile("^[0-9]+\\b"), SymbolType.INTEGER_LITERAL),
		new TokenPattern(Pattern.compile("^\"(.*?)\""), SymbolType.STRING_LITERAL),
		new TokenPattern(Pattern.compile("^([a-zA-Z]|!|\\$|%|&|\\*|/|:|<|=|>|\\?|\\^|_|~|\\+|-)([a-zA-Z]|!|\\$|%|&|\\*|/|:|<|=|>|\\?|\\^|_|~|\\+|-|[0-9])*"), SymbolType.IDENTIFIER),
		new TokenPattern(Pattern.compile("'"), SymbolType.PRIME),
		new TokenPattern(Pattern.compile("^define\\b"), SymbolType.DEFINE_KEYWORD),
	};

	@Override
	public Token recognizeToken(String text, int lineNum) throws LexicalAnalysisException {
		for (TokenPattern pat : TOKEN_PATTERNS) {
			Matcher m = pat.regex.matcher(text);
			if (m.find()) {
				// match
				String lexeme = m.group();
			
				return new Token(pat.SymbolType, lexeme, lineNum);
			}
		}
		
		// the text was not matched by any TokenPattern
		throw new LexicalAnalysisException("Text " + text + " is not a legal token", lineNum);
	}
}
