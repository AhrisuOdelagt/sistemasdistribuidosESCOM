package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mycompany.model.Country;
import com.mycompany.model.Info;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ObjectMapperUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static Country country = new Country("India", 135260000000L, 29, true);
	private static String countryStr = "{\"name\":\"India\",\"population\":135260000000,\"numberOfProvinces\":29,\"developed\":true}";

	public static void objectToBytes() throws JsonProcessingException {
		byte[] countryAsBytes = objectMapper.writeValueAsBytes(country);
		System.out.println("El método objectToBytes convierte un objeto Java a un arreglo de Bytes mediante writeValueAsBytes.");
		System.out.println("objectToBytes : " + countryAsBytes + "\n");
	}

	public static void jsonStrToObject() throws IOException {
		Country countryFromString = objectMapper.readValue(countryStr, Country.class);
		System.out.println("El método jsonStrToObject convierte una String que contiene un JSON en un objeto de tipo Java mediante readValue.");
		System.out.println("jsonStrToObject : " + countryFromString + "\n");
	}
}
