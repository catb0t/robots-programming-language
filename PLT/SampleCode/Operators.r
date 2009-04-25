//Identifier Test
think
   //Arithmetic Operators
   a# = 100
   b# = 50
   c# = a# + b#
   d# = a# - b#
   e# = a# * b#
   f# = a# / b#
   
   health_boost% = 70
   health% = 40
   health% = health% + health_boost% 
   
   multiplier# = 2
   health% = 60
   healthNew% = health% * multiplier# // healthNew is now 100% due to clipping
   healthNum# = health% * multiplier# // healthNum is 1.2
   
   //Comparision Operatior
   if a# < b# then
   done
   if a# <= b# then
   done
   if a# > b# then
   done
   if a# >= b# then
   done
   if a# == b# then
   done
   if a# != b# then
   done
   if a# is_strictly_lower_than b# then
   done
   if a# is_lower_than b# then
   done
   if a# is_strictly_greater_than b# then
   done
   if a# is_greater_than b# then
   done
   if a# is_equal_to b# then
   done
   if a# is_different_from b# then
   done
   
   //Boolean operator -------- grammar error !!!! in testing 4/24
   x?=true
   y?=false
   z?= x? && y?
   z?= x? || y?
   z?= !x?
   z?= x? and y?
   z?= x? or y?
   z?= not x?
end   
