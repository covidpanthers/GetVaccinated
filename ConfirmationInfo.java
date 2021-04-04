package com.sweng894.GetVaccinated;
//Use Case SWEN-24
class ConfirmationInfo {

    private Integer confirmationNumber;
    private String email;

    public ConfirmationInfo() {

    }

      public ConfirmationInfo(String email) {
        this.email = email;
      }
  public void setEmail(String email) {
    this.email = email;
  }
      public String getEmail() {
      return email;
    }

      public void setConfirmationNumber(Integer confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
      }
      public Integer getConfirmationNumber() {
        return confirmationNumber;
      }
    }
