package io1.github.example.codes.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SerializationUtil {
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

   // private static Objenesis objenesis = new ObjenesisStd(true);

    private SerializationUtil() {
    }

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> cls) {
        //Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        Schema<T> schema = RuntimeSchema.getSchema(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            if (schema != null) {
                cachedSchema.put(cls, schema);
            }
        }
        return schema;
    }

    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            //T message = (T) objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            T message =  schema.newMessage();
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static class Zoo{
        private String zooname;
        private int num;
        private boolean isSili;
        private List<Tiger> tigers;

        public String getZooname() {
            return zooname;
        }

        public void setZooname(String zooname) {
            this.zooname = zooname;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public boolean isSili() {
            return isSili;
        }

        public void setSili(boolean sili) {
            isSili = sili;
        }

        public List<Tiger> getTigers() {
            return tigers;
        }

        public void setTigers(List<Tiger> tigers) {
            this.tigers = tigers;
        }
    }


    private static class Tiger{
        private String tigerName;
        private int old;

        public String getTigerName() {
            return tigerName;
        }

        public void setTigerName(String tigerName) {
            this.tigerName = tigerName;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }

    public static void main(String[] args){
        Tiger tiger = new Tiger();
        tiger.setOld(10);
        tiger.setTigerName("tiger1");
        Zoo zoo = new Zoo();
        List<Tiger> list = new ArrayList<Tiger>();
        list.add(tiger);
        zoo.setTigers(list);
        zoo.setNum(222);
        zoo.setSili(false);
        zoo.setZooname("虎虎");


        byte[] bytes = serialize(zoo);
        System.out.println(bytes.length);

        Zoo zoo1 = deserialize(bytes,zoo.getClass());
        System.out.println(zoo1.getNum());
        System.out.println(zoo1.getZooname());
        System.out.println(zoo1.getTigers());

    }
}