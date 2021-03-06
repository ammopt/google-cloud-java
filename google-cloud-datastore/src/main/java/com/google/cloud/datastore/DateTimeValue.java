/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.datastore;

import static com.google.datastore.v1.Value.TIMESTAMP_VALUE_FIELD_NUMBER;

public final class DateTimeValue extends Value<DateTime> {

  private static final long serialVersionUID = -8502433575902990648L;

  static final BaseMarshaller<DateTime, DateTimeValue, Builder> MARSHALLER =
      new BaseMarshaller<DateTime, DateTimeValue, Builder>() {

        private static final long serialVersionUID = -5789520029958113745L;

        @Override
        public int getProtoFieldId() {
          return TIMESTAMP_VALUE_FIELD_NUMBER;
        }

        @Override
        public Builder newBuilder(DateTime value) {
          return DateTimeValue.newBuilder(value);
        }

        @Override
        protected DateTime getValue(com.google.datastore.v1.Value from) {
          return new DateTime(DateTime.timestampPbToMicroseconds(from.getTimestampValue()));
        }

        @Override
        protected void setValue(DateTimeValue from, com.google.datastore.v1.Value.Builder to) {
          to.setTimestampValue(DateTime.microsecondsToTimestampPb(from.get()
              .getTimestampMicroseconds()));
        }
      };

  public static final class Builder extends Value.BaseBuilder<DateTime, DateTimeValue, Builder> {

    private Builder() {
      super(ValueType.DATE_TIME);
    }

    @Override
    public DateTimeValue build() {
      return new DateTimeValue(this);
    }
  }

  public DateTimeValue(DateTime dateTime) {
    this(newBuilder(dateTime));
  }

  private DateTimeValue(Builder builder) {
    super(builder);
  }

  @Override
  public Builder toBuilder() {
    return new Builder().mergeFrom(this);
  }

  public static DateTimeValue of(DateTime dateTime) {
    return new DateTimeValue(dateTime);
  }

  @Deprecated
  public static Builder builder(DateTime dateTime) {
    return newBuilder(dateTime);
  }

  public static Builder newBuilder(DateTime dateTime) {
    return new Builder().set(dateTime);
  }
}
