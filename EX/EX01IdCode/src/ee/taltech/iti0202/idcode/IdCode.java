    package ee.taltech.iti0202.idcode;
  public class IdCode {
      private final String idCodeValue;
      enum Gender {
          MALE, FEMALE
      }
      /**
       * Method returns the id code.
       *
       * @return id code.
       */
      public String getIdCodeValue() {
          return idCodeValue;
      }
      public IdCode(String idCodeValue) {
          this.idCodeValue = idCodeValue;
      }

      /**
       * Check if the id code is valid or not.
       *
       * @return boolean describing whether or not the id code was correct.
       */
      public boolean isCorrect() {
          return (isGenderNumberCorrect()
          && isYearNumberCorrect()
          && isMonthNumberCorrect()
          && isDayNumberCorrect()
          && isControlNumberCorrect()) && idCodeValue.length() == 11;
      }

      /**
       * Get all information about id code.
       * @return String containing information.
       */
      public String getInformation() {
          Gender gender = getGender();
          String birthDay = idCodeValue.substring(5, 7);
          String birthMonth = idCodeValue.substring(3, 5);
          String birthYear = Integer.toString(getFullYear());
          String fullDate = birthDay + "." + birthMonth + "." + birthYear;
          String town = getBirthPlace();
          return "This is a " + gender + " born on " + fullDate + " in " + town;


      }

      /**
       * Get gender enum.
       * @return enum describing person's gender
       */
      public Gender getGender() {
          if (!isCorrect()) {
              return null;
          }
          int genderNumber = Character.getNumericValue(idCodeValue.charAt(0));
          if (genderNumber % 2 == 0) {
              return Gender.FEMALE;
          } else {
              return Gender.MALE;
          }
      }

      /**
       * Get person's birth location.
       * @return String with the person's birth place.
       */
      public String getBirthPlace() {
          int birthPlace = Integer.parseInt(idCodeValue.substring(7, 10));
          if (birthPlace >= 1 && birthPlace <= 10) {
              return "Kuressaare";
          } else if ((birthPlace >= 11 && birthPlace <= 20) || (birthPlace >= 271 && birthPlace <= 370)) {
              return "Tartu";
          } else if ((birthPlace >= 21 && birthPlace <= 220) || (birthPlace >= 471 && birthPlace <= 490)) {
              return "Tallinn";
          } else if (birthPlace >= 221 && birthPlace <= 270) {
              return "Kohtla-Järve";
          } else if (birthPlace >= 371 && birthPlace <= 420) {
              return "Narva";
          } else if (birthPlace >= 421 && birthPlace <= 470) {
              return "Pärnu";
          } else if (birthPlace >= 491 && birthPlace <= 520) {
              return "Paide";
          } else if (birthPlace >= 521 && birthPlace <= 570) {
              return "Rakvere";
          } else if (birthPlace >= 571 && birthPlace <= 600) {
              return "Valga";
          } else if (birthPlace >= 601 && birthPlace <= 650) {
              return "Viljandi";
          } else if (birthPlace >= 651 && birthPlace <= 710) {
              return "Võru";
          } else {
              return "unknown";
          }


          }
      /**
       * Get the year that the person was born in.
       * @return int with person's birth year.
       */
      public int getFullYear() {
          int century;
          if (!isCorrect()) {
              return 0;
          }
          int genderNumber = Integer.parseInt(idCodeValue.substring(0, 1));
          int yearNumber = Integer.parseInt(idCodeValue.substring(1, 3));
          if (genderNumber == 1 || genderNumber == 2) {
              century = 1800;
          } else if (genderNumber == 3 || genderNumber == 4) {
              century = 1900;
          } else {
              century = 2000;
          }
          return century + yearNumber;
      }
    
      /**
       * Check if gender number is correct.
       * 
       * @return boolean describing whether the gender number is correct.
       */
      private boolean isGenderNumberCorrect() {
          int genderNumber = Integer.parseInt(idCodeValue.substring(0, 1));
          return genderNumber >= 1 && genderNumber <= 6;
      }

      /**
       * Check if the year number is correct.
       * 
       * @return boolean describing whether the year number is correct.
       */
      private boolean isYearNumberCorrect() {
          int yearNumber = Integer.parseInt(idCodeValue.substring(1,3));
          return yearNumber >= 0 && yearNumber <= 99;
      }

      /**
       * Check if the month number is correct.
       * 
       * @return boolean describing whether the month number is correct.
       */
      private boolean isMonthNumberCorrect() {
          int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
          return monthNumber >= 1 && monthNumber <= 12;
      }

      /**
       * Check if the day number is correct.
       * 
       * @return boolean describing whether the day number is correct.
       */
      private boolean isDayNumberCorrect() {
          int day = Integer.parseInt(idCodeValue.substring(5, 7));
          int month = Integer.parseInt(idCodeValue.substring(3, 5));
          int year = Integer.parseInt(idCodeValue.substring(1, 3));

          if (month == 2 && isLeapYear(1900 + year)) {
              return day >= 1 && day <= 29;
          } else if (month == 2) {
              return day >= 1 && day <= 28;
          } else if (month == 4 || month == 6 || month == 9 || month == 11) {
              return day >= 1 && day <= 30;
          } else {
              return day >= 1 && day <= 31;
          }
      }

      /**
       * Check if the control number is correct.
       * 
       * @return boolean describing whether the control number is correct.
       */
      private boolean isControlNumberCorrect() {
          int[] positions1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
          int[] positions2 = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
          int sum = 0;
          int remainder;
          int controlNumber = Integer.parseInt(idCodeValue.substring(10));

          for (int i = 0; i < 10; i++) {
              int digit = Integer.parseInt(idCodeValue.substring(i, i + 1));

              sum += digit * positions1[i];
          }

          remainder = sum % 11;
          if (remainder == 10) {
              sum = 0;
              for (int i = 0; i < 10; i++) {
                  int digit = Integer.parseInt(idCodeValue.substring(i, i + 1));

                  sum += digit * positions2[i];
              } remainder = sum % 11;
              if (remainder == 10) {
                  remainder = 0;
              }
          }

          return controlNumber == remainder;
      }

      /**
       * Check if the given year is a leap year.
       * @return boolean describing whether the given year is a leap year.
       */
      private boolean isLeapYear(int fullYear) {
          return (fullYear % 4 == 0 && fullYear % 100 != 0) || fullYear % 400 == 0;
      }

      /**
       * Run tests.
       * @param args info.
       */
      public static void main(String[] args) {
          IdCode validMaleIdCode = new IdCode("47605037779");
          System.out.println(validMaleIdCode.isCorrect());
          System.out.println(validMaleIdCode.getInformation());
          System.out.println(validMaleIdCode.getGender());
          System.out.println(validMaleIdCode.getBirthPlace());
          System.out.println(validMaleIdCode.getFullYear());
          System.out.println(validMaleIdCode.isGenderNumberCorrect());
          System.out.println(validMaleIdCode.isYearNumberCorrect());
          System.out.println(validMaleIdCode.isMonthNumberCorrect());
          System.out.println(validMaleIdCode.isDayNumberCorrect());
          System.out.println(validMaleIdCode.isControlNumberCorrect());
          System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
      }

  }
