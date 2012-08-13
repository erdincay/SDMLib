package org.sdmlib.codegen.creators;

import java.util.ArrayList;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.codegen.creators.StatementEntrySet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.codegen.creators.StatementEntryPO;
import org.sdmlib.models.pattern.LinkConstraint;

public class StatementEntryPO extends PatternObject
{
   public StatementEntryPO startNAC()
   {
      return (StatementEntryPO) super.startNAC();
   }
   
   public StatementEntryPO endNAC()
   {
      return (StatementEntryPO) super.endNAC();
   }
   
   public StatementEntrySet allMatches()
   {
      StatementEntrySet matches = new StatementEntrySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((StatementEntry) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StatementEntryPO hasKind(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getKind()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) getCurrentMatch()).getKind();
      }
      return null;
   }
   
   public StatementEntryPO hasTokenList(ArrayList<String> value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_TOKENLIST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ArrayList<String> getTokenList()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) getCurrentMatch()).getTokenList();
      }
      return null;
   }
   
   public StatementEntryPO hasAssignTargetVarName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_ASSIGNTARGETVARNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getAssignTargetVarName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) getCurrentMatch()).getAssignTargetVarName();
      }
      return null;
   }
   
   public StatementEntryPO hasBodyStats()
   {
      StatementEntryPO result = new StatementEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(StatementEntry.PROPERTY_BODYSTATS, result);
      
      return result;
   }
   
   public StatementEntryPO hasBodyStats(StatementEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(StatementEntry.PROPERTY_BODYSTATS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntrySet getBodyStats()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) this.getCurrentMatch()).getBodyStats();
      }
      return null;
   }
   
   public StatementEntryPO hasParent()
   {
      StatementEntryPO result = new StatementEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(StatementEntry.PROPERTY_PARENT, result);
      
      return result;
   }
   
   public StatementEntryPO hasParent(StatementEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(StatementEntry.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntry getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) this.getCurrentMatch()).getParent();
      }
      return null;
   }
   
}
