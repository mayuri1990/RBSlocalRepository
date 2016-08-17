package com.org.accenture.core.models.AAJava; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUse;

public class AAController extends WCMUse { 

private static final Logger log = LoggerFactory.getLogger(AAController.class.getName()); 

	private AABean aaBean;

	@Override 
	public void activate() throws Exception { 
	}
	public AABean  getAABean() {

	return this.aaBean;
  }
} 
