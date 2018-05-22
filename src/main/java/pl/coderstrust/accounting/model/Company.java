package pl.coderstrust.accounting.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Company {

  private String name;
  private String taxId;
  private String streetAndNumber;
  private String postalCode;
  private String location;

  public Company() {
    // Left empty constructor for Jackson
  }

  @JsonCreator
  public Company(@JsonProperty("name") String name, @JsonProperty("taxId") String taxId,
      @JsonProperty("streetAndNumber") String streetAndNumber, @JsonProperty("postalCode") String postalCode,
      @JsonProperty("location") String location) {
    this.name = name;
    this.taxId = taxId;
    this.streetAndNumber = streetAndNumber;
    this.postalCode = postalCode;
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public String getTaxId() {
    return taxId;
  }

  public String getStreetAndNumber() {
    return streetAndNumber;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getLocation() {
    return location;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Company company = (Company) obj;
    return Objects.equals(name, company.name)
        &&
        Objects.equals(taxId, company.taxId)
        &&
        Objects.equals(streetAndNumber, company.streetAndNumber)
        &&
        Objects.equals(postalCode, company.postalCode)
        &&
        Objects.equals(location, company.location);
  }
}