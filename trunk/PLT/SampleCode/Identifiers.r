//Identifier Test
think
   count# = 0
   count1#= 10.5
   energy_level%=100
   isEnemy?=false
   my_loc@ = location@ of self
   closest_resource$ = NOTHING$
   closest_enemy! = NOTHING! 
   enemies!... = get_enemies   //Compile Error !!!
end