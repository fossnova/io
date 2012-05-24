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
 * <p>
 * A <code>DelegatingReader</code> overrides all methods of
 * <code>Reader</code> and delegates their execution to the wrapped
 * <code>Reader</code>. The wrapped <code>Reader</code>
 * is always obtained via {@link #getDelegate()} method.
 * </p>
 * <p>
 * This class represents the alternative to <code>java.io.FilterReader</code>. 
 * </p>
 * <p>
 * This class is not thread safe. 
 * </p>
 * 
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public class DelegatingReader extends Reader {

    private final Reader delegate;

    /**
     * Creates a <code>DelegatingReader</code> that wraps passed reader.
     *
     * @param delegate the reader to be wrapped
     * @throws <code>IllegalArgumentException</code> if parameter is null
     */
    public DelegatingReader( final Reader delegate ) {
        if ( delegate == null ) {
            throw new IllegalArgumentException( "Reader cannot be null" );
        }
        this.delegate = delegate;
    }

    /**
     * Returns wrapped reader.
     */
    protected Reader getDelegate() {
        return delegate;
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public int read( final CharBuffer buffer ) throws IOException {
        return getDelegate().read( buffer );
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public int read() throws IOException {
        return getDelegate().read();
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public int read( final char[] buffer ) throws IOException {
        return getDelegate().read( buffer );
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public int read( final char[] buffer, final int offset, final int length ) throws IOException {
        return getDelegate().read( buffer, offset, length );
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public void close() throws IOException {
        getDelegate().close();
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public long skip( final long count ) throws IOException {
        return getDelegate().skip( count );
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public boolean ready() throws IOException {
        return getDelegate().ready();
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public boolean markSupported() {
        return getDelegate().markSupported();
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public void mark( final int readAheadLimit ) throws IOException {
        getDelegate().mark( readAheadLimit );
    }

    /**
     * Delegates the call to the wrapped reader.
     */
    @Override
    public void reset() throws IOException {
        getDelegate().reset();
    }
}
