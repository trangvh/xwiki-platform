/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.tree.test.po;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.xwiki.test.ui.po.BaseElement;

/**
 * Page object used to interact with the generic tree widget available in XWiki.
 * 
 * @version $Id$
 * @since 6.3RC1
 */
public class TreeElement extends BaseElement
{
    /**
     * The element that represents the tree.
     */
    private WebElement element;

    /**
     * Creates a new instance that can be used to interact with the tree represented by the given element.
     * 
     * @param element the element that represents the tree
     */
    public TreeElement(WebElement element)
    {
        this.element = element;
    }

    /**
     * @param nodeId the node identifier
     * @return the node with the specified identifier
     */
    public TreeNodeElement getNode(String nodeId)
    {
        return new TreeNodeElement(this.element, By.id(nodeId));
    }

    /**
     * @param nodeId the node identifier
     * @return {@code true} if the specified node is present (loaded), {@code false} otherwise
     */
    public boolean hasNode(String nodeId)
    {
        return getUtil().findElementsWithoutWaiting(getDriver(), this.element, By.id(nodeId)).size() > 0;
    }

    /**
     * Open the tree to the specified node.
     * 
     * @param nodeId the node to open to
     * @return this tree
     */
    public TreeElement openTo(String nodeId)
    {
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript(
                "jQuery.jstree.reference(jQuery(arguments[0])).openTo(arguments[1])", this.element, nodeId);
        }
        waitUntilElementIsVisible(By.id(nodeId));
        return this;
    }

    /**
     * Wait as long as the tree in busy (loading).
     * 
     * @return this tree
     */
    public TreeElement waitForIt()
    {
        getUtil().waitUntilCondition(new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                return !Boolean.valueOf(element.getAttribute("aria-busy"));
            }
        });
        return this;
    }

    protected WebElement getElement()
    {
        return this.element;
    }
}
