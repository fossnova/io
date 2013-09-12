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
import java.io.Reader;
import java.nio.CharBuffer;

/**
 * <P>
 * A <code>PushbackReader</code> allows one or more characters to be pushed back to the reader.
 * If there are some pushed back characters in the reader, these are returned
 * first when <B>read</B> methods or <B>skip</b> method are called.
 * If there are no pushed back characters then <B>read</B> methods or <B>skip</b> method
 * calls are delegated to wrapped reader.
 * </P>
 * <P>
 * The pushback buffer has fixed length. Any attempt to push back more characters
 * than buffer length will cause <B>java.io.IOException</B>.
 * </P>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class PushbackReader extends DelegatingReader {

    private final char[] pushBuffer;

    private int pushPosition;

    private boolean closed;

    /**
     * Creates a <code>PushbackReader</code> that wraps passed reader with a one-character pushback buffer size.
     *
     * @param delegate reader to operate upon
     */
    public PushbackReader( final Reader delegate ) {
        this( delegate, 1 );
    }

    /**
     * Creates a <code>PushbackReader</code> that wraps passed reader.
     *
     * @param delegate reader to operate upon
     * @param size fixed pushback buffer size
     */
    public PushbackReader( final Reader delegate, final int size ) {
        // ensure preconditions
        super( delegate );
        if ( size <= 0 ) {
            throw new IllegalArgumentException( "Pushback buffer size must be positive" );
        }
        // initialize
        pushBuffer = new char[ size ];
        pushPosition = pushBuffer.length;
    }

    /**
     * See {@link java.io.Reader#read()} javadoc.
     */
    @Override
    public int read() throws IOException {
        // ensure preconditions
        ensureOpen();
        // the implementation
        if ( !isPushbackBufferEmpty() ) {
            return pushBuffer[ pushPosition++ ];
        } else {
            return super.read();
        }
    }

    /**
     * Push back one character so it is visible to next read attempts.
     *
     * @param b character to be pushed back
     * @throws IOException if some I/O error occurs
     */
    public void unread( final int b ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( pushPosition == 0 ) {
            throw new IOException( "Push back buffer is full" );
        }
        // the implementation
        pushBuffer[ --pushPosition ] = ( char ) b;
    }

    /**
     * See {@link java.io.Reader#read(char[])} javadoc.
     */
    @Override
    public int read( final char[] buffer ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // the implementation
        return read( buffer, 0, buffer.length );
    }

    /**
     * Push back all characters from the buffer so these are visible to next read attempts.
     *
     * @param buffer characters to be pushed back
     * @throws IOException if some I/O error occurs
     */
    public void unread( final char[] buffer ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // the implementation
        unread( buffer, 0, buffer.length );
    }

    /**
     * See {@link java.io.Reader#read(char[], int, int)} javadoc.
     */
    @Override
    public int read( final char[] buffer, int offset, int length ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        if ( offset < 0 ) {
            throw new IllegalArgumentException( "offset must be positive" );
        }
        if ( length < 0 ) {
            throw new IllegalArgumentException( "length must be positive" );
        }
        if ( length > ( buffer.length - offset ) ) {
            throw new IllegalArgumentException( "length must be less or equal to free space available in the buffer" );
        }
        if ( length == 0 ) {
            return 0;
        }
        // method implementation
        int returnValue = 0;
        // process pushBuffer first
        if ( !isPushbackBufferEmpty() ) {
            final int count = Math.min( length, getPushbackBufferSize() );
            System.arraycopy( pushBuffer, pushPosition, buffer, offset, count );
            // update variables accordingly
            pushPosition += count;
            offset += count;
            length -= count;
            returnValue = count;
        }
        if ( length == 0 ) {
            // pushBuffer served method request completely
            return returnValue;
        }
        // process delegate last
        final int count = super.read( buffer, offset, length );
        if ( count == -1 ) {
            return ( returnValue == 0 ) ? -1 : returnValue;
        } else {
            return returnValue + count;
        }
    }

    /**
     * Push back <B>length</B> characters from this buffer starting from specified <B>offset</B> position
     * so these are visible to next read attempts.
     *
     * @param buffer holding characters to be pushed back
     * @param offset to start copy from
     * @param length count of characters to process
     * @throws IOException if some I/O error occurs
     */
    public void unread( final char[] buffer, final int offset, final int length ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        if ( offset < 0 ) {
            throw new IllegalArgumentException( "offset must be positive" );
        }
        if ( length < 0 ) {
            throw new IllegalArgumentException( "length must be positive" );
        }
        if ( length > ( buffer.length - offset ) ) {
            throw new IllegalArgumentException( "length must be less or equal to free space available in the buffer" );
        }
        // method implementation
        if ( length == 0 ) {
            return;
        }
        if ( length > pushPosition ) {
            throw new IOException( "Pushback buffer is full" );
        }
        pushPosition -= length;
        System.arraycopy( buffer, offset, pushBuffer, pushPosition, length );
    }

    /**
     * See {@link java.io.Reader#skip(long)} javadoc.
     */
    @Override
    public long skip( long count ) throws IOException {
        // ensure preconditions
        ensureOpen();
        // method implementation
        if ( count <= 0 ) {
            return 0;
        }
        long returnValue = 0;
        // process pushBuffer first
        if ( !isPushbackBufferEmpty() ) {
            final long skipped = Math.min( count, getPushbackBufferSize() );
            // update variables accordingly
            pushPosition += skipped;
            count -= skipped;
            returnValue = skipped;
        }
        // process delegate last
        if ( count > 0 ) {
            returnValue += super.skip( count );
        }
        return returnValue;
    }

    /**
     * See {@link java.io.Reader#read(CharBuffer)} javadoc.
     */
    @Override
    public int read( final CharBuffer buffer ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // method implementation
        if ( buffer.remaining() == 0 ) {
            return 0;
        }
        int returnValue = 0;
        if ( buffer.hasArray() ) {
            // reuse internal array
            final char[] data = buffer.array();
            returnValue = read( data, buffer.position(), buffer.remaining() );
            if ( returnValue > 0 ) {
                buffer.position( buffer.position() + returnValue );
            }
        } else {
            // create temporary array
            final char[] data = new char[ buffer.remaining() ];
            returnValue = read( data );
            if ( returnValue > 0 ) {
                buffer.put( data, 0, returnValue );
            }
        }
        return returnValue;
    }

    /**
     * Push back <B>buffer.remaining()</B> characters from this buffer starting from <B>buffer.position()</B>
     * so these are visible to next read attempts.
     *
     * @param buffer holding characters to be pushed back
     * @throws IOException if some I/O error occurs
     */
    public void unread( final CharBuffer buffer ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // method implementation
        final int available = buffer.remaining();
        if ( available == 0 ) {
            return;
        }
        if ( available > pushPosition ) {
            throw new IOException( "Pushback buffer is full" );
        }
        if ( buffer.hasArray() ) {
            // reuse internal array
            final char[] data = buffer.array();
            unread( data, buffer.position(), available );
            buffer.position( buffer.position() + available );
        } else {
            // create temporary array
            final char[] data = new char[ available ];
            buffer.get( data );
            unread( data );
        }
    }

    /**
     * See {@link java.io.Reader#ready()} javadoc.
     */
    @Override
    public boolean ready() throws IOException {
        // ensure preconditions
        ensureOpen();
        // method implementation
        return !isPushbackBufferEmpty() || super.ready();
    }

    /**
     * Not supported on this reader implementation. Always returns <B>false</B>.
     */
    @Override
    public boolean markSupported() {
        return false;
    }

    /**
     * Not supported on this reader implementation. Always throws <B>UnsupportedOperationException</B>.
     */
    @Override
    public void mark( final int readLimit ) {
        throw new UnsupportedOperationException( "mark() not supported" );
    }

    /**
     * Not supported on this reader implementation. Always throws <B>UnsupportedOperationException</B>.
     */
    @Override
    public void reset() throws IOException {
        throw new UnsupportedOperationException( "reset() not supported" );
    }

    /**
     * See {@link java.io.Reader#close()} javadoc.
     */
    @Override
    public void close() throws IOException {
        if ( !closed ) {
            closed = true;
            super.close();
        }
    }

    private void ensureOpen() {
        if ( closed ) {
            throw new IllegalStateException( "Reader is closed" );
        }
    }

    private boolean isPushbackBufferEmpty() {
        return pushPosition == pushBuffer.length;
    }

    private int getPushbackBufferSize() {
        return pushBuffer.length - pushPosition;
    }

}
