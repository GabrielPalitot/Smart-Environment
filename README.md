# SistemasDistribuidospt2
## Introduction
The work consists of implementing an intelligent environment. The place has a total of 4 pieces of equipment, they are Air Conditioning, a Window, a Lamp, and a temperature sensor. They are controlled by a user-used gateway application. All are simulated by software (each one is a process). The programming language used was Java which was used for communication between the equipment. The gateway was predominantly by Protocol Buffer, except for the temperature sensor, in which a string was used.

## Requirements for simulating the project
To do this, you must have installed the JDK on your machine, which can be installed on Oracle's website.

To simulate the project it is not necessary to have the protocol compiler buffers since the .proto is already compiled, but if you want to make any changes to the .proto it will be necessary to recompile it and for that, it will be necessary to install your compiler. To recompile, simply follow the command below:

```protocol -I=%SRC_DIR% --java_out=%DST_DIR% %SRC_DIR%\<name-of-archive>.proto```

DST_DIR is the destination directory and SRC_DIR is where the .proto is.

## How to run the project

To do this, just run the server that has the name "ProtobuffGatewayTCP_UDP" which is the gateway application. After that, you can select any of the smart devices. These are the air conditioner, window, lamp, and sensor (remembering that the sensor has a UDP connection). 
To compile a program in Java, simply:

```javac *.java```

Keep in mind that it will compile all .java files from the directory. After that, to execute, just use:

```java <executable-name>```

Enjoy this.



