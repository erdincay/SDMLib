/*
   Copyright (c) 2015 zuendorf 
   
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
   
package org.sdmlib.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.studyrightWithAssignments.model.University;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;

public class UniversitySet extends SDMSet<University>
{

   public static final UniversitySet EMPTY_SET = new UniversitySet().withReadonly(true);


   public UniversityPO hasUniversityPO()
   {
      return new UniversityPO(this.toArray(new University[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.studyrightWithAssignments.model.University";
   }


   @SuppressWarnings("unchecked")
   public UniversitySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<University>)value);
      }
      else if (value != null)
      {
         this.add((University) value);
      }
      
      return this;
   }
   
   public UniversitySet without(University value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (University obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public UniversitySet hasName(String value)
   {
      UniversitySet result = new UniversitySet();
      
      for (University obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UniversitySet hasName(String lower, String upper)
   {
      UniversitySet result = new UniversitySet();
      
      for (University obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UniversitySet withName(String value)
   {
      for (University obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
