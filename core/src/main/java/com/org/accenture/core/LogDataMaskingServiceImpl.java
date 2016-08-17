package com.org.accenture.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;


@Service(LogDataMaskingService.class)
@Component(immediate = true, enabled = true)
public class LogDataMaskingServiceImpl implements LogDataMaskingService {

	@Reference(referenceInterface = MaskingFactoryConfig.class, cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
            policy = ReferencePolicy.DYNAMIC)
    public List<MaskingFactoryConfig> maskingFactoryConfig;

    @Activate
    public void activate() {
    	System.out.println("Log masking service is activated");  
    	LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
    	LoggerEvent loggerEvent = new LoggerEvent(maskingFactoryConfig);
    	for (Logger logger : context.getLoggerList()) {
    	        for (Iterator<Appender<ILoggingEvent>> index = logger.iteratorForAppenders(); index.hasNext();) {
    	            Appender<ILoggingEvent> appender = index.next();
    	            appender.addFilter(loggerEvent);
    	        }
    	    }
    	
    }
    
    protected synchronized void bindMaskingFactoryConfig(final MaskingFactoryConfig config) {
        if (maskingFactoryConfig == null) {
        	maskingFactoryConfig = new ArrayList<MaskingFactoryConfig>();
        }
        maskingFactoryConfig.add(config);
   }

   protected synchronized void unbindMaskingFactoryConfig(final MaskingFactoryConfig config) {
	   maskingFactoryConfig.remove(config);
   }
    
    
}
