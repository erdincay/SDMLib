/*
   Copyright (c) 2016 christoph
   
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

import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.pattern.ReachabilityGraph;

public class OptionalSubPatternCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      OptionalSubPattern.PROPERTY_MATCHFORWARD,
      Pattern.PROPERTY_DEBUGMODE,
      Pattern.PROPERTY_NAME,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      Pattern.PROPERTY_ELEMENTS,
      Pattern.PROPERTY_CURRENTSUBPATTERN,
      OptionalSubPattern.PROPERTY_RGRAPH,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new OptionalSubPattern();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (OptionalSubPattern.PROPERTY_MATCHFORWARD.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern) target).isMatchForward();
      }

      if (Pattern.PROPERTY_DEBUGMODE.equalsIgnoreCase(attribute))
      {
         return ((Pattern) target).getDebugMode();
      }

      if (Pattern.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Pattern) target).getName();
      }

      if (PatternElement.PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).getModifier();
      }

      if (PatternElement.PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).isHasMatch();
      }

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).getPatternObjectName();
      }

      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).isDoAllMatches();
      }

      if (OptionalSubPattern.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern) target).getPattern();
      }

      if (OptionalSubPattern.PROPERTY_ELEMENTS.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern) target).getElements();
      }

      if (OptionalSubPattern.PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern) target).getCurrentSubPattern();
      }

      if (OptionalSubPattern.PROPERTY_RGRAPH.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern) target).getRgraph();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setDoAllMatches((Boolean) value);
         return true;
      }

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setPatternObjectName((String) value);
         return true;
      }

      if (PatternElement.PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setHasMatch((Boolean) value);
         return true;
      }

      if (PatternElement.PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setModifier((String) value);
         return true;
      }

      if (Pattern.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Pattern) target).setName((String) value);
         return true;
      }

      if (Pattern.PROPERTY_DEBUGMODE.equalsIgnoreCase(attrName))
      {
         ((Pattern) target).setDebugMode(Integer.parseInt(value.toString()));
         return true;
      }

      if (OptionalSubPattern.PROPERTY_MATCHFORWARD.equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).setMatchForward((Boolean) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (OptionalSubPattern.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).setPattern((Pattern) value);
         return true;
      }

      if (OptionalSubPattern.PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).withElements((PatternElement) value);
         return true;
      }
      
      if ((OptionalSubPattern.PROPERTY_ELEMENTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).withoutElements((PatternElement) value);
         return true;
      }

      if (OptionalSubPattern.PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).setCurrentSubPattern((Pattern) value);
         return true;
      }

      if (OptionalSubPattern.PROPERTY_RGRAPH.equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).setRgraph((ReachabilityGraph) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((OptionalSubPattern) entity).removeYou();
   }
}
