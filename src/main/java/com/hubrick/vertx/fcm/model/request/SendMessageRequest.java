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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * https://firebase.google.com/docs/cloud-messaging/http-server-ref
 *
 * @author marcus
 * @since 1.0.0
 */
public class SendMessageRequest {

    private final String to;

    @JsonProperty("registration_ids")
    private String registrationIds;
    private String condition;

    @JsonProperty("collapse_key")
    private String collapseKey;

    private String priority;

    @JsonProperty("content_available")
    private Boolean contentAvailable;

    @JsonProperty("mutable_content")
    private Boolean mutableContent;

    @JsonProperty("time_to_live")
    private Long timeToLive;

    @JsonProperty("resticted_package_name")
    private String restrictedPackageName;

    @JsonProperty("dry_run")
    private Boolean dryRun;

    public SendMessageRequest(final String to) {
        Preconditions.checkArgument(StringUtils.isNotBlank(to), "To must be set");
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(final String registrationIds) {
        this.registrationIds = registrationIds;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public String getCollapseKey() {
        return collapseKey;
    }

    public void setCollapseKey(final String collapseKey) {
        this.collapseKey = collapseKey;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(final String priority) {
        this.priority = priority;
    }

    public Boolean getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(final Boolean contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public Boolean getMutableContent() {
        return mutableContent;
    }

    public void setMutableContent(final Boolean mutableContent) {
        this.mutableContent = mutableContent;
    }

    public Long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(final Long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getRestrictedPackageName() {
        return restrictedPackageName;
    }

    public void setRestrictedPackageName(final String restrictedPackageName) {
        this.restrictedPackageName = restrictedPackageName;
    }

    public Boolean getDryRun() {
        return dryRun;
    }

    public void setDryRun(final Boolean dryRun) {
        this.dryRun = dryRun;
    }
}
