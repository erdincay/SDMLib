package org.sdmlib.models.classes.logic;

import java.util.ArrayList;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.pattern.AttributeConstraint;

public class GenAttribute
{
   private Attribute model;

   public void setModel(Attribute value)
   {
      if (this.model != value)
      {
         Attribute oldValue = this.model;
         if (this.model != null)
         {
            this.model = null;
            oldValue.setGenerator(null);
         }
         this.model = value;
         if (value != null)
         {
            value.setGenerator(this);
         }
      }
   }
   

   private void insertAttrDeclPlusAccessors(Clazz clazz, Parser parser)
   {
      //    int pos = parser.indexOf(Parser.ATTRIBUTE+":" + getName()); //remove

      parser.indexOf(Parser.CLASS_END);
      // add attribute declaration and get, set, with methods in class file
      StringBuilder text = null;

      if(clazz.isInterfaze()) 
      {      
         text = insertPropertyInInterface(parser);     
      }
      else 
      {
         text = insertPropertyInClass(parser);
      }

      if (text == null)
         return;

      String valueCompare = "this.name != value";

      if ("String".equals(model.getType()))
      {
         valueCompare = " ! StrUtil.stringEquals(this.name, value)";
         clazz.getGenerator().insertImport(StrUtil.class.getName()); 
      }

      else if ("Boolean".equals(model.getType()))
      {
         CGUtil.replaceAll(text, "getName()", "isName()");
         clazz.getGenerator().insertImport(StrUtil.class.getName()); 
      }

      CGUtil.replaceAll(text, "valueCompare", valueCompare);

      CGUtil.replaceAll(text, 
         "type", model.getType().getValue(), 
         "name", model.getName(),
         "Name", StrUtil.upFirstChar(model.getName()),
         "NAME", model.getName().toUpperCase(),
         " init", model.getInitialization() == null ? "" : " = " +model.getInitialization(),
               "ownerClass", CGUtil.shortClassName(model.getClazz().getName())
            );

      int pos = parser.indexOf(Parser.CLASS_END);

      parser.getFileBody().insert(pos, text.toString());
      ArrayList<String> importClassesFromTypes = checkImportClassesFromType(model.getType());
      for (String typeImport : importClassesFromTypes) {
         clazz.getGenerator().insertImport(parser, typeImport);
      }
      clazz.getGenerator().setFileHasChanged(true);

   }

   private StringBuilder insertPropertyInClass(Parser parser) 
   {
      boolean hasNewContent = false;
      StringBuilder text;
      text = new StringBuilder( "" +
            "\n   " +
            "\n   //==========================================================================" +
            "\n   " 
            );
      
      if (!entryExist(Parser.ATTRIBUTE+":PROPERTY_" + model.getName().toUpperCase(), parser)
            && ! model.getClazz().isInterfaze())
      {
         text.append("" +
               "\n   public static final String PROPERTY_NAME = \"name\";" +
               "\n   " );
         hasNewContent = true;
      }
      
      if (!entryExist(Parser.ATTRIBUTE+":" + model.getName(), parser))
      {
         text.append("\n   private type name init;\n");
         hasNewContent = true;
      }
      
      if (!entryExist(Parser.METHOD + ":get" + StrUtil.upFirstChar(model.getName())+ "()", parser))
      {
         text.append("\n   public type getName()" +
               "\n   {" +
               "\n      return this.name;" +
               "\n   }" +
               "\n   ");

         hasNewContent = true;
      }

      if (!entryExist(Parser.METHOD + ":set" + StrUtil.upFirstChar(model.getName())+ "(" + model.getType() + ")", parser))
      {
         text.append("\n   public void setName(type value)" +
               "\n   {" +
               "\n      if (valueCompare)" +
               "\n      {PGOLD" +
               "\n         this.name = value;PROPERTYCHANGE"+
               "\n      }" +
               "\n   }" +
               "\n   ");
         
         if(model.getClazz().getClassModel().hasFeature(Feature.PropertyChangeSupport)){
            CGUtil.replaceAll(text, 
                  "PGOLD", "\n         type oldValue = this.name;",
                  "PROPERTYCHANGE", "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);");
         }else{
            CGUtil.replaceAll(text, 
                  "PGOLD", "",
                  "PROPERTYCHANGE", "");
         }
         hasNewContent = true;
      }

      if (!entryExist(Parser.METHOD + ":with" + StrUtil.upFirstChar(model.getName())+ "(" + model.getType() + ")", parser))
      {
         text.append("\n   public ownerClass withName(type value)" +
               "\n   {" +
               "\n      setName(value);" +
               "\n      return this;" +
               "\n   } " +
               "\n");
         hasNewContent = true;
      }
      
      if (hasNewContent)
      {
         return text;
      }
      else
      {
         return null;
      }
   }

