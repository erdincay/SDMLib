package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.examples.mancala.model.Pit;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class PitPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PitPO(new Pit[]{});
      } else {
          return new PitPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.mancala.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
