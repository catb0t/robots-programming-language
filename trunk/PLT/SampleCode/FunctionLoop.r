//This code baseline for compiler test
//Expected Result : FindShoot.java
think

   closest_enemy! = NOTHING!
   enemies!... = get_enemies  // alternatively: enemies!... = enemies!... of radar

   // 2. Find closest
   closest_enemy! = find_closest_enemy enemies!...

end

instruction find_closest_enemy with enemies!... means

   closest_distance# = MAX#
   closest_enemy! = NOTHING!
   my_loc@ = location@ of self

   repeat with each enemy! in enemies!...

      enemy_loc@ = location@ of enemy!
      distance# = distance enemy_loc@ my_loc@
   
      if distance# is_lower_than closest_distance# then
      
         closest_distance# = distance#
         closest_enemy! = enemy!
         
      done

   done
   
   a#=1
   repeat 5 times      //Compile Error !!!
        a#=a#+1
   done
   
   while a#<=10        //Compile Error !!!
        a#=a#+1
   done
   

gives closest_enemy!