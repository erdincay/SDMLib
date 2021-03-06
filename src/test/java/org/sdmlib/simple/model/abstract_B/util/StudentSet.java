/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.abstract_B.util;

import java.util.Collection;

import org.sdmlib.simple.model.abstract_B.Flower;
import org.sdmlib.simple.model.abstract_B.Student;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class StudentSet extends SimpleSet<Student>
{
	public Class<?> getTypClass() {
		return Student.class;
	}

   public StudentSet()
   {
      // empty
   }

   public StudentSet(Student... objects)
   {
      for (Student obj : objects)
      {
         this.add(obj);
      }
   }

   public StudentSet(Collection<Student> objects)
   {
      this.addAll(objects);
   }

   public static final StudentSet EMPTY_SET = new StudentSet().withFlag(StudentSet.READONLY);


   public StudentPO createStudentPO()
   {
      return new StudentPO(this.toArray(new Student[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.abstract_B.Student";
   }


   @Override
   public StudentSet getNewList(boolean keyValue)
   {
      return new StudentSet();
   }


   public StudentSet filter(Condition<Student> condition) {
      StudentSet filterList = new StudentSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public StudentSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Student>)value);
      }
      else if (value != null)
      {
         this.add((Student) value);
      }
      
      return this;
   }
   
   public StudentSet without(Student value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Student objects and collect a set of the Flower objects reached via has. 
    * 
    * @return Set of Flower objects reachable via has
    */
   public FlowerSet getHas()
   {
      FlowerSet result = new FlowerSet();
      
      for (Student obj : this)
      {
         result.with(obj.getHas());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Student objects and collect all contained objects with reference has pointing to the object passed as parameter. 
    * 
    * @param value The object required as has neighbor of the collected results. 
    * 
    * @return Set of Flower objects referring to value via has
    */
   public StudentSet filterHas(Object value)
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
      
      StudentSet answer = new StudentSet();
      
      for (Student obj : this)
      {
         if (neighbors.contains(obj.getHas()) || (neighbors.isEmpty() && obj.getHas() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Student object passed as parameter to the Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Has attributes.
    */
   public StudentSet withHas(Flower value)
   {
      for (Student obj : this)
      {
         obj.withHas(value);
      }
      
      return this;
   }

}
