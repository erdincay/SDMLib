package org.sdmlib.serialization.interfaces;

public class EntityFactory implements SendableEntityCreator
{
   /**
    * Calls entity.removeYou().
    *
    * @param entity the entity to be deleted
    */
   public void removeObject(Object entity)
   {
      
   }

   public Object call(Object entity, String method, Object... args)
   {
      return null;
   }

   @Override
   public String[] getProperties()
   {
      return null;
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return null;
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String type)
   {
      return false;
   }
   
}