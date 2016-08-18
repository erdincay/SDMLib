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
   
package org.sdmlib.models.tables.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.tables.Cell;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.Column;

public class CellCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Cell.PROPERTY_VALUE,
      Cell.PROPERTY_ROW,
      Cell.PROPERTY_COLUMN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Cell();
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

      if (Cell.PROPERTY_VALUE.equalsIgnoreCase(attribute))
      {
         return ((Cell) target).getValue();
      }

      if (Cell.PROPERTY_ROW.equalsIgnoreCase(attribute))
      {
         return ((Cell) target).getRow();
      }

      if (Cell.PROPERTY_COLUMN.equalsIgnoreCase(attribute))
      {
         return ((Cell) target).getColumn();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Cell.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((Cell) target).setValue((Object) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Cell.PROPERTY_ROW.equalsIgnoreCase(attrName))
      {
         ((Cell) target).setRow((Row) value);
         return true;
      }

      if (Cell.PROPERTY_COLUMN.equalsIgnoreCase(attrName))
      {
         ((Cell) target).setColumn((Column) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.tables.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Cell) entity).removeYou();
   }
}