//Identifier Test
wholecount# = 0
Maximum_energy_level%=100
isEnemy?=false
think
   count# = 0
   count1#= 10.5
   energy_level%=90
   isEnemy?=true
   my_loc@ = getLocation of self
   closest_resource$ = NOTHING$
   closest_enemy! = NOTHING!
   enemies!... = get_enemies  
   my_loc@ = getLocation of self
   x# of 1st of targetLocation@...=10
   y# of 1st of targetLocation@...=y# of my_loc@
   x# of 2nd of targetLocation@...=10
   y# of 2nd of targetLocation@...=10
   x# of 3th of targetLocation@...=x# of my_loc@
   y# of 3th of targetLocation@...=10
   Number# is 10
   closest_distance# = MAX#
   farest_distance# = MIN#
   closest_enemy_loc@=NOWHERE@
end