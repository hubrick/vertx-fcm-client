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
package com.hubrick.vertx.fcm.client;

import com.google.common.collect.ImmutableList;
import com.hubrick.vertx.fcm.AbstractFunctionalTest;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.JsonBody;

import java.util.List;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author marcus
 * @since 1.0.0
 */
public class FcmDeviceGroupClientTest extends AbstractFunctionalTest {

    public static final String HOSTNAME = "localhost";
    private static final String SERVER_KEY = "test-api-key";
    private static final String SENDER_ID = "1231043129403";

    private FcmDeviceGroupClient fcmDeviceGroupClient;

    @Before
    public void setUp() throws Exception {
        final FcmDeviceGroupClientOptions clientOptions = new FcmDeviceGroupClientOptions();
        clientOptions.setDefaultHost(HOSTNAME);
        clientOptions.setDefaultPort(MOCKSERVER_PORT);
        clientOptions.setMaxPoolSize(10);
        clientOptions.setHostnameOverride(HOSTNAME);

        clientOptions.setFcmSenderId(SENDER_ID);
        clientOptions.setFcmServerKey(SERVER_KEY);

        fcmDeviceGroupClient = new FcmDeviceGroupClient(
                vertx,
                clientOptions);

    }

    @Test
    public void testCreateDeviceGroup(final TestContext testContext) {
        final List<Header> headers = ImmutableList.of(
                Header.header("Authorization", "key=" + SERVER_KEY),
                Header.header("project_id", SENDER_ID));

        final HttpRequest httpRequest = request()
                .withMethod("POST")
                .withPath("/gcm/notification")
                .withHeaders(headers)
                .withBody(JsonBody.json("{\"operation\":\"create\",\"notification_key_name\":\"test-key\",\"registration_ids\":[\"registration-id\"]}"));

        getMockServerClient().when(
                httpRequest
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                        .withBody("{\n" +
                                "   \"notification_key\": \"APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ\"\n" +
                                "}")
        );


        final Async async = testContext.async();

        fcmDeviceGroupClient.createDeviceGroup("test-key", "registration-id")
                .subscribe(deviceGroupResponse -> {
                    testContext.assertEquals(deviceGroupResponse.getNotificationKey(), "APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ");

                    async.complete();
                }, testContext::fail);
    }

    @Test
    public void testAddToDeviceGroup(final TestContext testContext) {
        final List<Header> headers = ImmutableList.of(
                Header.header("Authorization", "key=" + SERVER_KEY),
                Header.header("project_id", SENDER_ID));

        final HttpRequest httpRequest = request()
                .withMethod("POST")
                .withPath("/gcm/notification")
                .withHeaders(headers)
                .withBody(JsonBody.json("{\"operation\":\"add\",\"notification_key_name\":\"test-key\",\"registration_ids\":[\"registration-id\"],\"notification_key\":\"APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ\"}"));

        getMockServerClient().when(
                httpRequest
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                        .withBody("{\n" +
                                "   \"notification_key\": \"APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ\"\n" +
                                "}")
        );


        final Async async = testContext.async();

        fcmDeviceGroupClient.addToDeviceGroup("test-key", "APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ","registration-id")
                .subscribe(deviceGroupResponse -> {
                    testContext.assertEquals(deviceGroupResponse.getNotificationKey(), "APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ");

                    async.complete();
                }, testContext::fail);
    }

    @Test
    public void testRemoveFromDeviceGroup(final TestContext testContext) {
        final List<Header> headers = ImmutableList.of(
                Header.header("Authorization", "key=" + SERVER_KEY),
                Header.header("project_id", SENDER_ID));

        final HttpRequest httpRequest = request()
                .withMethod("POST")
                .withPath("/gcm/notification")
                .withHeaders(headers)
                .withBody(JsonBody.json("{\"operation\":\"remove\",\"notification_key_name\":\"test-key\",\"registration_ids\":[\"registration-id\"],\"notification_key\":\"APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ\"}"));

        getMockServerClient().when(
                httpRequest
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                        .withBody("{\n" +
                                "   \"notification_key\": \"APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ\"\n" +
                                "}")
        );


        final Async async = testContext.async();

        fcmDeviceGroupClient.removeFromDeviceGroup("test-key", "APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ","registration-id")
                .subscribe(deviceGroupResponse -> {
                    testContext.assertEquals(deviceGroupResponse.getNotificationKey(), "APA91bGHXQBB...9QgnYOEURwm0I3lmyqzk2TXQ");

                    async.complete();
                }, testContext::fail);
    }


}
