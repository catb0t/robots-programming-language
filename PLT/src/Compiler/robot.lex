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
%state COMMENTS, SAYSTATE, THINKSTATE

anything=[^ \t\r\f\n].+
whitespace=[ \t\r\f]

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
