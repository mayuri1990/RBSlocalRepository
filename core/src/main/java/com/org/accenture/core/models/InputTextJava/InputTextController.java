package com.org.accenture.core.models.InputTextJava; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUse;

public class InputTextController extends WCMUse { 

private static final Logger log = LoggerFactory.getLogger(InputTextController.class.getName()); 

	private InputTextBean inputtextBean;

	@Override 
	public void activate() throws Exception { 
	}
	public InputTextBean  getInputTextBean() {

	return this.inputtextBean;
  }
} 
