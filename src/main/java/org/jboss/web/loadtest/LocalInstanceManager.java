package org.jboss.web.loadtest;

import java.lang.reflect.InvocationTargetException;

import javax.naming.NamingException;

import org.apache.tomcat.InstanceManager;

public class LocalInstanceManager implements InstanceManager {

	@Override
	public Object newInstance(String className) throws IllegalAccessException,
			InvocationTargetException, NamingException, InstantiationException,
			ClassNotFoundException {
		return Class.forName(className).newInstance();
	}

	@Override
	public Object newInstance(String fqcn, ClassLoader classLoader)
			throws IllegalAccessException, InvocationTargetException,
			NamingException, InstantiationException, ClassNotFoundException {
		return Class.forName(fqcn, false, classLoader).newInstance();
	}

	@Override
	public Object newInstance(Class<?> c) throws IllegalAccessException,
			InvocationTargetException, NamingException, InstantiationException {
		return c.newInstance();
	}

	@Override
	public void newInstance(Object o) throws IllegalAccessException,
			InvocationTargetException, NamingException {		
	}

	@Override
	public void destroyInstance(Object o) throws IllegalAccessException,
			InvocationTargetException {
	}

}
