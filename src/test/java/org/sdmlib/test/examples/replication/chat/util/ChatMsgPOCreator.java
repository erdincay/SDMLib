package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.replication.chat.ChatMsg;

import de.uniks.networkparser.IdMap;

public class ChatMsgPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChatMsgPO(new ChatMsg[]{});
      } else {
          return new ChatMsgPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap(sessionID);
   }
}
