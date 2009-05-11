think
     //5.1 say
     say Oh, oh. My health is now >>health% of self<<
     
     //5.2 move_to
     x# of targetLocation@=10
	 y# of targetLocation@=10
	 move_to targetLocation@ 100%
	 
	 //5.3 shoot
	 closest_enemy! = NOTHING!
	 shoot closest_enemy!
	 
	 //5.4 ping
	 ping
	 
	 //5.5 distance
	 my_loc@ = getLocation of self
     enemy_loc@ = location@ of enemy!
     distance# = distance enemy_loc@ my_loc@
     
     //5.6 get enemies
     enemies!... = get_enemies
     
     //5.7 get resources
     resources$... = get_resources
     
     //5.8 get_environment_height
     height#=get_environment_height enemy_loc@
     
     //5.9 Sort
     furthest_enemies!... = sort enemy!... < health#
     most_valuable_resources$... = sort resources$... > ammostash#  
          
     //5.10 modify_list
     modify_list cliff_locations@ add loc_of_cliff@
     
     //5.11 gen_random_num
     randomNumber%=gen_random_num
     
     //5.12 flipCoin
     should_attack? = flipCoin health% of self
     
     //5.13.1 sqrt
     testnum#=100
     result#=sqrt testnum#
     
     //5.13.2 power
     result#=power testnum# 2
     
     //5.13.3 sin
     result#=sin 45
     
     //5.13.4 cos
     result#=cos 120
     
     //5.13.5 tan
     result#=tan 125
     
     //5.13.6 rollover
     result#= testnum# rollover  10           
end
