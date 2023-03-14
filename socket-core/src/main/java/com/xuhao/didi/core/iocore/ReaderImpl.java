package com.xuhao.didi.core.iocore;

import com.xuhao.didi.core.exceptions.ReadException;
import com.xuhao.didi.core.iocore.interfaces.IOAction;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.core.utils.BytesUtils;
import com.xuhao.didi.core.utils.SLog;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by xuhao on 2017/5/31.
 */

public class ReaderImpl extends AbsReader {

    private Buffer mRemainingBuf;

    @Override
    public void read() throws RuntimeException {
        OriginalData originalData = new OriginalData();
        IReaderProtocol headerProtocol = mOkOptions.getReaderProtocol();
        int headerLength = headerProtocol.getHeaderLength();
        ByteBuffer headBuf = ByteBuffer.allocate(headerLength);
       /* ByteBuffer tempBuff = ByteBuffer.allocate(1024*30);
        readBufferFromChanel(tempBuff);*/
        headBuf.order(mOkOptions.getReadByteOrder());
        try {
            if (mRemainingBuf != null) {
                //System.out.println("buffer remain: " + BytesUtils.toHexStringForLog(mRemainingBuf.array()));
                mRemainingBuf.flip();
                int length = Math.min(mRemainingBuf.remaining(), headerLength);
                headBuf.put((byte[]) mRemainingBuf.array(), 0, length);
                if (length < headerLength) {
                    //there are no data left
                    mRemainingBuf = null;
                    readHeaderFromChannel(headBuf, headerLength - length);
                } else {
                    mRemainingBuf.position(headerLength);
                }
            } else {
                if (headerLength > 0)
                    //System.out.println("no buffer remain: " +BytesUtils.toHexStringForLog(headBuf.array()));
                    readHeaderFromChannel(headBuf, headBuf.capacity());
            }
            //TODO 需要在这里对数据包头部初始位判断是否正确，若头部数据不对会造成读取数据异常（MOOZ中判断头部是否为AABB开头）
            originalData.setHeadBytes(headBuf.array());
            if (SLog.isDebug()) {
                if (headerLength > 0)
                    SLog.i("read head: " + BytesUtils.toHexStringForLog(headBuf.array()));
            }
            int bodyLength = headerProtocol.getBodyLength(originalData.getHeadBytes(), mOkOptions.getReadByteOrder());
            if (SLog.isDebug()) {
                if (bodyLength > 0)
                    SLog.i("need read body length: " + bodyLength);
            }
            if (bodyLength > 0) {
                if (bodyLength > mOkOptions.getMaxReadDataMB() * 1024 * 1024) {
                    throw new ReadException("Need to follow the transmission protocol.\r\n" +
                            "Please check the client/server code.\r\n" +
                            "According to the packet header data in the transport protocol, the package length is " + bodyLength + " Bytes.\r\n" +
                            "You need check your <ReaderProtocol> definition");
                }
                ByteBuffer byteBuffer = ByteBuffer.allocate(bodyLength);
                byteBuffer.order(mOkOptions.getReadByteOrder());
                if (mRemainingBuf != null) {
                    int bodyStartPosition = mRemainingBuf.position();
                    int length = Math.min(mRemainingBuf.remaining(), bodyLength);
                    byteBuffer.put((byte[]) mRemainingBuf.array(), bodyStartPosition, length);
                    mRemainingBuf.position(bodyStartPosition + length);
                    if (length == bodyLength) {
                        if (mRemainingBuf.remaining() > 0) {//there are data left
                            ByteBuffer temp = ByteBuffer.allocate(mRemainingBuf.remaining());
                            temp.order(mOkOptions.getReadByteOrder());
                            temp.put((byte[]) mRemainingBuf.array(), mRemainingBuf.position(), mRemainingBuf.remaining());
                            mRemainingBuf = temp;
                        } else {//there are no data left
                            mRemainingBuf = null;
                        }
                        //cause this time data from remaining buffer not from channel.
                        byte bodyBytes[] = new byte[byteBuffer.position()];
                        System.arraycopy(byteBuffer.array(), 0, bodyBytes, 0, bodyBytes.length);
                        originalData.setBodyBytes(bodyBytes);
                        mStateSender.sendBroadcast(IOAction.ACTION_READ_COMPLETE, originalData);
                        return;
                    } else {//there are no data left in buffer and some data pieces in channel
                        mRemainingBuf = null;
                    }
                }
                readBodyFromChannel(byteBuffer);
                byte bodyBytes[] = new byte[byteBuffer.position()];
                System.arraycopy(byteBuffer.array(), 0, bodyBytes, 0, bodyBytes.length);
                originalData.setBodyBytes(bodyBytes);
            } else if (bodyLength == 0) {
                originalData.setBodyBytes(new byte[0]);
                if (mRemainingBuf != null) {
                    //the body is empty so header remaining buf need set null
                    if (mRemainingBuf.hasRemaining()) {
                        ByteBuffer temp = ByteBuffer.allocate(mRemainingBuf.remaining());
                        temp.order(mOkOptions.getReadByteOrder());
                        temp.put((byte[]) mRemainingBuf.array(), mRemainingBuf.position(), mRemainingBuf.remaining());
                        mRemainingBuf = temp;
                    } else {
                        mRemainingBuf = null;
                    }
                }

            } else if (bodyLength < 0) {
                throw new ReadException(
                        "read body is wrong,this socket input stream is end of file read " + bodyLength + " ,that mean this socket is disconnected by server");
            }
          /*  if (mInputStream.available() > 0) {
               // System.out.println("need solve no body no length no remaining buff:" + mInputStream.available());
                if (mRemainingBuf == null)
                    mRemainingBuf = ByteBuffer.allocate(mInputStream.available());
                else {
                    ByteBuffer temp = ByteBuffer.allocate(mRemainingBuf.capacity() + mInputStream.available());
                    temp.order(mOkOptions.getReadByteOrder());
                    temp.put((byte[]) mRemainingBuf.array(), mRemainingBuf.position(), mRemainingBuf.remaining());
                    mRemainingBuf = temp;
                }
                readLastBufferFromChanel((ByteBuffer) mRemainingBuf);
            } *//*else
                System.out.println("no opt input available:" + mInputStream.available());*//*
            if (mRemainingBuf != null) {
             //   System.out.println("has RemainingBuf:" + mRemainingBuf.capacity());
            }*/
           /* if (originalData.getTotalBytes().length==0&&mRemainingBuf.capacity()>0){
                originalData.setBodyBytes((byte[]) mRemainingBuf.array());
            }*/
            mStateSender.sendBroadcast(IOAction.ACTION_READ_COMPLETE, originalData);
        } catch (Exception e) {
            ReadException readException = new ReadException(e);
            e.printStackTrace();
            throw readException;
        }
    }

