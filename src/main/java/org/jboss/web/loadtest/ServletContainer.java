/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.web.loadtest;

import org.apache.catalina.Engine;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;


public class ServletContainer {
    private static final String JBOSS_WEB = "web";
    private static final String DEFAULT_HOST = "localhost";
	private Engine engine;
    private StandardServer server;
    private StandardService service;
    
    public synchronized void start() throws Exception {
        final StandardServer server = new StandardServer();

        final StandardService service = new StandardService();
        service.setName(JBOSS_WEB);
        service.setServer(server);
        server.addService(service);

        final Engine engine = new StandardEngine();
        engine.setName(JBOSS_WEB);
        engine.setService(service);
		engine.setDefaultHost(DEFAULT_HOST);

        service.setContainer(engine);
        
        server.init();
        server.start();
        
        this.server = server;
        this.service = service;
        this.engine = engine;

    }
    
    public synchronized void stop() throws LifecycleException {
    	server.stop();
        engine = null;
        service = null;
        server = null;
    }
    
    public synchronized void addConnector(Connector connector) {
        final StandardService service = this.service;
        service.addConnector(connector);
    }

    public synchronized void removeConnector(Connector connector) {
        final StandardService service = this.service;
        service.removeConnector(connector);
    }

}
