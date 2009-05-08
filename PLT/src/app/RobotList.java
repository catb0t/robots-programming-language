package app;

import java.util.*;

public class RobotList<E> extends LinkedList<E> {

   Class<E> clazz;

   public RobotList(Class<E> cls) {
      super();
      clazz = cls;
   }

   public E get(float index) {

      E item = null;
      int indexZeroBased = ((int) index) - 1;

      try {
         
         item = super.get(indexZeroBased);

      } catch (IndexOutOfBoundsException e) {
         
         try {

            for (int i = this.size(); i <= indexZeroBased; i++) {
               item = clazz.newInstance();
               this.add(item);
            }

         } catch (InstantiationException e2) {
            e2.printStackTrace();
         } catch (IllegalAccessException e2) {
            e2.printStackTrace();
         }

      } catch (Exception other) {
         other.printStackTrace();
      }

      return item;
   }
   
   
   public void add (float index, E item) {
	   int indexZeroBased = ((int) index) - 1;
	   
	   try {
	         
	         super.add(indexZeroBased, item);

	      } catch (IndexOutOfBoundsException e) {
	         
	         try {

	            for (int i = this.size(); i <= indexZeroBased; i++) {
	               item = clazz.newInstance();
	               this.add(item);
	            }

	         } catch (InstantiationException e2) {
	            e2.printStackTrace();
	         } catch (IllegalAccessException e2) {
	            e2.printStackTrace();
	         }

	      } catch (Exception other) {
	         other.printStackTrace();
	      }
   }
   
   
   public void remove (float index) {
	   
	   int indexZeroBased = ((int) index) - 1;
	   if (indexZeroBased < this.size()) {
		   
		   this.remove(indexZeroBased);
	   }
   }

   
}
