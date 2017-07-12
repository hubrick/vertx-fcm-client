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

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.hubrick.vertx.fcm.model.common.DeviceGroupOperation;
import com.hubrick.vertx.fcm.model.request.DeviceGroupCreateRequest;
import com.hubrick.vertx.fcm.model.request.DeviceGroupModifyRequest;
import com.hubrick.vertx.fcm.model.response.DeviceGroupResponse;
import com.hubrick.vertx.rest.MediaType;
import com.hubrick.vertx.rest.RestClientRequest;
import com.hubrick.vertx.rest.rx.RxRestClient;
import io.vertx.core.Vertx;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Single;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class FcmDeviceGroupClient extends AbstractFcmClient {

    private static final Logger log = LoggerFactory.getLogger(FcmDeviceGroupClient.class);

    private static final String DEFAULT_HOSTNAME = "android.googleapis.com";
    private static final String GCM_NOTIFICATION_ENDPOINT = "/gcm/notification";

    private final Long globalTimeout;
    private final String hostname;

    private final RxRestClient client;
    private final String fcmServerKey;
    private final String fcmSenderId;

    public FcmDeviceGroupClient(Vertx vertx, FcmDeviceGroupClientOptions fcmDeviceGroupClientOptions) {
        checkNotNull(vertx, "vertx must not be null");
        checkArgument(isNotBlank(fcmDeviceGroupClientOptions.getFcmSenderId()), "FCM sender ID must be set");
        checkArgument(isNotBlank(fcmDeviceGroupClientOptions.getFcmServerKey()), "FCM server key must be set");
        checkNotNull(fcmDeviceGroupClientOptions.getGlobalTimeoutMs(), "global timeout must be null");
        checkArgument(fcmDeviceGroupClientOptions.getGlobalTimeoutMs() > 0, "global timeout must be more than zero ms");

        this.fcmServerKey = fcmDeviceGroupClientOptions.getFcmServerKey();
        this.fcmSenderId = fcmDeviceGroupClientOptions.getFcmSenderId();
        this.globalTimeout = fcmDeviceGroupClientOptions.getGlobalTimeoutMs();

        final String hostnameOverride = fcmDeviceGroupClientOptions.getHostnameOverride();
        if (!Strings.isNullOrEmpty(hostnameOverride)) {
            hostname = hostnameOverride;
        } else {
            hostname = DEFAULT_HOSTNAME;
        }

        final FcmDeviceGroupClientOptions options = new FcmDeviceGroupClientOptions(fcmDeviceGroupClientOptions);
        options.setDefaultHost(hostname);

        this.client = RxRestClient.create(vertx, options, makeConverter());
    }

    public String getHostname() {
        return hostname;
    }

    public Long getGlobalTimeout() {
        return globalTimeout;
    }

    public Single<DeviceGroupResponse> createDeviceGroup(
                          final String notificationKeyName,
                          final String... registrationIds) {
        Preconditions.checkArgument(StringUtils.isNotBlank(notificationKeyName), "IosNotification key name must be set");
        Preconditions.checkArgument(ArrayUtils.isNotEmpty(registrationIds), "registrationIds must not be empty");

        final DeviceGroupCreateRequest createRequest = new DeviceGroupCreateRequest(notificationKeyName, registrationIds);

        return client.post(GCM_NOTIFICATION_ENDPOINT, DeviceGroupResponse.class, restClientRequest -> applyHeaders(restClientRequest).end(createRequest))
                     .toSingle()
                     .map(response -> response.getBody());
    }

    public Single<DeviceGroupResponse> addToDeviceGroup(
            final String notificationKeyName,
            final String notificationKey,
            final String... registrationIds) {
        return modifyDeviceGroup(DeviceGroupOperation.ADD, notificationKeyName, notificationKey, registrationIds);
    }

    public Single<DeviceGroupResponse> removeFromDeviceGroup(
            final String notificationKeyName,
            final String notificationKey,
            final String... registrationIds) {
        return modifyDeviceGroup(DeviceGroupOperation.REMOVE, notificationKeyName, notificationKey, registrationIds);
    }

    private Single<DeviceGroupResponse> modifyDeviceGroup(
            final DeviceGroupOperation operation,
            final String notificationKeyName,
            final String notificationKey,
            final String... registrationIds) {
        checkNotNull(operation, "Operation must not be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(notificationKeyName), "IosNotification key name must be set");
        Preconditions.checkArgument(StringUtils.isNotBlank(notificationKey), "IosNotification key must be set");
        Preconditions.checkArgument(ArrayUtils.isNotEmpty(registrationIds), "registrationIds must not be empty");

        final DeviceGroupModifyRequest modifyRequest = new DeviceGroupModifyRequest(operation, notificationKeyName, notificationKey, registrationIds);

        return client.post(GCM_NOTIFICATION_ENDPOINT, DeviceGroupResponse.class, restClientRequest -> applyHeaders(restClientRequest).end(modifyRequest))
                .toSingle()
                .map(response -> response.getBody());
    }

    private <T> RestClientRequest<T> applyHeaders(final RestClientRequest<T> restRequest) {
        restRequest.putHeader("Authorization", "key=" + fcmServerKey);
        restRequest.putHeader("project_id", fcmSenderId);

        restRequest.setContentType(MediaType.APPLICATION_JSON);

        return restRequest;
    }


}
