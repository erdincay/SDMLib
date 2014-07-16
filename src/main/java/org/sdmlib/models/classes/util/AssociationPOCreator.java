package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class AssociationPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AssociationPO(new Association[]{});
      } else {
          return new AssociationPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}