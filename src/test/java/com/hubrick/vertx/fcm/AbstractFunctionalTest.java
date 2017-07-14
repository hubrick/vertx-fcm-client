/**
 * Copyright (C) 2017 Etaia AS (oss@hubrick.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hubrick.vertx.fcm;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

/**
 * @author Emir Dizdarevic
 * @since 1.1.0
 */
@RunWith(VertxUnitRunner.class)
public abstract class AbstractFunctionalTest {

    protected static final int MOCKSERVER_PORT = 8089;
    private static MockServerClient mockServerClient = startClientAndServer(MOCKSERVER_PORT);
    protected Vertx vertx;

    protected static MockServerClient getMockServerClient() {
        return mockServerClient;
    }

    @Before
    public void before(TestContext context) {
        mockServerClient.reset();
        vertx = Vertx.vertx();
    }

    @After
    public void after(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }
}
