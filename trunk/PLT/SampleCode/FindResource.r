think

   closest_resource$ = NOTHING$
   // 1. Get resource locations
   ping_radar // Decreases energy
   resources$... = get_resources

   // 2. Find closest
   closest_resource$ = find_closest_resource resources$...

   // 3. Get energy
   move_to location@ of closest_resources$ 30% 

end

instruction find_closest_resource with resources$... means

   closest_distance# = MAX#
   closest_resource$ = NOTHING$
   my_loc@ = location@ of self

   repeat with each resource$ in resources$...

      resource_loc@ = location of resource$
      distance# = distance resource_loc@ my_loc@

      if distance# is_less_than closest_distance# then
      
         closest_distance# = distance#
         closest_resource$ = resource$
      
      done
   done

gives closest_resource$