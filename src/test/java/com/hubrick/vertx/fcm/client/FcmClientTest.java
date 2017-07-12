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
import com.google.common.collect.ImmutableMap;
import com.hubrick.vertx.fcm.AbstractFunctionalTest;
import com.hubrick.vertx.fcm.model.notification.AndroidNotification;
import com.hubrick.vertx.fcm.model.request.SendDataMessageRequest;
import com.hubrick.vertx.fcm.model.request.SendNotificationMessageRequest;
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
public class FcmClientTest extends AbstractFunctionalTest {

    public static final String HOSTNAME = "localhost";
    private static final String SERVER_KEY = "test-api-key";

    private FcmClient fcmClient;

    @Before
    public void setUp() throws Exception {
        final FcmClientOptions clientOptions = new FcmClientOptions();
        clientOptions.setDefaultHost(HOSTNAME);
        clientOptions.setDefaultPort(MOCKSERVER_PORT);
        clientOptions.setMaxPoolSize(10);
        clientOptions.setHostnameOverride(HOSTNAME);

        clientOptions.setFcmServerKey(SERVER_KEY);

        fcmClient = new FcmClient(
                vertx,
                clientOptions);

    }

    @Test
    public void testDataMessageSuccessfull(final TestContext testContext) {
        final List<Header> headers = ImmutableList.of(
                Header.header("Authorization", "key=" + SERVER_KEY));

        final HttpRequest httpRequest = request()
                .withMethod("POST")
                .withPath("/fcm/send")
                .withHeaders(headers)
                .withBody(JsonBody.json("{\"to\":\"test-to\",\"data\":{\"test-key\":\"test-value\"}}"));

        getMockServerClient().when(
                httpRequest
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                        .withBody("{\"success\": 2,\"failure\": 0}")
        );

        final SendDataMessageRequest request = new SendDataMessageRequest("test-to", ImmutableMap.of("test-key", "test-value"));

        final Async async = testContext.async();

        fcmClient.sendMessage(request)
                .subscribe(sendMessageResponse -> {
                    testContext.assertEquals(sendMessageResponse.getSuccess(), 2);
                    testContext.assertEquals(sendMessageResponse.getFailure(), 0);
                    testContext.assertNull(sendMessageResponse.getFailedRegistrationIds());

                    async.complete();
                }, testContext::fail);

    }

    @Test
    public void testDataMessageFail(final TestContext testContext) {
        final List<Header> headers = ImmutableList.of(
                Header.header("Authorization", "key=" + SERVER_KEY));

        final HttpRequest httpRequest = request()
                .withMethod("POST")
                .withPath("/fcm/send")
                .withHeaders(headers)
                .withBody(JsonBody.json("{\"to\":\"test-to\",\"data\":{\"test-key\":\"test-value\"}}"));

        getMockServerClient().when(
                httpRequest
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                        .withBody("{\"success\":1, \"failure\":2, \"failed_registration_ids\":[\"regId1\",\"regId2\"]}")
        );

        final SendDataMessageRequest request = new SendDataMessageRequest("test-to", ImmutableMap.of("test-key", "test-value"));

        final Async async = testContext.async();

        fcmClient.sendMessage(request)
                .subscribe(sendMessageResponse -> {
                    testContext.assertEquals(sendMessageResponse.getSuccess(), 1);
                    testContext.assertEquals(sendMessageResponse.getFailure(), 2);
                    testContext.assertNotNull(sendMessageResponse.getFailedRegistrationIds());
                    testContext.assertEquals(sendMessageResponse.getFailedRegistrationIds().size(), 2);

                    async.complete();
                }, testContext::fail);

    }

    @Test
    public void testNotificationMessageSuccessfull(final TestContext testContext) {
        final List<Header> headers = ImmutableList.of(
                Header.header("Authorization", "key=" + SERVER_KEY));

        final HttpRequest httpRequest = request()
                .withMethod("POST")
                .withPath("/fcm/send")
                .withHeaders(headers)
                .withBody(JsonBody.json("{\"to\":\"test-to\",\"notification\":{\"title\":\"Test notification\"}}"));

        getMockServerClient().when(
                httpRequest
        ).respond(
                response()
                        .withStatusCode(200)
                        .withHeader(Header.header("Content-Type", "application/json;charset=UTF-8"))
                        .withBody("{\"success\": 2,\"failure\": 0}")
        );


        final AndroidNotification notification = new AndroidNotification();
        notification.setTitle("Test notification");
        final SendNotificationMessageRequest request = new SendNotificationMessageRequest("test-to", notification);

        final Async async = testContext.async();

        fcmClient.sendMessage(request)
                .subscribe(sendMessageResponse -> {
                    testContext.assertEquals(sendMessageResponse.getSuccess(), 2);
                    testContext.assertEquals(sendMessageResponse.getFailure(), 0);
                    testContext.assertNull(sendMessageResponse.getFailedRegistrationIds());

                    async.complete();
                }, testContext::fail);

    }

}
