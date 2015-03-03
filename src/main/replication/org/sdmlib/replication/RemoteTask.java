/*
   Copyright (c) 2014 christian 
   
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
   
package org.sdmlib.replication;


public abstract class RemoteTask extends Task implements Runnable
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      withoutLogEntries(this.getLogEntries().toArray(new LogEntry[this.getLogEntries().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_BOARDTASK = "boardTask";
   
   private BoardTask boardTask;

   public BoardTask getBoardTask()
   {
      return this.boardTask;
   }
   
   public void setBoardTask(BoardTask value)
   {
      if (this.boardTask != value)
      {
         BoardTask oldValue = this.boardTask;
         this.boardTask = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOARDTASK, oldValue, value);
      }
   }
   
   public RemoteTask withBoardTask(BoardTask value)
   {
      setBoardTask(value);
      return this;
   }

}
