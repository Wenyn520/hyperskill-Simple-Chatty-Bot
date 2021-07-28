package com.example.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

//java.nio Channel
public class Main {

    public static void main(String[] args) {
        try{
            //Pipe is use to transfer data between threads in single direction
            //have 2 channels, writable SinkChannel and readable SourceChannel
            //open() open a pipe
            Pipe pipe = Pipe.open();
            //need to implement run()
            //writer to write current time
            Runnable writer = new Runnable() {
                //run() will be called after thread start
                @Override
                public void run() {
                    try{
                        //SinkChannel is writable channel
                        //sink() return SinkChannel of this Pipe
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        //allocate new bytebuffer of 56 bytes
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        //write 10 times
                        for(int i=0;i<10;i++){
                            //return current time in milliseconds
                            String currentTime = "The time is: "+System.currentTimeMillis();
                            //write to buffer
                            buffer.put(currentTime.getBytes());
                            //set position to 0
                            buffer.flip();
                            //hasRemaining() return true if there is any element between current position
                            //and limit
                            while (buffer.hasRemaining()){
                                //write to sinkchannel from buffer
                                sinkChannel.write(buffer);
                            }
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };

            //reader
            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try{
                        //source() return source channel of this pipe
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for(int i=0;i<10;i++){
                            //read from channel to buffer, return number of bytes read
                            int bytesRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip(); //switch from write to read
                            //get from buffer to byte[]
                            buffer.get(timeString);
                            System.out.println("Reader thread: "+new String(timeString));
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            //allocate a new Thread to the Runnable object whose run() will be call after this thread
            //has started
            new Thread(writer).start();
            new Thread(reader).start();
        }catch (IOException e){
            e.printStackTrace();
        }



        //binary data
//        try(FileOutputStream binFile = new FileOutputStream("data.dat");
//            FileChannel binChannel = binFile.getChannel()){
//            //write to file
//            ByteBuffer buffer = ByteBuffer.allocate(100);
//            byte[] outputBytes = "Hello World".getBytes();
//            byte[] outputBytes2 = "Nice to meet you".getBytes();
//            //chain the put() together
//            //buffer.put(outputBytes).putInt(245).putInt(-234).put(outputBytes2).putInt(1000);
//            buffer.put(outputBytes);
//            //start position of 245
//            long int1Pos = outputBytes.length;
//            buffer.putInt(245);
//            //start position of -234
//            long int2Pos = int1Pos+Integer.BYTES;
//            buffer.putInt(-234);
//            buffer.put(outputBytes2);
//            //start position of 1000
//            long int3Pos = int2Pos+outputBytes2.length+Integer.BYTES;
//            buffer.putInt(1000);
//            buffer.flip();
//            //actually read from buffer, so call flip()
//            binChannel.write(buffer);
//
//            //read in
//            RandomAccessFile ra = new RandomAccessFile("data.dat","rwd");
//            FileChannel channel = ra.getChannel();
//
//            //read in int in reverse order (random access)
//            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
//            //set the channel's file position at starting position of 1000
//            channel.position(int3Pos);
//            //read into buffer at current file position, then update file position
//            channel.read(readBuffer); //actually write to buffer
//            readBuffer.flip();
//            System.out.println("int 3 = "+readBuffer.getInt()); //return the value
//            readBuffer.flip();
//            //set channel file position at starting position of -234
//            channel.position(int2Pos);
//            channel.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int 2 = "+readBuffer.getInt()); //return the value
//            //set position
//            channel.position(int1Pos);
//            readBuffer.flip();
//            channel.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int 1 = "+readBuffer.getInt()); //return the value
//
//            //copy a file to another
//            RandomAccessFile copyFile = new RandomAccessFile("datacopy.dat","rw");
//            FileChannel copyChannel = copyFile.getChannel();
//            channel.position(0);
//            //transferFrom() read number of bytes (channel.size()), from channel, and write to this
//            //channel (copyChannel) at position 0
//            //return number of bytes that have transferred
//            //note that the position is relative, means that relative to current position
//            //0 means start after current position
//            //long numTransferred = copyChannel.transferFrom(channel,0,channel.size());
//            //transferTo() read number of bytes (channel.size) from this channel start at position 0
//            //and write to copyChannel
//            long numTransferred = channel.transferTo(0,channel.size(),copyChannel);
//            System.out.println("Num transferred = "+numTransferred); //39
//            channel.close();;
//            ra.close();
//            copyChannel.close();



            //random access write
            //calculate start position
//            byte[] outputString = "Hello World".getBytes();
//            long str1Pos = 0;
//            //int1 position to be after hello world
//            long newInt1Pos = outputString.length;
//            //int2 position is itself + int1 length
//            long newInt2Pos = newInt1Pos+Integer.BYTES;
//            byte[] outputString2 = "Nice to meet you".getBytes();
//            //byte[] starting position
//            long str2Pos = newInt2Pos+Integer.BYTES;
//            long newInt3Pos = str2Pos+outputString2.length;
//
//            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
//            intBuffer.putInt(245); //write into buffer
//            intBuffer.flip();
//            //set position to write
//            binChannel.position(newInt1Pos);
//            //write into channel
//            binChannel.write(intBuffer); //buffer perform read
//            intBuffer.flip(); //so flip
//            //write into buffer
//            intBuffer.putInt(-234);
//            intBuffer.flip();
//            binChannel.position(newInt2Pos);
//            binChannel.write(intBuffer);
//            intBuffer.flip(); //so flip
//            //write into buffer
//            intBuffer.putInt(1000);
//            intBuffer.flip();
//            binChannel.position(newInt3Pos);
//            binChannel.write(intBuffer);
//            binChannel.position(str1Pos);
//            //wrap byte[] into buffer, then write the buffer
//            //wra[() will reset to 0 so no need flip
//            binChannel.write(ByteBuffer.wrap(outputString));
//            binChannel.position(str2Pos);
//            //wrap byte[] into buffer, then write the buffer
//            binChannel.write(ByteBuffer.wrap(outputString2));




//            ByteBuffer readBuffer = ByteBuffer.allocate(100);
//            channel.read(readBuffer); //read in from file, which is actually write to buffer
//            readBuffer.flip();
//            byte[] inputString = new byte[outputBytes.length];
//            readBuffer.get(inputString); //get byte[] into this array
//            System.out.println("inputString = "+new String(inputString));
//            System.out.println("int 1 = "+readBuffer.getInt());
//            System.out.println("int 2 = "+readBuffer.getInt());
//            byte[] inputString2 = new byte[outputBytes2.length];
//            readBuffer.get(inputString2);
//            System.out.println("inputString2 = "+new String(inputString2));
//            System.out.println("int 3 = "+readBuffer.getInt());

            //wrap() wrap byte[] into buffer, buffer capacity will be array length, buffer position set to 0
            //new buffer will be backed by byte[], means that any modification of buffer will cause
            //array being modified, vice versa
            //ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            //allocate a new buffer for byte[]
//            ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
//            //put() transfer byte[] to buffer
//            //write to file
//            buffer.put(outputBytes);
//            buffer.flip();
//            //write byte[] from buffer to this channel, return number of bytes written
//            //write() is consider as read from buffer, then write to channel
//            int numBytes = binChannel.write(buffer);
//            System.out.println("numBytes written is: "+numBytes);
//            //create new buffer for int
//            //allocate() create new buffer of buffer capacity as specified in bytes
//            //allocate number of bytes of a integer to buffer
//            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
//            //putInt() write the integer to buffer for 4 bytes, increase buffer position by 4
//            intBuffer.putInt(245);
//            //flip() to set buffer position to 0
//            //use flip() after putInt() so that buffer position move back, then when next write the buffer
//            //will able to write the content in buffer
//            //if not flip(), buffer position currently is at after 245, hence if write without flip, then
//            //will write nothing, cos nothing behind buffer position
//            //call flip() so later perform read operation from buffer
//            intBuffer.flip();
//            numBytes = binChannel.write(intBuffer);
//            System.out.println("numBytes written is: "+numBytes);
//            //if add elements into buffer, but exceed buffer capacity, wil
//            //throw java.nio.BufferOverflowException
//            //set buffer position to 0
//            intBuffer.flip(); //flip from read to write
//            intBuffer.putInt(-234);
//            intBuffer.flip(); //position set to 0
//            numBytes = binChannel.write(intBuffer);

            //use java.nio to read file
//            RandomAccessFile ra = new RandomAccessFile("data.dat","rwd");
//            FileChannel channel = ra.getChannel();
//            outputBytes[0] = 'a';
//            outputBytes[1] = 'b';
//            buffer.flip(); //to change write to read
//            //read() read bytes[] from file into buffer, return number of bytes read
//            long numBytesRead = channel.read(buffer);
//            //hasArray() return true, if buffer has backed by byte[]
//            if(buffer.hasArray()){
//                //array() return byte[] backed by buffer
//                System.out.println("byte buffer = "+new String(buffer.array()));
//            }

            //absolute read of get(index)
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer); //read 245
//            //getInt(index) read int at index position
//            //so no need to everytime use flip()
//            System.out.println(intBuffer.getInt(0)); //245
//            intBuffer.flip(); //flip to write into buffer
//            numBytesRead = channel.read(intBuffer); //read -234
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt(0)); //-234
//            System.out.println(intBuffer.getInt()); //-234

            //relative read of get()
//            //flip the other buffer to change from write to read
//            intBuffer.flip();
//            //read 245 into buffer
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            //getInt() read an int at buffer current position, then increment by 4
//            System.out.println(intBuffer.getInt()); //245
//            intBuffer.flip();
//            //read from channel to buffer, actually is write to buffer
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());
//            channel.close();
//            ra.close();

            //System.out.println("output bytes = "+ new String(outputBytes));



            //use java.io to read file
//            RandomAccessFile ra = new RandomAccessFile("data.dat","rwd");
//            //byte length same as hello world
//            byte[] b = new byte[outputBytes.length];
//            //read all elements in array from RandomAccessFile
//            ra.read(b); //read hello world
//            //create a String from byte[]
//            System.out.println(new String(b));
//            //read int from file
//            long int1 = ra.readInt(); //read 245
//            long int2 = ra.readInt(); //read -234
//            System.out.println(int1);
//            System.out.println(int2);


            //read bytes from FileInputStream
//            FileInputStream file = new FileInputStream("data.txt");
//            //FileChannel is a channel to read or write files, if is created from FileInputStream then
//            //can only used to read file, cannot be both read and write
//            //getChannel() return FileChannel of input stream
//            FileChannel channel = file.getChannel();

            //read write file sequentially
//            Path dataPath = FileSystems.getDefault().getPath("data.txt");
//            //getBytes() convert String to byte[]
//            //write() write byte[] to Path, by append write it at end of file
//            //option is optional to define, but if not define, default one will be erase out file if
//            //exist for rewrite
//            Files.write(dataPath,"\nLine 4".getBytes("UTF-8"), StandardOpenOption.APPEND);
//            //readAllLines() read all lines from file, return as List<String>
//            //can specify charsets also using StandardCharsets, by default is UTF-8
//            List<String> lines = Files.readAllLines(dataPath);
//            for(String line:lines){
//                System.out.println(line);
//            }

//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
