package com.test.java;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class tspFileController{

	@RequestMapping(value = "/tspFile", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<InputStreamResource> downloadJSONFile(@RequestParam("tail") String tail) throws IOException, ResourceNotFoundException {
//System.out.println("inside");
		//System.out.println(id);
		String fileparam=tail+".json";
		ResponseEntity<InputStreamResource> a=null;
		
		try{
			ClassPathResource jsonFile = new ClassPathResource(fileparam);
		a = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + jsonFile.getFilename()).contentLength(jsonFile.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(jsonFile.getInputStream()));

		
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
            System.out.println(e);
            throw new ResourceNotFoundException("File not found");

				}
		return a;
		
	}

	
}