package org.sdmlib.simple.model.abstract_B.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.abstract_B.Flower;

import de.uniks.networkparser.IdMap;

public class FlowerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new FlowerPO(new Flower[]{});
      } else {
          return new FlowerPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.abstract_B.util.CreatorCreator.createIdMap(sessionID);
   }
}
