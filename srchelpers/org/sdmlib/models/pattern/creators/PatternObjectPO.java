package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.creators.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.creators.PatternObjectSet;
import org.sdmlib.models.pattern.creators.PatternLinkPO;
import org.sdmlib.models.pattern.creators.AttributeConstraintPO;
import org.sdmlib.models.pattern.creators.DestroyObjectElemPO;
import org.sdmlib.models.pattern.creators.CardinalityConstraintPO;
import org.sdmlib.models.pattern.creators.PatternObjectPO;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.creators.CardinalityConstraintSet;
import org.sdmlib.models.pattern.creators.MatchOtherThenPO;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.creators.MatchOtherThenSet;
import org.sdmlib.models.pattern.creators.PatternLinkSet;


public class PatternObjectPO extends PatternObject
{
   public PatternObjectPO hasCurrentMatch(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_CURRENTMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withCurrentMatch(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).withCurrentMatch(value);
      }
      return this;
   }
   
   public PatternObjectPO hasCandidates(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_CANDIDATES)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withCandidates(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).withCandidates(value);
      }
      return this;
   }
   
   public PatternObjectPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public PatternObjectPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public PatternLinkPO hasIncomming()
   {
      PatternLinkPO result = new PatternLinkPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternObject.PROPERTY_INCOMMING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PatternObjectPO hasIncomming(PatternLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_INCOMMING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withIncomming(PatternLinkPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) this.getCurrentMatch()).withIncomming((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternObjectPO withoutIncomming(PatternLinkPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) this.getCurrentMatch()).withoutIncomming((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternLinkPO hasOutgoing()
   {
      PatternLinkPO result = new PatternLinkPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternObject.PROPERTY_OUTGOING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PatternObjectPO hasOutgoing(PatternLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_OUTGOING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withOutgoing(PatternLinkPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) this.getCurrentMatch()).withOutgoing((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternObjectPO withoutOutgoing(PatternLinkPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) this.getCurrentMatch()).withoutOutgoing((PatternLink) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public AttributeConstraintPO hasAttrConstraints()
   {
      AttributeConstraintPO result = new AttributeConstraintPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(PatternObject.PROPERTY_ATTRCONSTRAINTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PatternObjectPO hasAttrConstraints(AttributeConstraintPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_ATTRCONSTRAINTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternObjectPO withAttrConstraints(AttributeConstraintPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) this.getCurrentMatch()).withAttrConstraints((AttributeConstraint) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternObjectPO withoutAttrConstraints(AttributeConstraintPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) this.getCurrentMatch()).withoutAttrConstraints((AttributeConstraint) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public Object getCurrentMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getCurrentMatch();
      }
      return null;
   }
   
   public Object getCandidates()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getCandidates();
      }
      return null;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
//   public PatternLinkSet getIncomming()
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((PatternObject) this.getCurrentMatch()).getIncomming();
//      }
//      return null;
//   }
   
//   public PatternLinkSet getOutgoing()
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((PatternObject) this.getCurrentMatch()).getOutgoing();
//      }
//      return null;
//   }
   
   public AttributeConstraintSet getAttrConstraints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getAttrConstraints();
      }
      return null;
   }
   
   public DestroyObjectElemPO hasDestroyElem()
   {
      DestroyObjectElemPO result = new DestroyObjectElemPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_DESTROYELEM, result);
      
      return result;
   }
   
   public PatternObjectPO hasDestroyElem(DestroyObjectElemPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_DESTROYELEM)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DestroyObjectElem getDestroyElem()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getDestroyElem();
      }
      return null;
   }
   
   public PatternObjectPO hasDoAllMatches(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getDoAllMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public PatternObjectPO hasPatternObjectName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getPatternObjectName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternPO hasPattern()
   {
      PatternPO result = new PatternPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public PatternObjectPO hasPattern(PatternPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternElement.PROPERTY_PATTERN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Pattern getPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return null;
   }

   public PatternObjectPO hasPoName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PatternObject.PROPERTY_PONAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getPoName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) getCurrentMatch()).getPoName();
      }
      return null;
   }
   
   public PatternObjectPO withPoName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternObject) getCurrentMatch()).setPoName(value);
      }
      return this;
   }
   
   public CardinalityConstraintPO hasCardConstraints()
   {
      CardinalityConstraintPO result = new CardinalityConstraintPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_CARDCONSTRAINTS, result);
      
      return result;
   }

   public PatternObjectPO hasCardConstraints(CardinalityConstraintPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_CARDCONSTRAINTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public CardinalityConstraintSet getCardConstraints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getCardConstraints();
      }
      return null;
   }

   public MatchOtherThenPO hasMatchOtherThen()
   {
      MatchOtherThenPO result = new MatchOtherThenPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_MATCHOTHERTHEN, result);
      
      return result;
   }

   public PatternObjectPO hasMatchOtherThen(MatchOtherThenPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_MATCHOTHERTHEN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public MatchOtherThenSet getMatchOtherThen()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getMatchOtherThen();
      }
      return null;
   }

   public MatchOtherThenPO hasExcluders()
   {
      MatchOtherThenPO result = new MatchOtherThenPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternObject.PROPERTY_EXCLUDERS, result);
      
      return result;
   }

   public PatternObjectPO hasExcluders(MatchOtherThenPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternObject.PROPERTY_EXCLUDERS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public MatchOtherThenSet getExcluders()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternObject) this.getCurrentMatch()).getExcluders();
      }
      return null;
   }

//   public PatternLinkSet getOutgoing()
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((PatternObject) this.getCurrentMatch()).getOutgoing();
//      }
//      return null;
//   }

}















