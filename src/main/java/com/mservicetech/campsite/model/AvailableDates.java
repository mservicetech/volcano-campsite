package com.mservicetech.campsite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AvailableDates
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-10-29T09:15:01.057646300-04:00[America/New_York]")
public class AvailableDates implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("datelist")
  @Valid
  private List<LocalDate> datelist = null;

  @JsonProperty("startDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  @JsonProperty("endDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate endDate;

  @JsonProperty("comment")
  private String comment;

  public AvailableDates datelist(List<LocalDate> datelist) {
    this.datelist = datelist;
    return this;
  }

  public AvailableDates addDatelistItem(LocalDate datelistItem) {
    if (this.datelist == null) {
      this.datelist = new ArrayList<>();
    }
    this.datelist.add(datelistItem);
    return this;
  }

  /**
   * Get datelist
   * @return datelist
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<LocalDate> getDatelist() {
    return datelist;
  }

  public void setDatelist(List<LocalDate> datelist) {
    this.datelist = datelist;
  }

  public AvailableDates startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public AvailableDates endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public AvailableDates comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   * @return comment
  */
  @ApiModelProperty(value = "")


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvailableDates availableDates = (AvailableDates) o;
    return Objects.equals(this.datelist, availableDates.datelist) &&
        Objects.equals(this.startDate, availableDates.startDate) &&
        Objects.equals(this.endDate, availableDates.endDate) &&
        Objects.equals(this.comment, availableDates.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datelist, startDate, endDate, comment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableDates {\n");
    
    sb.append("    datelist: ").append(toIndentedString(datelist)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

