package org.sdmlib.simple.model.attribute_d.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.attribute_d.Person;

import de.uniks.networkparser.list.SimpleSet;

public class PersonPO extends PatternObject<PersonPO, Person>
{

    public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonPO(){
      newInstance(null);
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createNamesCondition(SimpleSet<String> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNamesAssignment(SimpleSet<String> value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAMES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SimpleSet<String> getNames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getNames();
      }
      return null;
   }
   
   public PersonPO withNames(SimpleSet<String> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setNames(value);
      }
      return this;
   }
   
}
