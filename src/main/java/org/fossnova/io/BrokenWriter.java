/*
 * Copyright (c) 2012-2017, FOSS Nova Software Foundation (FNSF),
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
import java.io.Writer;

/**
 * A <code>BrokenWriter</code> always throws <code>IOException</code>.
 * <p>
 * This class is thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class BrokenWriter extends Writer {

    private static final BrokenWriter INSTANCE = new BrokenWriter();

    private final IOException exception;

    private BrokenWriter() {
        exception = new IOException( "Broken writer" );
    }

    /**
     * Returns <code>BrokenWriter</code> singleton instance.
     */
    public static BrokenWriter getInstance() {
        return INSTANCE;
    }

    /**
     * Throws IOException.
     */
    @Override
    public void write( final int data ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public void write( final char[] data ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public void write( final char[] data, final int offset, final int length ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public void write( final String data ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public void write( final String data, final int offset, final int length ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public Writer append( final CharSequence data ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public Writer append( final CharSequence data, final int start, final int end ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public Writer append( final char data ) throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public void flush() throws IOException {
        throw exception;
    }

    /**
     * Throws IOException.
     */
    @Override
    public void close() throws IOException {
        throw exception;
    }

}
