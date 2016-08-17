package com.org.accenture.core;

import java.util.Dictionary;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
@Component(label = "Masking Factory configuration", immediate = true, enabled = true, 
metatype = true, 
description = "This is factory configuration which acts as a interface for allowing user to enter mask patterns",
policy = ConfigurationPolicy.REQUIRE, configurationFactory = true)
@Service(MaskingFactoryConfig.class)
public class MaskingFactoryConfig {
	
	@Property(name = "masking.precount", label = "Prefix visible character Count for pattern", description = "Prefix visible character Count for pattern", intValue = 0)
	private Integer preInvisibleCount = 0;
	
	@Property(name = "masking.regex", label = "Regex pattern", description = "Regex pattern", value = "")
	private String regex = "";
	
	@Property(name = "masking.postcount", label = "Postfix visible character Count for pattern", description = "Postfix visible character Count for pattern", intValue = 0)
	private Integer postInvisibleCount = 0;
	
	@Property(name = "masking.char", label = "Masking Character", description = "Masking Character i.e #", value = "#")
	private String maskingChar = "#";
	
	 @Activate      
     public void activate(ComponentContext componentContext) {           
           Dictionary properties = componentContext.getProperties();        
           preInvisibleCount = PropertiesUtil.toInteger(properties.get("masking.precount"), 0);
           postInvisibleCount = PropertiesUtil.toInteger(properties.get("masking.postcount"), 0);
           regex =  PropertiesUtil.toString(properties.get("masking.regex"), "");
           maskingChar = PropertiesUtil.toString(properties.get("masking.char"), "#");
     }
	 
	 @Modified      
     public void modified(ComponentContext componentContext) {           
		    Dictionary properties = componentContext.getProperties();        
	           preInvisibleCount = PropertiesUtil.toInteger(properties.get("masking.precount"), 0);
	           postInvisibleCount = PropertiesUtil.toInteger(properties.get("masking.postcount"), 0);
	           regex =  PropertiesUtil.toString(properties.get("masking.regex"), "");
	           maskingChar = PropertiesUtil.toString(properties.get("masking.char"), "#");
     }

	public Integer getPreInvisibleCount() {
		return preInvisibleCount;
	}


	public String getRegex() {
		return regex;
	}

	public Integer getPostInvisibleCount() {
		return postInvisibleCount;
	}

	public void setPreInvisibleCount(Integer preInvisibleCount) {
		this.preInvisibleCount = preInvisibleCount;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public void setPostInvisibleCount(Integer postInvisibleCount) {
		this.postInvisibleCount = postInvisibleCount;
	}

	public String getMaskingChar() {
		return maskingChar;
	}

	public void setMaskingChar(String maskingChar) {
		this.maskingChar = maskingChar;
	}

}
