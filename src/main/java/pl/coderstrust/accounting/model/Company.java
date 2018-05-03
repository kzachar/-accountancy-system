package pl.coderstrust.accounting.model;

public class Company {

  private final String name;
  private final String  taxId;
  private final String streetAndNumber;
  private final String postalCode;
  private final String location;


  public Company(String name, String  taxId, String streetAndNumber, String postalCode,
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
}
