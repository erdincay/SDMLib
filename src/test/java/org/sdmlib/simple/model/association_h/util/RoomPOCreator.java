package org.sdmlib.simple.model.association_h.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.association_h.Room;

import de.uniks.networkparser.IdMap;

public class RoomPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new RoomPO(new Room[]{});
      } else {
          return new RoomPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.association_h.util.CreatorCreator.createIdMap(sessionID);
   }
}
