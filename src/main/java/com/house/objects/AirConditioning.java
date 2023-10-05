// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: livingroom.proto

package com.house.objects;

/**
 * Protobuf type {@code intelligentHouse.AirConditioning}
 */
public final class AirConditioning extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:intelligentHouse.AirConditioning)
    AirConditioningOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AirConditioning.newBuilder() to construct.
  private AirConditioning(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AirConditioning() {
    status_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AirConditioning();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.house.objects.thingsOuterClass.internal_static_intelligentHouse_AirConditioning_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.house.objects.thingsOuterClass.internal_static_intelligentHouse_AirConditioning_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.house.objects.AirConditioning.class, com.house.objects.AirConditioning.Builder.class);
  }

  /**
   * Protobuf enum {@code intelligentHouse.AirConditioning.Status}
   */
  public enum Status
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>TURNED_ON = 0;</code>
     */
    TURNED_ON(0),
    /**
     * <code>TURNED_OFF = 1;</code>
     */
    TURNED_OFF(1),
    /**
     * <code>MALFUNCTION = 3;</code>
     */
    MALFUNCTION(3),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>TURNED_ON = 0;</code>
     */
    public static final int TURNED_ON_VALUE = 0;
    /**
     * <code>TURNED_OFF = 1;</code>
     */
    public static final int TURNED_OFF_VALUE = 1;
    /**
     * <code>MALFUNCTION = 3;</code>
     */
    public static final int MALFUNCTION_VALUE = 3;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Status valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static Status forNumber(int value) {
      switch (value) {
        case 0: return TURNED_ON;
        case 1: return TURNED_OFF;
        case 3: return MALFUNCTION;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Status>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Status> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Status>() {
            public Status findValueByNumber(int number) {
              return Status.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.house.objects.AirConditioning.getDescriptor().getEnumTypes().get(0);
    }

    private static final Status[] VALUES = values();

    public static Status valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Status(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:intelligentHouse.AirConditioning.Status)
  }

  public static final int TURN_FIELD_NUMBER = 1;
  private boolean turn_ = false;
  /**
   * <code>bool turn = 1;</code>
   * @return The turn.
   */
  @java.lang.Override
  public boolean getTurn() {
    return turn_;
  }

  public static final int STATUS_FIELD_NUMBER = 2;
  private int status_ = 0;
  /**
   * <code>.intelligentHouse.AirConditioning.Status status = 2;</code>
   * @return The enum numeric value on the wire for status.
   */
  @java.lang.Override public int getStatusValue() {
    return status_;
  }
  /**
   * <code>.intelligentHouse.AirConditioning.Status status = 2;</code>
   * @return The status.
   */
  @java.lang.Override public com.house.objects.AirConditioning.Status getStatus() {
    com.house.objects.AirConditioning.Status result = com.house.objects.AirConditioning.Status.forNumber(status_);
    return result == null ? com.house.objects.AirConditioning.Status.UNRECOGNIZED : result;
  }

  public static final int SETTING_TEMPERATURE_FIELD_NUMBER = 3;
  private int settingTemperature_ = 0;
  /**
   * <code>int32 setting_temperature = 3;</code>
   * @return The settingTemperature.
   */
  @java.lang.Override
  public int getSettingTemperature() {
    return settingTemperature_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (turn_ != false) {
      output.writeBool(1, turn_);
    }
    if (status_ != com.house.objects.AirConditioning.Status.TURNED_ON.getNumber()) {
      output.writeEnum(2, status_);
    }
    if (settingTemperature_ != 0) {
      output.writeInt32(3, settingTemperature_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (turn_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, turn_);
    }
    if (status_ != com.house.objects.AirConditioning.Status.TURNED_ON.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, status_);
    }
    if (settingTemperature_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, settingTemperature_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.house.objects.AirConditioning)) {
      return super.equals(obj);
    }
    com.house.objects.AirConditioning other = (com.house.objects.AirConditioning) obj;

    if (getTurn()
        != other.getTurn()) return false;
    if (status_ != other.status_) return false;
    if (getSettingTemperature()
        != other.getSettingTemperature()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TURN_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getTurn());
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    hash = (37 * hash) + SETTING_TEMPERATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSettingTemperature();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.house.objects.AirConditioning parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.house.objects.AirConditioning parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.house.objects.AirConditioning parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.house.objects.AirConditioning parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.house.objects.AirConditioning parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.house.objects.AirConditioning parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.house.objects.AirConditioning parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.house.objects.AirConditioning parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.house.objects.AirConditioning parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.house.objects.AirConditioning parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.house.objects.AirConditioning parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.house.objects.AirConditioning parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.house.objects.AirConditioning prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code intelligentHouse.AirConditioning}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:intelligentHouse.AirConditioning)
      com.house.objects.AirConditioningOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.house.objects.thingsOuterClass.internal_static_intelligentHouse_AirConditioning_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.house.objects.thingsOuterClass.internal_static_intelligentHouse_AirConditioning_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.house.objects.AirConditioning.class, com.house.objects.AirConditioning.Builder.class);
    }

    // Construct using com.house.objects.AirConditioning.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      turn_ = false;
      status_ = 0;
      settingTemperature_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.house.objects.thingsOuterClass.internal_static_intelligentHouse_AirConditioning_descriptor;
    }

    @java.lang.Override
    public com.house.objects.AirConditioning getDefaultInstanceForType() {
      return com.house.objects.AirConditioning.getDefaultInstance();
    }

    @java.lang.Override
    public com.house.objects.AirConditioning build() {
      com.house.objects.AirConditioning result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.house.objects.AirConditioning buildPartial() {
      com.house.objects.AirConditioning result = new com.house.objects.AirConditioning(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.house.objects.AirConditioning result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.turn_ = turn_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.status_ = status_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.settingTemperature_ = settingTemperature_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.house.objects.AirConditioning) {
        return mergeFrom((com.house.objects.AirConditioning)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.house.objects.AirConditioning other) {
      if (other == com.house.objects.AirConditioning.getDefaultInstance()) return this;
      if (other.getTurn() != false) {
        setTurn(other.getTurn());
      }
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
      }
      if (other.getSettingTemperature() != 0) {
        setSettingTemperature(other.getSettingTemperature());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              turn_ = input.readBool();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              status_ = input.readEnum();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              settingTemperature_ = input.readInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private boolean turn_ ;
    /**
     * <code>bool turn = 1;</code>
     * @return The turn.
     */
    @java.lang.Override
    public boolean getTurn() {
      return turn_;
    }
    /**
     * <code>bool turn = 1;</code>
     * @param value The turn to set.
     * @return This builder for chaining.
     */
    public Builder setTurn(boolean value) {

      turn_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>bool turn = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTurn() {
      bitField0_ = (bitField0_ & ~0x00000001);
      turn_ = false;
      onChanged();
      return this;
    }

    private int status_ = 0;
    /**
     * <code>.intelligentHouse.AirConditioning.Status status = 2;</code>
     * @return The enum numeric value on the wire for status.
     */
    @java.lang.Override public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.intelligentHouse.AirConditioning.Status status = 2;</code>
     * @param value The enum numeric value on the wire for status to set.
     * @return This builder for chaining.
     */
    public Builder setStatusValue(int value) {
      status_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.intelligentHouse.AirConditioning.Status status = 2;</code>
     * @return The status.
     */
    @java.lang.Override
    public com.house.objects.AirConditioning.Status getStatus() {
      com.house.objects.AirConditioning.Status result = com.house.objects.AirConditioning.Status.forNumber(status_);
      return result == null ? com.house.objects.AirConditioning.Status.UNRECOGNIZED : result;
    }
    /**
     * <code>.intelligentHouse.AirConditioning.Status status = 2;</code>
     * @param value The status to set.
     * @return This builder for chaining.
     */
    public Builder setStatus(com.house.objects.AirConditioning.Status value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000002;
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.intelligentHouse.AirConditioning.Status status = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatus() {
      bitField0_ = (bitField0_ & ~0x00000002);
      status_ = 0;
      onChanged();
      return this;
    }

    private int settingTemperature_ ;
    /**
     * <code>int32 setting_temperature = 3;</code>
     * @return The settingTemperature.
     */
    @java.lang.Override
    public int getSettingTemperature() {
      return settingTemperature_;
    }
    /**
     * <code>int32 setting_temperature = 3;</code>
     * @param value The settingTemperature to set.
     * @return This builder for chaining.
     */
    public Builder setSettingTemperature(int value) {

      settingTemperature_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int32 setting_temperature = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearSettingTemperature() {
      bitField0_ = (bitField0_ & ~0x00000004);
      settingTemperature_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:intelligentHouse.AirConditioning)
  }

  // @@protoc_insertion_point(class_scope:intelligentHouse.AirConditioning)
  private static final com.house.objects.AirConditioning DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.house.objects.AirConditioning();
  }

  public static com.house.objects.AirConditioning getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AirConditioning>
      PARSER = new com.google.protobuf.AbstractParser<AirConditioning>() {
    @java.lang.Override
    public AirConditioning parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<AirConditioning> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AirConditioning> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.house.objects.AirConditioning getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

