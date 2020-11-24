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
import java.io.PrintStream;
import java.util.Locale;

/**
 * A <code>TeePrintStream</code> overrides all methods of <code>PrintStream</code>
 * and delegates their execution to the wrapped print streams.
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class TeePrintStream extends PrintStream {

    private final PrintStream[] delegates;

    /**
     * Creates a <code>DelegatingPrintStream</code> that wraps passed print streams.
     *
     * @throws <code>IllegalArgumentException</code> if parameter is null
     */
    public TeePrintStream( final PrintStream first, final PrintStream second, final PrintStream ... others ) {
        super( NullOutputStream.getInstance() );
        // ensure preconditions
        if ( first == null || second == null ) {
            throw new IllegalArgumentException( "PrintStream cannot be null" );
        }
        if ( others != null && others.length > 0 ) {
            for ( final OutputStream os : others ) {
                if ( os == null ) {
                    throw new IllegalArgumentException( "PrintStream cannot be null" );
                }
            }
        }
        // initialize
        final int size = 2 + ( others != null ? others.length : 0 );
        delegates = new PrintStream[ size ];
        delegates[ 0 ] = first;
        delegates[ 1 ] = second;
        if ( size > 2 ) {
            for ( int i = 0; i < others.length; i++ ) {
                delegates[ 2 + i ] = others[ i ];
            }
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void flush() {
        for ( final PrintStream delegate : delegates ) {
            delegate.flush();
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void close() {
        for ( final PrintStream delegate : delegates ) {
            delegate.close();
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public boolean checkError() {
        for ( final PrintStream delegate : delegates ) {
            if ( delegate.checkError() ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void write( final int data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.write( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void write( final byte[] data, final int offset, final int length ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.write( data, offset, length );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final boolean data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final char data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final int data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final long data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final float data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final double data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final char[] data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final String data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void print( final Object data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.print( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println() {
        for ( final PrintStream delegate : delegates ) {
            delegate.println();
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final boolean data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final char data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final int data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final long data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final float data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final double data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final char[] data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final String data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void println( final Object data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.println( data );
        }
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public PrintStream printf( final String format, final Object ... args ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.printf( format, args );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public PrintStream printf( final Locale locale, final String format, final Object ... args ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.printf( locale, format, args );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public PrintStream format( final String format, final Object ... args ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.format( format, args );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public PrintStream format( final Locale locale, final String format, final Object ... args ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.format( locale, format, args );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public PrintStream append( final CharSequence data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.append( data );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public PrintStream append( final CharSequence data, final int start, final int end ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.append( data, start, end );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public PrintStream append( final char data ) {
        for ( final PrintStream delegate : delegates ) {
            delegate.append( data );
        }
        return this;
    }

    /**
     * Delegates the call to the wrapped print streams.
     */
    @Override
    public void write( final byte[] data ) throws IOException {
        for ( final PrintStream delegate : delegates ) {
            delegate.write( data );
        }
    }

}
