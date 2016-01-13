LRM brainstorming

# Introduction #

LRM brainstorming

# Details #

Loading a Java class on-the-fly (the first link is most relevant)

http://www.exampledepot.com/egs/java.lang/ReloadClass.html

http://twit88.com/blog/2007/10/04/java-dynamic-loading-of-class-and-jar-file/

// program 1

think (means)

> say 		hello
> {keyword (kw)}

end

// user defined procedures/functions
instruction 

&lt;name&gt;

 means

done

// program 2

think
> moveTo 

&lt;global-coordinate-X&gt;

 

&lt;global-coordinate-Y&gt;


end

// program 3
think
remember enemy\_locations is\_a list
enemy\_locations = locateenemies
end


Grammar

program := entry
> ;

instruction := say expr
> | moveTo numerical\_expr numerical\_expr
> ;

say\_expr := [anything](anything.md)**$
> ;**

numberical\_expr :=
> ;

num\_var := id
> ;

entry := think
> {instruction}