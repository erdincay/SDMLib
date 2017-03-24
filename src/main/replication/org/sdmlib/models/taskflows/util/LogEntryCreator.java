/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.taskflows.LogEntry;
import org.sdmlib.models.taskflows.Logger;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class LogEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      LogEntry.PROPERTY_NODENAME,
      LogEntry.PROPERTY_TASKNAME,
      LogEntry.PROPERTY_LOGGER,
      LogEntry.PROPERTY_CHILDREN,
      LogEntry.PROPERTY_PARENT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LogEntry();
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

      if (LogEntry.PROPERTY_NODENAME.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getNodeName();
      }

      if (LogEntry.PROPERTY_TASKNAME.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getTaskName();
      }

      if (LogEntry.PROPERTY_LOGGER.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getLogger();
      }

      if (LogEntry.PROPERTY_CHILDREN.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getChildren();
      }

      if (LogEntry.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getParent();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (LogEntry.PROPERTY_NODENAME.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).withNodeName((String) value);
         return true;
      }

      if (LogEntry.PROPERTY_TASKNAME.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).withTaskName((String) value);
         return true;
      }

      if (LogEntry.PROPERTY_LOGGER.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).setLogger((Logger) value);
         return true;
      }

      if (LogEntry.PROPERTY_CHILDREN.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).withChildren((LogEntry) value);
         return true;
      }
      
      if ((LogEntry.PROPERTY_CHILDREN + REMOVE).equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).withoutChildren((LogEntry) value);
         return true;
      }

      if (LogEntry.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).setParent((LogEntry) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LogEntry) entity).removeYou();
   }
}
