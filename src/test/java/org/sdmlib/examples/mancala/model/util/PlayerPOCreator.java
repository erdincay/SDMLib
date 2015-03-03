package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.examples.mancala.model.Player;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class PlayerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PlayerPO(new Player[]{});
      } else {
          return new PlayerPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.mancala.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
