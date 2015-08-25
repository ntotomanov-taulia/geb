/*
 * Copyright 2012 the original author or authors.
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
package geb.navigator

import geb.Module
import geb.Page
import geb.js.JQueryAdapter
import geb.waiting.Wait
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * Navigator is a jQuery-style DOM traversal tool that wraps a set of WebDriver WebElements.
 * The code is based on the Doj library written by Kevin Wetzels: http://code.google.com/p/hue/
 */
interface Navigator extends Iterable<Navigator>, Locator {

    /**
     * Filters the set of elements represented by this Navigator to include only that have one or more descendants
     * that match the selector.
     * @param selector a CSS selector
     * @return a new Navigator instance
     */
    Navigator has(String selector)

    /**
     * Filters the set of elements represented by this Navigator to include only that have one or more descendants
     * that match the selector and attributes as defined in the predicate.
     * @param selector a CSS selector
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance
     */
    Navigator has(Map<String, Object> predicates, String selector)

    /**
     * Filters the set of elements represented by this Navigator to include only that have one or more descendants
     * that match the attributes as defined in the predicate.
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance
     */
    Navigator has(Map<String, Object> predicates)

    /**
     * Filters the set of elements represented by this Navigator to include only that have one or more descendants
     * that match the bySelector.
     * @param bySelector a WebDriver By selector
     * @return a new Navigator instance
     */
    Navigator has(By bySelector)

    /**
     * Filters the set of elements represented by this Navigator to include only that have one or more descendants
     * that match the bySelector and attributes as defined in the predicate.
     * @param bySelector a WebDriver By selector
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance
     */
    Navigator has(Map<String, Object> predicates, By bySelector)

    /**
     * Filters the set of elements represented by this Navigator to include only those that match
     * the selector. Note that unlike find only tag, id and class based selectors are supported for this method
     * regardless of the capabilities of the underlying WebDriver instance.
     * @param selector a CSS selector
     * @return a new Navigator instance
     */
    Navigator filter(String selector)

    Navigator filter(Map<String, Object> predicates)

    Navigator filter(Map<String, Object> predicates, String selector)

    /**
     * Returns a new Navigator instance containing all elements of the current Navigator that do not match the selector.
     * @param selector a CSS selector
     * @return a new Navigator instance
     */
    Navigator not(String selector)

    /**
     * Returns a new Navigator instance containing all elements of the current Navigator that do not match the selector
     * and attributes as defined in the predicate.
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @param selector a CSS selector
     * @return a new Navigator instance
     */
    Navigator not(Map<String, Object> predicates, String selector)

    /**
     * Returns a new Navigator instance containing all elements of the current Navigator that do not match the attributes
     * as defined in the predicate.
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance
     */
    Navigator not(Map<String, Object> predicates)

    /**
     * Filters the set of elements represented by this Navigator to exclude that have one or more descendants
     * that match the selector.
     * @param selector a CSS selector
     * @return a new Navigator instance
     */
    Navigator hasNot(String selector)

    /**
     * Filters the set of elements represented by this Navigator to exclude that have one or more descendants
     * that match the selector and attributes as defined in the predicate.
     * @param selector a CSS selector
     * @param predicates a Map with keys representing attributes and values representing excluded values or patterns
     * @return a new Navigator instance
     */
    Navigator hasNot(Map<String, Object> predicates, String selector)

    /**
     * Filters the set of elements represented by this Navigator to exclude that have one or more descendants
     * that match the attributes as defined in the predicate.
     * @param predicates a Map with keys representing attributes and values representing excluded values or patterns
     * @return a new Navigator instance
     */
    Navigator hasNot(Map<String, Object> predicates)

    /**
     * Filters the set of elements represented by this Navigator to exclude that have one or more descendants
     * that match the bySelector.
     * @param bySelector a WebDriver By selector
     * @return a new Navigator instance
     */
    Navigator hasNot(By bySelector)

    /**
     * Filters the set of elements represented by this Navigator to exclude that have one or more descendants
     * that match the bySelector and attributes as defined in the predicate.
     * @param bySelector a WebDriver By selector
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance
     */
    Navigator hasNot(Map<String, Object> predicates, By bySelector)

