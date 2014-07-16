/*
   Copyright (c) 2013 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.models.pattern.util;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

public class DestroyObjectElemSet extends LinkedHashSet<DestroyObjectElem>
{
   private static final long serialVersionUID = 1L;



   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public DestroyObjectElemSet withModifier(String value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public DestroyObjectElemSet withHasMatch(boolean value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }

   public PatternObjectSet getPatternObject()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getPatternObject());
      }
      
      return result;
   }
   public DestroyObjectElemSet withPatternObject(PatternObject value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withPatternObject(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public DestroyObjectElemSet withDoAllMatches(boolean value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   
   public DestroyObjectElemSet withPatternObjectName(String value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (DestroyObjectElem elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.DestroyObjectElem";
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public DestroyObjectElemSet withPattern(Pattern value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   public DestroyObjectElemPO startModelPattern()
   {
      return new DestroyObjectElemPO(this.toArray(new DestroyObjectElem[this.size()]));
   }


   public DestroyObjectElemSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<DestroyObjectElem>)value);
      }
      else if (value != null)
      {
         this.add((DestroyObjectElem) value);
      }
      
      return this;
   }
   
   public DestroyObjectElemSet without(DestroyObjectElem value)
   {
      this.remove(value);
      return this;
   }



   public DestroyObjectElemPO hasDestroyObjectElemPO()
   {
      return new DestroyObjectElemPO(this.toArray(new DestroyObjectElem[this.size()]));
   }
}