package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.modelsets.booleanList;

public class AttributeConstraintSet extends LinkedHashSet<AttributeConstraint>
{
   public StringList getAttrName()
   {
      StringList result = new StringList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getAttrName());
      }
      
      return result;
   }

   public LinkedHashSet<Object> getTgtValue()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getTgtValue());
      }
      
      return result;
   }

   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }
   public AttributeConstraintSet withAttrName(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withAttrName(value);
      }
      
      return this;
   }

   public AttributeConstraintSet withTgtValue(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withTgtValue(value);
      }
      
      return this;
   }

   public AttributeConstraintSet withHostGraphSrcObject(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withHostGraphSrcObject(value);
      }
      
      return this;
   }

   public AttributeConstraintSet withSrc(PatternObject value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public AttributeConstraintSet withModifier(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public AttributeConstraintSet withHasMatch(boolean value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public AttributeConstraintSet withName(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

}