    /**
     * Gets the wrapped element at the given index.
     * <p>
     * When no such element exists, an empty Navigator instance is returned.
     * </p>
     * @param index index of the element to retrieve - pass a negative value to start from the back
     * @return new Navigator instance
     */
    Navigator eq(int index)

    /**
     * Gets the wrapped element at the given index.
     * <p>
     * When no such element exists, an empty Navigator instance is returned.
     * </p>
     * @param index index of the element to retrieve - pass a negative value to start from the back
     * @return new Navigator instance
     */
    Navigator getAt(int index)

    /**
     * Gets the wrapped elements in the given range.
     * <p>
     * When no such elements exist, an empty Navigator instance is returned.
     * </p>
     * @param range range of the elements to retrieve
     * @return new Navigator instance
     */
    Navigator getAt(Range range)

    /**
     * Gets the wrapped elements at the given indexes.
     * <p>
     * When no such elements exist, an empty Navigator instance is returned.
     * </p>
     * @param indexes indexes of the elements to retrieve
     * @return new Navigator instance
     */
    Navigator getAt(Collection indexes)

    Navigator add(String selector)

    Navigator add(By bySelector)

    Navigator add(WebElement[] elements)

    Navigator add(Collection<WebElement> elements)

    /**
     * Creates a new Navigator instance by removing the element at the given index
     * from the context.
     * <p>
     * If no such element exists, the current instance is returned.
     * </p>
     * @param index index of the element to remove - pass a negative value to start from the back
     * @return new Navigator instance
     */
    Navigator remove(int index)

    /**
     * Merges the Navigator instance with the current instance to create a new
     * Navigator instance containing the context elements of both.
     * @param navigator navigator to merge with this one
     * @return new Navigator instance
     */
    Navigator plus(Navigator navigator)

