package pl.coderstrust.accounting.model;

public class Company {

  private final String name;
  private final Integer taxId;
  private final String streetAndNumber;
  private final Integer postalCode;
  private final String location;


  public Company(String name, Integer taxId, String streetAndNumber, Integer postalCode, String location) {
    this.name = name;
    this.taxId = taxId;
    this.streetAndNumber = streetAndNumber;
    this.postalCode = postalCode;
    this.location = location;
  }
}
