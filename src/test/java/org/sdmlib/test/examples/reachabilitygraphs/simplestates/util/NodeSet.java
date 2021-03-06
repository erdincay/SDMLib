/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class NodeSet extends SimpleSet<Node>
{


   public NodePO hasNodePO()
   {
      return new NodePO(this.toArray(new Node[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public NodeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Node>)value);
      }
      else if (value != null)
      {
         this.add((Node) value);
      }
      
      return this;
   }
   
   public NodeSet without(Node value)
   {
      this.remove(value);
      return this;
   }

   public intList getNum()
   {
      intList result = new intList();
      
      for (Node obj : this)
      {
         result.add(obj.getNum());
      }
      
      return result;
   }

   public NodeSet hasNum(int value)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (value == obj.getNum())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public NodeSet hasNum(int lower, int upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower <= obj.getNum() && obj.getNum() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public NodeSet withNum(int value)
   {
      for (Node obj : this)
      {
         obj.setNum(value);
      }
      
      return this;
   }

   public SimpleStateSet getGraph()
   {
      SimpleStateSet result = new SimpleStateSet();
      
      for (Node obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }

   public NodeSet hasGraph(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if (neighbors.contains(obj.getGraph()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public NodeSet withGraph(SimpleState value)
   {
      for (Node obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public NodeSet getNext()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.addAll(obj.getNext());
      }
      
      return result;
   }

   public NodeSet hasNext(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public NodeSet getNextTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getNext().minus(result));
         }
      }
      
      return result;
   }

   public NodeSet withNext(Node value)
   {
      for (Node obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public NodeSet withoutNext(Node value)
   {
      for (Node obj : this)
      {
         obj.withoutNext(value);
      }
      
      return this;
   }

   public NodeSet getPrev()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.addAll(obj.getPrev());
      }
      
      return result;
   }

   public NodeSet hasPrev(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public NodeSet getPrevTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getPrev().minus(result));
         }
      }
      
      return result;
   }

   public NodeSet withPrev(Node value)
   {
      for (Node obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public NodeSet withoutPrev(Node value)
   {
      for (Node obj : this)
      {
         obj.withoutPrev(value);
      }
      
      return this;
   }


   public static final NodeSet EMPTY_SET = new NodeSet().withFlag(NodeSet.READONLY);


   public NodePO filterNodePO()
   {
      return new NodePO(this.toArray(new Node[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node";
   }

   /**
    * Loop through the current set of Node objects and collect those Node objects where the num attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet filterNum(int value)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (value == obj.getNum())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Node objects and collect those Node objects where the num attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet filterNum(int lower, int upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower <= obj.getNum() && obj.getNum() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public NodeSet()
   {
      // empty
   }

   public NodeSet(Node... objects)
   {
      for (Node obj : objects)
      {
         this.add(obj);
      }
   }

   public NodeSet(Collection<Node> objects)
   {
      this.addAll(objects);
   }
}