    /**
     * Creates a new Navigator instance containing the next sibling elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator next()

    /**
     * Creates a new Navigator instance containing the next sibling elements of the
     * current context elements, matching the selector.
     * <p>
     * Unlike {@link #next()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator next(String selector)

    /**
     * Creates a new Navigator instance containing the next sibling elements of the
     * current context elements, matching the given attributes.
     * <p>
     * Unlike {@link #next()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator next(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing the next sibling elements of the
     * current context elements, matching the selector and given attributes.
     * <p>
     * Unlike {@link #next()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator next(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all following sibling elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator nextAll()

    /**
     * Creates a new Navigator instance containing all following sibling elements of the
     * current context elements that match the selector.
     * <p>
     * Unlike {@link #next()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator nextAll(String selector)

    /**
     * Creates a new Navigator instance containing all following sibling elements of the
     * current context elements, matching the given attributes.
     * <p>
     * Unlike {@link #next()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator nextAll(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all following sibling elements of the
     * current context elements, matching the selector and given attributes.
     * <p>
     * Unlike {@link #next()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator nextAll(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all following sibling elements of the
     * current context elements up to, but not including, the first to match the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator nextUntil(String selector)

    /**
     * Creates a new Navigator instance containing all following sibling elements of the
     * current context elements up to, but not including, the first to match the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator nextUntil(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all following sibling elements of the
     * current context elements up to, but not including, the first to match the selector and given attributes.
     *
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator nextUntil(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing the previous sibling elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator previous()

    /**
     * Creates a new Navigator instance containing all preceding sibling elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator prevAll()

    /**
     * Creates a new Navigator instance containing the previous sibling elements of the
     * current context elements, matching the selector.
     * <p>
     * Unlike {@link #previous()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator previous(String selector)

    /**
     * Creates a new Navigator instance containing the previous sibling elements of the
     * current context elements, matching the given attributes.
     * <p>
     * Unlike {@link #previous()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator previous(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing the previous sibling elements of the
     * current context elements, matching the selector and given attributes.
     * <p>
     * Unlike {@link #previous()}, this method will keep looking for the first
     * matching sibling until it finds a match or is out of siblings.
     * </p>
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator previous(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all preceding sibling elements of the
     * current context elements, matching the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator prevAll(String selector)

    /**
     * Creates a new Navigator instance containing all preceding sibling elements of the
     * current context elements, matching the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator prevAll(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all preceding sibling elements of the
     * current context elements, matching the selector and given attributes.
     *
     * @param selector to match
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator prevAll(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all preceding sibling elements of the
     * current context elements up to, but not including the first matching the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator prevUntil(String selector)

    /**
     * Creates a new Navigator instance containing all preceding sibling elements of the
     * current context elements up to, but not including the first matching the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator prevUntil(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all preceding sibling elements of the
     * current context elements up to, but not including the first matching the selector and given attributes.
     *
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator prevUntil(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing the direct parent elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator parent()

    /**
     * Creates a new Navigator instance containing the direct parent elements of the current
     * context elements that match the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator parent(String selector)

    /**
     * Creates a new Navigator instance containing the direct parent elements of the current
     * context elements that match the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator parent(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing the direct parent elements of the current
     * context elements that match the selector and given attributes.
     *
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator parent(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all the ancestor elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator parents()

    /**
     * Creates a new Navigator instance containing all the ancestor elements of the
     * current context elements that match the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator parents(String selector)

    /**
     * Creates a new Navigator instance containing all the ancestor elements of the
     * current context elements that match the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator parents(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all the ancestor elements of the
     * current context elements that match the given attributes.
     *
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator parents(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all the ancestor elements of the
     * current context elements up to but not including the first that matches the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator parentsUntil(String selector)

    /**
     * Creates a new Navigator instance containing all the ancestor elements of the
     * current context elements up to but not including the first that matches the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator parentsUntil(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all the ancestor elements of the
     * current context elements up to but not including the first that matches the selector and given attributes.
     *
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator parentsUntil(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing the first ancestor element of each of the current
     * context elements that match the selector.
     * <p>
     * Unlike {@link #parent()}, this method will keep traversing up the DOM
     * until a match is found or the top of the DOM has been found
     * </p>
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator closest(String selector)

    /**
     * Creates a new Navigator instance containing the first ancestor element of each of the current
     * context elements that match the given attributes.
     * <p>
     * Unlike {@link #parent()}, this method will keep traversing up the DOM
     * until a match is found or the top of the DOM has been found
     * </p>
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator closest(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing the first ancestor element of each of the current
     * context elements that match the selector and given attributes.
     * <p>
     * Unlike {@link #parent()}, this method will keep traversing up the DOM
     * until a match is found or the top of the DOM has been found
     * </p>
     * @param selector to match
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator closest(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all the child elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator children()

    /**
     * Creates a new Navigator instance containing all the child elements of the
     * current context elements that match the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator children(String selector)

    /**
     * Creates a new Navigator instance containing all the child elements of the
     * current context elements that match the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator children(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all the child elements of the
     * current context elements that match the selector and given attributes.
     *
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator children(Map<String, Object> attributes, String selector)

    /**
     * Creates a new Navigator instance containing all the sibling elements of the
     * current context elements.
     * @return new Navigator instance
     */
    Navigator siblings()

    /**
     * Creates a new Navigator instance containing all the sibling elements of the
     * current context elements that match the selector.
     *
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator siblings(String selector)

    /**
     * Creates a new Navigator instance containing all the sibling elements of the
     * current context elements that match the given attributes.
     *
     * @param attributes to match
     * @return new Navigator instance
     */
    Navigator siblings(Map<String, Object> attributes)

    /**
     * Creates a new Navigator instance containing all the sibling elements of the
     * current context elements that match the selector and given attributes.
     *
     * @param attributes to match
     * @param selector to match
     * @return new Navigator instance
     */
    Navigator siblings(Map<String, Object> attributes, String selector)

    /**
     * Returns true if at least one of the context elements has the given class.
     * @param className class to check for
     * @return true if at least one of the context elements has the given class
     */
    boolean hasClass(String className)

    /**
     * Returns true if at least one of the context elements matches the tag.
     * @param tag tag to match
     * @return true if at least one of the context elements matches the tag
     */
    boolean is(String tag)

