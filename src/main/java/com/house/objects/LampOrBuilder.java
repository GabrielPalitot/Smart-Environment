// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: livingroom.proto

package com.house.objects;

public interface LampOrBuilder extends
    // @@protoc_insertion_point(interface_extends:intelligentHouse.Lamp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required bool turn = 1;</code>
   * @return Whether the turn field is set.
   */
  boolean hasTurn();
  /**
   * <code>required bool turn = 1;</code>
   * @return The turn.
   */
  boolean getTurn();

  /**
   * <code>required .intelligentHouse.Lamp.Status status = 2;</code>
   * @return Whether the status field is set.
   */
  boolean hasStatus();
  /**
   * <code>required .intelligentHouse.Lamp.Status status = 2;</code>
   * @return The status.
   */
  com.house.objects.Lamp.Status getStatus();
}
