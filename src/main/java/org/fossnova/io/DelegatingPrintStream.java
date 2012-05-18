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
import java.io.PrintStream;
import java.util.Locale;

/**
 * <p>
 * A <code>DelegatingPrintStream</code> overrides all methods of
 * <code>PrintStream</code> and delegates their execution to the wrapped
 * <code>PrintStream</code>. The wrapped <code>PrintStream</code>
 * is always obtained via {@link #getDelegate()} method.
 * </p>
 * <p>
 * This class is not thread safe. 
 * </p>
 * 
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public class DelegatingPrintStream extends PrintStream {

    private final PrintStream delegate;

    /**
     * Creates a <code>DelegatingPrintStream</code> that wraps passed print stream.
     *
     * @param delegate the print stream to be wrapped
     * @throws <code>IllegalArgumentException</code> if parameter is null
     */
    public DelegatingPrintStream( final PrintStream delegate ) {
        super( NullOutputStream.getInstance() );
        if ( delegate == null ) {
            throw new IllegalArgumentException();
        }
        this.delegate = delegate;
    }

    /**
     * Returns wrapped print stream.
     */
    protected PrintStream getDelegate() {
        return delegate;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void flush() {
        getDelegate().flush();
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void close() {
        getDelegate().close();
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public boolean checkError() {
        return getDelegate().checkError();
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void write( final int data ) {
        getDelegate().write( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void write( final byte[] data, final int offset, final int length ) {
        getDelegate().write( data, offset, length );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final boolean data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final char data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final int data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final long data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final float data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final double data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final char[] data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final String data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void print( final Object data ) {
        getDelegate().print( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println() {
        getDelegate().println();
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final boolean data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final char data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final int data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final long data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final float data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final double data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final char[] data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final String data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void println( final Object data ) {
        getDelegate().println( data );
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public PrintStream printf( final String format, final Object ... args ) {
        getDelegate().printf( format, args );
        return this;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public PrintStream printf( final Locale locale, final String format, final Object ... args ) {
        getDelegate().printf( locale, format, args );
        return this;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public PrintStream format( final String format, final Object ... args ) {
        getDelegate().format( format, args );
        return this;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public PrintStream format( final Locale locale, final String format, final Object ... args ) {
        getDelegate().format( locale, format, args );
        return this;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public PrintStream append( final CharSequence data ) {
        getDelegate().append( data );
        return this;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public PrintStream append( final CharSequence data, final int start, final int end ) {
        getDelegate().append( data, start, end );
        return this;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public PrintStream append( final char data ) {
        getDelegate().append( data );
        return this;
    }

    /**
     * Delegates the call to the wrapped print stream.
     */
    @Override
    public void write( final byte[] data ) throws IOException {
        getDelegate().write( data );
    }
}
