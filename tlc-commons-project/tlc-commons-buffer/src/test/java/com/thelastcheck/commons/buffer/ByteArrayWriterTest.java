package com.thelastcheck.commons.buffer;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class ByteArrayWriterTest {

    @Test
    public void testWriteCharArrayIntInt() throws IOException {
        ByteArray ba = new ByteArray(100, ByteArray.EBCDIC_CHARSET_NAME);
        write(ba);
        ba = new ByteArray(100);
        write(ba);
        
    }

    private void write(ByteArray ba) throws IOException {
        ByteArrayWriter writer = new ByteArrayWriter(ba);
        
        writer.write(new String("ABCDEFG1234").toCharArray());
        writer.write(new String("ABCDEFG1234").toCharArray());
        writer.write(new String("BIG TEST FOR SIZE AND ALLOCATION").toCharArray());
        writer.write('a');
        writer.write("STRING");
        writer.close();
        
        assertTrue(writer.getTotalBytesWritten() == 61);
        
        ByteArrayDumpFormatter.dumpByteArray("test", writer.getBuffer());
        ByteArrayDumpFormatter.dumpByteArray("orig", ba);
    }

}
