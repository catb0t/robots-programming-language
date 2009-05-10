package app;

import java_cup.runtime.Symbol;

%%
%cup
%line
%char
%column
%ignorecase
%pack
%state COMMENTS, SAYSTATE, SAY_EXPR

%{
    // for error handling : called by parser
	public int getLineNumber() {
		return yyline;
	}
	
	public int getColumnNumber() {
	    return yycolumn;
	}
	
	public String lastToken() {
	    return yytext();
	}
	
	public void lexerError(String errMsg) {
	    System.err.println("*** Lexical error ***");
	    System.err.println(errMsg + "\nLine#: " + (yyline + 1) + "\nColumn#: " + (yycolumn + 1));
        System.exit(0);
	}

%}

whitespace=[ \t\r\f]
block={whitespace}*(\||\+---)
identifier=[a-zA-Z][a-zA-Z0-9_]*
id_suffix=[!@#$%?]
list_suffix=\.\.\.
digits=[0-9]+
number={digits}(\.{digits})?(E[+-]?{digits})?
percentage={number}%
max_hash=max\#
min_hash=min\#

%%

<YYINITIAL, SAY_EXPR>   {

   ^{block}+
         {
            if (parser.bDebugFlag) {
               System.out.println("matched block");
            }
            /* ignore block stuff */
         }

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

   sort
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched sort");
                  }
                  return new Symbol(sym.SORT);
         }

