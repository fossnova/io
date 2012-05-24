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
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class PushbackInputStream extends DelegatingInputStream {

    private static final int MASK = 0xFF;

    private final byte[] pushBuffer;

    private int pushPosition;

    private boolean closed;

    public PushbackInputStream( final InputStream delegate, final int size ) {
        // ensure preconditions
        super( delegate );
        if ( size <= 0 ) {
            throw new IllegalArgumentException( "Push back buffer size must be positive" );
        }
        // initialize
        pushBuffer = new byte[ size ];
        pushPosition = pushBuffer.length;
    }

    @Override
    public int read() throws IOException {
        // ensure preconditions
        ensureOpen();
        // the implementation
        if ( !isPushbackBufferEmpty() ) {
            return MASK & pushBuffer[ pushPosition++ ];
        } else {
            return super.read();
        }
    }

    public void unread( final int b ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( pushPosition == 0 ) {
            throw new IOException( "Push back buffer is full" );
        }
        // the implementation
        pushBuffer[ --pushPosition ] = ( byte ) b;
    }

    @Override
    public int read( final byte[] buffer ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // the implementation
        return read( buffer, 0, buffer.length );
    }

    public void unread( final byte[] buffer ) throws IOException {
        // ensure preconditions
        ensureOpen();
        if ( buffer == null ) {
            throw new IllegalArgumentException( "buffer cannot be null" );
        }
        // the implementation
        unread( buffer, 0, buffer.length );
    }

    @Override
    public int read( final byte[] buffer, int offset, int length ) throws IOException {
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
            final int requestedBytesCount = length - offset;
            final int count = Math.min( requestedBytesCount, getPushbackBufferSize() );
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

    public void unread( final byte[] buffer, final int offset, final int length ) throws IOException {
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
            return;
        }
        if ( length > pushPosition ) {
            throw new IOException( "Push back buffer is full" );
        }
        // method implementation
        pushPosition -= length;
        System.arraycopy( buffer, offset, pushBuffer, pushPosition, length );
    }

    @Override
    public void close() throws IOException {
        if ( !closed ) {
            closed = true;
            super.close();
        }
    }

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

    @Override
    public int available() throws IOException {
        // ensure preconditions
        ensureOpen();
        // method implementation
        return getPushbackBufferSize() + super.available();
    }

    @Override
    public void mark( final int readLimit ) {
        throw new UnsupportedOperationException( "mark() not supported" );
    }

    @Override
    public void reset() throws IOException {
        throw new UnsupportedOperationException( "reset() not supported" );
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    private boolean isPushbackBufferEmpty() {
        return pushPosition == pushBuffer.length;
    }

    private int getPushbackBufferSize() {
        return pushBuffer.length - pushPosition;
    }

    private void ensureOpen() {
        if ( closed ) {
            throw new IllegalStateException( "Stream is closed" );
        }
    }
}
