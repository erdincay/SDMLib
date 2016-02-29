package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.IdMap;

public class ObjectPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ObjectPO(new Object[]{});
      } else {
          return new ObjectPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.transformations.util.CreatorCreator.createIdMap(sessionID);
   }
}
