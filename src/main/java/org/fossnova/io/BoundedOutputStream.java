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
 * <P>
 * A <code>BoundedOutputStream</code> allows to specify maximum of bytes to be written to wrapped stream.
 * If the specified maximum of bytes is written any further attempt to write one more byte results in IOException.
 * </P>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class BoundedOutputStream extends DelegatingOutputStream {

    private final long limit;

    private boolean closed;

    private long position;

    /**
     * Creates a <code>BoundedOutputStream</code>.
     *
     * @param delegate output stream to be limited
     * @param limit maximum number of bytes to be written
     */
    public BoundedOutputStream( final OutputStream delegate, final long limit ) {
        // ensure preconditions
        super( delegate );
        if ( limit <= 0 ) {
            throw new IllegalArgumentException( "Limit must be positive" );
        }
        // initialize
        this.limit = limit;
    }

    /**
     * See {@link java.io.OutputStream#write(int)} javadoc.
     */
    @Override
    public void write( final int data ) throws IOException {
        // ensure preconditions
        ensureOpen();
        // method implementation
        if ( remaining() == 0 ) {
            throwOutOfSpaceException();
        }
        super.write( data );
        position++;
    }

    /**
     * See {@link java.io.OutputStream#write(byte[])} javadoc.
     */
    @Override
    public void write( final byte[] data ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( data == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // the implementation
        write( data, 0, data.length );
    }

    /**
     * See {@link java.io.OutputStream#write(byte[], int, int)} javadoc.
     */
    @Override
    public void write( final byte[] data, final int offset, final int length ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( data == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        if ( offset < 0 ) {
            throw new IllegalArgumentException( "offset must be positive" );
        }
        if ( length < 0 ) {
            throw new IllegalArgumentException( "length must be positive" );
        }
        if ( length > ( data.length - offset ) ) {
            throw new IllegalArgumentException( "length must be less or equal to free space available in the buffer" );
        }
        // method implementation
        if ( length == 0 ) {
            return;
        }
        if ( remaining() == 0 ) {
            throwOutOfSpaceException();
        }
        final int count = remaining() > length ? length : ( int ) remaining();
        super.write( data, offset, count );
        position += count;
        if ( count != length ) {
            throwOutOfSpaceException();
        }
    }

    /**
     * See {@link java.io.OutputStream#flush()} javadoc.
     */
    @Override
    public void flush() throws IOException {
        // ensure preconditions
        ensureOpen();
        // method implementation
        super.flush();
    }

    /**
     * See {@link java.io.OutputStream#close()} javadoc.
     */
    @Override
    public void close() throws IOException {
        if ( !closed ) {
            closed = true;
            super.close();
        }
    }

    private void throwOutOfSpaceException() throws IOException {
        throw new IOException( "Output stream is full: " + limit + " bytes have been written" );
    }

    private void ensureOpen() {
        if ( closed ) {
            throw new IllegalStateException( "Stream is closed" );
        }
    }

    private long remaining() {
        return limit - position;
    }

}