    private void readBufferFromChanel(ByteBuffer byteBuffer) {
        int i;
        for (i = 0; i < byteBuffer.array().length; i++) {
            byte[] bytes = new byte[1];
            int value = 0;
            try {
                value = mInputStream.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (value == -1) {
                break;
            }
            byteBuffer.put(bytes);
        }
        if (SLog.isDebug()) {
            SLog.i("readBufferFromChanel total bytes: " + BytesUtils.toHexStringForLog(byteBuffer.array()));
        }
    }

    private void readLastBufferFromChanel(ByteBuffer byteBuffer) {
        int i;
        for (i = 0; i < byteBuffer.array().length; i++) {
            byte[] bytes = new byte[1];
            int value = 0;
            try {
               // System.out.println("do read buffer i:" + i);
                value = mInputStream.read(bytes);
              //  System.out.println("do read buffer i:" + i + " value:" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (value == -1) {
                break;
            }
            byteBuffer.put(bytes);
        }
        if (SLog.isDebug()) {
            SLog.i("readLastBufferFromChanel total bytes: " + BytesUtils.toHexStringForLog(byteBuffer.array()));
        }
    }

    private void readHeaderFromChannel(ByteBuffer headBuf, int readLength) throws IOException {
        for (int i = 0; i < readLength; i++) {
            byte[] bytes = new byte[1];
            int value = mInputStream.read(bytes);
            if (value == -1) {
                throw new ReadException(
                        "read head is wrong, this socket input stream is end of file read " + value + " ,that mean this socket is disconnected by server");
            }
            headBuf.put(bytes);
        }
    }

    private void readBodyFromChannel(ByteBuffer byteBuffer) throws IOException {
        int bodyRealLength = 0;
        while (byteBuffer.hasRemaining()) {
            try {
                byte[] bufArray = new byte[mOkOptions.getReadPackageBytes()];
                int len = mInputStream.read(bufArray);
                if (len == -1) {
                    break;
                }
                bodyRealLength += len;
                int remaining = byteBuffer.remaining();
                //System.out.println("read body len:"+len+" remaining:"+remaining+" has remaining:"+byteBuffer.hasRemaining());
                if (len > remaining) {
                    byteBuffer.put(bufArray, 0, remaining);
                    mRemainingBuf = ByteBuffer.allocate(len - remaining);
                    ((ByteBuffer) mRemainingBuf).order(mOkOptions.getReadByteOrder());
                    ((ByteBuffer) mRemainingBuf).put(bufArray, remaining, len - remaining);
                } else {
                    byteBuffer.put(bufArray, 0, len);
                }
                if (!mOkOptions.getReaderProtocol().isKnowLength() && len != 50 && len <= remaining) {
                    break;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        if (SLog.isDebug()) {
            SLog.i("readBodyFromChannel total bytes: " + BytesUtils.toHexStringForLog(byteBuffer.array()));
            SLog.i("readBodyFromChannel STR: " + new String(byteBuffer.array()));
            SLog.i("readBodyFromChannel total length:" + (byteBuffer.capacity() - byteBuffer.remaining()));
        }
    }

}
