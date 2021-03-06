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
package org.xwiki.lesscss.internal.colortheme;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.lesscss.colortheme.ColorThemeReference;
import org.xwiki.lesscss.colortheme.ColorThemeReferenceSerializer;
import org.xwiki.lesscss.colortheme.NamedColorThemeReference;

/**
 * Specialized implementation of {@link ColorThemeReferenceSerializer} to serialize {@link NamedColorThemeReference}
 * references.
 *
 * @since 6.4M2
 * @version $Id$
 */
@Component
@Named("org.xwiki.lesscss.colortheme.NamedColorThemeReference")
@Singleton
public class NamedColorThemeReferenceSerializer implements ColorThemeReferenceSerializer
{
    @Inject
    private Logger logger;

    @Override
    public String serialize(ColorThemeReference colorThemeReference)
    {
        if (!(colorThemeReference instanceof NamedColorThemeReference)) {
            logger.warn("Invalid LESS resource type [{}].", colorThemeReference.toString());
            return null;
        }

        NamedColorThemeReference colorThemeRef = (NamedColorThemeReference) colorThemeReference;
        return String.format("ColorThemeFS[%s]", colorThemeRef.getColorThemeName());
    }
}
