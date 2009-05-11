//This code baseline for compiler test
//Expected Result : PatrolRobot.java
count# = 0
think
   my_loc@ = getLocation of self
   x# of 1st of targetLocation@...=10
   y# of 1st of targetLocation@...=y# of my_loc@
   x# of 2nd of targetLocation@...=10
   y# of 2nd of targetLocation@...=10
   x# of 3rd of targetLocation@...=x# of my_loc@
   y# of 3rd of targetLocation@...=10
   x# of 4th of targetLocation@...=x# of my_loc@
   y# of 4th of targetLocation@...=y# of my_loc@

   if my_loc@ != count#th of targetLocation@... then
       move_to count#th of targetLocation@... 10%
   else
      count# = (count# + 1) rollover 4
   done
end