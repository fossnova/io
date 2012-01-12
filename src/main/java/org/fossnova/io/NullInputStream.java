/*
 * Copyright (c) 2012, FOSS Nova Software foundation (FNSF),
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
 * TODO: javadoc
 * 
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class NullInputStream extends InputStream {

    private static final NullInputStream INSTANCE = new NullInputStream();

    private NullInputStream() {
        // forbidden constructor
    }

    public static NullInputStream getInstance() {
        return INSTANCE;
    }

    @Override
    public int read() throws IOException {
        return -1;
    }

    @Override
    public int read( final byte[] buffer ) throws IOException {
        return -1;
    }

    @Override
    public int read( final byte[] buffer, final int offset, final int length ) throws IOException {
        return -1;
    }

    @Override
    public void close() throws IOException {
        // does nothing
    }

    @Override
    public long skip( final long count ) throws IOException {
        // does nothing
        return 0L;
    }

    @Override
    public int available() throws IOException {
        // does nothing
        return 0;
    }

    @Override
    public void mark( final int readLimit ) {
        // does nothing
    }

    @Override
    public void reset() throws IOException {
        // does nothing
    }

    @Override
    public boolean markSupported() {
        // does nothing
        return false;
    }
}
