package ar.com.gepp.framework.core;

import org.jboss.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gpidote
 * 
 */
@Component
public class FrameWorkApplicationContext implements ApplicationContextAware {

	private static final Logger log = Logger.getLogger(FrameWorkApplicationContext.class);
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
		log.info("FrameWorkApplicationContext: Inicializando contexto de aplicacion " + ctx.getApplicationName());
		applicationContext = ctx;
	}

}
