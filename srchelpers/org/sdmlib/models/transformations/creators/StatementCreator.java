package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.Statement;

public class StatementCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Statement.PROPERTY_TEXT,
      Statement.PROPERTY_NEXT,
      Statement.PROPERTY_PREV,
      Statement.PROPERTY_OPERATIONOBJECTS,
      Statement.PROPERTY_TRANSFORMOP,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Statement();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Statement) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Statement) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}