   private StringBuilder insertPropertyInInterface(Parser parser) 
   {
      boolean hasNewContent = false;
      StringBuilder text;
      text = new StringBuilder( "" +
            "\n   " +
            "\n   //==========================================================================" +
            "\n   " 
            );
      
      if (!entryExist(Parser.ATTRIBUTE+":PROPERTY_" + model.getName().toUpperCase(), parser))
      {
         text.append("" +
               "\n   public static final String PROPERTY_NAME = \"name\";" +
               "\n   " );
         hasNewContent = true;
      }
      
      if (!entryExist(Parser.METHOD + ":get" + StrUtil.upFirstChar(model.getName())+ "()", parser))
      {
         text.append("\n   public type getName();\n");
         hasNewContent = true;
      }

      if (!entryExist(Parser.METHOD + ":set" + StrUtil.upFirstChar(model.getName())+ "(" + model.getType() + ")", parser))
      {
         text.append("\n   public void setName(type value);\n");
         hasNewContent = true;
      }

      if (!entryExist(Parser.METHOD + ":with" + StrUtil.upFirstChar(model.getName())+ "(" + model.getType() + ")", parser))
      {
         text.append("\n   public ownerClass withName(type value);\n");
         hasNewContent = true;
      }
      
      if (hasNewContent)
      {
         return text;
      }
      else
      {
         return null;
      }
   }

   private boolean entryExist(String string, Parser parser) {
      if (parser.indexOf(string) > -1)
         return true;
      return false;
   }

   private void insertCaseInGenericGetSet(Parser parser)
   {
      insertCaseInGenericGet(parser);
      insertCaseInGenericSet(parser);
   }