/*   "move_to"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched move_to");
                  }
                  return new Symbol(sym.MOVE_TO);
         }
*/ instruction
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
                  return new Symbol(sym.VARIABLE, new RobotVar("NULL")); 
         }

   repeat
        {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.REPEAT); 
        }

   times
        {
                  if (parser.bDebugFlag) {
                     System.out.println("matched times_repeat: " + yytext());
                  }
                  return new Symbol(sym.TIMES_REPEAT); 
        }

   each
        {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.EACH); 
        }
   
   in
        {
                  if (parser.bDebugFlag) {
                     System.out.println("matched " + yytext());
                  }
                  return new Symbol(sym.IN); 
        }

   rollover
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched rollover" + yytext());
                  }
                  return new Symbol(sym.ROLLOVER); 
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
                     System.out.println("matched equals: " + yytext());
                  }
                  return new Symbol(sym.EQUALS);
         }

   "is"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched is: " + yytext());
                  }
                  return new Symbol(sym.IS); }
   "=="|is_equal_to
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched ==: " + yytext());
                  }
                  return new Symbol(sym.IS_EQUAL_TO);
         }

   ">="|is_greater_than
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched >=: " + yytext());
                  }
                  return new Symbol(sym.IS_GREATER_THAN);
         }

   "<="|is_lower_than
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched <=: " + yytext());
                  }
                  return new Symbol(sym.IS_LOWER_THAN);
         }

   "!="|is_different_from
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched !=: " + yytext());
                  }
                  return new Symbol(sym.IS_DIFFERENT_FROM);
         }

   ">"|is_strictly_greater_than
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched >: " + yytext());
                  }
                  return new Symbol(sym.IS_STRICTLY_GREATER_THAN);
         }

   "<"|is_strictly_lower_than
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched <: " + yytext());
                  }
                  return new Symbol(sym.IS_STRICTLY_LOWER_THAN);
         }

    of
	  {
	          if (parser.bDebugFlag) {
                     System.out.println("matched of: " + yytext());
                  }
                  return new Symbol(sym.OF);
	  }
    self
	  {
	          if (parser.bDebugFlag) {
                     System.out.println("matched self: " + yytext());
                  }
                  return new Symbol(sym.SELF);
	  }


   true
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched true1");
                  }
                  return new Symbol(sym.TRUE);
         }

   false
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched false1");
                  }
                  return new Symbol(sym.FALSE);
         }

   if
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched if: " + yytext());
                  }
                  return new Symbol(sym.IF);
         }
         
   then
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched then: " + yytext());
                  }
                  return new Symbol(sym.THEN);
         }

   else
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched else: " + yytext());
                  }
                  return new Symbol(sym.ELSE);
         }         

   done
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched done: " + yytext());
                  }
                  return new Symbol(sym.DONE);
         }

   while
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched while: " + yytext());
                  }
                  return new Symbol(sym.WHILE);
         }

   {max_hash}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched max_hash: " + yytext());
                  }
                  return new Symbol(sym.MAX_HASH);
         }

   {min_hash}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched min_hash: " + yytext());
                  }
                  return new Symbol(sym.MIN_HASH);
         }
         
   nothing[\!\$]
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched nothing[!$]: " + yytext());
                  }
                  if(yytext().endsWith("!")) {
                  	 return new Symbol(sym.NOTHING_NOT);
                  }
                  return new Symbol(sym.NOTHING_RES);
         }

   nowhere\@
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched nowhere@: " + yytext());
                  }
                  return new Symbol(sym.NOWHERE_AT);
         }

   and|\&\&
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched &&: " + yytext());
                  }
                  return new Symbol(sym.AND);
         }

   or|\|\|
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched ||: " + yytext());
                  }
                  return new Symbol(sym.OR);
         }

   not|\!
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched !: " + yytext());
                  }
                  return new Symbol(sym.NOT);
         }

   add
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched add: " + yytext());
                  }
                  return new Symbol(sym.ADD);
         }
   remove
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched remove: " + yytext());
                  }
                  return new Symbol(sym.REMOVE);
         }

   item\_at
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched item_at: " + yytext());
                  }
                  return new Symbol(sym.ITEM_AT);
         }

   change
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched change: " + yytext());
                  }
                  return new Symbol(sym.CHANGE);
         }

   {identifier}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched id function name: " + yytext());
                  }
                  return new Symbol(sym.FUNCTION_NAME, new String(yytext()));
         }

   {identifier}{id_suffix}({list_suffix})?
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched id/list: " + yytext());
                  }
                  /* check if list type */
                  return new Symbol(sym.VARIABLE, new RobotVar(yytext()));
         }

   "//"
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched comments: " + yytext());
                  }
                  yybegin(COMMENTS);
                  /* ignore comments */
         }
   {percentage}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched percentage: " + yytext());
                  }
                  return new Symbol(sym.NUMBER_PERCENTAGE, new String(yytext()));
         }

   {digits}(st|th|nd|rd)
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched integer_index: " + yytext());
                  }
                  return new Symbol(sym.INT_IDX, new String(yytext()));
         }

   {identifier}\#(st|th|nd)
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched integer_index: " + yytext());
                  }
                  return new Symbol(sym.NUMBER_NAME_IDX, new String(yytext()));
         }

   {number}
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched number: " + yytext());
                  }
                  return new Symbol(sym.NUMBER_EXPRESSION, new String(yytext()+"f"));
         }

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

   \<\<
         {
            if (parser.bDebugFlag) {
               System.out.println("matched say_expr_end: " + yytext());
            }

            if(yystate() == SAY_EXPR) {
           	   yybegin(SAYSTATE);
           	} else {
           	   lexerError("Unexpected token: " + yytext());
           	}

            return new Symbol(sym.SAY_EXPR_END);
         }

}

<SAYSTATE>   {

   \n
         {
            if (parser.bDebugFlag) {
               System.out.println("matched saystate newline");
            }
            yybegin(YYINITIAL);
            return new Symbol(sym.NEWLINE);
         }

   \>\>
         {
            if (parser.bDebugFlag) {
               System.out.println("matched saystate expr sta:" + yytext());
            }
            yybegin(SAY_EXPR);
            return new Symbol(sym.SAY_EXPR_STA);
         }

   \/\/
         {
                  if (parser.bDebugFlag) {
                     System.out.println("matched comments: " + yytext());
                  }
                  yybegin(COMMENTS);
                  /* ignore comments */
         }

   [^(\>\>)|\n|(\/\/)]+
         {
            if (parser.bDebugFlag) {
               System.out.println("matched saystate vtext: " + yytext());
            }
            return new Symbol(sym.VERBATIM_OUT_TXT, new String(yytext()));
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
      lexerError("Unexpected character: " + yytext());
      /*System.err.println("Illegal character: " + yytext() + "\nLine number: " + yyline + "\nColumn number: " + yycolumn);
      System.exit(0);*/
}
