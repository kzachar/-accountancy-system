package pl.coderstrust.accounting.model;

import java.util.Objects;

public class Company {

  private final String name;
  private final String taxId;
  private final String streetAndNumber;
  private final String postalCode;
  private final String location;

  public Company(String name, String taxId, String streetAndNumber, String postalCode,
      String location) {
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
        && Objects.equals(taxId, company.taxId)
        && Objects.equals(streetAndNumber, company.streetAndNumber)
        && Objects.equals(postalCode, company.postalCode)
        && Objects.equals(location, company.location);
  }
}
