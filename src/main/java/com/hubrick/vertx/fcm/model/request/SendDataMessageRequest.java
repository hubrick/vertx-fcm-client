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

import java.util.Map;

/**
 * @author marcus
 * @since 1.0.0
 */
public class SendDataMessageRequest extends SendMessageRequest {

    private Map<String, String> data;

    public SendDataMessageRequest(final String to) {
        super(to);
    }

    public SendDataMessageRequest(final String to, final Map<String, String> data) {
        super(to);
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(final Map<String, String> data) {
        this.data = data;
    }
}
