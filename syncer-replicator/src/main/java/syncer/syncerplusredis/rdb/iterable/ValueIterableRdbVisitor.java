/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package syncer.syncerplusredis.rdb.iterable;


import syncer.syncerplusredis.event.Event;
import syncer.syncerplusredis.io.RedisInputStream;
import syncer.syncerplusredis.rdb.BaseRdbParser;
import syncer.syncerplusredis.rdb.DefaultRdbVisitor;
import syncer.syncerplusredis.rdb.RdbValueVisitor;
import syncer.syncerplusredis.rdb.datatype.ContextKeyValuePair;
import syncer.syncerplusredis.rdb.datatype.KeyValuePair;
import syncer.syncerplusredis.rdb.datatype.ZSetEntry;
import syncer.syncerplusredis.rdb.iterable.datatype.KeyStringValueByteArrayIterator;
import syncer.syncerplusredis.rdb.iterable.datatype.KeyStringValueMapEntryIterator;
import syncer.syncerplusredis.rdb.iterable.datatype.KeyStringValueZSetEntryIterator;
import syncer.syncerplusredis.replicator.Replicator;
import syncer.syncerplusredis.rdb.datatype.Module;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static syncer.syncerplusredis.replicator.Constants.*;

/**
 * @author Leon Chen
 * @since 2.3.0
 */
public class ValueIterableRdbVisitor extends DefaultRdbVisitor {

    public ValueIterableRdbVisitor(Replicator replicator) {
        this(replicator, new ValueIterableRdbValueVisitor(replicator));
    }

    public ValueIterableRdbVisitor(Replicator replicator, RdbValueVisitor valueParser) {
        super(replicator, valueParser);
    }

    @Override
    public Event applyList(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<byte[]>> o1 = new KeyStringValueByteArrayIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o1.setValueRdbType(RDB_TYPE_LIST);
        o1.setKey(key);
        o1.setValue(valueVisitor.applyList(in, version));
        //    o1.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o1);
    }

    @Override
    public Event applySet(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<byte[]>> o2 = new KeyStringValueByteArrayIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o2.setValueRdbType(RDB_TYPE_SET);
        o2.setKey(key);
        o2.setValue(valueVisitor.applySet(in, version));
        //    o2.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o2);
    }

    @Override
    public Event applyZSet(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<ZSetEntry>> o3 = new KeyStringValueZSetEntryIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o3.setValueRdbType(RDB_TYPE_ZSET);
        o3.setKey(key);
        o3.setValue(valueVisitor.applyZSet(in, version));

        //      o3.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o3);
    }

    @Override
    public Event applyZSet2(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<ZSetEntry>> o5 = new KeyStringValueZSetEntryIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o5.setValueRdbType(RDB_TYPE_ZSET_2);
        o5.setKey(key);
        o5.setValue(valueVisitor.applyZSet2(in, version));
        //    o5.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o5);
    }

    @Override
    public Event applyHash(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<Map.Entry<byte[], byte[]>>> o4 = new KeyStringValueMapEntryIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o4.setValueRdbType(RDB_TYPE_HASH);
        o4.setKey(key);
        o4.setValue(valueVisitor.applyHash(in, version));
        //    o4.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o4);
    }

    @Override
    public Event applyHashZipMap(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<Map.Entry<byte[], byte[]>>> o9 = new KeyStringValueMapEntryIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o9.setValueRdbType(RDB_TYPE_HASH_ZIPMAP);
        o9.setKey(key);
        o9.setValue(valueVisitor.applyHashZipMap(in, version));
        //    o9.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o9);
    }

    @Override
    public Event applyListZipList(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<byte[]>> o10 = new KeyStringValueByteArrayIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o10.setValueRdbType(RDB_TYPE_LIST_ZIPLIST);
        o10.setKey(key);
        o10.setValue(valueVisitor.applyListZipList(in, version));
        //    o10.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o10);
    }

    @Override
    public Event applySetIntSet(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<byte[]>> o11 = new KeyStringValueByteArrayIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o11.setValueRdbType(RDB_TYPE_SET_INTSET);
        o11.setKey(key);
        o11.setValue(valueVisitor.applySetIntSet(in, version));
        //      o11.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o11);
    }

    @Override
    public Event applyZSetZipList(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<ZSetEntry>> o12 = new KeyStringValueZSetEntryIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o12.setValueRdbType(RDB_TYPE_ZSET_ZIPLIST);
        o12.setKey(key);
        o12.setValue(valueVisitor.applyZSetZipList(in, version));
        //     o12.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o12);
    }

    @Override
    public Event applyHashZipList(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<Map.Entry<byte[], byte[]>>> o13 = new KeyStringValueMapEntryIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o13.setValueRdbType(RDB_TYPE_HASH_ZIPLIST);
        o13.setKey(key);
        o13.setValue(valueVisitor.applyHashZipList(in, version));
        //       o13.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o13);
    }

    @Override
    public Event applyListQuickList(RedisInputStream in, int version, ContextKeyValuePair context) throws IOException {
        BaseRdbParser parser = new BaseRdbParser(in);
        KeyValuePair<byte[], Iterator<byte[]>> o14 = new KeyStringValueByteArrayIterator();
        byte[] key = parser.rdbLoadEncodedStringObject().first();

        o14.setValueRdbType(RDB_TYPE_LIST_QUICKLIST);
        o14.setKey(key);
        o14.setValue(valueVisitor.applyListQuickList(in, version));
        //        o14.setSize(parser.rdbLoadLen().len);
        return context.valueOf(o14);
    }
}
