package com.warpit.springdemo3.domain;


import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class TourRating {
	
	/**
	 * validation parameters on the fields
	 * we no longer need RatingDto into the controller
	 * because this object is now simple and can be used in RestController as payload
	 */

    //@EmbeddedId
    //private TourRatingPk pk;
	
	@Id
    private String id;

    private String tourId;

    @NotNull
    private Integer customerId;

    @Min(0)
    @Max(5)
    private Integer score;    

    @Size(max = 255)
    private String comment;

    /**
     * Construct a new Tour Rating.
     *
     * @param tourId tour identifier
     * @param customerId customer identifier
     * @param score Integer score (1-5)
     * @param comment Optional comment from the customer
     */
    public TourRating(String tourId, Integer customerId, Integer score, String comment) {
        this.tourId = tourId;
        this.customerId = customerId;
        this.score = score;
        this.comment = comment;
    }

    protected TourRating() {
    }

    public Integer getScore() {
        return score;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourRating that = (TourRating) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tourId, that.tourId) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(score, that.score) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tourId, customerId, score, comment);
    }

    @Override
    public String toString() {
        return "TourRating{" +
                "id='" + id + '\'' +
                ", tourId='" + tourId + '\'' +
                ", customerId=" + customerId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
