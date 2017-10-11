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

import com.google.common.base.Strings;
import com.hubrick.vertx.fcm.model.request.SendMessageRequest;
import com.hubrick.vertx.fcm.model.response.SendMessageResponse;
import com.hubrick.vertx.rest.MediaType;
import com.hubrick.vertx.rest.RestClientRequest;
import com.hubrick.vertx.rest.rx.RxRestClient;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Single;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class FcmClient extends AbstractFcmClient {

    private static final Logger log = LoggerFactory.getLogger(FcmClient.class);

    private static final String DEFAULT_HOSTNAME = "fcm.googleapis.com";
    private static final String FCM_SEND_ENDPOINT = "/fcm/send";

    private final Long globalTimeout;
    private final String hostname;

    private final RxRestClient client;
    private final String fcmServerKey;

    public FcmClient(Vertx vertx, FcmClientOptions fcmClientOptions) {
        checkNotNull(vertx, "vertx must not be null");
        checkArgument(isNotBlank(fcmClientOptions.getFcmServerKey()), "FCM server key must be set");
        checkNotNull(fcmClientOptions.getGlobalTimeoutMs(), "global timeout must be null");
        checkArgument(fcmClientOptions.getGlobalTimeoutMs() > 0, "global timeout must be more than zero ms");

        this.fcmServerKey = fcmClientOptions.getFcmServerKey();
        this.globalTimeout = fcmClientOptions.getGlobalTimeoutMs();

        final String hostnameOverride = fcmClientOptions.getHostnameOverride();
        if (!Strings.isNullOrEmpty(hostnameOverride)) {
            hostname = hostnameOverride;
        } else {
            hostname = DEFAULT_HOSTNAME;
        }

        final FcmClientOptions options = new FcmClientOptions(fcmClientOptions);
        options.setDefaultHost(hostname);

        this.client = RxRestClient.create(vertx, options, makeConverter());
    }

    public String getHostname() {
        return hostname;
    }

    public Long getGlobalTimeout() {
        return globalTimeout;
    }


    public Single<SendMessageResponse> sendMessage(final SendMessageRequest messageRequest) {
        checkNotNull(messageRequest, "messageRequest must not be null");

        return client.post(FCM_SEND_ENDPOINT, SendMessageResponse.class, restClientRequest -> applyHeaders(restClientRequest).end(messageRequest))
                .toSingle()
                .map(response -> response.getBody());
    }

    private <T> RestClientRequest<T> applyHeaders(final RestClientRequest<T> restRequest) {
        restRequest.putHeader("Authorization", "key=" + fcmServerKey);

        restRequest.setContentType(MediaType.APPLICATION_JSON);

        return restRequest;
    }

}
