package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.creators.DestroyObjectElemSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.PatternObjectPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.DestroyObjectElemPO;

public class DestroyObjectElemPO extends PatternObject
{
   public DestroyObjectElemPO startNAC()
   {
      return (DestroyObjectElemPO) super.startNAC();
   }
   
   public DestroyObjectElemPO endNAC()
   {
      return (DestroyObjectElemPO) super.endNAC();
   }
   
   public DestroyObjectElemSet allMatches()
   {
      DestroyObjectElemSet matches = new DestroyObjectElemSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DestroyObjectElem) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public DestroyObjectElemPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DestroyObjectElem) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public DestroyObjectElemPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DestroyObjectElem) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public DestroyObjectElemPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(DestroyObjectElem.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DestroyObjectElem) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PatternObjectPO hasPatternObject()
   {
      PatternObjectPO result = new PatternObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(DestroyObjectElem.PROPERTY_PATTERNOBJECT, result);
      
      return result;
   }
   
   public DestroyObjectElemPO hasPatternObject(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(DestroyObjectElem.PROPERTY_PATTERNOBJECT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObject getPatternObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DestroyObjectElem) this.getCurrentMatch()).getPatternObject();
      }
      return null;
   }
   
}
