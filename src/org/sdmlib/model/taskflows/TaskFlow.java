/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.model.taskflows;

import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.utils.PropertyChangeInterface;

public abstract class TaskFlow extends TimerTask implements PropertyChangeInterface
{
	public abstract Object[] getTaskNames();
	
	public void switchTo(PeerProxy peer)
   {
      taskNo++;
      
      peer.transferTaskFlow(getRootParent());
   }
   
   public void switchToThisAnd(PeerProxy peer)
   {
      taskNo++;
      
      peer.transferTaskFlow(getRootParent());
		
		this.run();
   }
   
   public void switchTo(PeerProxySet peers)
   {
      taskNo++;
      
      for (PeerProxy peer : peers)
      {
         peer.transferTaskFlow(getRootParent());
      }
   }
   
//   public void switchTo(Display display)
//   {
//      taskNo++;
//      
//      display.asyncExec(this);
//   }
//   
   public void switchTo(Timer timer)
   {
      taskNo++;
      
      timer.schedule(this, 0);
   }

   //==========================================================================
   
   public void switchTo(int targetAction, PeerProxy peer,
         TreeSet<PeerProxy> proxies)
   {
      // visit all peers in perfect shuffle mode
      taskNo = targetAction;
      
      // find the number of myself in the proxies
      int i = 0;
      for (PeerProxy peerProxy : proxies)
      {
         if (peerProxy.equals(peer))
         {
            // found myself
            break;
         }
         i++;
      }
      
      if (i == proxies.size())
      {
         // no good, I should be in that list
         throw new RuntimeException("no good, I should be in that list");
      }
      
      // send myself to 3 * i + 1 and the next two
      PeerProxy[] peerArray = proxies.toArray(new PeerProxy[] {});
      
      int tgt = (3 * i + 1) % proxies.size();
      peerArray[tgt].transferTaskFlow(getRootParent());
      
      tgt = (tgt + 1) % proxies.size();
      peerArray[tgt].transferTaskFlow(getRootParent());
      
      tgt = (tgt + 1) % proxies.size();
      peerArray[tgt].transferTaskFlow(getRootParent());
   }

   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         return getSubFlow();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         setSubFlow((TaskFlow) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((TaskFlow) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setSubFlow(null);
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


//   @Override
//   public void widgetSelected(SelectionEvent e)
//   {
//      run();
//   }
//
//
//   @Override
//   public void widgetDefaultSelected(SelectionEvent e)
//   {
//      run();
//   }
//
   //==========================================================================
   
   public static final String PROPERTY_IDMAP = "idMap";
   
   protected org.sdmlib.serialization.json.SDMLibJsonIdMap idMap;

   public org.sdmlib.serialization.json.SDMLibJsonIdMap getIdMap()
   {
      return this.idMap;
   }
   
   public void setIdMap(org.sdmlib.serialization.json.SDMLibJsonIdMap value)
   {
      if (this.idMap != value)
      {
         org.sdmlib.serialization.json.SDMLibJsonIdMap oldValue = this.idMap;
         this.idMap = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IDMAP, oldValue, value);
      }
   }
   
   public TaskFlow withIdMap(org.sdmlib.serialization.json.SDMLibJsonIdMap value)
   {
      setIdMap(value);
      return this;
   }
   
   
   //==========================================================================
   
   public static final String PROPERTY_TASKNO = "taskNo";
   
   protected int taskNo;

   public int getTaskNo()
   {
      return this.taskNo;
   }
   
   public void setTaskNo(int value)
   {
      if (this.taskNo != value)
      {
         int oldValue = this.taskNo;
         this.taskNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKNO, oldValue, value);
      }
   }
   
   public TaskFlow withTaskNo(int value)
   {
      setTaskNo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * TaskFlow ----------------------------------- TaskFlow
    *              parent                   subFlow
    * </pre>
    */
   
   public static final String PROPERTY_SUBFLOW = "subFlow";
   
   private TaskFlow subFlow = null;
   
   public TaskFlow getSubFlow()
   {
      return this.subFlow;
   }
   
   public boolean setSubFlow(TaskFlow value)
   {
      boolean changed = false;
      
      if (this.subFlow != value)
      {
         TaskFlow oldValue = this.subFlow;
         
         if (this.subFlow != null)
         {
            this.subFlow = null;
            oldValue.setParent(null);
         }
         
         this.subFlow = value;
         
         if (value != null)
         {
            value.withParent(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBFLOW, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public TaskFlow withSubFlow(TaskFlow value)
   {
      setSubFlow(value);
      return this;
   } 
   
   public TaskFlow createSubFlow()
   {
      return null;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * TaskFlow ----------------------------------- TaskFlow
    *              subFlow                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";
   
   private TaskFlow parent = null;
   
   public TaskFlow getRootParent()
   {
      TaskFlow root = this;
      
      while (root.getParent() != null)
      {
         root = root.getParent();
      }
      
      return root;
   }
   
   public TaskFlow getParent()
   {
      return this.parent;
   }
   
   public boolean setParent(TaskFlow value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         TaskFlow oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.setSubFlow(null);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withSubFlow(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public TaskFlow withParent(TaskFlow value)
   {
      setParent(value);
      return this;
   } 
   
   public TaskFlow createParent()
   {
      return null;
   }

   public boolean isDone()
   {
      // TODO Auto-generated method stub
      return false;
   } 
}

