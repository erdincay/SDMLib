package org.sdmlib.test.examples.modelcouch.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.modelcouch.Task;

import de.uniks.networkparser.IdMap;

public class TaskPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TaskPO(new Task[]{});
      } else {
          return new TaskPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.modelcouch.util.CreatorCreator.createIdMap(sessionID);
   }
}
