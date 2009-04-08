think

   closest_enemy! = NOTHING!
   // 1. Get enemy locations
   ping_radar  // Decreases energy
   enemies!... = get_enemies  // alternatively: enemies!... = enemies!... of radar

   // 2. Find closest
   closest_enemy! = find_closest_enemy enemies!...

   // 3. Shoot
   shoot closest_enemy!  // Decreases energy

end

instruction find_closest_enemy with enemies!... means

   closest_distance# = MAX#
   closest_enemy! = NOTHING!
   my_loc@ = location@ of self

   repeat with each enemy! in enemies!...

      enemy_loc@ = location of enemy!
      distance# = distance enemy_loc@ my_loc@
   
      if distance# is_less_than closest_distance# then
      
         closest_distance# = distance#
         closest_enemy! = enemy!
         
      done

   done

gives closest_enemy!