    /**
     * Uses the isDisplayed() of RenderedWebElement to determine if the first element in the context is displayed.
     * If the context is empty, or the first element is not a RenderedWebElement, false will be returned.
     *
     * @return true if the first element is displayed
     */
    boolean isDisplayed()

    /**
     * Shorthand for <code>hasAttribute("disabled")</code>.
     * @return true when the first element is disabled
     * @throws java.lang.UnsupportedOperationException if this navigator contains anything else than a button, input, option, select or textarea
     * @deprecated Use {@link geb.module.FormElement#isDisabled()} instead.
     */
    @Deprecated
    boolean isDisabled()

    /**
     * Shorthand for <code>!hasAttribute("disabled")</code>.
     * @return true when the first element is enabled
     * @throws java.lang.UnsupportedOperationException if this navigator contains anything else than a button, input, option, select or textarea
     * @deprecated Use {@link geb.module.FormElement#isEnabled()} instead.
     */
    @Deprecated
    boolean isEnabled()

    /**
     * Shorthand for <code>hasAttribute("readonly")</code>.
     * @return true when the first element is readonly
     * @throws java.lang.UnsupportedOperationException if this navigator contains anything else than an input or a textarea
     * @deprecated Use {@link geb.module.FormElement#isReadOnly()} instead.
     */
    @Deprecated
    boolean isReadOnly()

    /**
     * Shorthand for <code>!hasAttribute("readonly")</code>.
     * @return true when the first element is editable
     * @throws java.lang.UnsupportedOperationException if this navigator contains anything else than an input or a textarea
     * @deprecated Use {@link geb.module.FormElement#isEditable()} instead.
     */
    @Deprecated
    boolean isEditable()

    /**
     * Returns the tag name of the first context element.
     * @return the tag name of the first context element
     */
    String tag()

    /**
     * Returns the text content of the first context element.
     * @return the text content of the first context element
     */
    String text()

    /**
     * Returns the value of the given attribute of the first context element.
     * @param name name of the attribute
     * @return the value of the given attribute of the first context element
     */
    String getAttribute(String name)

    /**
     * Returns the value of the given attribute of the first context element.
     * @param name name of the attribute
     * @return the value of the given attribute of the first context element
     */
    String attr(String name)

    /**
     * Returns the class names present on all elements. The result is a unique set and is in alphabetical order.
     * @return the class names present on all elements.
     */
    List<String> classes()

    /**
     * Returns the value of the first context element for input elements
     * (including textarea, select and button).
     * <p>
     * In the case of a select, the value of the first selected option is returned.
     * </p>
     * @return value of the first context element
     */
    def value()

    /**
     * Sets the value of the form input elements to the given value.
     * @param value value to use
     * @return current Navigator instance
     */
    Navigator value(value)

    Navigator leftShift(value)

    /**
     * Clicks on the first context element.
     */
    Navigator click()

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the class passed as the argument and sets it as the current page.
     *
     * @param pageClass page class to be used as the new current page after clicking the element
     * @return this
     */
    Navigator click(Class<? extends Page> pageClass)

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the instance passed as the argument and sets it as the current page.
     *
     * @param pageInstance page instance to be used as the new current page after clicking the element
     * @return this
     */
    Navigator click(Page pageInstance)

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the instance passed as the argument and sets it as the current page.
     *
     * @param pageClass page class to be used as the new current page after clicking the element
     * @param wait configuration to be used for waiting for the at checker to succeed
     * @return this
     */
    Navigator click(Class<? extends Page> pageClass, Wait wait)

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the instance passed as the argument and sets it as the current page.
     *
     * @param pageInstance page instance to be used as the new current page after clicking the element
     * @param wait configuration to be used for waiting for the at checker to succeed
     * @return this
     */
    Navigator click(Page pageInstance, Wait wait)

    /**
     * Clicks on the first context element, finds the first page from the list for which the at checker is defined and sets it as the current page.
     *
     * @param potentialPages a list of classes extending {@link geb.Page} or a list of instances of such classes
     * @return this
     */
    Navigator click(List potentialPages)

