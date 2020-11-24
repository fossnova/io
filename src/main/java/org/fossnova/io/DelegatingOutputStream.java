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
import java.io.OutputStream;

/**
 * <p>
 * A <code>DelegatingOutputStream</code> overrides all methods of
 * <code>OutputStream</code> and delegates their execution to the wrapped
 * <code>OutputStream</code>. The wrapped <code>OutputStream</code>
 * is always obtained via {@link #getDelegate()} method.
 * </p>
 * <p>
 * This class represents the alternative to <code>java.io.FilterOutputStream</code>. 
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public class DelegatingOutputStream extends OutputStream {

    private final OutputStream delegate;

    /**
     * Creates a <code>DelegatingOutputStream</code> that wraps passed output stream.
     *
     * @param delegate the output stream to be wrapped
     * @throws <code>IllegalArgumentException</code> if parameter is null
     */
    public DelegatingOutputStream( final OutputStream delegate ) {
        if ( delegate == null ) {
            throw new IllegalArgumentException( "OutputStream cannot be null" );
        }
        this.delegate = delegate;
    }

    /**
     * Returns wrapped output stream.
     */
    protected OutputStream getDelegate() {
        return delegate;
    }

    /**
     * Delegates the call to the wrapped output stream.
     */
    @Override
    public void write( final int data ) throws IOException {
        getDelegate().write( data );
    }

    /**
     * Delegates the call to the wrapped output stream.
     */
    @Override
    public void write( final byte[] data ) throws IOException {
        getDelegate().write( data );
    }

    /**
     * Delegates the call to the wrapped output stream.
     */
    @Override
    public void write( final byte[] data, final int offset, final int length ) throws IOException {
        getDelegate().write( data, offset, length );
    }

    /**
     * Delegates the call to the wrapped output stream.
     */
    @Override
    public void flush() throws IOException {
        getDelegate().flush();
    }

    /**
     * Delegates the call to the wrapped output stream.
     */
    @Override
    public void close() throws IOException {
        getDelegate().close();
    }

}
