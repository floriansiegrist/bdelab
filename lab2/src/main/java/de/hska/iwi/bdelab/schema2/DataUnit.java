/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package de.hska.iwi.bdelab.schema2;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataUnit extends org.apache.thrift.TUnion<DataUnit, DataUnit._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DataUnit");
  private static final org.apache.thrift.protocol.TField USER_PROPERTY_FIELD_DESC = new org.apache.thrift.protocol.TField("user_property", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField FRIEND_FIELD_DESC = new org.apache.thrift.protocol.TField("friend", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField PAGEVIEW_FIELD_DESC = new org.apache.thrift.protocol.TField("pageview", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_PROPERTY((short)1, "user_property"),
    FRIEND((short)2, "friend"),
    PAGEVIEW((short)3, "pageview");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // USER_PROPERTY
          return USER_PROPERTY;
        case 2: // FRIEND
          return FRIEND;
        case 3: // PAGEVIEW
          return PAGEVIEW;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USER_PROPERTY, new org.apache.thrift.meta_data.FieldMetaData("user_property", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, UserProperty.class)));
    tmpMap.put(_Fields.FRIEND, new org.apache.thrift.meta_data.FieldMetaData("friend", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, FriendEdge.class)));
    tmpMap.put(_Fields.PAGEVIEW, new org.apache.thrift.meta_data.FieldMetaData("pageview", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, PageviewEdge.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DataUnit.class, metaDataMap);
  }

  public DataUnit() {
    super();
  }

  public DataUnit(_Fields setField, Object value) {
    super(setField, value);
  }

  public DataUnit(DataUnit other) {
    super(other);
  }
  public DataUnit deepCopy() {
    return new DataUnit(this);
  }

  public static DataUnit user_property(UserProperty value) {
    DataUnit x = new DataUnit();
    x.set_user_property(value);
    return x;
  }

  public static DataUnit friend(FriendEdge value) {
    DataUnit x = new DataUnit();
    x.set_friend(value);
    return x;
  }

  public static DataUnit pageview(PageviewEdge value) {
    DataUnit x = new DataUnit();
    x.set_pageview(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, Object value) throws ClassCastException {
    switch (setField) {
      case USER_PROPERTY:
        if (value instanceof UserProperty) {
          break;
        }
        throw new ClassCastException("Was expecting value of type UserProperty for field 'user_property', but got " + value.getClass().getSimpleName());
      case FRIEND:
        if (value instanceof FriendEdge) {
          break;
        }
        throw new ClassCastException("Was expecting value of type FriendEdge for field 'friend', but got " + value.getClass().getSimpleName());
      case PAGEVIEW:
        if (value instanceof PageviewEdge) {
          break;
        }
        throw new ClassCastException("Was expecting value of type PageviewEdge for field 'pageview', but got " + value.getClass().getSimpleName());
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case USER_PROPERTY:
          if (field.type == USER_PROPERTY_FIELD_DESC.type) {
            UserProperty user_property;
            user_property = new UserProperty();
            user_property.read(iprot);
            return user_property;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case FRIEND:
          if (field.type == FRIEND_FIELD_DESC.type) {
            FriendEdge friend;
            friend = new FriendEdge();
            friend.read(iprot);
            return friend;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case PAGEVIEW:
          if (field.type == PAGEVIEW_FIELD_DESC.type) {
            PageviewEdge pageview;
            pageview = new PageviewEdge();
            pageview.read(iprot);
            return pageview;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case USER_PROPERTY:
        UserProperty user_property = (UserProperty)value_;
        user_property.write(oprot);
        return;
      case FRIEND:
        FriendEdge friend = (FriendEdge)value_;
        friend.write(oprot);
        return;
      case PAGEVIEW:
        PageviewEdge pageview = (PageviewEdge)value_;
        pageview.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case USER_PROPERTY:
          UserProperty user_property;
          user_property = new UserProperty();
          user_property.read(iprot);
          return user_property;
        case FRIEND:
          FriendEdge friend;
          friend = new FriendEdge();
          friend.read(iprot);
          return friend;
        case PAGEVIEW:
          PageviewEdge pageview;
          pageview = new PageviewEdge();
          pageview.read(iprot);
          return pageview;
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      throw new TProtocolException("Couldn't find a field with field id " + fieldID);
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case USER_PROPERTY:
        UserProperty user_property = (UserProperty)value_;
        user_property.write(oprot);
        return;
      case FRIEND:
        FriendEdge friend = (FriendEdge)value_;
        friend.write(oprot);
        return;
      case PAGEVIEW:
        PageviewEdge pageview = (PageviewEdge)value_;
        pageview.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case USER_PROPERTY:
        return USER_PROPERTY_FIELD_DESC;
      case FRIEND:
        return FRIEND_FIELD_DESC;
      case PAGEVIEW:
        return PAGEVIEW_FIELD_DESC;
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public UserProperty get_user_property() {
    if (getSetField() == _Fields.USER_PROPERTY) {
      return (UserProperty)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'user_property' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void set_user_property(UserProperty value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.USER_PROPERTY;
    value_ = value;
  }

  public FriendEdge get_friend() {
    if (getSetField() == _Fields.FRIEND) {
      return (FriendEdge)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'friend' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void set_friend(FriendEdge value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.FRIEND;
    value_ = value;
  }

  public PageviewEdge get_pageview() {
    if (getSetField() == _Fields.PAGEVIEW) {
      return (PageviewEdge)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'pageview' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void set_pageview(PageviewEdge value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.PAGEVIEW;
    value_ = value;
  }

  public boolean is_set_user_property() {
    return setField_ == _Fields.USER_PROPERTY;
  }


  public boolean is_set_friend() {
    return setField_ == _Fields.FRIEND;
  }


  public boolean is_set_pageview() {
    return setField_ == _Fields.PAGEVIEW;
  }


  public boolean equals(Object other) {
    if (other instanceof DataUnit) {
      return equals((DataUnit)other);
    } else {
      return false;
    }
  }

  public boolean equals(DataUnit other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(DataUnit other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  @Override
  public int hashCode() {
    HashCodeBuilder hcb = new HashCodeBuilder();
    hcb.append(this.getClass().getName());
    org.apache.thrift.TFieldIdEnum setField = getSetField();
    if (setField != null) {
      hcb.append(setField.getThriftFieldId());
      Object value = getFieldValue();
      if (value instanceof org.apache.thrift.TEnum) {
        hcb.append(((org.apache.thrift.TEnum)getFieldValue()).getValue());
      } else {
        hcb.append(value);
      }
    }
    return hcb.toHashCode();
  }
  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


}
