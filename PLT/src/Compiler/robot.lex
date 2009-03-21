package Robot;

import java_cup.runtime.Symbol;

/* anything is defined as any string not beginning with whitespace 
	We to define it this way coz otherwise leading whitespace shows up as part of the string
	which is probably not what the user intended */

%%
%cup
%line
%char
%column
%ignorecase
%state COMMENTS, SAYSTATE

anything=[^ \t\r\f\n].+
whitespace=[ \t\r\f]
identifier=[a-z][a-z0-9_]*

%%

<YYINITIAL>	"think" 	{	if(parser.bDebugFlag) {
								System.out.println("matched think"); 
							}
							return new Symbol(sym.THINK);
						}

<YYINITIAL>	"end"		{	if(parser.bDebugFlag) {
								System.out.println("matched end");
							}
							return new Symbol(sym.END); 
						}

<YYINITIAL>	"say"		{	if(parser.bDebugFlag) {
								System.out.println("matched say");
							}
							yybegin(SAYSTATE);
							return new Symbol(sym.SAY); 
						}

<YYINITIAL>	\n			{	if(parser.bDebugFlag) {
								System.out.println("matched newline");
							}
							return new Symbol(sym.NEWLINE); 
						}

<YYINITIAL>	"="			{	if(parser.bDebugFlag) {
								System.out.println("matched " + yytext());
							}
							return new Symbol(sym.EQUALS);
						}

<YYINITIAL>	"is"		{	if(parser.bDebugFlag) {
								System.out.println("matched " + yytext());
							}
							return new Symbol(sym.IS);
						}

<YYINITIAL> {identifier}	{	if(parser.bDebugFlag) {
									System.out.println("matched " + yytext());
								}
								return new Symbol(sym.FUNCTION_NAME, new String(yytext()));
							}

<YYINITIAL> {identifier}\#	{	if(parser.bDebugFlag) {
									System.out.println("matched " + yytext());
								}
								return new Symbol(sym.NUMBER_NAME, new String(yytext()));
							}

<YYINITIAL> {identifier}\?	{	if(parser.bDebugFlag) {
									System.out.println("matched " + yytext());
								}
								return new Symbol(sym.BOOLEAN_NAME, new String(yytext()));
							}

<YYINITIAL> {identifier}\%	{	if(parser.bDebugFlag) {
									System.out.println("matched " + yytext());
								}
								return new Symbol(sym.PERCENTAGE_NAME, new String(yytext()));
							}

<YYINITIAL> {identifier}\@	{	if(parser.bDebugFlag) {
									System.out.println("matched " + yytext());
								}
								return new Symbol(sym.LOCATION_NAME, new String(yytext()));
							}

<YYINITIAL> {identifier}\!	{	if(parser.bDebugFlag) {
									System.out.println("matched " + yytext());
								}
								return new Symbol(sym.ENEMY_NAME, new String(yytext()));
							}

<YYINITIAL> {identifier}\$	{	if(parser.bDebugFlag) {
									System.out.println("matched " + yytext());
								}
								return new Symbol(sym.RESOURCE_NAME, new String(yytext()));
							}

<YYINITIAL> {identifier}\.\.\.	{	if(parser.bDebugFlag) {
										System.out.println("matched " + yytext());
									}
									return new Symbol(sym.LIST_NAME, new String(yytext()));
								}

<SAYSTATE>	{whitespace}	{	if(parser.bDebugFlag) {
									System.out.println("matched whitespace");
								} 
								/* ignore white space. */ 
							}

<SAYSTATE>	{anything}		{	if(parser.bDebugFlag) {
									System.out.println("matched saystate anything");
								}
								yybegin(YYINITIAL); 
								return new Symbol(sym.ANYTHING, new String(yytext())); 
							}

<SAYSTATE>	\n				{	if(parser.bDebugFlag) {
									System.out.println("matched saystate newline");
								}
								return new Symbol(sym.NEWLINE); 
							}

<YYINITIAL>	";" 	{ return new Symbol(sym.SEMI); }
<YYINITIAL>	"+" 	{ return new Symbol(sym.PLUS); }
<YYINITIAL>	"*" 	{ return new Symbol(sym.TIMES); }
<YYINITIAL>	"(" 	{ return new Symbol(sym.LPAREN); }
<YYINITIAL>	")" 	{ return new Symbol(sym.RPAREN); }
<YYINITIAL>	[0-9]+ 	{ return new Symbol(sym.NUMBER, new Integer(yytext())); }

<YYINITIAL>	{whitespace}	{	if(parser.bDebugFlag) {
								System.out.println("matched whitespace");
								}
								/* ignore white space. */ 
							}

.	{	System.err.println("Illegal character: " + yytext() + "\nLine number: " + yyline + "\nColumn number: " + yycolumn);
		System.exit(0); 
	}
