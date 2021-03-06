package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;

import de.uniks.networkparser.IdMap;

public class BoatPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BoatPO(new Boat[]{});
      } else {
          return new BoatPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
