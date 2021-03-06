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
   
package org.sdmlib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.storyboards.GenericIdMap;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class CGUtil 
{
   public static final String SEARCH_POS = "__searchPos__";

   public static boolean isPrimitiveType(String type)
   {
      String primitiveTypes = " String long int char short boolean byte float double Object java.util.Date ";
      
      if (type == null)  return false;
      
      return primitiveTypes.indexOf(" " + type + " ") >= 0;
   }
   
   public static <ST extends SimpleSet> ST instanceOf(SimpleSet<Object> source, ST target)
   {
	   String className;
	   ParameterizedType genericSuperclass = (ParameterizedType) target.getClass().getGenericSuperclass();
	   if(genericSuperclass.getActualTypeArguments().length>0){
		   className = genericSuperclass.getActualTypeArguments()[0].getTypeName();
	   }else{
	      className = target.getClass().getName();
	      className = CGUtil.baseClassName(className, "Set");
	   }
      try
      {
         Class<?> targetClass = target.getClass().getClassLoader().loadClass(className);
         for (Object elem : source)
         {
            if (targetClass.isAssignableFrom(elem.getClass()))
            {
               target.with(elem);
            }
         }
      }
      catch (ClassNotFoundException e) {
      }
      return target;
   }
   
   public static void printFile(Parser parser){
      File file = new File(parser.getFileName());
      if(parser.isFileBodyChanged() || ! file.exists()){
         printFile(file, parser.getText().toString());
      }
   }
   public static void printFile(File file, String text)
   {
      try {
         File parentFile = file.getParentFile();
         if ( ! parentFile.exists())
         {
            parentFile.mkdirs();
         }
         PrintStream out = new PrintStream(file);
         out.print( text );
         out.flush();
         out.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }
   
   public static StringBuilder readFile(String fileName)
   {
      File file = new File(fileName);
      StringBuilder stringBuilder = readFile(file);
      return stringBuilder;
   }
   
   public static StringBuilder readFile(File file)
   {
      StringBuilder result = new StringBuilder();
      try
      {
         BufferedReader in = new BufferedReader(new FileReader(file));
         
         String line = in.readLine();
         while (line != null)
         {
            result.append(line).append('\n');
            line = in.readLine();
         }
         in.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      
      return result;
   }

   public static void replace(StringBuilder fileBody, String placeholder,
         String text)
   {
      // find placeholder
      int pos = fileBody.indexOf(placeholder);
      
      if (pos > 0)
      {
         fileBody.replace(pos, pos+placeholder.length(), text);
      }
   }

   public static String fill(String text, String... args)
   {
      StringBuilder builder = new StringBuilder(text);
      Object[] params = Arrays.asList(args).toArray(new Object[args.length]);
      replaceAll(builder, params);
      return builder.toString();
   }
   
   public static String replaceAll(String text, Object... args)
   {
      StringBuilder buf = new StringBuilder(text);
      replaceAll(buf, args);
      return buf.toString();
   }
   
   public static void replaceAll(StringBuilder text, Object... args)
   {
      int pos = -1 - args[0].toString().length();
      String placeholder;
      // args are pairs of placeholder, replacement
      
      // in the first run, replace placeholders by <$<placeholders>$> to mark them uniquely
      for (int i = 0; i < args.length; i += 2)
      {
         placeholder = args[i].toString();
         pos = -1 - placeholder.length();
         
         pos = text.indexOf(placeholder, pos + placeholder.length());
         
         while (pos >= 0)
         {
            text.replace(pos, pos + placeholder.length(), "<$<" + placeholder + ">$>");
            pos = text.indexOf(placeholder, pos + placeholder.length()+6);
         }
      }
      
      // in the second run, replace <$<placeholders>$> by replacement
      for (int i = 0; i < args.length; i += 2)
      {
         placeholder = "<$<" + args[i] + ">$>";
         pos = -1 - placeholder.length();
           
         pos = text.indexOf(placeholder, pos + placeholder.length());
         
         while (pos >= 0)
         {
            String newString = "" + args[i+1];
            text.replace(pos, pos + placeholder.length(), newString);
            pos = text.indexOf(placeholder, pos + newString.length());
         }
      }
      
   }
   
   public static String encodeHTML(String s)
   {
       StringBuffer out = new StringBuffer();
       for(int i=0; i<s.length(); i++)
       {
           char c = s.charAt(i);
           if(c > 127 || c=='"' || c=='<' || c=='>')
           {
              out.append("&#"+(int)c+";");
           }
           else
           {
               out.append(c);
           }
       }
       return out.toString();
   }
   
   public static String shortClassNameHTMLEncoded(String name)
   {
      return encodeHTML(shortClassName(name));
   }

   public static String shortClassName(String name)
   {
      int pos = name.lastIndexOf('.');
      name = name.substring(pos + 1);
      pos = name.lastIndexOf('$');
      if (pos >= 0)
      {
         name = name.substring(pos + 1);
      }
      return name;
   }

   public static String packageName(String name)
   {
      if (name == null)
      {
         return "";
      }
      
      int pos = name.lastIndexOf('.');
      
      if (pos >= 0)
      {
         return name.substring(0, pos);
      }
      else
      {
         return "";
      }
   }

   public static String helperClassName(String modelClassName, String suffix)
   {
      return packageName(modelClassName) + GenClassModel.UTILPATH + "." + shortClassName(modelClassName) + suffix;
   }
   
   public static String encodeJavaName(String text)
   {
      StringBuilder result = new StringBuilder();
      
      if ( text.length() == 0 || ! Character.isLetter(text.charAt(0)))
      {
         result.append('_');
      }
      
      for(int i = 0; i < text.length(); i++)
      {
         if (Character.isLetter(text.charAt(i)) ||  Character.isDigit(text.charAt(i)))
         {
            result.append(text.charAt(i));
         }
      }
      
      return result.toString();
   }

   public static String baseClassName(String typeName, String suffix)
   {
      if (typeName.endsWith(suffix))
      {
         String className = CGUtil.shortClassName(typeName);
         
         String baseClassName = className.substring(0, className.length() - suffix.length());
         
         
         String packageName = packageName(typeName);
         
         if (packageName.endsWith(GenClassModel.UTILPATH))
         {
            packageName = packageName(packageName);
            
            return packageName + "." + baseClassName;
         }
         
      }
      return typeName;
   }

   public static LinkedHashMap<String, String> find(String newText, int searchPos, String pattern, Object... objects)
   {
      // the pattern contains placeholders and constant text fragments. Match the constant fragments in the new text. 
      // Assign the content in between to the placeholders
      
      LinkedHashMap<String, ObjectSet> placeholderTargets = new LinkedHashMap<String, ObjectSet>();
      
      // find placeholders
      ObjectSet dummyTarget = new ObjectSet();
      int i = 0;
      while (i < objects.length)
      {
         if ( ! (objects[i] instanceof String))
         {
            i++;
            continue;
         }
         
         String placeholder = (String) objects[i];
         i++;
         
         if (i + 1 < objects.length && ! (objects[i] instanceof String))
         {
            // target object and target attribute name
            Object target = objects[i];
            String attrName = (String) objects[i+1];
            ObjectSet value = new ObjectSet().with(target, attrName);
            placeholderTargets.put(placeholder, value);
            
            i += 2;
         }
         else
         {
           
            placeholderTargets.put(placeholder, dummyTarget);
         }
      }
   
      // match placeholders in pattern
      TreeMap<Integer, String> placeholderPositions = new TreeMap<Integer, String>();
      for (String placeholder : placeholderTargets.keySet())
      {
         int pos = pattern.indexOf(placeholder);
         if (pos >= 0)
         {
            placeholderPositions.put(pos, placeholder);
         }
      }
      
      // loop through placeholder positions and find constant fragments
      StringList fragments = new StringList();
      int fragmentPos = 0;
      for (Integer placeholderPos : placeholderPositions.keySet())
      {
         String fragment = pattern.substring(fragmentPos, placeholderPos);
         fragments.add(fragment);
         
         fragmentPos = placeholderPos + placeholderPositions.get(placeholderPos).length();
      }
      
      fragments.add(pattern.substring(fragmentPos, pattern.length()));
      
      // now match fragments and placeholders to newText
      Iterator<String> fragmentsIterator = fragments.iterator();
      String fragment = fragmentsIterator.next();
      
      LinkedHashMap<String, String> placeholderValues = new LinkedHashMap<String, String>();
      
      placeholderValues.put(SEARCH_POS, "" + searchPos);
      
      // newText should start with first fragment
      if (! newText.startsWith(fragment, searchPos))
      {
         // ups
         return placeholderValues;
      }
      
      searchPos += fragment.length();
      // visit the placeholders in order of appearance
      int previousFragmentPos = 0;
      for (Integer placeholderPos : placeholderPositions.keySet())
      {
         // placeholder to be filled
         String placeholder = placeholderPositions.get(placeholderPos);
         
         // content between searchpos and pos of next fragment
         fragment = fragmentsIterator.next();
         fragmentPos = newText.indexOf(fragment, searchPos);
         
         if (fragmentPos >= searchPos)
         {
            // did we find the previous fragment
            if (previousFragmentPos >= 0)
            {
               String value = newText.substring(searchPos, fragmentPos);
               placeholderValues.put(placeholder, value);
            }
            searchPos = fragmentPos + fragment.length();
         }
         previousFragmentPos = fragmentPos;
      }
      
      placeholderValues.put(SEARCH_POS, "" + searchPos);
      
      IdMap map = new GenericIdMap();
      
      // finally assign values to model objects
      for ( String placeholder : placeholderTargets.keySet())
      {
         ObjectSet targetDescription = placeholderTargets.get(placeholder);
         
         if (targetDescription == dummyTarget)
         {
            continue;
         }
         
         Iterator<Object> targetDescriptionIterator = targetDescription.iterator();
         
         Object target = targetDescriptionIterator.next();
         String attrName = (String) targetDescriptionIterator.next();
         
         Object value = placeholderValues.get(placeholder);
         
         SendableEntityCreator creatorClass = map.getCreatorClass(target);
         creatorClass.setValue(target, attrName, value, null);
      }
      
      return placeholderValues;
   }

   public static String[] split(String list)
   {
      return split(list, "[", ", ", "]");
   }
   
   public static String[] split(String list, String prefix, String separator,
         String suffix)
   {
      list = list.substring(prefix.length(), list.length() - suffix.length());
      String[] entries = list.split(separator);
      
      return entries;
   }
   
   public static final String javaKeyWords = " abstract assert boolean break byte case catch char class const continue default do double else enum extends final finally float for if goto implements import instanceof int interface long native new package private protected public return short static strictfp super switch synchronized this throw throws transient try void volatile while ";

   public static String toValidJavaId(String tag)
   {
      if (javaKeyWords.indexOf(" " + tag + " ") >= 0)
      {
         tag = "_" + tag;
      }

      return tag;
   }
   public static final String emfTypes = " EOBJECT EBIG_DECIMAL EBOOLEAN EBYTE EBYTE_ARRAY ECHAR EDATE EDOUBLE EFLOAT EINT EINTEGER ELONG EMAP ERESOURCE ESHORT ESTRING ";

   public static boolean isEMFType(String tag)
   {
      return emfTypes.indexOf(" " + tag.toUpperCase() + " ")>=0;
   }
}
