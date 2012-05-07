/*
   Copyright (c) 2012 NeTH 
   
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
   
package org.sdmlib.examples.studyrightextends;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Room implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_ROOMNO.equalsIgnoreCase(attrName))
      {
         return getRoomNo();
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         return getCredits();
      }

      if (PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         return getUni();
      }

      if (PROPERTY_NEIGHBORS.equalsIgnoreCase(attrName))
      {
         return getNeighbors();
      }

      if (PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         return getLecture();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_ROOMNO.equalsIgnoreCase(attrName))
      {
         setRoomNo((String) value);
         return true;
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         setCredits((Integer) value);
         return true;
      }

      if (PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         setUni((University) value);
         return true;
      }

      if (PROPERTY_NEIGHBORS.equalsIgnoreCase(attrName))
      {
         addToNeighbors((Room) value);
         return true;
      }
      
      if ((PROPERTY_NEIGHBORS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromNeighbors((Room) value);
         return true;
      }

      if (PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         addToLecture((Lecture) value);
         return true;
      }
      
      if ((PROPERTY_LECTURE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLecture((Lecture) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setUni(null);
      removeAllFromNeighbors();
      removeAllFromLecture();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public void studentCount(  )
   {
   }

   
   //==========================================================================
   
   public static final String PROPERTY_ROOMNO = "roomNo";
   
   private String roomNo;
   
   public String getRoomNo()
   {
      return this.roomNo;
   }
   
   public void setRoomNo(String value)
   {
      if ( ! StrUtil.stringEquals(this.roomNo, value))
      {
         String oldValue = this.roomNo;
         this.roomNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMNO, oldValue, value);
      }
   }
   
   public Room withRoomNo(String value)
   {
      setRoomNo(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CREDITS = "credits";
   
   private int credits;
   
   public int getCredits()
   {
      return this.credits;
   }
   
   public void setCredits(int value)
   {
      if (this.credits != value)
      {
         int oldValue = this.credits;
         this.credits = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CREDITS, oldValue, value);
      }
   }
   
   public Room withCredits(int value)
   {
      setCredits(value);
      return this;
   } 

   
   public static final LinkedHashSet<Room> EMPTY_SET = new LinkedHashSet<Room>();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Room ----------------------------------- University
    *              rooms                   uni
    * </pre>
    */
   
   public static final String PROPERTY_UNI = "uni";
   
   private University uni = null;
   
   public University getUni()
   {
      return this.uni;
   }
   
   public boolean setUni(University value)
   {
      boolean changed = false;
      
      if (this.uni != value)
      {
         University oldValue = this.uni;
         
         if (this.uni != null)
         {
            this.uni = null;
            oldValue.withoutRooms(this);
         }
         
         this.uni = value;
         
         if (value != null)
         {
            value.withRooms(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_UNI, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Room withUni(University value)
   {
      setUni(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Room ----------------------------------- Room
    *              neighbors                   neighbors
    * </pre>
    */
   
   public static final String PROPERTY_NEIGHBORS = "neighbors";
   
   private LinkedHashSet<Room> neighbors = null;
   
   public LinkedHashSet<Room> getNeighbors()
   {
      if (this.neighbors == null)
      {
         return Room.EMPTY_SET;
      }
   
      return this.neighbors;
   }
   
   public boolean addToNeighbors(Room value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.neighbors == null)
         {
            this.neighbors = new LinkedHashSet<Room>();
         }
         
         changed = this.neighbors.add (value);
         
         if (changed)
         {
            value.withNeighbors(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEIGHBORS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromNeighbors(Room value)
   {
      boolean changed = false;
      
      if ((this.neighbors != null) && (value != null))
      {
         changed = this.neighbors.remove (value);
         
         if (changed)
         {
            value.withoutNeighbors(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEIGHBORS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Room withNeighbors(Room value)
   {
      addToNeighbors(value);
      return this;
   } 
   
   public Room withoutNeighbors(Room value)
   {
      removeFromNeighbors(value);
      return this;
   } 
   
   public void removeAllFromNeighbors()
   {
      LinkedHashSet<Room> tmpSet = new LinkedHashSet<Room>(this.getNeighbors());
   
      for (Room value : tmpSet)
      {
         this.removeFromNeighbors(value);
      }
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Room ----------------------------------- Lecture
    *              in                   lecture
    * </pre>
    */
   
   public static final String PROPERTY_LECTURE = "lecture";
   
   private LinkedHashSet<Lecture> lecture = null;
   
   public LinkedHashSet<Lecture> getLecture()
   {
      if (this.lecture == null)
      {
         return Lecture.EMPTY_SET;
      }
   
      return this.lecture;
   }
   
   public boolean addToLecture(Lecture value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.lecture == null)
         {
            this.lecture = new LinkedHashSet<Lecture>();
         }
         
         changed = this.lecture.add (value);
         
         if (changed)
         {
            value.withIn(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromLecture(Lecture value)
   {
      boolean changed = false;
      
      if ((this.lecture != null) && (value != null))
      {
         changed = this.lecture.remove (value);
         
         if (changed)
         {
            value.setIn(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, value, null);
         }
      }
         
      return changed;   
   }
   
   public Room withLecture(Lecture value)
   {
      addToLecture(value);
      return this;
   } 
   
   public Room withoutLecture(Lecture value)
   {
      removeFromLecture(value);
      return this;
   } 
   
   public void removeAllFromLecture()
   {
      LinkedHashSet<Lecture> tmpSet = new LinkedHashSet<Lecture>(this.getLecture());
   
      for (Lecture value : tmpSet)
      {
         this.removeFromLecture(value);
      }
   }
}