   private void insertCaseInGenericSet(Parser parser)
   {
      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
//         System.err.println("Warning: SDMLib codgen for attribute " + getName() + " for class " + getClazz().getName() 
//            + ": \nDid not find method set(String,Object). Should have been generated by my clazz. " 
//            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + model.getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic set method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }

         StringBuilder text = new StringBuilder
               (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attrName))" +
                     "\n      {" +
                     "\n         setPropertyName((type) value);" +
                     "\n         return true;" +
                     "\n      }" +
                     "\n" 
                     );

         String typePlaceholder = "type";
         String type = model.getType().getValue();
         if ("int".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Integer.parseInt(value.toString())";
         }
         else if ("long".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Long.parseLong(value.toString())";
         }
         else if ("double".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Double.parseDouble(value.toString())";
         }
         else if ("float".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Float.parseFloat(value.toString())";
         }
         else if ("boolean".equals(type))
         {
            type = "Boolean";
         }


         CGUtil.replaceAll(text, 
            typePlaceholder, type, 
            "PropertyName", StrUtil.upFirstChar(model.getName()),
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase()
               );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         model.getClazz().getGenerator().setFileHasChanged(true);
      }
   }


   private void insertCaseInGenericGet(Parser parser)
   {
      int pos = parser.indexOf(Parser.METHOD + ":get(String)");

      if (pos < 0)
      {
         if (model.getClazz().isInterfaze())
            // ups, did not find generic get method. 
            System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for class " + model.getClazz().getName() 
               + ": \nDid not find method get(String). Should have been generated by my clazz. " 
               + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + model.getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic get method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }

         StringBuilder text = new StringBuilder
               (  "\n      if (PROPERTY_NAME.equalsIgnoreCase(attrName))" +
                     "\n      {" +
                     "\n         return getPropertyName();" +
                     "\n      }" +
                     "\n" 
                     );

         if ("Boolean".equals(model.getType()))
         {
            CGUtil.replaceAll(text, "getPropertyName()", "isPropertyName()");
         }

         CGUtil.replaceAll(text, 
            "PropertyName", StrUtil.upFirstChar(model.getName()),
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase()
               );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         model.getClazz().getGenerator().setFileHasChanged(true);
      }
   }


   private void insertCaseInToString(Parser parser)
   {
      if ("String int double float".indexOf(CGUtil.shortClassName(model.getType().getValue())) < 0)
      {
         // only standard types contribute to toString()
         return;
      }
      
      // do we have a toString() method?
      int pos = parser.indexOf(Parser.METHOD + ":toString()");

      if (pos < 0)
      {
         // insert empty toString()
         StringBuilder text = new StringBuilder(  
            "\n" +
            "\n   @Override" +
            "\n   public String toString()\n" + 
            "   {\n" + 
            "      StringBuilder _ = new StringBuilder();\n" + 
            "      \n" + 
            "      return _.substring(1);\n" + 
            "   }\n\n" 
               );
         
         pos = parser.indexOf(Parser.CLASS_END);

         parser.getFileBody().insert(pos, text.toString());
         
         model.getClazz().getGenerator().setFileHasChanged(true);
         
         pos = parser.indexOf(Parser.METHOD + ":toString()");
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);
      
      int attrPos = parser.getFileBody().indexOf("get" + StrUtil.upFirstChar(model.getName()), methodBodyStartPos);
      
      boolean noAttr = attrPos < 0 || attrPos > pos;
      
      if ( noAttr)
      {
         attrPos = parser.getFileBody().indexOf(".append(" + model.getName() +")", methodBodyStartPos);
         noAttr = attrPos < 0 || attrPos > pos;
      }
      
      if ( noAttr)
      {         
         // need to add attr to text
         int returnPos = parser.getFileBody().indexOf("return", methodBodyStartPos);
         
         StringBuilder text = new StringBuilder(  
            "_.append(\" \").append(this.getName());\n      " 
               );

         CGUtil.replaceAll(text, 
            "Name", StrUtil.upFirstChar(model.getName())
               );

         parser.getFileBody().insert(returnPos, text.toString());
         model.getClazz().getGenerator().setFileHasChanged(true);
      }
   }
   private ArrayList<String> checkImportClassesFromType(DataType value) 
   {
      String modelSetType = value.getValue();
      ArrayList<String> importList = new ArrayList<String>();
      modelSetType = modelSetType.trim();
      int index = modelSetType.indexOf("<");
      
      if(index >= 0) 
      {
         String listType = modelSetType.substring(0, index);
         
         if ("List Vector HashSet HashMap".indexOf(listType) >= 0)
         {
            listType = "java.util." +listType;
         }
         
         importList.add( listType);
         String substring = modelSetType.substring(index+1, modelSetType.lastIndexOf(">"));
         String[] strings = substring.split(",");
         
         for (String string : strings) 
         {
            Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(string);
            if(findClass != null)
            {
               importList.add(findClass.getName());
            }
         }
      } 
      else 
      {
         Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(modelSetType);
         
         if (findClass != null)
         {
            importList.add(findClass.getName());
         }
      }

      return importList;
   }
   private void insertCreateMethodInPatternObjectClassOneParam(Parser parser, Clazz ownerClazz) 
   {
      String attrType = ownerClazz.getGenerator().shortNameAndImport(model.getType().getValue(), parser);
      String key = Parser.METHOD + ":create"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public PatternObjectType createName(AttrType value)\n" + 
                  "   {\n" + 
                  "      this.startCreate().hasName(value).endCreate();\n" + 
                  "      return this;\n" + 
                  "   }\n" +
               "   \n");

         ownerClazz.getGenerator().insertImport(parser, AttributeConstraint.class.getName());
         String patternObjectType = CGUtil.shortClassName(ownerClazz.getName()) + "PO";

         String modelClass = ownerClazz.getGenerator().shortNameAndImport(ownerClazz.getName(), parser);
         
         if (ownerClazz.getWrapped())
         {
            modelClass = modelClass + "Creator";
         }
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "Name", StrUtil.upFirstChar(model.getName()), 
            "AttrType", attrType, 
            "ModelClass", modelClass);

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         ownerClazz.getGenerator().setPatternObjectFileHasChanged(true);
      }
   }


   private void insertGetterInPatternObjectClass(Parser parser, Clazz ownerClazz) 
   {
      String attrType = ownerClazz.getGenerator().shortNameAndImport(model.getType().getValue(), parser);

      String attrNameUpFirstChar = StrUtil.upFirstChar(model.getName());

      String key = Parser.METHOD + ":get" + attrNameUpFirstChar + "()";

      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public AttrType getAttrName()\n" + 
                  "   {\n" + 
                  "      if (this.getPattern().getHasMatch())\n" + 
                  "      {\n" + 
                  "         return ((ModelClass) getCurrentMatch()).getAttrName();\n" + 
                  "      }\n" + 
                  "      return nullValue;\n" + 
                  "   }\n" +
                  "   \n" +
                  "   public ModelClassPO withAttrName(AttrType value)\n" + 
                  "   {\n" + 
                  "      if (this.getPattern().getHasMatch())\n" + 
                  "      {\n" + 
                  "         ((ModelClass) getCurrentMatch()).setAttrName(value);\n" + 
                  "      }\n" + 
                  "      return this;\n" + 
                  "   }\n" +
                  "   \n"

               );

         String nullValue = "null";

         if ("int long double float".indexOf(attrType) >= 0)
         {
            nullValue = "0";
         }
         else if ("boolean".equals(attrType))
         {
            nullValue = "false";
         }

         CGUtil.replaceAll(text, 
            "nullValue", nullValue,
            "AttrName", StrUtil.upFirstChar(model.getName()), 
            "AttrType", attrType, 
            "ModelClass", ownerClazz.getGenerator().shortNameAndImport(ownerClazz.getName(), parser));

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         ownerClazz.getGenerator().setPatternObjectFileHasChanged(true);
      }
   }

   private String checkSetImportFor(String type) {
      Clazz findClass = model.getClazz().getClassModel().getGenerator().findClass(type);
      if (findClass == null)
         return null;
      Clazz attributClass = model.getClazz();
      String packageNameFromFindClass = CGUtil.packageName(findClass.getName());
      String packageNameFromOwnerClass = CGUtil.packageName(attributClass.getName());
      if (findClass.getWrapped())
      {
         return packageNameFromOwnerClass + ".creators." + CGUtil.shortClassName(findClass.getName()) + "Set";
      }
      else if (!packageNameFromFindClass.equals(packageNameFromOwnerClass)) 
      {
         return packageNameFromFindClass + ".creators." + CGUtil.shortClassName(findClass.getName()) + "Set";
      }
      else 
      {
         return null;
      }
   }
   private void insertHasMethodInPatternObjectClass(Parser parser, Clazz ownerClazz) 
   {
      insertHasMethodInPatternObjectClassOneParam(parser, ownerClazz);
      insertHasMethodInPatternObjectClassRange(parser, ownerClazz);
      insertCreateMethodInPatternObjectClassOneParam(parser, ownerClazz);
   }
   
   private void insertHasMethodInPatternObjectClassRange(Parser parser, Clazz ownerClazz) 
   {
      if ("int long float double String".indexOf(model.getType().getValue()) < 0)
      {
         // range query only for numbers and strings
         return;
      }
      
      
      String attrType = ownerClazz.getGenerator().shortNameAndImport(model.getType().getValue(), parser);
      String key = Parser.METHOD + ":has"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + "," + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public PatternObjectType hasName(AttrType lower, AttrType upper)\n" + 
                  "   {\n" + 
                  "      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()\n" + 
                  "      .withAttrName(ModelClass.PROPERTY_NAME)\n" + 
                  "      .withTgtValue(lower)\n" + 
                  "      .withUpperTgtValue(upper)\n" + 
                  "      .withSrc(this)\n" +
                  "      .withModifier(this.getPattern().getModifier())\n" + 
                  "      .withPattern(this.getPattern());\n" + 
                  "      \n" + 
                  "      this.getPattern().findMatch();\n" + 
                  "      \n" + 
                  "      return this;\n" + 
                  "   }\n" +
               "   \n");

         ownerClazz.getGenerator().insertImport(parser, AttributeConstraint.class.getName());
         String patternObjectType = CGUtil.shortClassName(ownerClazz.getName()) + "PO";

         String modelClass = ownerClazz.getGenerator().shortNameAndImport(ownerClazz.getName(), parser);
         
         if (ownerClazz.getWrapped())
         {
            modelClass = modelClass + "Creator";
         }
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "hasName", "has" + StrUtil.upFirstChar(model.getName()), 
            "AttrType", attrType, 
            "ModelClass", modelClass,
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         ownerClazz.getGenerator().setPatternObjectFileHasChanged(true);
      }
   }


   private void insertHasMethodInPatternObjectClassOneParam(Parser parser, Clazz ownerClazz) 
   {
      String attrType = ownerClazz.getGenerator().shortNameAndImport(model.getType().getValue(), parser);
      String key = Parser.METHOD + ":has"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public PatternObjectType hasName(AttrType value)\n" + 
                  "   {\n" + 
                  "      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()\n" + 
                  "      .withAttrName(ModelClass.PROPERTY_NAME)\n" + 
                  "      .withTgtValue(value)\n" + 
                  "      .withSrc(this)\n" +
                  "      .withModifier(this.getPattern().getModifier())\n" + 
                  "      .withPattern(this.getPattern());\n" + 
                  "      \n" + 
                  "      this.getPattern().findMatch();\n" + 
                  "      \n" + 
                  "      return this;\n" + 
                  "   }\n" +
               "   \n");

         ownerClazz.getGenerator().insertImport(parser, AttributeConstraint.class.getName());
         String patternObjectType = CGUtil.shortClassName(ownerClazz.getName()) + "PO";

         String modelClass = ownerClazz.getGenerator().shortNameAndImport(ownerClazz.getName(), parser);
         
         if (ownerClazz.getWrapped())
         {
            modelClass = modelClass + "Creator";
         }
         
         CGUtil.replaceAll(text, 
            "PatternObjectType", patternObjectType,
            "hasName", "has" + StrUtil.upFirstChar(model.getName()), 
            "AttrType", attrType, 
            "ModelClass", modelClass,
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase());

         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         ownerClazz.getGenerator().setPatternObjectFileHasChanged(true);
      }
   }

   private void insertGetterInModelSetClass(Parser parser, Clazz ownerClazz) {
      if(parser==null){
         return;
      }
      String key = Parser.METHOD + ":get"
            + StrUtil.upFirstChar(model.getName()) + "()";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(""
                  + "   public ModelSetType getName()\n"
                  + "   {\n"
                  + "      ModelSetType result = new ModelSetType();\n"
                  + "      \n"
                  + "      for (ContentType obj : this)\n"
                  + "      {\n"
                  + "         result.addOneOrMore(obj.getName());\n"
                  + "      }\n" + "      \n"
                  + "      return result;\n" 
                  + "   }\n" 
                  + "\n"
                  + 
                  "   public ObjectSetType hasName(AttrType value)\n" + 
                  "   {\n" + 
                  "      ObjectSetType result = new ObjectSetType();\n" + 
                  "      \n" + 
                  "      for (ContentType obj : this)\n" + 
                  "      {\n" + 
                  "         if (valueComparison)\n" + 
                  "         {\n" + 
                  "            result.add(obj);\n" + 
                  "         }\n" + 
                  "      }\n" + 
                  "      \n" + 
                  "      return result;\n" + 
                  "   }\n" + 
                  "\n" );
         
         if ( " int long float double String ".indexOf( " " + model.getType() + " " ) >= 0)
         {
          text.append(
                  "   public ObjectSetType hasName(AttrType lower, AttrType upper)\n" + 
                  "   {\n" + 
                  "      ObjectSetType result = new ObjectSetType();\n" + 
                  "      \n" + 
                  "      for (ContentType obj : this)\n" + 
                  "      {\n" + 
                  "         if (rangeCheck)\n" + 
                  "         {\n" + 
                  "            result.add(obj);\n" + 
                  "         }\n" + 
                  "      }\n" + 
                  "      \n" + 
                  "      return result;\n" + 
                  "   }\n"
                  + "\n"    
               );
         }
         DataType dataType = model.getType();
         String fullModelSetType = dataType.getValue();
         String modelSetType = CGUtil.shortClassName(fullModelSetType);

         ArrayList<String> importClassesFromTypes = new ArrayList<String>();

         if ( ! CGUtil.isPrimitiveType(fullModelSetType) && !fullModelSetType.contains("<") && !fullModelSetType.endsWith("Set")) 
         {
            fullModelSetType = CGUtil.packageName(fullModelSetType) + ".creators." + CGUtil.shortClassName(fullModelSetType)+ "Set";
            modelSetType = CGUtil.shortClassName(fullModelSetType) + "Set";
            String importForSet = checkSetImportFor(CGUtil.shortClassName(fullModelSetType));
            if(importForSet != null)
            {
               importClassesFromTypes.add(importForSet);
            }
            else if ( ! fullModelSetType.startsWith("."))
            {
               importClassesFromTypes.add(fullModelSetType);
            }
         }
         
         String add = "add";

         if (fullModelSetType.contains("<") || fullModelSetType.endsWith("Set")) 
         {
            add = "addAll";
         }

         if (CGUtil.isPrimitiveType(fullModelSetType)) {
            modelSetType = CGUtil.shortClassName(fullModelSetType) + "List";
            fullModelSetType = "org.sdmlib.models.modelsets."
                  + modelSetType;
            importClassesFromTypes.add(fullModelSetType);
            importClassesFromTypes.add("java.util.List");
         }
         else
         {
            // check for enum types
            try
            {
               String innerClassName = CGUtil.packageName(fullModelSetType) + "$" + CGUtil.shortClassName(fullModelSetType); 
               Class<?> forName = Class.forName(innerClassName);
               
               if (forName.isEnum())
               {
                  // use an ArrayList<Enum> as ModelSetType
                  modelSetType = "ArrayList<ElemType>".replaceAll("ElemType", CGUtil.shortClassName(fullModelSetType));
                  importClassesFromTypes.remove(importClassesFromTypes.size()-1);
                  importClassesFromTypes.add("java.util.ArrayList");
               }
            }
            catch (ClassNotFoundException e)
            {
               // did not find class on class path. Thus, it probably still needs to be generated. Ignore 
               // e.printStackTrace();
            }
         }
            
         String objectSetType = CGUtil.shortClassName(ownerClazz.getName() + "Set");

         String valueComparison = "value.equals(obj.get" + StrUtil.upFirstChar(model.getName()) + "())";
         String rangeCheck = "lower.compareTo(obj.get" + StrUtil.upFirstChar(model.getName()) + "()) <= 0 && obj.get" + StrUtil.upFirstChar(model.getName()) + "().compareTo(upper) <= 0";
         
         if ( ! DataType.STRING.equals(model.getType()))
         {
            valueComparison = "value == obj.get" + StrUtil.upFirstChar(model.getName()) + "()";
            rangeCheck = "lower <= obj.get" + StrUtil.upFirstChar(model.getName()) + "() && obj.get" + StrUtil.upFirstChar(model.getName()) + "() <= upper";
         }
         
         CGUtil.replaceAll(text, "ContentType",
            CGUtil.shortClassName(ownerClazz.getName()),
            "ModelSetType", modelSetType, 
            "Name", StrUtil.upFirstChar(model.getName()), 
            "addOneOrMore", add, 
            "ObjectSetType", objectSetType, 
            "AttrType", model.getType().getValue(),
            "valueComparison", valueComparison,
            "rangeCheck", rangeCheck
            );

         //       importClassesFromTypes.addAll(checkImportClassesFromType(fullModelSetType));
         //       
         //       ownerClazz.printFile(true);
         //       
         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         // getClazz()
         ownerClazz.getGenerator().setModelSetFileHasChanged(true);



         // getClazz()
         for (String setType : importClassesFromTypes) {
            ownerClazz.getGenerator().insertImport(parser, setType);
         }

      }
   }
   private void insertCaseInGenericGetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
      int pos = parser.indexOf(Parser.METHOD + ":getValue(Object,String)");

      if (pos < 0)
      {
         if (model.getClazz().isInterfaze())
            // ups, did not find generic get method. 
            System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for class " + CGUtil.shortClassName(model.getClazz().getName()) + "Creator" 
               + ": \nDid not find method get(Object,String). Should have been generated by my clazz. " 
               + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + model.getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic get method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }

         StringBuilder text = new StringBuilder
               (  "\n      if (entitiyClassName.PROPERTY_NAME.equalsIgnoreCase(attrName))" +
                     "\n      {" +
                     "\n         return ((entitiyClassName) target).getPropertyName();" +
                     "\n      }" +
                     "\n" 
                     );

         if ("Boolean".equals(model.getType()))
         {
            CGUtil.replaceAll(text, "getPropertyName()", "isPropertyName()");
         }

         CGUtil.replaceAll(text, 
            "PropertyName", StrUtil.upFirstChar(model.getName()),
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase(), 
            "entitiyClassName", CGUtil.shortClassName(model.getClazz().getName())
               );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         model.getClazz().getGenerator().setFileHasChanged(true);
      }
   }

   private void insertSetterInModelSetClass(Parser parser, Clazz ownerClazz) {
      if(parser==null){
         return;
      }
      String attrType = ownerClazz.getGenerator().shortNameAndImport(model.getType().getValue(), parser);
      String key = Parser.METHOD + ":with"
            + StrUtil.upFirstChar(model.getName()) + "(" + attrType + ")";
      int pos = parser.indexOf(key);

      if (pos < 0) {
         // need to add property to string array

         StringBuilder text = new StringBuilder(
            "   public ModelSetType withName(AttrType value)\n"
                  + "   {\n"
                  + "      for (ContentType obj : this)\n"
                  + "      {\n"
                  + "         obj.setName(value);\n"
                  + "      }\n" + "      \n"
                  + "      return this;\n" 
                  + "   }\n" 
                  + "\n");

//         String fullModelSetType = getType();
         String modelSetType = CGUtil.shortClassName(ownerClazz.getName()) + "Set";

         CGUtil.replaceAll(text, 
            "AttrType", attrType,
            "ContentType", CGUtil.shortClassName(ownerClazz.getName()),
            "ModelSetType", modelSetType, 
            "Name", StrUtil.upFirstChar(model.getName()));

         // ownerClazz.printFile(true);
         int classEnd = parser.indexOf(Parser.CLASS_END);
         parser.getFileBody().insert(classEnd, text.toString());
         ownerClazz.getGenerator().setModelSetFileHasChanged(true);         
      }
   }
   private void insertGenericGetSetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
      insertCaseInGenericGetForWrapperInCreatorClass(parser, ownerClazz);
      insertCaseInGenericSetForWrapperInCreatorClass(parser, ownerClazz);
   }

   private void insertCaseInGenericSetForWrapperInCreatorClass(Parser parser,
         Clazz ownerClazz)
   {
      int pos = parser.indexOf(Parser.METHOD + ":setValue(Object,String,Object,String)");

      if (pos < 0)
      {
         // ups, did not find generic set method. 
         System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for wrapped class " + model.getClazz().getName() 
            + ": \nDid not find method set(Object,String,Object,String) in creator class. Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int methodBodyStartPos = parser.getMethodBodyStartPos();

      pos = parser.methodBodyIndexOf(Parser.NAME_TOKEN + ":PROPERTY_" + model.getName().toUpperCase() , methodBodyStartPos);

      if (pos < 0)
      {         
         // need to add if block to generic set method
         parser.methodBodyIndexOf(Parser.METHOD_END, methodBodyStartPos);

         int lastIfEndPos = parser.lastIfEnd + 2; // add 1 to be after } and 1 to be after \n
         if (lastIfEndPos - 2  < 0)
         {
            lastIfEndPos = methodBodyStartPos + 1;
         }

         StringBuilder text = new StringBuilder
               (  "\n      if (entitiyClassName.PROPERTY_NAME.equalsIgnoreCase(attrName))" +
                     "\n      {" +
                     "\n         ((entitiyClassName) target).setPropertyName((type) value);" +
                     "\n         return true;" +
                     "\n      }" +
                     "\n" 
                     );

         String typePlaceholder = "type";
         DataType dataType = model.getType();
         String type = dataType.getValue();
         if ("int".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Integer.parseInt(value.toString())";
         }
         else if ("long".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Long.parseLong(value.toString())";
         }
         else if ("double".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Double.parseDouble(value.toString())";
         }
         else if ("float".equals(type))
         {
            typePlaceholder = "(type) value";
            type = "Float.parseFloat(value.toString())";
         }
         else if ("boolean".equals(type))
         {
            type = "Boolean";
         }

         CGUtil.replaceAll(text, 
            typePlaceholder, type, 
            "PropertyName", StrUtil.upFirstChar(model.getName()),
            "PROPERTY_NAME", "PROPERTY_" + model.getName().toUpperCase(),
            "entitiyClassName", CGUtil.shortClassName(ownerClazz.getName())
               );

         parser.getFileBody().insert(lastIfEndPos, text.toString());
         model.getClazz().getGenerator().setFileHasChanged(true);
      }
   }
   public GenAttribute generate(String rootDir, String helpersDir)
   {
      generate(model.getClazz(), rootDir, helpersDir, true);

      return this;
   } 

   public GenAttribute generate(String rootDir, String helpersDir, boolean doGenerate)
   {
      generate(model.getClazz(), rootDir, helpersDir, doGenerate);

      return this;
   } 

   public GenAttribute generate(Clazz clazz, String rootDir, String helpersDir)
   {
      generate(clazz, rootDir, helpersDir, true);

      return this;
   } 

   public GenAttribute generate(Clazz clazz, String rootDir, String helpersDir, boolean doGenerate)
   {
      return generate( clazz,  rootDir,  helpersDir,  doGenerate, false);
   }

   public GenAttribute generate(Clazz clazz, String rootDir, String helpersDir, boolean doGenerate, boolean fromSuperClass)
   {
      // get parser from class
      Parser parser;
      if (! clazz.getWrapped())
      {
         parser = clazz.getGenerator().getOrCreateParser(rootDir);
         if (!fromSuperClass)
         {
            insertAttrDeclPlusAccessors(clazz, parser);
         }
         if ( !clazz.isInterfaze())
         {
            insertCaseInGenericGetSet(parser);
            
            insertCaseInToString(parser);
         }

         clazz.getGenerator().printFile(doGenerate);
      }

      if ( !clazz.isInterfaze() && clazz.getClassModel().hasFeature(Feature.Serialization))
      {
         Parser creatorParser = clazz.getGenerator().getOrCreateParserForCreatorClass(helpersDir);

         insertPropertyInCreatorClass(creatorParser, clazz );
         
         clazz.getGenerator().printCreatorFile(doGenerate);
      }

      Parser modelSetParser = clazz.getGenerator().getOrCreateParserForModelSetFile(helpersDir);
      insertGetterInModelSetClass(modelSetParser, clazz);
      insertSetterInModelSetClass(modelSetParser, clazz);
      model.getClazz().getGenerator().printModelSetFile(doGenerate);

      Parser patternObjectParser = clazz.getGenerator().getOrCreateParserForPatternObjectFile(helpersDir);
      insertHasMethodInPatternObjectClass(patternObjectParser, clazz);
      insertGetterInPatternObjectClass(patternObjectParser, clazz);

      return this;
   }

   public void insertPropertyInCreatorClass(String className, Parser creatorParser, String helpersDir, boolean doGenerate) 
   {
      insertPropertyInCreatorClass(creatorParser, model.getClazz());

      int pos = creatorParser.indexOf(Parser.IMPORT);

      String prefix = "";
      StringBuilder fileBody = creatorParser.getFileBody();
      if (fileBody .indexOf(Parser.IMPORT, pos) < 0)
      {
         prefix = "\n";
      }

      SymTabEntry symTabEntry = creatorParser.getSymTab().get(Parser.IMPORT + ":" + className);
      if (symTabEntry == null)
      {
         creatorParser.getFileBody().insert(creatorParser.getEndOfImports() + 1, 
            prefix + "\nimport " + className + ";");

      }      
   }

   private void insertPropertyInCreatorClass(Parser parser, Clazz ownerClazz)
   {
      String key = Parser.ATTRIBUTE + ":properties";
      int pos = parser.indexOf(key);

      if (pos < 0)
      {
         // ups, did not find generic get method. 
         System.err.println("Warning: SDMLib codgen for attribute " + model.getName() + " for creator class for " + model.getClazz().getName() 
            + ": \nDid not find properties field. Should have been generated by my clazz. " 
            + "\nCould not add required code fragment there. :( ");

         return;
      }

      // OK, found method, parse its body to find if that handles me. 
      int endOfStringArrayInit = parser.getEndOfAttributeInitialization();

      String propertyName = "PROPERTY_" + model.getName().toUpperCase();

      int propertyNameIndex = parser.getFileBody().indexOf(propertyName, pos);

      if (propertyNameIndex < 0 || propertyNameIndex > endOfStringArrayInit)
      {         
         // need to add property to string array

         StringBuilder text = new StringBuilder(  "   className.PROPERTY_NAME,\n   ");

         CGUtil.replaceAll(text, 
            "className", CGUtil.shortClassName(model.getClazz().getName()),
            "PROPERTY_NAME", propertyName
               );

         if (ownerClazz.getWrapped())
         {
            // declare the property in the creator class
            text = new StringBuilder(  "   className.PROPERTY_NAME,\n   ");

            CGUtil.replaceAll(text, 
               "className.PROPERTY_NAME", propertyName
                  );
         }

         parser.getFileBody().insert(endOfStringArrayInit, text.toString());

         if (ownerClazz.getWrapped())
         {
            // declare the property
            text = new StringBuilder(  "public static final String PROPERTY_NAME = \"propertyName\";\n   ");

            CGUtil.replaceAll(text, 
               "PROPERTY_NAME", propertyName,
               "propertyName", model.getName()
                  );

            parser.getFileBody().insert(pos, text.toString());
            
         }
         insertGenericGetSetForWrapperInCreatorClass(parser, ownerClazz);

         ownerClazz.getGenerator().insertImport(parser, model.getClazz().getName());
         ownerClazz.getGenerator().setCreatorFileHasChanged(true);
      }
   }
}