    /**
     * Clicks on the first context element, finds the first page from the list for which the at checker is defined and sets it as the current page.
     *
     * @param potentialPages a list of classes extending {@link geb.Page} or a list of instances of such classes
     * @param wait configuration to be used for waiting for the at checkers to succeed
     * @return this
     */
    Navigator click(List potentialPages, Wait wait)

    /**
     * Returns the number of context elements.
     * @return the number of context elements
     */
    int size()

    /**
     * Returns true when there are no context elements.
     * @return true when there are no context elements
     */
    boolean isEmpty()

    /**
     * Creates a new Navigator instance containing only the first context element (wrapped).
     * @return new Navigator instance
     */
    Navigator head()

    /**
     * Creates a new Navigator instance containing only the first context element (wrapped).
     * @return new Navigator instance
     */
    Navigator first()

    /**
     * Creates a new Navigator instance containing only the last context element (wrapped).
     * @return new Navigator instance
     */
    Navigator last()

    /**
     * Creates a new Navigator instance containing all but the first context element (wrapped).
     * @return new Navigator instance
     */
    Navigator tail()

    /**
     * Returns the first context element (not wrapped).
     * @return the first context element (not wrapped)
     */
    // tag::web_element_returning_methods[]
    WebElement firstElement()
    // end::web_element_returning_methods[]

    /**
     * Returns the last context element (not wrapped).
     * @return the last context element (not wrapped)
     */
    // tag::web_element_returning_methods[]
    WebElement lastElement()
    // end::web_element_returning_methods[]

    /**
     * Returns all context elements.
     * @return all context elements
     */
    // tag::web_element_returning_methods[]
    Collection<WebElement> allElements()
    // end::web_element_returning_methods[]

    Iterator<Navigator> iterator()

    /**
     * Overrides the standard Groovy findAll so that the object returned is a Navigator rather than a Collection<Navigator>.
     */
    Navigator findAll(Closure predicate)

    /**
     * Throws an exception when the Navigator instance is empty.
     * @return the current Navigator instance
     */
    Navigator verifyNotEmpty()

    /**
     * Returns an adapter for calling jQuery methods on the elements in this navigator.
     */
    JQueryAdapter getJquery()

    /**
     * Returns the height of the first element the navigator matches or 0 if it matches nothing.
     * <p>
     * To get the height of all matched elements you can use the spread operator {@code navigator*.height}
     */
    int getHeight()

    /**
     * Returns the width of the first element the navigator matches or 0 if it matches nothing.
     * <p>
     * To get the width of all matched elements you can use the spread operator {@code navigator*.width}
     */
    int getWidth()

    /**
     * Returns the x coordinate (from the top left corner) of the first element the navigator matches or 0 if it matches nothing.
     * <p>
     * To get the x coordinate of all matched elements you can use the spread operator {@code navigator*.x}
     */
    int getX()

    /**
     * Returns the y coordinate (from the top left corner) of the first element the navigator matches or 0 if it matches nothing.
     * <p>
     * To get the y coordinate of all matched elements you can use the spread operator {@code navigator*.y}
     */
    int getY()

    /**
     * Creates a new Navigator instance containing all elements of this instance with duplicate elements removed
     */
    Navigator unique()

    /**
     * Gets the value of a given CSS property. Color values should be returned as rgba strings, so, for example if the "background-color"
     * property is set as "green" in the HTML source, the returned value will be "rgba(0, 255, 0, 1)". Note that shorthand
     * CSS properties (e.g. background, font, border, border-top, margin, margin-top, padding, padding-top, list-style, outline, pause, cue)
     * are not returned, in accordance with the DOM CSS2 specification - you should directly access the longhand properties (e.g. background-color)
     * to access the desired values.
     *
     * @param propertyName
     * @return The current, computed value of the property or null if the is not specified.
     */
    String css(String propertyName)

    /**
     * Create and initialize an instance of the given module class, using {@code this} as its base.
     *
     * @param moduleClass a class extending {@link geb.Module}
     * @return an initialized instance of the module class passed as the argument
     */
    public <T extends Module> T module(Class<T> moduleClass)

    /**
     * Initialize a module instance using {@code this} as its base.
     *
     * @param module an instance of a class extending {@link geb.Module}
     * @return an initialized instance of passed as the argument
     */
    public <T extends Module> T module(T module)
}