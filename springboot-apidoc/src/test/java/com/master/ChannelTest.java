package com.master;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-7-18
 * Time: 10:48
 * info:
 */
public class ChannelTest {

    public static void fileChannel() throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("D:\\BaiduNetdiskDownload\\架构师3.pdf", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    public static void main(String[] args) throws IOException {

        fileChannel();
    }
}
