package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.studyright.model.Assignment;

import de.uniks.networkparser.json.JsonIdMap;

public class AssignmentPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AssignmentPO(new Assignment[]{});
      } else {
          return new AssignmentPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}