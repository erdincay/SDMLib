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
   
package org.sdmlib.examples.helloworld.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.helloworld.model.Edge;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.helloworld.model.util.GraphSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.helloworld.model.Graph;
import org.sdmlib.examples.helloworld.model.util.NodeSet;
import org.sdmlib.examples.helloworld.model.Node;

public class EdgeSet extends SDMSet<Edge>
{
        private static final long serialVersionUID = 1L;


   public EdgePO hasEdgePO()
   {
      return new EdgePO (this.toArray(new Edge[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.model.Edge";
   }


   public EdgeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
           Collection<?> collection = (Collection<?>) value;
           for(Object item : collection){
               this.add((Edge) item);
           }
      }
      else if (value != null)
      {
         this.add((Edge) value);
      }
      
      return this;
   }
   
   public EdgeSet without(Edge value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Edge obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public EdgeSet hasName(String value)
   {
      EdgeSet result = new EdgeSet();
      
      for (Edge obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EdgeSet withName(String value)
   {
      for (Edge obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public GraphSet getGraph()
   {
      GraphSet result = new GraphSet();
      
      for (Edge obj : this)
      {
         result.with(obj.getGraph());
      }
      
      return result;
   }

   public EdgeSet hasGraph(Object value)
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
      
      EdgeSet answer = new EdgeSet();
      
      for (Edge obj : this)
      {
         if (neighbors.contains(obj.getGraph()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public EdgeSet withGraph(Graph value)
   {
      for (Edge obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public NodeSet getSrc()
   {
      NodeSet result = new NodeSet();
      
      for (Edge obj : this)
      {
         result.with(obj.getSrc());
      }
      
      return result;
   }

   public EdgeSet hasSrc(Object value)
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
      
      EdgeSet answer = new EdgeSet();
      
      for (Edge obj : this)
      {
         if (neighbors.contains(obj.getSrc()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public EdgeSet withSrc(Node value)
   {
      for (Edge obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public NodeSet getTgt()
   {
      NodeSet result = new NodeSet();
      
      for (Edge obj : this)
      {
         result.with(obj.getTgt());
      }
      
      return result;
   }

   public EdgeSet hasTgt(Object value)
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
      
      EdgeSet answer = new EdgeSet();
      
      for (Edge obj : this)
      {
         if (neighbors.contains(obj.getTgt()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public EdgeSet withTgt(Node value)
   {
      for (Edge obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Edge obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public EdgeSet hasText(String value)
   {
      EdgeSet result = new EdgeSet();
      
      for (Edge obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EdgeSet withText(String value)
   {
      for (Edge obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public GraphSet getParent()
   {
      GraphSet result = new GraphSet();
      
      for (Edge obj : this)
      {
         result.with(obj.getParent());
      }
      
      return result;
   }

   public EdgeSet hasParent(Object value)
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
      
      EdgeSet answer = new EdgeSet();
      
      for (Edge obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public EdgeSet withParent(Graph value)
   {
      for (Edge obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}





















