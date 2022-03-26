package com.warpit.springdemo3;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warpit.springdemo3.service.TourPackageService;
import com.warpit.springdemo3.service.TourService;


@SpringBootApplication
public class Springdemo3Application implements CommandLineRunner{
	
	
	@Value("${ec.importfile}")
	private String importFile;
	
	
	@Value("classpath:${ec.importfile}")
	private Resource resource;
	
	@Autowired
	private TourPackageService tourPackageService;
	
	@Autowired
	private TourService tourService;

	public static void main(String[] args) {
		SpringApplication.run(Springdemo3Application.class, args);
	}

	
	@Override
    public void run(String... args) throws Exception {
		createTourAllPackages();
        long numOfTourPackages = tourPackageService.total();
       // createTours(importFile);
        createTours(resource);
        long numOfTours = tourService.total();
    }

	/**
     * Initialize all the known tour packages
     */
    private void createTourAllPackages(){
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
    }

    /**
     * Create tour entities from an external file
     */
    private void createTours(Resource fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(tourFromFile ->
                        tourService.createTour(tourFromFile.getTitle(),
                                tourFromFile.getPackageName(), tourFromFile.getDetails())
        );
    }

    
    /**
     * Helper class to import ExploreCalifornia.json for a MongoDb Document.
     * Only interested in the title and package name, the remaining fields
     * are a collection of key-value pairs
     *
     */
    private static class TourFromFile {
        //fields
        String title;
        String packageName;
        Map<String, String> details;

        TourFromFile(Map<String, String> record) {
            this.title =  record.get("title");
            this.packageName = record.get("packageType");
            this.details = record;
            this.details.remove("packageType");
            this.details.remove("title");
        }
        //reader
        static List<TourFromFile> read(Resource fileToImport) throws IOException {
        	
        	InputStream fileToImportStream = fileToImport.getInputStream();
        	
            List<Map<String, String>> records = new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(fileToImportStream,
                            new TypeReference<List<Map<String, String>>>() {});
            return records.stream().map(TourFromFile::new)
                    .collect(Collectors.toList());
        }

        String getTitle() {
            return title;
        }

        String getPackageName() {
            return packageName;
        }

        Map<String, String> getDetails() {
            return details;
        }
    }
}
