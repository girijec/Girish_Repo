package com.example.utils;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(CSVLoader.class);

	public static <T> List<T> loadObjectList(Class<T> type, File file) {
	    try {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<T> readValues = 
	          mapper.reader(type).with(bootstrapSchema).readValues(file);
	        return readValues.readAll();
	    } catch (Exception e) {
	        logger.error("Error occurred while loading object list from file " + file.getName(), e);
	        return Collections.emptyList();
	    }
	}


}
