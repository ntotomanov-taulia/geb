/*
 * Copyright 2014 the original author or authors.
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
package geb.test

import groovy.transform.InheritConstructors
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.Response

@InheritConstructors
class RemoteWebDriverWithExpectations extends RemoteWebDriver {

    private final static List<String> IGNORED_COMMANDS = ['newSession', 'getPageSource', 'quit', 'screenshot', 'deleteAllCookies']

    private List<Command> executedCommands = []

    void clearRecordedCommands() {
        executedCommands = []
    }

    void resetExpectations() {
        try {
            def unexpected = executedCommands.findAll { !it.matched }
            if (unexpected) {
                throw new UnexpectedCommandException(*unexpected)
            }
        } finally {
            clearRecordedCommands()
        }
    }

    void ensureExecuted(Map<String, ?> parameters = [:], String command) {
        def executed = executedCommands.find { it.matches(command, parameters) }
        if (!executed) {
            throw new CommandNotExecutedException(new Command(command: command, parameters: parameters))
        }
    }

    @Override
    protected Response execute(String command, Map<String, ?> parameters) {
        if (!IGNORED_COMMANDS.contains(command)) {
            executedCommands << new Command(command: command, parameters: parameters)
        }
        super.execute(command, parameters)
    }

    void getTitleExecuted() {
        ensureExecuted('getTitle')
    }

    void findByXPathExecuted(String expression) {
        ensureExecuted('findElement', using: 'xpath', value: expression)
    }

    void findChildElementsByNameExecuted(String name) {
        ensureExecuted('findChildElements', using: 'name', value: name)
    }

    void findRootElementExecuted() {
        findByXPathExecuted('/*')
    }

    void findElementsByCssExecuted(String selector) {
        ensureExecuted('findElements', using: 'css selector', value: selector)
    }

    void getElementTagNameExecuted() {
        ensureExecuted('getElementTagName')
    }

    void getElementAttributeExecuted(String name) {
        ensureExecuted('getElementAttribute', name: name)
    }

    void clearElementExecuted() {
        ensureExecuted('clearElement')
    }

    void sendKeysExecuted() {
        ensureExecuted('sendKeysToElement')
    }

    private static class Command {

        String command
        Map<String, ?> parameters = [:]

        boolean matched = false

        @Override
        String toString() {
            def parametersString = parameters ? parameters.toString() : ''
            "$command${parametersString ? ", parameters: $parametersString" : ''}"
        }

        boolean matches(String command, Map<String, ?> parameters) {
            def matches = !matched && command == this.command && this.parameters.subMap(parameters.keySet()) == parameters
            matched = (matched || matches)
            matches
        }
    }

    private static class UnexpectedCommandException extends Exception {

        UnexpectedCommandException(Command... unexpectedCommands) {
            super("An unexpected command has been issued:\n${unexpectedCommands.join("\n")}")
        }
    }

    private static class CommandNotExecutedException extends Exception {

        CommandNotExecutedException(Command expectedCommand) {
            super("A command has been expected but has not been executed: $expectedCommand")
        }
    }
}
