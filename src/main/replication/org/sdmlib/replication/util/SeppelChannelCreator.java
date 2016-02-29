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

import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class SeppelChannelCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      // SeppelChannel.PROPERTY_SOCKET,
      // SeppelChannel.PROPERTY_LOGINVALIDATED,
      // SeppelChannel.PROPERTY_SEPPELSPACEPROXY,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SeppelChannel();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (SeppelChannel.PROPERTY_SOCKET.equalsIgnoreCase(attribute))
      {
         return ((SeppelChannel) target).getSocket();
      }

      if (SeppelChannel.PROPERTY_LOGINVALIDATED.equalsIgnoreCase(attribute))
      {
         return ((SeppelChannel) target).isLoginValidated();
      }

      if (SeppelChannel.PROPERTY_SEPPELSPACEPROXY.equalsIgnoreCase(attribute))
      {
         return ((SeppelChannel) target).getSeppelSpaceProxy();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (SeppelChannel.PROPERTY_SOCKET.equalsIgnoreCase(attrName))
      {
         ((SeppelChannel) target).withSocket((Socket) value);
         return true;
      }

      if (SeppelChannel.PROPERTY_LOGINVALIDATED.equalsIgnoreCase(attrName))
      {
         ((SeppelChannel) target).withLoginValidated((Boolean) value);
         return true;
      }

      if (SeppelChannel.PROPERTY_SEPPELSPACEPROXY.equalsIgnoreCase(attrName))
      {
         ((SeppelChannel) target).setSeppelSpaceProxy((SeppelSpaceProxy) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SeppelChannel) entity).removeYou();
   }
}
