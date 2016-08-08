package blazingcache.client.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import blazingcache.client.CacheException;
import blazingcache.client.EntrySerializer;

public class KyroEntrySerializer implements EntrySerializer{

	private Kryo kyro;
	
	public KyroEntrySerializer() {
		this.kyro=new Kryo();
	}
	
	@Override
	public byte[] serializeObject(String key, Object object) throws CacheException {
		try {
            ByteArrayOutputStream oo = new ByteArrayOutputStream();
            ObjectOutputStream oo2 = new ObjectOutputStream(oo);
			Output output = new Output(oo2);
			kyro.writeObject(output, object);
			output.close();
			return oo.toByteArray();
		} catch (IOException err) {
			throw new CacheException(err);
		}
		
	}

	@Override
	public Object deserializeObject(String key, byte[] value) throws CacheException {
        try {
        	ByteArrayInputStream oo = new ByteArrayInputStream(value);
			ObjectInputStream oo2 = new ObjectInputStream(oo);
			Input input = new Input(oo2);
			Object object = kyro.readClassAndObject(input);
			input.close();
			return object;
		} catch (IOException err) {
			throw new CacheException(err);
		}
	}

}
