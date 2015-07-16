package org.sdmlib.models.classes.templates;

import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleList;

public class Template implements TemplateTask{
	public static final int DEBUG=1;
	private String searchString;
	private StringBuilder template;
	private int mode=DEBUG;
	private Feature condition;
	private boolean active=true;
	private ClassModel model;
	private SimpleList<ReplaceText> variables=new SimpleList<ReplaceText>();
	
	public Template() {
		
	}

	public Template(String search) {
		this.withSearch(search);
	}

	public boolean validate(Parser parser, ClassModel model) {
		if(!active) {
			return false;
		}
		this.model = model;
		if(condition != null && !model.hasFeature(condition)) {
			return false;
		}
		return parser.indexOf(searchString) >= 0;
	}
	

	public Template withSearch(String value) {
		this.searchString = value;
		return this;
	}
	
	public Template withCondition(boolean condition) {
		if(!condition) {
			this.active = false;
		}
		return this;
	}
	
	public Template withVariable(ReplaceText... values) {
		if(values==null) {
			return this;
		}
		for(ReplaceText item : values) {
			variables.with(item);
		}
		return this;
	}
	
	public Template withVariable(String... values) {
		if(values==null) {
			return this;
		}
		for(String item : values) {
			variables.with(new ReplaceText(item));
		}
		return this;
	}
	
	public ReplaceText get(String value) {
		for(ReplaceText item : variables) {
			if(item .getSearch().equals(value)) {
				return item;
			}
		}
		return null;
	}
	public StringBuilder execute(String... values) {
		if(model == null) {
			if(mode==DEBUG) {
				throw new RuntimeException("model cant be null");
			}
			return null;
		}
		if(values==null || values.length%2==1) {
			if(mode==DEBUG) {
				throw new RuntimeException("values are: "+values);
			}
			return null;
		}
		for(int i=0;i<values.length;i+=2) {
			ReplaceText item = get(values[i]);
			if(item == null) {
				continue;
			}
			if(item.getText(model)!=null){
				if(mode==DEBUG) {
					throw new RuntimeException("Variable not overide: "+values[i]);
				}
				item.withValue(values[i+1]);
			} else {
				item.withValue(values[i+1]);

				String first = values[i].substring(0, 1);
				if(first.toLowerCase() == first) {
					// Maybe autofill
					String temp = first.toUpperCase() + values[i].substring(1);
					item = get(temp); 
					if(item == null ) {
						variables.with(new ReplaceText(temp, StrUtil.upFirstChar(values[i+1])));
					}else if( item.getText(model) == null) {
						item.withValue(StrUtil.upFirstChar(values[i+1]));				
					}
					temp = values[i].toUpperCase();
					item = get(temp); 

					item = variables.indexOf(temp); 
					if(item < 0 || variables.getValueByIndex(item) == null) {
						variables.setValueItem(temp, values[i+1].toUpperCase());
					}
				}
			}
		}
		for(int i = 0; i < variables.size(); i++) {
			if(variables.get(i)==null && mode==DEBUG) {
				throw new RuntimeException("Variable <"+variables.getKeyByIndex(i)+">cant be null: "+values[i]);
			}
		}
		StringBuilder text=new StringBuilder(template.toString());
		// in the second run, replace <$<placeholders>$> by replacement
	    for (int i = 0; i < variables.size(); i ++) {
	    	String placeholder = "{{" + variables.getKeyByIndex(i) + "}}";
	        int pos = -1 - placeholder.length();
	        pos = text.indexOf(variables.get(i), pos + placeholder.length());
	        while (pos >= 0)
	         {
	            String newString = variables.getValueByIndex(i);
	            text.replace(pos, pos + placeholder.length(), newString);
	            pos = text.indexOf(placeholder, pos + newString.length());
	         }
	      }
		return text;
	}

	public Template withTemplate(String value) {
		this.template = new StringBuilder(value);
		return this;
	}
	public Template addTemplate(String value) {
		this.template.append(value);
		return this;
	}


	@Override
	public boolean insert(Parser parser, String... values) {
		 StringBuilder text = execute(values);
		 if(text==null) {
			 return false;
		 }
		 int pos = parser.indexOf(Parser.CLASS_END);
         parser.insert(pos, text.toString());
         return true;
	}
}
