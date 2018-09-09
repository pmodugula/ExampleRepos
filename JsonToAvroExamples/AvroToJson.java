package com.rbc.test;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;


public class AvroToJson {
	
	public static void main (String a[]){
		AvroToJson a1 = new AvroToJson();
		a1.method1();
	}

		public void method1()
		{
			String json = "{\"foo\": 30.1," + " \"bar\": 60.2}";
			String schemaLines = "{\"type\":\"record\",\"name\":\"FooBar\",\"namespace\":\"com.foo.bar\",\"fields\":[{\"name\":\"foo\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"bar\",\"type\":[\"null\",\"double\"],\"default\":null}]}";

			Schema.Parser schemaParser = new Schema.Parser();
			Schema schema = schemaParser.parse(schemaLines);
			DecoderFactory decoderFactory =  new DecoderFactory();
			GenericRecord genericRecird;; 
			
			try{
				Decoder decoder = decoderFactory.jsonDecoder(schema, json);
				DatumReader<GenericData.Record> reader= new GenericDatumReader(schema);
				genericRecird= reader.read(null, decoder);
				System.out.println(genericRecird.toString());
			}
			catch(IOException e){
				
			}
			
		}
		}
		