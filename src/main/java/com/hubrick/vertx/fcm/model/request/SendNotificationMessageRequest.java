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
package com.hubrick.vertx.fcm.model.request;

import com.hubrick.vertx.fcm.model.notification.Notification;

import java.util.Map;

/**
 * @author marcus
 * @since 1.0.0
 */
public class SendNotificationMessageRequest extends SendDataMessageRequest {

    private Notification notification;

    public SendNotificationMessageRequest(final String to) {
        super(to);
    }

    public SendNotificationMessageRequest(final String to, final Notification notification) {
        super(to);
        this.notification = notification;
    }

    public SendNotificationMessageRequest(final String to, final Map<String, String> data, final Notification notification) {
        super(to, data);
        this.notification = notification;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(final Notification notification) {
        this.notification = notification;
    }
}
