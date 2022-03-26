package com.warpit.springdemo3.domain;

import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class Tour {
	    @Id
	    private String id;


	    @Indexed
	    private String title;


	    @Indexed
	    private String tourPackageCode;  //duplication of attributes normal for noSQLDB
	    private String tourPackageName;  //duplication of attributes normal for noSQLDB
	    
	  //  @ManyToOne
	  //  private TourPackage tourPackage; - this was in Relational DB , and in JPA one table reference another table

	    private Map<String, String> details;  //here are the attributes description,blurb
	                                          //all atributes are key pairs here because it could change

	    /**
	     * Construct a fully initialized Tour.
	     *
	     * @param title title of the tour
	     * @param tourPackage tour package
	     * @param details details about the tour (key-value pairs)
	     */
	    public Tour(String title, TourPackage tourPackage, Map<String, String> details) {
	        this.title = title;
	        this.tourPackageCode = tourPackage.getCode();
	        this.tourPackageName = tourPackage.getName();
	        this.details = details;
	    }

	    protected Tour() {
	    }

	    public String getId() {
	        return id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public String getTourPackageCode() {
	        return tourPackageCode;
	    }

	    public String getTourPackageName() {
	        return tourPackageName;
	    }

	    public Map<String, String> getDetails() {
	        return details;
	    }

	    @Override
	    public String toString() {
	        return "Tour{" +
	                "id='" + id + '\'' +
	                ", details=" + details +
	                '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Tour tour = (Tour) o;
	        return Objects.equals(id, tour.id) &&
	                Objects.equals(details, tour.details);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, details);
	    }
	    
	    
	    
	    
}
