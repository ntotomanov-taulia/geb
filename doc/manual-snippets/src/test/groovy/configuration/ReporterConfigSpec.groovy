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
package configuration

import geb.report.ReportState
import geb.report.Reporter
import geb.report.ReportingListener
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class ReporterConfigSpec extends Specification implements InlineConfigurationLoader {

    @Rule
    TemporaryFolder temporaryFolder

    def "configuring custom reporter"() {
        when:
        configScript """
            import configuration.CustomReporter

            // tag::config[]
            reporter = new CustomReporter()
            // end::config[]
        """

        then:
        config.reporter instanceof CustomReporter
    }
}

class CustomReporter implements Reporter {

    @Override
    void writeReport(ReportState reportState) {
    }

    @Override
    void addListener(ReportingListener listener) {
    }
}