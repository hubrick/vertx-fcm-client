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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hubrick.vertx.rest.converter.HttpMessageConverter;
import com.hubrick.vertx.rest.converter.JacksonJsonHttpMessageConverter;

import java.util.Collections;
import java.util.List;

/**
 * @author marcus
 * @since 1.0.0
 */
class AbstractFcmClient {

    protected List<HttpMessageConverter> makeConverter() {
        return Collections.singletonList(new JacksonJsonHttpMessageConverter(makeObjectMapper()));
    }

    protected ObjectMapper makeObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        mapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }

}
