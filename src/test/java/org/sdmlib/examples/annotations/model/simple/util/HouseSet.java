/*
   Copyright (c) 2015 Olaf Gunkel 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.annotations.model.simple.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.annotations.model.simple.House;
import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.examples.annotations.model.simple.util.DoorSet;
import org.sdmlib.examples.annotations.model.simple.Door;
import org.sdmlib.examples.annotations.model.simple.util.WindowSet;
import org.sdmlib.examples.annotations.model.simple.Window;

public class HouseSet extends SDMSet<House>
{

   public static final HouseSet EMPTY_SET = new HouseSet().withReadOnly(true);


   public HousePO hasHousePO()
   {
      return new HousePO(this.toArray(new House[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.annotations.model.simple.House";
   }


   @SuppressWarnings("unchecked")
   public HouseSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<House>)value);
      }
      else if (value != null)
      {
         this.add((House) value);
      }
      
      return this;
   }
   
   public HouseSet without(House value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public HouseSet init()
   {
      for (House obj : this)
      {
         obj.init();
      }
      return this;
   }

   public DoorSet getDoors()
   {
      DoorSet result = new DoorSet();
      
      for (House obj : this)
      {
         result.addAll(obj.getDoors());
      }
      
      return result;
   }

   public HouseSet hasDoors(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getDoors()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public HouseSet withDoors(Door value)
   {
      for (House obj : this)
      {
         obj.withDoors(value);
      }
      
      return this;
   }

   public HouseSet withoutDoors(Door value)
   {
      for (House obj : this)
      {
         obj.withoutDoors(value);
      }
      
      return this;
   }

   public WindowSet getWindows()
   {
      WindowSet result = new WindowSet();
      
      for (House obj : this)
      {
         result.addAll(obj.getWindows());
      }
      
      return result;
   }

   public HouseSet hasWindows(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getWindows()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public HouseSet withWindows(Window value)
   {
      for (House obj : this)
      {
         obj.withWindows(value);
      }
      
      return this;
   }

   public HouseSet withoutWindows(Window value)
   {
      for (House obj : this)
      {
         obj.withoutWindows(value);
      }
      
      return this;
   }

}
