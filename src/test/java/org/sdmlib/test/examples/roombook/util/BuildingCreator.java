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
   
package org.sdmlib.test.examples.roombook.util;

import org.sdmlib.test.examples.roombook.Building;
import org.sdmlib.test.examples.roombook.Floor;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class BuildingCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Building.PROPERTY_NAME,
      Building.PROPERTY_HAS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Building();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Building.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Building) target).getName();
      }

      if (Building.PROPERTY_HAS.equalsIgnoreCase(attribute))
      {
         return ((Building) target).getHas();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Building.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Building) target).withName((String) value);
         return true;
      }

      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Building.PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         ((Building) target).withHas((Floor) value);
         return true;
      }
      
      if ((Building.PROPERTY_HAS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((Building) target).withoutHas((Floor) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.roombook.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Building) entity).removeYou();
   }
}
