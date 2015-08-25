/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package assertions

import geb.Page

@SuppressWarnings("DuplicateStringLiteral")
// tag::introduction_page[]
class ImplicitAssertionsExamplePage extends Page {

    static at = { title == "Implicit Assertions!" }

    // end::introduction_page[]
    // tag::content[]
    static content = {
        headingText(wait: true) { $("h1").text() }
    }
    // end::content[]
    // tag::introduction_page[]

    def waitForHeading() {
        waitFor { $("h1") }
    }
}
// end::introduction_page[]
