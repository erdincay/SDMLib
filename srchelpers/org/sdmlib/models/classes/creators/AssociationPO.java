package org.sdmlib.models.classes.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.creators.AssociationSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.classes.creators.ClassModelPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.classes.creators.AssociationPO;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.creators.RolePO;
import org.sdmlib.models.classes.Role;

public class AssociationPO extends PatternObject
{
   public AssociationPO startNAC()
   {
      return (AssociationPO) super.startNAC();
   }
   
   public AssociationPO endNAC()
   {
      return (AssociationPO) super.endNAC();
   }
   
   public AssociationSet allMatches()
   {
      AssociationSet matches = new AssociationSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Association) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ClassModelPO hasModel()
   {
      ClassModelPO result = new ClassModelPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Association.PROPERTY_MODEL, result);
      
      return result;
   }
   
   public AssociationPO hasModel(ClassModelPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Association.PROPERTY_MODEL)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClassModel getModel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Association) this.getCurrentMatch()).getModel();
      }
      return null;
   }
   
   public RolePO hasSource()
   {
      RolePO result = new RolePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Association.PROPERTY_SOURCE, result);
      
      return result;
   }
   
   public AssociationPO hasSource(RolePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Association.PROPERTY_SOURCE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Role getSource()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Association) this.getCurrentMatch()).getSource();
      }
      return null;
   }
   
   public RolePO hasTarget()
   {
      RolePO result = new RolePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Association.PROPERTY_TARGET, result);
      
      return result;
   }
   
   public AssociationPO hasTarget(RolePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Association.PROPERTY_TARGET)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Role getTarget()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Association) this.getCurrentMatch()).getTarget();
      }
      return null;
   }
   
}
