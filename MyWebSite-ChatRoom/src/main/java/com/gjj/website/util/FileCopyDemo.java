package com.gjj.website.util;

import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;

/**
 * @author :
 * @since 2020/1/20 16:36
 */


public class FileCopyDemo {
    private static final int ROUNDS = 5;


    private static void benchmark(FileCopyRunner test, File source, File target) {
        long elapsed = 0L;
        for (int i = 0; i < ROUNDS; i++) {
            long startTime = System.currentTimeMillis();
            test.copyFile(source, target);
            elapsed += (System.currentTimeMillis() - startTime);
            target.delete();
        }
        System.out.println(test.toString() + ":" + elapsed / ROUNDS);
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        FileCopyRunner noBufferStreamCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                InputStream input = null;
                OutputStream out = null;
                try {
                    input = new FileInputStream(source);
                    out = new FileOutputStream(target);
                    int result;

                    while ((result = input.read()) != -1) {
                        out.write(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close(input);
                    close(out);
                }
            }

            @Override
            public String toString() {
                return "noBufferStreamCopy";
            }
        };

        FileCopyRunner bufferedStreamCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                BufferedOutputStream fout = null;
                BufferedInputStream fin = null;
                try {
                    fin = new BufferedInputStream(new FileInputStream(source));
                    fout = new BufferedOutputStream(new FileOutputStream(target));

                    byte[] buffer = new byte[1024];

                    int result;
                    while ((result = fin.read(buffer)) != -1) {
                        fout.write(buffer, 0, result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close(fin);
                    close(fout);
                }
            }

            @Override
            public String toString() {
                return "bufferedStreamCopy";
            }
        };

        //使用channel    nio方式
        FileCopyRunner nioBufferCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                FileChannel fin = null;
                FileChannel fout = null;

                try {
                    fin = new FileInputStream(source).getChannel();
                    fout = new FileOutputStream(target).getChannel();
                    //有 IntBuffer等等buffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (fin.read(byteBuffer) != -1) {
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            //确保buffer中的数据全部读完
                            fout.write(byteBuffer);
                        }
                        byteBuffer.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close(fin);
                    close(fout);
                }
            }

            @Override
            public String toString() {
                return "nioBufferCopy";
            }
        };

        FileCopyRunner nioTransferCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                FileChannel fin = null;
                FileChannel fout = null;

                try {
                    fin = new FileInputStream(source).getChannel();
                    fout = new FileOutputStream(target).getChannel();
                    long size = fin.size();
                    long transfer = 0;
                    while (transfer != size) {
                        //fin.size()源文件的大小
                        transfer += fin.transferTo(0, size, fout);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close(fin);
                    close(fout);
                }

            }

            @Override
            public String toString() {
                return "nioTransferCopy";
            }
        };


        File smallFile = new File("C:\\Users\\Kelly\\Desktop\\MyWebSite\\MyWebSite-ChatRoom\\smallFile.log");
        File smallFileCopy = new File("C:\\Users\\Kelly\\Desktop\\MyWebSite\\MyWebSite-ChatRoom\\smallFileCopy");
        System.out.println("----Copying smallFile");
        benchmark(noBufferStreamCopy, smallFile, smallFileCopy);
        benchmark(bufferedStreamCopy, smallFile, smallFileCopy);
        benchmark(nioBufferCopy, smallFile, smallFileCopy);
        benchmark(nioTransferCopy, smallFile, smallFileCopy);


        File bigFile = new File("C:\\Users\\Kelly\\Desktop\\MyWebSite\\MyWebSite-ChatRoom\\bigFile.log");
        File bigFileCopy = new File("C:\\Users\\Kelly\\Desktop\\MyWebSite\\MyWebSite-ChatRoom\\bigFileCopy");
        System.out.println("----Copying bigFile");
        benchmark(bufferedStreamCopy, bigFile, bigFileCopy);
        benchmark(nioBufferCopy, bigFile, bigFileCopy);
        benchmark(nioTransferCopy, bigFile, bigFileCopy);



        File hugeFile = new File("C:\\Users\\Kelly\\Desktop\\MyWebSite\\MyWebSite-ChatRoom\\hugeFile.log");
        File hugeFileCopy = new File("C:\\Users\\Kelly\\Desktop\\MyWebSite\\MyWebSite-ChatRoom\\hugeFileCopy");
        System.out.println("----Copying hugeFileCopy");
        benchmark(bufferedStreamCopy, hugeFile, hugeFileCopy);
        benchmark(nioBufferCopy, hugeFile, hugeFileCopy);
        benchmark(nioTransferCopy, hugeFile, hugeFileCopy);





    }


}

