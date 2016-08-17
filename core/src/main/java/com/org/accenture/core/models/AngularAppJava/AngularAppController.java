package com.org.accenture.core.models.AngularAppJava; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUse;

public class AngularAppController extends WCMUse { 

private static final Logger log = LoggerFactory.getLogger(AngularAppController.class.getName()); 

	private AngularAppBean angularappBean;

	@Override 
	public void activate() throws Exception { 
	}
	public AngularAppBean  getAngularAppBean() {

	return this.angularappBean;
  }
} 
