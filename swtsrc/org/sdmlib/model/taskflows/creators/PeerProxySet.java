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
   
package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.model.taskflows.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class PeerProxySet extends LinkedHashSet<PeerProxy> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PeerProxy elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.PeerProxy";
   }


   public PeerProxySet with(PeerProxy value)
   {
      this.add(value);
      return this;
   }
   
   public PeerProxySet without(PeerProxy value)
   {
      this.remove(value);
      return this;
   }
   public StringList getIp()
   {
      StringList result = new StringList();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getIp());
      }
      
      return result;
   }

   public PeerProxySet withIp(String value)
   {
      for (PeerProxy obj : this)
      {
         obj.setIp(value);
      }
      
      return this;
   }

   public intList getPort()
   {
      intList result = new intList();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getPort());
      }
      
      return result;
   }

   public PeerProxySet withPort(int value)
   {
      for (PeerProxy obj : this)
      {
         obj.setPort(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public PeerProxySet withIdMap(SDMLibJsonIdMap value)
   {
      for (PeerProxy obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

}
