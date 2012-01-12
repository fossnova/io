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
 * TODO: javadoc
 * 
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public class DelegatingPrintStream extends PrintStream {

    private final PrintStream delegate;

    public DelegatingPrintStream() {
        super( NullOutputStream.getInstance() );
        delegate = NullPrintStream.getInstance();
    }

    public DelegatingPrintStream( final PrintStream delegate ) {
        super( NullOutputStream.getInstance() );
        if ( delegate == null ) throw new IllegalArgumentException();
        this.delegate = delegate;
    }

    protected PrintStream getDelegate() {
        return delegate;
    }

    @Override
    public final void flush() {
        getDelegate().flush();
    }

    @Override
    public final void close() {
        getDelegate().close();
    }

    @Override
    public final boolean checkError() {
        return getDelegate().checkError();
    }

    @Override
    public final void write( final int data ) {
        getDelegate().write( data );
    }

    @Override
    public final void write( final byte[] data, final int offset, final int length ) {
        getDelegate().write( data, offset, length );
    }

    @Override
    public final void print( final boolean data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final char data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final int data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final long data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final float data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final double data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final char[] data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final String data ) {
        getDelegate().print( data );
    }

    @Override
    public final void print( final Object data ) {
        getDelegate().print( data );
    }

    @Override
    public final void println() {
        getDelegate().println();
    }

    @Override
    public final void println( final boolean data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final char data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final int data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final long data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final float data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final double data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final char[] data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final String data ) {
        getDelegate().println( data );
    }

    @Override
    public final void println( final Object data ) {
        getDelegate().println( data );
    }

    @Override
    public final PrintStream printf( final String format, final Object ... args ) {
        getDelegate().printf( format, args );
        return this;
    }

    @Override
    public final PrintStream printf( final Locale locale, final String format, final Object ... args ) {
        getDelegate().printf( locale, format, args );
        return this;
    }

    @Override
    public final PrintStream format( final String format, final Object ... args ) {
        getDelegate().format( format, args );
        return this;
    }

    @Override
    public final PrintStream format( final Locale locale, final String format, final Object ... args ) {
        getDelegate().format( locale, format, args );
        return this;
    }

    @Override
    public final PrintStream append( final CharSequence data ) {
        getDelegate().append( data );
        return this;
    }

    @Override
    public final PrintStream append( final CharSequence data, final int start, final int end ) {
        getDelegate().append( data, start, end );
        return this;
    }

    @Override
    public final PrintStream append( final char data ) {
        getDelegate().append( data );
        return this;
    }

    @Override
    public final void write( final byte[] data ) throws IOException {
        getDelegate().write( data );
    }
}
