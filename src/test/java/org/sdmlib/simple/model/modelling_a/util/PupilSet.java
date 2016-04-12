/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.modelling_a.Pupil;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.simple.model.modelling_a.util.RoomSet;
import org.sdmlib.simple.model.modelling_a.Room;
import org.sdmlib.simple.model.modelling_a.util.TeacherSet;
import org.sdmlib.simple.model.modelling_a.Teacher;

public class PupilSet extends SDMSet<Pupil>
{

   public static final PupilSet EMPTY_SET = new PupilSet().withFlag(PupilSet.READONLY);


   public PupilPO filterPupilPO()
   {
      return new PupilPO(this.toArray(new Pupil[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.modelling_a.Pupil";
   }


   @SuppressWarnings("unchecked")
   public PupilSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Pupil>)value);
      }
      else if (value != null)
      {
         this.add((Pupil) value);
      }
      
      return this;
   }
   
   public PupilSet without(Pupil value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public PupilSet filter(Condition<Pupil> newValue) {
      PupilSet filterList = new PupilSet();
      filterItems(filterList, newValue);
      return filterList;
   }
   
   //==========================================================================
   
   public StringList read()
   {
      StringList result = new StringList();
      for (Pupil obj : this)
      {
         result.add(obj.read());
      }
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect a list of the credits attribute values. 
    * 
    * @return List of int objects reachable via credits attribute
    */
   public intList getCredits()
   {
      intList result = new intList();
      
      for (Pupil obj : this)
      {
         result.add(obj.getCredits());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the credits attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterCredits(int value)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the credits attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterCredits(int lower, int upper)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (lower <= obj.getCredits() && obj.getCredits() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and assign value to the credits attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Pupil objects now with new attribute values.
    */
   public PupilSet withCredits(int value)
   {
      for (Pupil obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Pupil objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Pupil obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterName(String value)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterName(String lower, String upper)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Pupil objects now with new attribute values.
    */
   public PupilSet withName(String value)
   {
      for (Pupil obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Pupil objects and collect a list of the age attribute values. 
    * 
    * @return List of int objects reachable via age attribute
    */
   public intList getAge()
   {
      intList result = new intList();
      
      for (Pupil obj : this)
      {
         result.add(obj.getAge());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the age attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterAge(int value)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the age attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterAge(int lower, int upper)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and assign value to the age attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Pupil objects now with new attribute values.
    */
   public PupilSet withAge(int value)
   {
      for (Pupil obj : this)
      {
         obj.setAge(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Pupil objects and collect a set of the Room objects reached via room. 
    * 
    * @return Set of Room objects reachable via room
    */
   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();
      
      for (Pupil obj : this)
      {
         result.with(obj.getRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Pupil objects and collect all contained objects with reference room pointing to the object passed as parameter. 
    * 
    * @param value The object required as room neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via room
    */
   public PupilSet filterRoom(Object value)
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
      
      PupilSet answer = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Pupil object passed as parameter to the Room attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Room attributes.
    */
   public PupilSet withRoom(Room value)
   {
      for (Pupil obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Pupil objects and collect a set of the Room objects reached via currentRoom. 
    * 
    * @return Set of Room objects reachable via currentRoom
    */
   public RoomSet getCurrentRoom()
   {
      RoomSet result = new RoomSet();
      
      for (Pupil obj : this)
      {
         result.with(obj.getCurrentRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Pupil objects and collect all contained objects with reference currentRoom pointing to the object passed as parameter. 
    * 
    * @param value The object required as currentRoom neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via currentRoom
    */
   public PupilSet filterCurrentRoom(Object value)
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
      
      PupilSet answer = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (neighbors.contains(obj.getCurrentRoom()) || (neighbors.isEmpty() && obj.getCurrentRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Pupil object passed as parameter to the CurrentRoom attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their CurrentRoom attributes.
    */
   public PupilSet withCurrentRoom(Room value)
   {
      for (Pupil obj : this)
      {
         obj.withCurrentRoom(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Pupil objects and collect a set of the Teacher objects reached via teacher. 
    * 
    * @return Set of Teacher objects reachable via teacher
    */
   public TeacherSet getTeacher()
   {
      TeacherSet result = new TeacherSet();
      
      for (Pupil obj : this)
      {
         result.with(obj.getTeacher());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Pupil objects and collect all contained objects with reference teacher pointing to the object passed as parameter. 
    * 
    * @param value The object required as teacher neighbor of the collected results. 
    * 
    * @return Set of Teacher objects referring to value via teacher
    */
   public PupilSet filterTeacher(Object value)
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
      
      PupilSet answer = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (neighbors.contains(obj.getTeacher()) || (neighbors.isEmpty() && obj.getTeacher() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Pupil object passed as parameter to the Teacher attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Teacher attributes.
    */
   public PupilSet withTeacher(Teacher value)
   {
      for (Pupil obj : this)
      {
         obj.withTeacher(value);
      }
      
      return this;
   }

}