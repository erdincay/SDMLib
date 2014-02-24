package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightWithAssignments.Room;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.University;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentPO;
import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentPO;
import org.sdmlib.examples.studyrightWithAssignments.Assignment;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.TeachingAssistantPO;
import org.sdmlib.examples.studyrightWithAssignments.TeachingAssistant;
import org.sdmlib.examples.studyrightWithAssignments.creators.TeachingAssistantSet;

public class RoomPO extends PatternObject<RoomPO, Room>
{
   public RoomSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RoomSet matches = new RoomSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Room) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void findPath(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Room) getCurrentMatch()).findPath( p0,  p1);
      }
   }

   public RoomPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public RoomPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public RoomPO hasTopic(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_TOPIC)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO hasTopic(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_TOPIC)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO createTopic(String value)
   {
      this.startCreate().hasTopic(value).endCreate();
      return this;
   }
   
   public String getTopic()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getTopic();
      }
      return null;
   }
   
   public RoomPO withTopic(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setTopic(value);
      }
      return this;
   }
   
   public RoomPO hasCredits(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO hasCredits(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO createCredits(int value)
   {
      this.startCreate().hasCredits(value).endCreate();
      return this;
   }
   
   public int getCredits()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getCredits();
      }
      return 0;
   }
   
   public RoomPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setCredits(value);
      }
      return this;
   }
   
   public UniversityPO hasUniversity()
   {
      UniversityPO result = new UniversityPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public UniversityPO createUniversity()
   {
      return this.startCreate().hasUniversity().endCreate();
   }

   public RoomPO hasUniversity(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_UNIVERSITY);
   }

   public RoomPO createUniversity(UniversityPO tgt)
   {
      return this.startCreate().hasUniversity(tgt).endCreate();
   }

   public University getUniversity()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getUniversity();
      }
      return null;
   }

   public RoomPO hasDoors()
   {
      RoomPO result = new RoomPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_DOORS, result);
      
      return result;
   }

   public RoomPO createDoors()
   {
      return this.startCreate().hasDoors().endCreate();
   }

   public RoomPO hasDoors(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_DOORS);
   }

   public RoomPO createDoors(RoomPO tgt)
   {
      return this.startCreate().hasDoors(tgt).endCreate();
   }

   public RoomSet getDoors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getDoors();
      }
      return null;
   }

   public StudentPO hasStudents()
   {
      StudentPO result = new StudentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudents()
   {
      return this.startCreate().hasStudents().endCreate();
   }

   public RoomPO hasStudents(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_STUDENTS);
   }

   public RoomPO createStudents(StudentPO tgt)
   {
      return this.startCreate().hasStudents(tgt).endCreate();
   }

   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

   public AssignmentPO hasAssignments()
   {
      AssignmentPO result = new AssignmentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public AssignmentPO createAssignments()
   {
      return this.startCreate().hasAssignments().endCreate();
   }

   public RoomPO hasAssignments(AssignmentPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_ASSIGNMENTS);
   }

   public RoomPO createAssignments(AssignmentPO tgt)
   {
      return this.startCreate().hasAssignments(tgt).endCreate();
   }

   public AssignmentSet getAssignments()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getAssignments();
      }
      return null;
   }

   public TeachingAssistantPO hasTas()
   {
      TeachingAssistantPO result = new TeachingAssistantPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_TAS, result);
      
      return result;
   }

   public TeachingAssistantPO createTas()
   {
      return this.startCreate().hasTas().endCreate();
   }

   public RoomPO hasTas(TeachingAssistantPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TAS);
   }

   public RoomPO createTas(TeachingAssistantPO tgt)
   {
      return this.startCreate().hasTas(tgt).endCreate();
   }

   public TeachingAssistantSet getTas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getTas();
      }
      return null;
   }

}

