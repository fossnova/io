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
 * <p>
 * A <code>DelegatingWriter</code> overrides all methods of
 * <code>Writer</code> and delegates their execution to the wrapped
 * <code>Writer</code>. The wrapped <code>Writer</code>
 * is always obtained via {@link #getDelegate()} method.
 * </p>
 * <p>
 * This class represents the alternative to <code>java.io.FilterWriter</code>. 
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public class DelegatingWriter extends Writer {

    private final Writer delegate;

    /**
     * Creates a <code>DelegatingWriter</code> that wraps passed writer.
     *
     * @param delegate the writer to be wrapped
     * @throws <code>IllegalArgumentException</code> if parameter is null
     */
    public DelegatingWriter( final Writer delegate ) {
        if ( delegate == null ) {
            throw new IllegalArgumentException( "Writer cannot be null" );
        }
        this.delegate = delegate;
    }

    /**
     * Returns wrapped writer.
     */
    protected Writer getDelegate() {
        return delegate;
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public void write( final int data ) throws IOException {
        getDelegate().write( data );
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public void write( final char[] data ) throws IOException {
        getDelegate().write( data );
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public void write( final char[] data, final int offset, final int length ) throws IOException {
        getDelegate().write( data, offset, length );
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public void write( final String data ) throws IOException {
        getDelegate().write( data );
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public void write( final String data, final int offset, final int length ) throws IOException {
        getDelegate().write( data, offset, length );
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public Writer append( final CharSequence data ) throws IOException {
        getDelegate().append( data );
        return this;
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public Writer append( final CharSequence data, final int start, final int end ) throws IOException {
        getDelegate().append( data, start, end );
        return this;
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public Writer append( final char data ) throws IOException {
        getDelegate().append( data );
        return this;
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public void flush() throws IOException {
        getDelegate().flush();
    }

    /**
     * Delegates the call to the wrapped writer.
     */
    @Override
    public void close() throws IOException {
        getDelegate().close();
    }

}
