package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.replication.SeppelSpaceProxy;

import de.uniks.networkparser.json.JsonIdMap;

public class SeppelSpaceProxyPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      } else {
          return new SeppelSpaceProxyPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
}
