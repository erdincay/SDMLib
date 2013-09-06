package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class BoatPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new BoatPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((BoatPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((BoatPO) target).set(attrName, value);
   }
}
