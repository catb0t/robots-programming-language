//This code baseline for compiler test
//Expected Result : DestroyAllEnemies.java
think
    if energy_level% of self is_greater_than 10% then //if energy is less than 10% then wait to recharge
        ping // Decreases energy
        // 1. Get enemy locations
        enemies!... = get_enemies
        
        if enemies!... is_different_from NOTHING! then

           // 2. Find closest
           closest_enemy! = NOTHING!
           closest_enemy! = find_closest_enemy enemies!...

           closest_resource$ = NOTHING$
           resources$... = get_resources

           if energy_level% of self is_lower_than 30% then
               //closest_resource$ = find_closest_resource resources$...
               move_to location@ of closest_resource$ 80% 
           else  //fight!  
               dist# = distance location@ of closest_enemy! getLocation of self
               
               if dist# is_greater_than 10 then
                   shoot closest_enemy!  // Decreases energy
               else
                   move_to location@ of closest_enemy! 100%
               done
           done
        else //There's nobody around to fight
           say I'm bored!!               
        done
    done
end