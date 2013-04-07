/*
   Copyright (c) 2013 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.replication.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.replication.ChatRoot;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.replication.creators.ChatMsgSet;
import org.sdmlib.examples.replication.ChatMsg;

public class ChatRootSet extends LinkedHashSet<ChatRoot> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ChatRoot elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.replication.ChatRoot";
   }


   public ChatRootSet with(ChatRoot value)
   {
      this.add(value);
      return this;
   }
   
   public ChatRootSet without(ChatRoot value)
   {
      this.remove(value);
      return this;
   }
   public ChatMsgSet getMsgs()
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatRoot obj : this)
      {
         result.addAll(obj.getMsgs());
      }
      
      return result;
   }

   public ChatRootSet withMsgs(ChatMsg value)
   {
      for (ChatRoot obj : this)
      {
         obj.withMsgs(value);
      }
      
      return this;
   }

   public ChatRootSet withoutMsgs(ChatMsg value)
   {
      for (ChatRoot obj : this)
      {
         obj.withoutMsgs(value);
      }
      
      return this;
   }

}
