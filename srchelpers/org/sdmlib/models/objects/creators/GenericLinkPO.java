package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.objects.creators.GenericObjectPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.objects.creators.GenericLinkPO;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.creators.GenericLinkSet;
import org.sdmlib.models.objects.creators.GenericGraphPO;
import org.sdmlib.models.objects.GenericGraph;

public class GenericLinkPO extends PatternObject
{
   public GenericLinkPO startNAC()
   {
      return (GenericLinkPO) super.startNAC();
   }
   
   public GenericLinkPO endNAC()
   {
      return (GenericLinkPO) super.endNAC();
   }
   
   public GenericLinkPO hasTgtLabel(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GenericLink.PROPERTY_TGTLABEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTgtLabel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) getCurrentMatch()).getTgtLabel();
      }
      return null;
   }
   
   public GenericLinkPO hasSrcLabel(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GenericLink.PROPERTY_SRCLABEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getSrcLabel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) getCurrentMatch()).getSrcLabel();
      }
      return null;
   }
   
   public GenericObjectPO hasSrc()
   {
      GenericObjectPO result = new GenericObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericLink.PROPERTY_SRC, result);
      
      return result;   }
   
   public GenericLinkPO hasSrc(GenericObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericLink.PROPERTY_SRC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) this.getCurrentMatch()).getSrc();
      }
      return null;
   }
   
   public GenericObjectPO hasTgt()
   {
      GenericObjectPO result = new GenericObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericLink.PROPERTY_TGT, result);
      
      return result;   }
   
   public GenericLinkPO hasTgt(GenericObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericLink.PROPERTY_TGT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObject getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) this.getCurrentMatch()).getTgt();
      }
      return null;
   }
   
   public GenericGraphPO hasGraph()
   {
      GenericGraphPO result = new GenericGraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericLink.PROPERTY_GRAPH, result);
      
      return result;
   }
   
   public GenericLinkPO hasGraph(GenericGraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericLink.PROPERTY_GRAPH)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericGraph getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

   public GenericLinkPO startCreate()
   {
      this.getPattern().startCreate();
      
      return this;
   }

   public GenericLink getCurrentMatch()
   {
      return (GenericLink) super.getCurrentMatch();
   }
   
   
   public GenericLinkSet allMatches()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      while (this.getPattern().getHasMatch())
      {
         result.add(this.getCurrentMatch());
         
         this.getPattern().findNextMatch();
      }
      
      return result;
   }
   
}

