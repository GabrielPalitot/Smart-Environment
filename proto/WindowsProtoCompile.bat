@echo off
cd %~dp0
set SRC_DIR=.
set DST_DIR=..\src\main\java
protoc -I=%SRC_DIR% --java_out=%DST_DIR% %SRC_DIR%\livingroom.proto
