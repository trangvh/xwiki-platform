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
package org.xwiki.rendering.internal.macro.container;

import java.util.List;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.rendering.block.Block;
import org.xwiki.rendering.internal.macro.MacroContentParser;
import org.xwiki.rendering.macro.MacroExecutionException;
import org.xwiki.rendering.macro.container.AbstractContainerMacro;
import org.xwiki.rendering.macro.container.ContainerMacroParameters;
import org.xwiki.rendering.transformation.MacroTransformationContext;

/**
 * Macro to hold a list groups and style them together, for example laying them out as indicated by the styleLayout
 * parameter. For the moment this macro handles only the layouting, and only the columns layout. When it will be
 * enhanced with other layout styles, it should be split in multiple classes, one to handle each.
 * 
 * @version $Id$
 * @since 2.5M2
 */
@Component(ContainerMacro.MACRO_NAME)
public class ContainerMacro extends AbstractContainerMacro<ContainerMacroParameters>
{
    /**
     * The name of this macro.
     */
    public static final String MACRO_NAME = "container";

    /**
     * The description of this macro.
     */
    private static final String DESCRIPTION = "A macro to enclose multiple groups and add decoration, such as layout.";

    /**
     * Used to parse the macro content.
     */
    @Requirement
    private MacroContentParser contentParser;

    /**
     * Creates a container macro.
     */
    public ContainerMacro()
    {
        super("Container", DESCRIPTION, ContainerMacroParameters.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.macro.container.AbstractContainerMacro
     *      #getContent(org.xwiki.rendering.macro.container.ContainerMacroParameters, java.lang.String,
     *      org.xwiki.rendering.transformation.MacroTransformationContext)
     */
    @Override
    protected List<Block> getContent(ContainerMacroParameters parameters, String content,
        MacroTransformationContext context) throws MacroExecutionException
    {
        return this.contentParser.parse(content, context, false, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xwiki.rendering.macro.Macro#getPriority()
     */
    @Override
    public int getPriority()
    {
        return 750;
    }

}
