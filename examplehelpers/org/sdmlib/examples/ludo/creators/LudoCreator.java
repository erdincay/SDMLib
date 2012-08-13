package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.ludo.Ludo;

public class LudoCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Ludo.PROPERTY_PLAYERS,
      Ludo.PROPERTY_DICE,
      Ludo.PROPERTY_FIELDS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Ludo();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Ludo) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Ludo) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Ludo) entity).removeYou();
   }
}

