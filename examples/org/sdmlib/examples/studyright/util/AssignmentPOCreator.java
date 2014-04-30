package org.sdmlib.examples.studyright.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class AssignmentPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new AssignmentPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((AssignmentPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((AssignmentPO) target).set(attrName, value);
   }
}

