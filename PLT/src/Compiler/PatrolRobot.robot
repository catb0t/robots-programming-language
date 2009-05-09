//This code baseline for compiler test
//Expected Result : PatrolRobot.java
count# = 1
think
|   my_loc@ = getLocation of self
|   x# of 1th of targetLocation@...=10
|   y# of 1th of targetLocation@...=y# of my_loc@
|   x# of 2th of targetLocation@...=x# of my_loc@
|   y# of 2th of targetLocation@...=10
|   x# of 3th of targetLocation@...=0
|   y# of 3th of targetLocation@...=y# of my_loc@
|   x# of 4th of targetLocation@...=x# of my_loc@
|   y# of 4th of targetLocation@...=0
|   
|   if my_loc@ != count#th of targetLocation@... then
|   |   move_to count#th of targetLocation@... 100%
|   +---else
|   |   count# = (count# + 1) rollover 4
|   +---done
+---end
