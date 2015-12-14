package org.sdmlib.models.classes;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.CGUtil;
import org.sdmlib.doc.DocEnvironment;
import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.JavascriptAdapter.Javascript;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.models.classes.util.EnumerationSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Enumeration;
import de.uniks.networkparser.graph.GraphModel;

public class ClassModel extends GraphModel implements PropertyChangeInterface {
	public static final String DEFAULTPACKAGE = "i.love.sdmlib";
	public static final String PROPERTY_CLASSES = "classes";
	private static final String PROPERTY_FEATURE = "feature";
	private Set<Feature> features = Feature.getAll();
	private GenClassModel generator;

	public ClassModel() {
		name = DEFAULTPACKAGE;
		setAuthorName(System.getProperty("user.name"));
		Feature.reset();
	}

	public ClassModel(String packageName)
	   {
		  this();
	      with(packageName);
	   }

	public ClassModel generate() {
		File srcDir = new File("src/main/java");

		if (srcDir.exists()) {
			return generate("src/main/java");
		} else {
			return generate("src");
		}
	}

	public ClassModel generate(String rootDir) {
		getGenerator().generate(rootDir);
		return this;
	}

	public GenClassModel getGenerator() {
		if (generator == null) {
			this.setGenerator(new GenClassModel());
		}
		return generator;
	}

	protected void setGenerator(GenClassModel value) {
		if (this.generator != value) {
			GenClassModel oldValue = this.generator;
			if (this.generator != null) {
				this.generator = null;
				oldValue.setModel(null);
			}
			this.generator = value;
			if (value != null) {
				value.setModel(this);
			}
		}
	}

	public String dumpClassDiagram(String diagName) {
		GuiAdapter graphViz = GraphFactory.getAdapter();

//FIXME		return graphViz.dumpClassDiagram(diagName, this);
		return "";
	}

	private String dumpClassDiagram(String diagName, String outputType) {
		GuiAdapter graphViz = GraphFactory.getAdapter(outputType);
		//FIXME		return graphViz.dumpClassDiagram(diagName, this);
		return "";
	}

	public void removeAllGeneratedCode() {
		getGenerator().removeAllGeneratedCode("src", "src", "src");
	}

	public void removeAllGeneratedCode(String rootDir) {
		getGenerator().removeAllGeneratedCode(rootDir, rootDir, rootDir);
	}

	protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getId());
		return result.substring(1);
	}
	
	public ClassModel with(Clazz... values) {
		super.with(values);
		return this;
	}
	
	@Override
	public ClassModel with(String name) {
		super.with(name);
		return this;
	}

	public ClassModel without(Clazz... values) {
		super.without(values);
		return this;
	}
	

	public ClassModel withFeature(Feature... value) {
		if (value == null) {
			return this;
		}
		for (Feature item : value) {
			if (item != null) {
				if (this.features.add(item)) {
					getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, null, item);
				}
			}
		}
		return this;
	}

	public ClassModel withoutFeature(Feature... value) {
		if (value == null) {
			return this;
		}
		for (Feature item : value) {
			if (item != null) {
				if (this.features.remove(item)) {
					getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, item, null);
				}
			}
		}
		return this;
	}

	public ClassModel withFeatures(HashSet<Feature> value) {
		if (value == null) {
			this.features.clear();
			return this;
		}
		for (Feature item : value) {
			if (item != null) {
				if (this.features.add(item)) {
					getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, null, item);
				}
			}
		}
		return this;
	}

	public boolean hasFeature(Feature value) {
		return features.contains(value);
	}

	public boolean hasFeature(Feature feature, Clazz value) {
		if (hasFeature(feature)) {
//FIXME			return feature.match(value);
		}
		return false;
	}

	/**
	 * dump classdiagram
	 * 
	 * @param diagramName
	 *            Diagrammname
	 */
	public boolean dumpHTML(String diagramName) {
		dumpHTML(diagramName, "doc", Javascript.NAME);
		return true;
	}

	/**
	 * dump classdiagram
	 * 
	 * @param diagramName
	 *            Diagrammname
	 * @param folder
	 *            target folder
	 * @param outputType
	 *            GuiAdapter name (Javascript.NAME or GraphViz.NAME)
	 */

	public void dumpHTML(String diagramName, String folder, String outputType) {
		new File(folder).mkdirs();

		String dumpClassDiagram = dumpClassDiagram(diagramName, outputType);

		// build index
		String htmlTemplate = "<html>\n" + "<head>\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"includes/diagramstyle.css\">\n"
				+ "<script src=\"includes/dagre.min.js\"></script>\n" + "<script src=\"includes/graph.js\"></script>\n"
				+ "<script src=\"includes/drawer.js\"></script>\n" + "</head>\n" + "<body>\n" + "bodytext\n"
				+ "</body>\n" + "</html>\n";

		htmlTemplate = htmlTemplate.replaceFirst("bodytext", dumpClassDiagram);

		File file = new File(folder + "/" + diagramName + ".html");
		try {
			PrintStream out = new PrintStream(file);
			out.println(htmlTemplate);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		new DocEnvironment().copyJS(folder);
	}
	public Clazz getClazz(String name)
	{
		if (name == null) {
			return null;
		}
		for (Clazz c : getClazzes()) {
			if (c.getName().equals(name) ) {
				return c;
			}else if(name.indexOf(".")>0) {
				if(c.getName().equals(name.substring(name.lastIndexOf(".")+1))) {
					return c;
				}
			}else if(c.getName().endsWith("." + name)){
				return c;
			}
		}
		return null;
	}
	   
	   /********************************************************************
	    * <pre>
	    *              one                       many
	    * ClassModel ----------------------------------- Enumeration
	    *              classModel                   enumerations
	    * </pre>
	    */
	   
	   public static final String PROPERTY_ENUMERATIONS = "enumerations";

	   private EnumerationSet enumerations = null;
	   
	   public EnumerationSet getEnumerations()
	   {
	      if (this.enumerations == null)
	      {
	         return EnumerationSet.EMPTY_SET;
	      }
	   
	      return this.enumerations;
	   }

	   public ClassModel withEnumerations(Enumeration... value)
	   {
	      if(value==null){
	         return this;
	      }
	      for (Enumeration item : value)
	      {
	         if (item != null)
	         {
	            if (this.enumerations == null)
	            {
	               this.enumerations = new EnumerationSet();
	            }
	            
	            boolean changed = this.enumerations.add (item);

	            if (changed)
	            {
	               item.with(this);
	               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENUMERATIONS, null, item);
	            }
	         }
	      }
	      return this;
	   } 

	   public ClassModel withoutEnumerations(Enumeration... value)
	   {
	      for (Enumeration item : value)
	      {
	         if ((this.enumerations != null) && (item != null))
	         {
	            if (this.enumerations.remove(item))
	            {
	               item.setClassModel(null);
	               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENUMERATIONS, item, null);
	            }
	         }
	      }
	      return this;
	   }

	public Enumeration createEnumeration(String name) {
		if (this.name == null) {
			this.name = CGUtil.packageName(name);
		}
		Enumeration enumeration = (Enumeration) new Enumeration().with(name);
		withEnumerations(enumeration);
		enumeration.with(this);
		return enumeration;
	} 
}
