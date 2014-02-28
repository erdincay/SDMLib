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
   
package org.sdmlib.examples.ludo.creators;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class FieldSet extends LinkedHashSet<Field> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Field elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.Field";
   }


   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Field obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public FieldSet withColor(String value)
   {
      for (Field obj : this)
      {
         obj.setColor(value);
      }
      
      return this;
   }

   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (Field obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public FieldSet withKind(String value)
   {
      for (Field obj : this)
      {
         obj.setKind(value);
      }
      
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Field obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public FieldSet withX(int value)
   {
      for (Field obj : this)
      {
         obj.setX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Field obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public FieldSet withY(int value)
   {
      for (Field obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

   public PointSet getPoint()
   {
      PointSet result = new PointSet();
      
      for (Field obj : this)
      {
         result.add(obj.getPoint());
      }
      
      return result;
   }

   public FieldSet withPoint(Point value)
   {
      for (Field obj : this)
      {
         obj.setPoint(value);
      }
      
      return this;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Field obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public FieldSet withGame(Ludo value)
   {
      for (Field obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public FieldSet getNext()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }

   public FieldSet withNext(Field value)
   {
      for (Field obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public FieldSet getPrev()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }

   public FieldSet withPrev(Field value)
   {
      for (Field obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public FieldSet getLanding()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getLanding());
      }
      
      return result;
   }

   public FieldSet withLanding(Field value)
   {
      for (Field obj : this)
      {
         obj.withLanding(value);
      }
      
      return this;
   }

   public FieldSet getEntry()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getEntry());
      }
      
      return result;
   }

   public FieldSet withEntry(Field value)
   {
      for (Field obj : this)
      {
         obj.withEntry(value);
      }
      
      return this;
   }

   public PlayerSet getStarter()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getStarter());
      }
      
      return result;
   }

   public FieldSet withStarter(Player value)
   {
      for (Field obj : this)
      {
         obj.withStarter(value);
      }
      
      return this;
   }

   public PlayerSet getBaseowner()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getBaseowner());
      }
      
      return result;
   }

   public FieldSet withBaseowner(Player value)
   {
      for (Field obj : this)
      {
         obj.withBaseowner(value);
      }
      
      return this;
   }

   public PlayerSet getLander()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getLander());
      }
      
      return result;
   }

   public FieldSet withLander(Player value)
   {
      for (Field obj : this)
      {
         obj.withLander(value);
      }
      
      return this;
   }

   public PawnSet getPawns()
   {
      PawnSet result = new PawnSet();
      
      for (Field obj : this)
      {
         result.addAll(obj.getPawns());
      }
      
      return result;
   }

   public FieldSet withPawns(Pawn value)
   {
      for (Field obj : this)
      {
         obj.withPawns(value);
      }
      
      return this;
   }

   public FieldSet withoutPawns(Pawn value)
   {
      for (Field obj : this)
      {
         obj.withoutPawns(value);
      }
      
      return this;
   }



   public FieldPO startModelPattern()
   {
      org.sdmlib.examples.ludo.creators.ModelPattern pattern = new org.sdmlib.examples.ludo.creators.ModelPattern();
      
      FieldPO patternObject = pattern.hasElementFieldPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public FieldSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Field>)value);
      }
      else if (value != null)
      {
         this.add((Field) value);
      }
      
      return this;
   }
   
   public FieldSet without(Field value)
   {
      this.remove(value);
      return this;
   }



   public FieldPO hasFieldPO()
   {
      org.sdmlib.examples.ludo.creators.ModelPattern pattern = new org.sdmlib.examples.ludo.creators.ModelPattern();
      
      FieldPO patternObject = pattern.hasElementFieldPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}



