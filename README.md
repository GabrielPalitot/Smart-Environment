# SistemasDistribuidospt2
## Introduction
The work consists of implementing an intelligent environment. The place has a total of 4 pieces of equipment, they are: Air Conditioning, Window, Lamp and temperature sensor. Controlled by a user-used gateway application. All simulated by software (each one is a process). The programming language used was Java and was used for communication between the equipment and the gateway was predominantly by Protocol Buffer, with the exception of the temperature sensor, in which a string was used.
## How to simulate the project
To do this, you need to have installed the JDK on your machine, which can be installed on Oracle's website.

To simulate the project it is not necessary to have the protocol compiler buffers since the .proto is already compiled, but if you want to make any changes to the .proto it will be necessary to recompile it and for that it will be necessary to install your compiler. To recompile, simply follow the command below:
```protoc -I=%SRC_DIR% --java_out=%DST_DIR% %SRC_DIR%\livingroom.proto```



