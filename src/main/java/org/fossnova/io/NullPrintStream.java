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
public final class NullPrintStream extends PrintStream {

    private static final NullPrintStream INSTANCE = new NullPrintStream();

    private NullPrintStream() {
        // forbidden constructor
        super( NullOutputStream.getInstance() );
    }

    public static NullPrintStream getInstance() {
        return INSTANCE;
    }

    @Override
    public void flush() {
        // does nothing
    }

    @Override
    public void close() {
        // does nothing
    }

    @Override
    public boolean checkError() {
        // does nothing
        return false;
    }

    @Override
    public void write( final int data ) {
        // does nothing
    }

    @Override
    public void write( final byte[] data, final int offset, final int length ) {
        // does nothing
    }

    @Override
    public void print( final boolean data ) {
        // does nothing
    }

    @Override
    public void print( final char data ) {
        // does nothing
    }

    @Override
    public void print( final int data ) {
        // does nothing
    }

    @Override
    public void print( final long data ) {
        // does nothing
    }

    @Override
    public void print( final float data ) {
        // does nothing
    }

    @Override
    public void print( final double data ) {
        // does nothing
    }

    @Override
    public void print( final char[] data ) {
        // does nothing
    }

    @Override
    public void print( final String data ) {
        // does nothing
    }

    @Override
    public void print( final Object data ) {
        // does nothing
    }

    @Override
    public void println() {
        // does nothing
    }

    @Override
    public void println( final boolean data ) {
        // does nothing
    }

    @Override
    public void println( final char data ) {
        // does nothing
    }

    @Override
    public void println( final int data ) {
        // does nothing
    }

    @Override
    public void println( final long data ) {
        // does nothing
    }

    @Override
    public void println( final float data ) {
        // does nothing
    }

    @Override
    public void println( final double data ) {
        // does nothing
    }

    @Override
    public void println( final char[] data ) {
        // does nothing
    }

    @Override
    public void println( final String data ) {
        // does nothing
    }

    @Override
    public void println( final Object data ) {
        // does nothing
    }

    @Override
    public PrintStream printf( final String format, final Object ... args ) {
        // does nothing
        return this;
    }

    @Override
    public PrintStream printf( final Locale locale, final String format, final Object ... args ) {
        // does nothing
        return this;
    }

    @Override
    public PrintStream format( final String format, final Object ... args ) {
        // does nothing
        return this;
    }

    @Override
    public PrintStream format( final Locale locale, final String format, final Object ... args ) {
        // does nothing
        return this;
    }

    @Override
    public PrintStream append( final CharSequence data ) {
        // does nothing
        return this;
    }

    @Override
    public PrintStream append( final CharSequence data, final int start, final int end ) {
        // does nothing
        return this;
    }

    @Override
    public PrintStream append( final char data ) {
        // does nothing
        return this;
    }

    @Override
    public void write( final byte[] data ) throws IOException {
        // does nothing
    }
}
