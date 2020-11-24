/*
 * Copyright (c) 2012-2020, FOSS Nova Software Foundation (FNSF),
 * and individual contributors as indicated by the @author tags.
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
package org.fossnova.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * A <code>NullInputStream</code> does nothing. It is always at the EOF position.
 * It never throws <code>IOException</code>.
 * <p>
 * This class is thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class NullInputStream extends InputStream {

    private static final NullInputStream INSTANCE = new NullInputStream();

    private NullInputStream() {
    }

    /**
     * Returns <code>NullInputStream</code> singleton instance.
     */
    public static NullInputStream getInstance() {
        return INSTANCE;
    }

    /**
     * Does nothing.
     */
    @Override
    public int read() throws IOException {
        return -1;
    }

    /**
     * Does nothing.
     */
    @Override
    public int read( final byte[] buffer ) throws IOException {
        return -1;
    }

    /**
     * Does nothing.
     */
    @Override
    public int read( final byte[] buffer, final int offset, final int length ) throws IOException {
        return -1;
    }

    /**
     * Does nothing.
     */
    @Override
    public void close() throws IOException {
    }

    /**
     * Does nothing.
     */
    @Override
    public long skip( final long count ) throws IOException {
        return 0;
    }

    /**
     * Does nothing.
     */
    @Override
    public int available() throws IOException {
        return 0;
    }

    /**
     * Does nothing.
     */
    @Override
    public void mark( final int readLimit ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void reset() throws IOException {
    }

    /**
     * Does nothing.
     */
    @Override
    public boolean markSupported() {
        return Boolean.FALSE.booleanValue();
    }

}
