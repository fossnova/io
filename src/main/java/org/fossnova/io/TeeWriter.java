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
import java.io.Writer;

/**
 * A <code>TeeWriter</code> overrides all methods of <code>Writer</code>
 * and delegates their execution to the wrapped writers.
 * 
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class TeeWriter extends Writer {

    private final Writer[] delegates;

    /**
     * Creates a <code>TeeWriter</code> that wraps passed writers.
     *
     * @throws <code>IllegalArgumentException</code> if any parameter is null
     */
    public TeeWriter( final Writer first, final Writer second, final Writer... others ) {
        // ensure preconditions
        if ( first == null || second == null ) { 
            throw new IllegalArgumentException();
        }
        if ( others != null && others.length > 0 ) {
            for ( final Writer os : others ) {
                if ( os == null ) {
                    throw new IllegalArgumentException();
                }
            }
        }
        // initialize
        final int size = 2 + ( others != null ? others.length : 0 ); 
        delegates = new Writer[size];
        delegates[0] = first;
        delegates[1] = second;
        if ( size > 2 ) {
            for ( int i = 0; i < others.length; i++ ) {
                delegates[2 + i] = others[i];
            }
        }
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public void write( final int data ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.write( data );
        }
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public void write( final char[] data ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.write( data );
        }
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public void write( final char[] data, final int offset, final int length ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.write( data, offset, length );
        }
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public void write( final String data ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.write( data );
        }
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public void write( final String data, final int offset, final int length ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.write( data, offset, length );
        }
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public Writer append( final CharSequence data ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.append( data );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public Writer append( final CharSequence data, final int start, final int end ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.append( data, start, end );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public Writer append( final char data ) throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.append( data );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public void flush() throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.flush();
        }
    }

    /**
     * Delegates the call to the wrapped writers.
     */
    @Override
    public void close() throws IOException {
        for ( final Writer delegate : delegates ) {
            delegate.close();
        }
    }
}
