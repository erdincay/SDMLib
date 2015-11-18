package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class SimpleKeyValueListPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SimpleKeyValueListPO(new SimpleKeyValueList[]{});
      } else {
          return new SimpleKeyValueListPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
