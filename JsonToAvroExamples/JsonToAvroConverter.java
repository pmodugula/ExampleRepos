package test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.commons.io.output.ByteArrayOutputStream;

String schema = '''{
"type":"record",
"namespace":"foo",
"name":"Person",
"fields":[
  {
    "name":"name",
    "type":"string"
  },
  {
    "name":"age",
    "type":"int"
  }
]
}''';

public class JsonToAvroConverter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonToAvroConverter jsonToAvroConverter =  new JsonToAvroConverter();
		
	
				String json = "{" +
				  "\"name\":\"Frank\"," +
				  "\"age\":47" +
				"}";
		jsonToAvroConverter.jsonToAvro(schema,json);
	}
	
	public static byte[] jsonToAvro(String json, String schemaStr) throws IOException {
	    InputStream input = null;
	    GenericDatumWriter<GenericRecord> writer = null;
	    Encoder encoder = null;
	    ByteArrayOutputStream output = null;
	    try {
	        Schema schema = new Schema.Parser().parse(schemaStr);
	        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
	        input = new ByteArrayInputStream(json.getBytes());
	        output = new ByteArrayOutputStream();
	        DataInputStream din = new DataInputStream(input);
	        writer = new GenericDatumWriter<GenericRecord>(schema);
	        Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
	        encoder = EncoderFactory.get().binaryEncoder(output, null);
	        GenericRecord datum;
	        while (true) {
	            try {
	                datum = reader.read(null, decoder);
	            } catch (EOFException eofe) {
	                break;
	            }
	            writer.write(datum, encoder);
	        }
	        encoder.flush();
	        return output.toByteArray();
	    } finally {
	        try { input.close(); } catch (Exception e) { }
	    }
	}
	
	
	public static String avroToJson(byte[] avro, String schemaStr) throws IOException {
	    boolean pretty = false;
	    GenericDatumReader<GenericRecord> reader = null;
	    JsonEncoder encoder = null;
	    ByteArrayOutputStream output = null;
	    try {
	        Schema schema = new Schema.Parser().parse(schemaStr);
	        reader = new GenericDatumReader<GenericRecord>(schema);
	        InputStream input = new ByteArrayInputStream(avro);
	        output = new ByteArrayOutputStream();
	        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
	        encoder = EncoderFactory.get().jsonEncoder(schema, output, pretty);
	        Decoder decoder = DecoderFactory.get().binaryDecoder(input, null);
	        GenericRecord datum;
	        while (true) {
	            try {
	                datum = reader.read(null, decoder);
	            } catch (EOFException eofe) {
	                break;
	            }
	            writer.write(datum, encoder);
	        }
	        encoder.flush();
	        output.flush();
	        return new String(output.toByteArray());
	    } finally {
	        try { if (output != null) output.close(); } catch (Exception e) { }
	    }
	}
}
