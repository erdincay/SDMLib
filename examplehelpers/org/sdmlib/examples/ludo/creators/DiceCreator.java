package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.ludo.Dice;

public class DiceCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Dice.PROPERTY_VALUE,
      Dice.PROPERTY_GAME,
      Dice.PROPERTY_PLAYER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Dice();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Dice) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Dice) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Dice) entity).removeYou();
   }
}

