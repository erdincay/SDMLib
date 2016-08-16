package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.storyboards.StoryboardWall;

import de.uniks.networkparser.IdMap;

public class StoryboardWallPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new StoryboardWallPO(new StoryboardWall[]{});
      } else {
          return new StoryboardWallPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}