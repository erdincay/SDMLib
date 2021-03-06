package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

public class Template extends TemplateItem {
	private String searchString = null;
	private Feature condition = null;
	private boolean active=true;
	
	public Template() {
		
	}

	public Template(String search) {
		this.withSearch(search);
	}
	
	public Template withTemplate(String value) {
		super.withTemplate(value);
		return this;
	}

	public boolean validate(Parser parser) {
		return validate(parser, null);
	}
	@Override
	public boolean validate(Parser parser, ClassModel model, String... values) {
		if(!active) {
			return false;
		}
		if(condition != null && !model.hasFeature(condition)) {
			return false;
		}
		for(int i = 0; i < variables.size(); i++) {
			variables.get(i).checking(model);
		}
		if(searchString==null) {
			return true;
		}
		TemplateResult searchText = run(searchString, parser, model, values);
		if(searchText.isEmpty()) {
			return false;
		}
		if(pos>=0) {
			return parser.methodBodyIndexOf(searchText.getTextValue(), pos) <= 0;
		}
		return parser.indexOf(searchText.getTextValue()) <= 0;
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
	
	public Template addTemplate(String value) {
		if(this.template != null) {
			this.template += value;
		}else {
			this.template = value;
		}
		return this;
	}
}
