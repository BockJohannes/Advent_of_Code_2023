package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AdventReader {
	
    @SneakyThrows
    public static List<String> read(String fileName) {
    	try {
    		URL url = AdventReader.class.getClassLoader().getResource(String.format("input/%s.txt", fileName));
    		return Files.lines(Paths.get(url.toURI())).collect(Collectors.toList());
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
	
    @AllArgsConstructor
    @Getter
    public static class IndexedLine {
        long index;
        String line;
    }

}