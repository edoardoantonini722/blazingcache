package blazingcache.client.impl;

import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import blazingcache.client.CacheException;
import blazingcache.client.EntrySerializer;
/**
 * Simple serializer that uses Kyro
 * @author edoardoantonini722
 *
 */
public class KyroEntrySerializer implements EntrySerializer{

	private Kryo kyro;
	
	public KyroEntrySerializer() {
		this.kyro=new Kryo();
	}
	
	@Override
	public byte[] serializeObject(String key, Object object) throws CacheException {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			Output output = new Output(buffer);
			kyro.writeObject(output, object);
			byte[] value = output.toBytes();
			output.close();
			return value;
	}

	@Override
	public Object deserializeObject(String key, byte[] value) throws CacheException {
			Input input = new Input(value);
			Object object = kyro.readClassAndObject(input);
			input.close();
			return object;
	}

}
