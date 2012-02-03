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
 * <p>
 * A <code>DelegatingInputStream</code> overrides all methods of
 * <code>InputStream</code> and delegates their execution to the wrapped
 * <code>InputStream</code>. The wrapped <code>InputStream</code>
 * is always obtained via {@link #getDelegate()} method.
 * </p>
 * <p>
 * This class represents the alternative to <code>java.io.FilterInputStream</code>. 
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public class DelegatingInputStream extends InputStream {

    private final InputStream delegate;

    /**
     * Creates a <code>DelegatingInputStream</code> that wraps passed input stream.
     *
     * @param delegate the input stream to be wrapped
     * @throws <code>IllegalArgumentException</code> if parameter is null
     */
    public DelegatingInputStream( final InputStream delegate ) {
        if ( delegate == null ) {
            throw new IllegalArgumentException();
        }
        this.delegate = delegate;
    }

    /**
     * Returns wrapped input stream.
     */
    protected InputStream getDelegate() {
        return delegate;
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public int read() throws IOException {
        return getDelegate().read();
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public int read( final byte[] buffer ) throws IOException {
        return getDelegate().read( buffer );
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public int read( final byte[] buffer, final int offset, final int length ) throws IOException {
        return getDelegate().read( buffer, offset, length );
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public void close() throws IOException {
        getDelegate().close();
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public long skip( final long count ) throws IOException {
        return getDelegate().skip( count );
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public int available() throws IOException {
        return getDelegate().available();
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public void mark( final int readLimit ) {
        getDelegate().mark( readLimit );
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public void reset() throws IOException {
        getDelegate().reset();
    }

    /**
     * Delegates the call to the wrapped input stream.
     */
    @Override
    public boolean markSupported() {
        return getDelegate().markSupported();
    }
}
