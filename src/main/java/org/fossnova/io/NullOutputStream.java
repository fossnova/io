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
import java.io.OutputStream;

/**
 * A <code>NullOutputStream</code> does nothing. All data written to it are
 * completely ignored. It never throws <code>IOException</code>.
 * <p>
 * This class is thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class NullOutputStream extends OutputStream {

    private static final NullOutputStream INSTANCE = new NullOutputStream();

    private NullOutputStream() {
    }

    /**
     * Returns <code>NullOutputStream</code> singleton instance.
     */
    public static NullOutputStream getInstance() {
        return INSTANCE;
    }

    /**
     * Does nothing.
     */
    @Override
    public void write( final int data ) throws IOException {
    }

    /**
     * Does nothing.
     */
    @Override
    public void write( final byte[] data ) throws IOException {
    }

    /**
     * Does nothing.
     */
    @Override
    public void write( final byte[] data, final int offset, final int length ) throws IOException {
    }

    /**
     * Does nothing.
     */
    @Override
    public void flush() throws IOException {
    }

    /**
     * Does nothing.
     */
    @Override
    public void close() throws IOException {
    }

}
