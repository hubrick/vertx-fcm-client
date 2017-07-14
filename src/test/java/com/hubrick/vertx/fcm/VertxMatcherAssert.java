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

import io.vertx.ext.unit.TestContext;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * @author Emir Dizdarevic
 * @since 2.0.7
 */
public class VertxMatcherAssert {

    public static <T> void assertThat(TestContext context, T actual,
                                      Matcher<? super T> matcher) {
        assertThat(context, "", actual, matcher);
    }

    public static <T> void assertThat(TestContext context, String reason,
                                      T actual, Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendText(reason)
                    .appendText("\nExpected: ")
                    .appendDescriptionOf(matcher)
                    .appendText("\n     but: ");
            matcher.describeMismatch(actual, description);
            context.fail(description.toString());
        }
    }

    public static void assertThat(TestContext context, String reason,
                                  boolean assertion) {
        if (!assertion) {
            context.fail(reason);
        }
    }
}
