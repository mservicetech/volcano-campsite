package com.mservicetech.campsite.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Reservation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-10-28T21:30:05.649560600-04:00[America/New_York]")
public class Reservation  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("client")
  private Client client;

  @JsonProperty("arrival")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate arrival;

  @JsonProperty("departure")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate departure;

  public Reservation client(Client client) {
    this.client = client;
    return this;
  }

  /**
   * Get client
   * @return client
  */
  @ApiModelProperty(value = "")

  @Valid

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Reservation arrival(LocalDate arrival) {
    this.arrival = arrival;
    return this;
  }

  /**
   * Arrival date
   * @return arrival
  */
  @ApiModelProperty(example = "Fri Jan 29 19:00:00 EST 2021", value = "Arrival date")

  @Valid

  public LocalDate getArrival() {
    return arrival;
  }

  public void setArrival(LocalDate arrival) {
    this.arrival = arrival;
  }

  public Reservation departure(LocalDate departure) {
    this.departure = departure;
    return this;
  }

  /**
   * Leaving date
   * @return departure
  */
  @ApiModelProperty(example = "Fri Jan 29 19:00:00 EST 2021", value = "Leaving date")

  @Valid

  public LocalDate getDeparture() {
    return departure;
  }

  public void setDeparture(LocalDate departure) {
    this.departure = departure;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reservation reservation = (Reservation) o;
    return Objects.equals(this.client, reservation.client) &&
        Objects.equals(this.arrival, reservation.arrival) &&
        Objects.equals(this.departure, reservation.departure);
  }

  @Override
  public int hashCode() {
    return Objects.hash(client, arrival, departure);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reservation {\n");
    
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    arrival: ").append(toIndentedString(arrival)).append("\n");
    sb.append("    departure: ").append(toIndentedString(departure)).append("\n");
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

