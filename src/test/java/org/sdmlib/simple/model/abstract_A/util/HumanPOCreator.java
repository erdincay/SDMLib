package org.sdmlib.simple.model.abstract_A.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.abstract_A.Human;

import de.uniks.networkparser.IdMap;

public class HumanPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new HumanPO(new Human[]{});
      } else {
          return new HumanPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.abstract_A.util.CreatorCreator.createIdMap(sessionID);
   }
}
