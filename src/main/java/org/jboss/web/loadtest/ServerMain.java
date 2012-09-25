

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

import org.apache.catalina.connector.Connector;

public class ServerMain {
	public static void main(String [ ] args) {
		Connector connector = null;
		try {
			connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		} catch (Exception e) {
			System.out.println("Can't create connector: " + e);
		}
		ServletContainer cont = new ServletContainer();
		try {
			cont.start();
		} catch (Exception e) {
			System.out.println("Can't start: " + e);
		}
		cont.addConnector(connector);
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// Ignored...
		}
	}

}
