package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.RemoteTaskBoard;

public class LanePO extends PatternObject<LanePO, Lane>
{

    public LaneSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LaneSet matches = new LaneSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Lane) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LanePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LanePO(Lane... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LanePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Lane.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LanePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Lane.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LanePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lane) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public LanePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Lane) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public RemoteTaskBoardPO hasBoard()
   {
      RemoteTaskBoardPO result = new RemoteTaskBoardPO(new RemoteTaskBoard[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Lane.PROPERTY_BOARD, result);
      
      return result;
   }

   public RemoteTaskBoardPO createBoard()
   {
      return this.startCreate().hasBoard().endCreate();
   }

   public LanePO hasBoard(RemoteTaskBoardPO tgt)
   {
      return hasLinkConstraint(tgt, Lane.PROPERTY_BOARD);
   }

   public LanePO createBoard(RemoteTaskBoardPO tgt)
   {
      return this.startCreate().hasBoard(tgt).endCreate();
   }

   public RemoteTaskBoard getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lane) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

   public BoardTaskPO hasTasks()
   {
      BoardTaskPO result = new BoardTaskPO(new BoardTask[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Lane.PROPERTY_TASKS, result);
      
      return result;
   }

   public BoardTaskPO createTasks()
   {
      return this.startCreate().hasTasks().endCreate();
   }

   public LanePO hasTasks(BoardTaskPO tgt)
   {
      return hasLinkConstraint(tgt, Lane.PROPERTY_TASKS);
   }

   public LanePO createTasks(BoardTaskPO tgt)
   {
      return this.startCreate().hasTasks(tgt).endCreate();
   }

   public BoardTaskSet getTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lane) this.getCurrentMatch()).getTasks();
      }
      return null;
   }

}