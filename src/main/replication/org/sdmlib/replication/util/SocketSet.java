/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.replication.util;

import java.net.Socket;
import java.util.Collection;

import de.uniks.networkparser.list.SimpleSet;

public class SocketSet extends SimpleSet<Socket>
{


   public SocketPO hasSocketPO()
   {
      return new SocketPO(this.toArray(new Socket[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public SocketSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Socket>)value);
      }
      else if (value != null)
      {
         this.add((Socket) value);
      }
      
      return this;
   }
   
   public SocketSet without(Socket value)
   {
      this.remove(value);
      return this;
   }


   public static final SocketSet EMPTY_SET = new SocketSet().withFlag(SocketSet.READONLY);


   public SocketPO filterSocketPO()
   {
      return new SocketPO(this.toArray(new Socket[this.size()]));
   }


   public String getEntryType()
   {
      return "java.net.Socket";
   }

   public SocketSet()
   {
      // empty
   }

   public SocketSet(Socket... objects)
   {
      for (Socket obj : objects)
      {
         this.add(obj);
      }
   }

   public SocketSet(Collection<Socket> objects)
   {
      this.addAll(objects);
   }
}
