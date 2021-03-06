package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;

import de.uniks.networkparser.IdMap;

public class RiverPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new RiverPO(new River[]{});
      } else {
          return new RiverPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
