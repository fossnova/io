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
import java.io.Writer;

/**
 * <P>
 * A <code>BoundedWriter</code> allows to specify maximum of characters to be written to wrapped writer.
 * If the specified maximum of characters is written any further attempt to write one more character results in IOException.
 * </P>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class BoundedWriter extends DelegatingWriter {

    private final long limit;

    private boolean closed;

    private long position;

    /**
     * Creates a <code>BoundedWriter.java</code>.
     *
     * @param delegate writer to be limited
     * @param limit maximum number of characters to be written
     */
    public BoundedWriter( final Writer delegate, final long limit ) {
        // ensure preconditions
        super( delegate );
        if ( limit <= 0 ) {
            throw new IllegalArgumentException( "Limit must be positive" );
        }
        // initialize
        this.limit = limit;
    }

    /**
     * See {@link java.io.Writer#write(int)} javadoc.
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
     * See {@link java.io.Writer#write(char[])} javadoc.
     */
    @Override
    public void write( final char[] data ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( data == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // the implementation
        write( data, 0, data.length );
    }

    /**
     * See {@link java.io.Writer#write(char[], int, int)} javadoc.
     */
    @Override
    public void write( final char[] data, final int offset, final int length ) throws IOException {
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
     * See {@link java.io.Writer#append(CharSequence)} javadoc.
     */
    @Override
    public Writer append( final CharSequence data ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( data == null ) {
            throw new IllegalArgumentException( "CharSequence cannot be null" );
        }
        // the implementation
        write( data.toString() );
        return this;
    }

    /**
     * See {@link java.io.Writer#append(char)} javadoc.
     */
    @Override
    public Writer append( final char data ) throws IOException {
        // ensure preconditions
        ensureOpen();
        // the implementation
        write( data );
        return this;
    }

    /**
     * See {@link java.io.Writer#append(CharSequence,int,int)} javadoc.
     */
    @Override
    public Writer append( final CharSequence data, final int offset, final int length ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( data == null ) {
            throw new IllegalArgumentException( "CharSequence cannot be null" );
        }
        if ( offset < 0 ) {
            throw new IllegalArgumentException( "offset must be positive" );
        }
        if ( length < 0 ) {
            throw new IllegalArgumentException( "length must be positive" );
        }
        if ( length > ( data.length() - offset ) ) {
            throw new IllegalArgumentException( "length must be less or equal to free space available in the CharSequence" );
        }
        // the implementation
        write( data.subSequence( offset, length ).toString() );
        return this;
    }

    /**
     * See {@link java.io.Writer#write(String)} javadoc.
     */
    @Override
    public void write( final String data ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( data == null ) {
            throw new IllegalArgumentException( "String cannot be null" );
        }
        // the implementation
        write( data, 0, data.length() );
    }

    /**
     * See {@link java.io.Writer#write(String,int,int)} javadoc.
     */
    @Override
    public void write( final String data, final int offset, final int length ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( data == null ) {
            throw new IllegalArgumentException( "String cannot be null" );
        }
        if ( offset < 0 ) {
            throw new IllegalArgumentException( "offset must be positive" );
        }
        if ( length < 0 ) {
            throw new IllegalArgumentException( "length must be positive" );
        }
        if ( length > ( data.length() - offset ) ) {
            throw new IllegalArgumentException( "length must be less or equal to free space available in the String" );
        }
        // the implementation
        final char[] buffer = new char[ length - offset ];
        data.getChars( offset, length, buffer, 0 );
        write( buffer );
    }

    /**
     * See {@link java.io.Writer#flush()} javadoc.
     */
    @Override
    public void flush() throws IOException {
        // ensure preconditions
        ensureOpen();
        // method implementation
        super.flush();
    }

    /**
     * See {@link java.io.Writer#close()} javadoc.
     */
    @Override
    public void close() throws IOException {
        if ( !closed ) {
            closed = true;
            super.close();
        }
    }

    private void throwOutOfSpaceException() throws IOException {
        throw new IOException( "Writer is full: " + limit + " characters have been written" );
    }

    private void ensureOpen() {
        if ( closed ) {
            throw new IllegalStateException( "Writer is closed" );
        }
    }

    private long remaining() {
        return limit - position;
    }

}
