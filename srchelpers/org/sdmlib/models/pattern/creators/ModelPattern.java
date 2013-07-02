package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.JsonIdMapPO;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapPO;

import java.lang.StringBuilder;
import org.sdmlib.models.pattern.creators.PatternElementPO;
import org.sdmlib.models.pattern.creators.PatternPO;
import org.sdmlib.models.pattern.creators.StringBuilderPO;
import org.sdmlib.models.pattern.creators.NegativeApplicationConditionPO;
import org.sdmlib.models.pattern.creators.OptionalSubPatternPO;
import org.sdmlib.models.pattern.creators.PatternObjectPO;
import org.sdmlib.models.pattern.creators.PatternLinkPO;
import org.sdmlib.models.pattern.creators.AttributeConstraintPO;
import org.sdmlib.models.pattern.creators.LinkConstraintPO;
import org.sdmlib.models.pattern.creators.MatchIsomorphicConstraintPO;
import org.sdmlib.models.pattern.creators.DestroyObjectElemPO;
import org.sdmlib.models.pattern.creators.CardinalityConstraintPO;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.creators.MatchOtherThenPO;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.creators.ReachabilityGraphPO;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.creators.ReachableStatePO;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.creators.CloneOpPO;
import org.sdmlib.models.pattern.CloneOp;
import org.sdmlib.models.pattern.creators.UnifyGraphsOpPO;
import org.sdmlib.models.pattern.UnifyGraphsOp;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public PatternElementPO hasElementPatternElementPO()
   {
      PatternElementPO value = new PatternElementPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternElementPO hasElementPatternElementPO(PatternElement hostGraphObject)
   {
      PatternElementPO value = new PatternElementPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PatternPO hasElementPatternPO()
   {
      PatternPO value = new PatternPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternPO hasElementPatternPO(Pattern hostGraphObject)
   {
      PatternPO value = new PatternPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public NegativeApplicationConditionPO hasElementNegativeApplicationConditionPO()
   {
      NegativeApplicationConditionPO value = new NegativeApplicationConditionPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public NegativeApplicationConditionPO hasElementNegativeApplicationConditionPO(NegativeApplicationCondition hostGraphObject)
   {
      NegativeApplicationConditionPO value = new NegativeApplicationConditionPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PatternObjectPO hasElementPatternObjectPO()
   {
      PatternObjectPO value = new PatternObjectPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternObjectPO hasElementPatternObjectPO(PatternObject hostGraphObject)
   {
      PatternObjectPO value = new PatternObjectPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PatternLinkPO hasElementPatternLinkPO()
   {
      PatternLinkPO value = new PatternLinkPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternLinkPO hasElementPatternLinkPO(PatternLink hostGraphObject)
   {
      PatternLinkPO value = new PatternLinkPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public AttributeConstraintPO hasElementAttributeConstraintPO()
   {
      AttributeConstraintPO value = new AttributeConstraintPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AttributeConstraintPO hasElementAttributeConstraintPO(AttributeConstraint hostGraphObject)
   {
      AttributeConstraintPO value = new AttributeConstraintPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LinkConstraintPO hasElementLinkConstraintPO()
   {
      LinkConstraintPO value = new LinkConstraintPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LinkConstraintPO hasElementLinkConstraintPO(LinkConstraint hostGraphObject)
   {
      LinkConstraintPO value = new LinkConstraintPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public MatchIsomorphicConstraintPO hasElementMatchIsomorphicConstraintPO()
   {
      MatchIsomorphicConstraintPO value = new MatchIsomorphicConstraintPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public MatchIsomorphicConstraintPO hasElementMatchIsomorphicConstraintPO(MatchIsomorphicConstraint hostGraphObject)
   {
      MatchIsomorphicConstraintPO value = new MatchIsomorphicConstraintPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public DestroyObjectElemPO hasElementDestroyObjectElemPO()
   {
      DestroyObjectElemPO value = new DestroyObjectElemPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public DestroyObjectElemPO hasElementDestroyObjectElemPO(DestroyObjectElem hostGraphObject)
   {
      DestroyObjectElemPO value = new DestroyObjectElemPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public OptionalSubPatternPO hasElementOptionalSubPatternPO()
   {
      OptionalSubPatternPO value = new OptionalSubPatternPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public OptionalSubPatternPO hasElementOptionalSubPatternPO(OptionalSubPattern hostGraphObject)
   {
      OptionalSubPatternPO value = new OptionalSubPatternPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public JsonIdMapPO hasElementJsonIdMapPO()
   {
      JsonIdMapPO value = new JsonIdMapPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public JsonIdMapPO hasElementJsonIdMapPO(JsonIdMap hostGraphObject)
   {
      JsonIdMapPO value = new JsonIdMapPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public SDMLibJsonIdMapPO hasElementSDMLibJsonIdMapPO()
   {
      SDMLibJsonIdMapPO value = new SDMLibJsonIdMapPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SDMLibJsonIdMapPO hasElementSDMLibJsonIdMapPO(SDMLibJsonIdMap hostGraphObject)
   {
      SDMLibJsonIdMapPO value = new SDMLibJsonIdMapPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StringBuilderPO hasElementStringBuilderPO()
   {
      StringBuilderPO value = new StringBuilderPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StringBuilderPO hasElementStringBuilderPO(StringBuilder hostGraphObject)
   {
      StringBuilderPO value = new StringBuilderPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CardinalityConstraintPO hasElementCardinalityConstraintPO()
   {
      CardinalityConstraintPO value = new CardinalityConstraintPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CardinalityConstraintPO hasElementCardinalityConstraintPO(CardinalityConstraint hostGraphObject)
   {
      CardinalityConstraintPO value = new CardinalityConstraintPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public MatchOtherThenPO hasElementMatchOtherThenPO()
   {
      MatchOtherThenPO value = new MatchOtherThenPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public MatchOtherThenPO hasElementMatchOtherThenPO(MatchOtherThen hostGraphObject)
   {
      MatchOtherThenPO value = new MatchOtherThenPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ReachabilityGraphPO hasElementReachabilityGraphPO()
   {
      ReachabilityGraphPO value = new ReachabilityGraphPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReachabilityGraphPO hasElementReachabilityGraphPO(ReachabilityGraph hostGraphObject)
   {
      ReachabilityGraphPO value = new ReachabilityGraphPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ReachableStatePO hasElementReachableStatePO()
   {
      ReachableStatePO value = new ReachableStatePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReachableStatePO hasElementReachableStatePO(ReachableState hostGraphObject)
   {
      ReachableStatePO value = new ReachableStatePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CloneOpPO hasElementCloneOpPO()
   {
      CloneOpPO value = new CloneOpPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CloneOpPO hasElementCloneOpPO(CloneOp hostGraphObject)
   {
      CloneOpPO value = new CloneOpPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public UnifyGraphsOpPO hasElementUnifyGraphsOpPO()
   {
      UnifyGraphsOpPO value = new UnifyGraphsOpPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public UnifyGraphsOpPO hasElementUnifyGraphsOpPO(UnifyGraphsOp hostGraphObject)
   {
      UnifyGraphsOpPO value = new UnifyGraphsOpPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}











