package com.janosgyerik.practice.codility.so;

import java.io.DataInput;
import java.io.IOException;

public class ByteCounterTest {

    public void doSomething() {

        //thread setup above
        int i = 0;
        int bytecounter = 0;

        byte[] inbyte = new byte[1024];

        byte b;


        while (true)

        {

            bytecounter = 0;


            try {

                DataInput disIn = getInput();
                byte endMarker = (byte) 0xfe;
                while ((b = disIn.readByte()) != endMarker ) {


                    if (b == (byte) 0xfd) {


                        inbyte = new byte[1024];
                        inbyte[0] = b;
                        bytecounter = 1;


                    } else {

                        inbyte[bytecounter] = b;
                        bytecounter++;

                    }


                }

            } catch (java.io.EOFException ioef) {
                System.out.println("EOF received");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }


//do stuff with the inbyte[] data....

// and come back through the while(true) loop


        }
    }

    private DataInput getInput() {
        return null;
    }
}
