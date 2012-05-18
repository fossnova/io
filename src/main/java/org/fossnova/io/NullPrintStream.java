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
 * This <code>PrintStream</code> does nothing. All data written to it are
 * completely ignored. It never throws <code>IOException</code>.
 * <p>
 * This class is thread safe. 
 * </p>
 * 
 * @author <a href="mailto:opalka dot richard at gmail dot com">Richard Opalka</a>
 */
public final class NullPrintStream extends PrintStream {

    private static final NullPrintStream INSTANCE = new NullPrintStream();

    private NullPrintStream() {
        super( NullOutputStream.getInstance() );
    }

    /**
     * Returns <code>NullPrintStream</code> singleton instance.
     */
    public static NullPrintStream getInstance() {
        return INSTANCE;
    }

    /**
     * Does nothing.
     */
    @Override
    public void flush() {
    }

    /**
     * Does nothing.
     */
    @Override
    public void close() {
    }

    /**
     * Does nothing.
     */
    @Override
    public boolean checkError() {
        return Boolean.FALSE.booleanValue();
    }

    /**
     * Does nothing.
     */
    @Override
    public void write( final byte[] data ) throws IOException {
    }

    /**
     * Does nothing.
     */
    @Override
    public void write( final int data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void write( final byte[] data, final int offset, final int length ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final boolean data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final char data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final int data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final long data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final float data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final double data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final char[] data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final String data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void print( final Object data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println() {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final boolean data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final char data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final int data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final long data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final float data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final double data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final char[] data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final String data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public void println( final Object data ) {
    }

    /**
     * Does nothing.
     */
    @Override
    public PrintStream printf( final String format, final Object ... args ) {
        return this;
    }

    /**
     * Does nothing.
     */
    @Override
    public PrintStream printf( final Locale locale, final String format, final Object ... args ) {
        return this;
    }

    /**
     * Does nothing.
     */
    @Override
    public PrintStream format( final String format, final Object ... args ) {
        return this;
    }

    /**
     * Does nothing.
     */
    @Override
    public PrintStream format( final Locale locale, final String format, final Object ... args ) {
        return this;
    }

    /**
     * Does nothing.
     */
    @Override
    public PrintStream append( final CharSequence data ) {
        return this;
    }

    /**
     * Does nothing.
     */
    @Override
    public PrintStream append( final CharSequence data, final int start, final int end ) {
        return this;
    }

    /**
     * Does nothing.
     */
    @Override
    public PrintStream append( final char data ) {
        return this;
    }
}
