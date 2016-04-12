package org.sdmlib.simple.model.removeCode_b.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.removeCode_b.Person;

public class PersonPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PersonPO(new Person[]{});
      } else {
          return new PersonPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.removeCode_b.util.CreatorCreator.createIdMap(sessionID);
   }
}