package Robot;

import java_cup.runtime.Symbol;

/*
   anything is defined as any string not beginning with whitespace 
   We to define it this way coz otherwise leading whitespace shows up as part of the string
   which is probably not what the user intended 
   
   [0-9]+    { return new Symbol(sym.NUMBER, new Integer(yytext())); }
*/

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
digits=[0-9]+
number={digits}(\.{digits})?(E[+-]?{digits})?
max_hash=max\#
min_hash=min\#

%%

<YYINITIAL>   {

   "think"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched think"); 
                  }
                  return new Symbol(sym.THINK);
         }

   "end"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched end");
                  }
                  return new Symbol(sym.END);
         }

   "say"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched say");
                  }
                  yybegin(SAYSTATE);
                  return new Symbol(sym.SAY);
         }
   instruction
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.INSTRUCTION); 
         }

   with
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.WITH); 
         }

   means
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.MEANS); 
         }

   gives
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.GIVES); 
         }

   nothing
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.NOTHING); 
         }

   \n   
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched newline");
                  }
                  return new Symbol(sym.NEWLINE); }

   "="
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.EQUALS);
         }

   "is"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.IS); }

   {identifier}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.FUNCTION_NAME, new String(yytext()));
         }

   {identifier}\#
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.NUMBER_NAME, new String(yytext()));
         }

   {identifier}\?
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.BOOLEAN_NAME, new String(yytext()));
         }

   {identifier}\%
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.PERCENTAGE_NAME, new String(yytext()));
         }

   {identifier}\@
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.LOCATION_NAME, new String(yytext()));
         }

   {identifier}\!
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.ENEMY_NAME, new String(yytext()));
         }

   {identifier}\$
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.RESOURCE_NAME, new String(yytext()));
         }

   {identifier}\.\.\.
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.LIST_NAME, new String(yytext()));
         }

   true
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.TRUE);
         }

   false
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.FALSE);
         }

   {max_hash}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.MAX_HASH);
         }

   {min_hash}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.MIN_HASH);
         }

   "//"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched comments " + yytext());
                  }
                  yybegin(COMMENTS);
                  /* ignore comments */
         }

   {number}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched number " + yytext());
                  }
                  return new Symbol(sym.NUMBER_EXPRESSION, new Double(yytext()));
         }

   ";"   { return new Symbol(sym.SEMI);   }
   "+"   { return new Symbol(sym.PLUS);   }
   "-"   { return new Symbol(sym.MINUS);  }
   "*"   { return new Symbol(sym.TIMES);  }
   "/"   { return new Symbol(sym.DIVIDE); }
   "("   { return new Symbol(sym.LPAREN); }
   ")"   { return new Symbol(sym.RPAREN); }
   
   {whitespace}
         {
            if (parser.bDebugFlag) {
               System.out.println("matched whitespace");
            }
            /* ignore white space. */
         }
}

<SAYSTATE>   {

   {whitespace}
         {
            if (parser.bDebugFlag) {
               System.out.println("matched whitespace");
            }
            /* ignore white space. */
         }

   {anything}
         {
            if (parser.bDebugFlag) {
               System.out.println("matched saystate anything");
            }
            yybegin(YYINITIAL);
            return new Symbol(sym.ANYTHING, new String(yytext()));
         }
}

<COMMENTS>   {
   .+
         {
            if (parser.bDebugFlag) {
               System.out.println("matched comments " + yytext());
            }
            /* ignore comments */ 
            yybegin(YYINITIAL);
         }
}

.   {
      System.err.println("Illegal character: " + yytext() + "\nLine number: " + yyline + "\nColumn number: " + yycolumn);
      System.exit(0);
}
