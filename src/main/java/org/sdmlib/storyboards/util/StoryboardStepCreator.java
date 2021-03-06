package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.StoryboardImpl;
import org.sdmlib.storyboards.StoryboardStep;

import de.uniks.networkparser.IdMap;

public class StoryboardStepCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      StoryboardStep.PROPERTY_TEXT,
      StoryboardStep.PROPERTY_STORYBOARD,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardStep();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (StoryboardStep.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((StoryboardStep)target).getText();
      }

      if (StoryboardStep.PROPERTY_STORYBOARD.equalsIgnoreCase(attrName))
      {
         return ((StoryboardStep)target).getStoryboard();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (StoryboardStep.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((StoryboardStep) target).setText((String) value);
         return true;
      }

      if (StoryboardStep.PROPERTY_STORYBOARD.equalsIgnoreCase(attrName))
      {
         ((StoryboardStep) target).setStoryboard((StoryboardImpl) value);
         return true;
      }
      
      return super.setValue(target, attrName, value, type);
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((StoryboardStep) entity).removeYou();
   }
}

