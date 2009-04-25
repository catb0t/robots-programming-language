
mybool? is TRUE
mynumber# = 546.45
yournumber# = 55 * 5 /6 -2 * 8 
	yourbool? = mybool?
	
	mynumber# = MAX#
yournumber2# = MIN#
targetLocation@
percentage%

Think

  closest_resource$ = NOTHING$
 // 1. Get resource locations
 ping // Decreases energy
 resources$... = get_resources

 // 2. Find closest
 closest_resource$ = find_closest_resource resources$...

 // 3. Get energy
 move_to location@ of closest_resource$



x# of targetLocation@=10
	y# of targetLocation@=10
	
	my_loc@ = location@ of self
	
	x# of 1th of targetLocation@...=10
	  y# of 1th of targetLocation@...=y# of my_loc@
x# of 2th of targetLocation@...=10
 y# of 2th of targetLocation@...=10
 x# of 3th of targetLocation@...=x# of my_loc@
 y# of 3th of targetLocation@...=10
 x# of 4th of targetLocation@...=x# of my_loc@
 y# of 4th of targetLocation@...=y# of my_loc@
 
 x? = true
 z? = x? && false

myLoc@ = count#th of targetLocation@...

if 5 > 2 then
// do something
else
// do something else
done

 if my_loc@ is_different_from count#th of targetLocation@... then
     move_to count#th of targetLocation@...
else
count# = (5 + 1) rollover 4
count# = (count# + 1) rollover 4
done

say    hello world

// move_to targetLocation@ 100%

end

instruction find_closest_resource with resources$... means
|
|   closest_distance# = MAX#
|   closest_resource$ = NOTHING$
|   my_loc@ = location@ of self
|
|   repeat with each resource$ in resources$...
|   |
|   |   resource_loc@ = location@ of resource$
|   |   distance# = distance resource_loc@ my_loc@
|   |
|   |   if distance# is_loweR_than closest_distance# then
|   |   |
|   |   |   closest_distance# = distance#
|   |   |   closest_resource$ = resource$
|   |   |
|   |   +---done
|   |
|   +---done
|
+---gives closest_resource$

	
	
instruction swap with a# b# means

gives nothing
