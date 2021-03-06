package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;

import de.uniks.networkparser.IdMap;

public class GroupAccountPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GroupAccountPO(new GroupAccount[]{});
      } else {
          return new GroupAccountPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
