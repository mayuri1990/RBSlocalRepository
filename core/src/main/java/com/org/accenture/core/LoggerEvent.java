package com.org.accenture.core;

import java.lang.reflect.Field;
import java.util.List;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;


public class LoggerEvent extends Filter<ILoggingEvent> {
	
	public List<MaskingFactoryConfig> factoryConfig;
	
	public LoggerEvent(List<MaskingFactoryConfig> factoryConfig){
		this.factoryConfig = factoryConfig;
	}

	  @Override
	  public FilterReply decide(ILoggingEvent event) { 
		  if(null == factoryConfig || factoryConfig.isEmpty()){
			  return FilterReply.NEUTRAL;
		  }
		  try {
              Field field = event.getClass().getDeclaredField("message");
              field.setAccessible(true);
              field.set(event, MaskingUtils.maskString(event.getMessage(), factoryConfig));
          } catch (Exception e) {
              // Dont log it as it will lead to infinite loop. Simply print the trace
             // e.printStackTrace();
          }
		  return FilterReply.NEUTRAL;
	  }
	}
