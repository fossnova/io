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
 * TODO: javadoc
 * 
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public class DelegatingInputStream extends InputStream {

    private final InputStream delegate;

    public DelegatingInputStream() {
        delegate = NullInputStream.getInstance();
    }

    public DelegatingInputStream( final InputStream delegate ) {
        if ( delegate == null ) throw new IllegalArgumentException();
        this.delegate = delegate;
    }

    protected InputStream getDelegate() {
        return delegate;
    }

    @Override
    public final int read() throws IOException {
        return getDelegate().read();
    }

    @Override
    public final int read( final byte[] buffer ) throws IOException {
        return getDelegate().read( buffer );
    }

    @Override
    public final int read( final byte[] buffer, final int offset, final int length ) throws IOException {
        return getDelegate().read( buffer, offset, length );
    }

    @Override
    public final void close() throws IOException {
        getDelegate().close();
    }

    @Override
    public final long skip( final long count ) throws IOException {
        return getDelegate().skip( count );
    }

    @Override
    public final int available() throws IOException {
        return getDelegate().available();
    }

    @Override
    public final void mark( final int readLimit ) {
        getDelegate().mark( readLimit );
    }

    @Override
    public final void reset() throws IOException {
        getDelegate().reset();
    }

    @Override
    public final boolean markSupported() {
        return getDelegate().markSupported();
    }
}
