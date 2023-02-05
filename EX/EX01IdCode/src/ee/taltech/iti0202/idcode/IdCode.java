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
          final int ID_CODE_LENGTH = 11;
          return (isGenderNumberCorrect()
          && isYearNumberCorrect()
          && isMonthNumberCorrect()
          && isDayNumberCorrect()
          && isControlNumberCorrect()) && idCodeValue.length() == ID_CODE_LENGTH;
      }

      /**
       * Get all information about id code.
       * @return String containing information.
       */
      public String getInformation() {
          final int BIRTH_DAY_SECOND_DIGIT = 7;
          Gender gender = getGender();
          String birthDay = idCodeValue.substring(5, BIRTH_DAY_SECOND_DIGIT);
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
          final int BIRTH_PLACE_FIRST_DIGIT = 7;
          final int TARTU_CODE_START = 11;
          final int TARTU_CODE_END = 20;
          final int TARTU_CODE_START2 = 271;
          final int TARTU_CODE_END2 = 370;
          final int TALLINN_CODE_START = 21;
          final int TALLINN_CODE_END = 220;
          final int TALLINN_CODE_START2 = 471;
          final int TALLINN_CODE_END2 = 490;
          final int KOHTLA_CODE_START = 221;
          final int KOHTLA_CODE_END = 270;
          final int NARVA_CODE_START = 371;
          final int NARVA_CODE_END = 420;
          final int PARNU_CODE_START = 421;
          final int PARNU_CODE_END = 470;
          final int PAIDE_CODE_START = 491;
          final int PAIDE_CODE_END = 520;
          final int RAKVERE_CODE_START = 521;
          final int RAKVERE_CODE_END = 570;
          final int VALGA_CODE_START = 571;
          final int VALGA_CODE_END = 600;
          final int VILJANDI_CODE_START = 601;
          final int VILJANDI_CODE_END = 650;
          final int VORU_CODE_START = 651;
          final int VORU_CODE_END = 710;
          int birthPlace = Integer.parseInt(idCodeValue.substring(BIRTH_PLACE_FIRST_DIGIT, 10));
          if (birthPlace >= 1 && birthPlace <= 10) {
              return "Kuressaare";
          } else if ((birthPlace >= TARTU_CODE_START && birthPlace <= TARTU_CODE_END)
                  || (birthPlace >= TARTU_CODE_START2 && birthPlace <= TARTU_CODE_END2)) {
              return "Tartu";
          } else if ((birthPlace >= TALLINN_CODE_START && birthPlace <= TALLINN_CODE_END)
                  || (birthPlace >= TALLINN_CODE_START2 && birthPlace <= TALLINN_CODE_END2)) {
              return "Tallinn";
          } else if (birthPlace >= KOHTLA_CODE_START && birthPlace <= KOHTLA_CODE_END) {
              return "Kohtla-Järve";
          } else if (birthPlace >= NARVA_CODE_START && birthPlace <= NARVA_CODE_END) {
              return "Narva";
          } else if (birthPlace >= PARNU_CODE_START && birthPlace <= PARNU_CODE_END) {
              return "Pärnu";
          } else if (birthPlace >= PAIDE_CODE_START && birthPlace <= PAIDE_CODE_END) {
              return "Paide";
          } else if (birthPlace >= RAKVERE_CODE_START && birthPlace <= RAKVERE_CODE_END) {
              return "Rakvere";
          } else if (birthPlace >= VALGA_CODE_START && birthPlace <= VALGA_CODE_END) {
              return "Valga";
          } else if (birthPlace >= VILJANDI_CODE_START && birthPlace <= VILJANDI_CODE_END) {
              return "Viljandi";
          } else if (birthPlace >= VORU_CODE_START && birthPlace <= VORU_CODE_END) {
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
          final int EIGHTEENTH_CENTURY = 1800;
          final int NINETEENTH_CENTURY = 1900;
          final int TWO_THOUSAND = 2000;
          int century;
          if (!isCorrect()) {
              return 0;
          }
          int genderNumber = Integer.parseInt(idCodeValue.substring(0, 1));
          int yearNumber = Integer.parseInt(idCodeValue.substring(1, 3));
          if (genderNumber == 1 || genderNumber == 2) {
              century = EIGHTEENTH_CENTURY;
          } else if (genderNumber == 3 || genderNumber == 4) {
              century = NINETEENTH_CENTURY;
          } else {
              century = TWO_THOUSAND;
          }
          return century + yearNumber;
      }
      /**
       * Check if gender number is correct.
       * @return boolean describing whether the gender number is correct.
       */
      private boolean isGenderNumberCorrect() {
          final int MAX_GENDER_NUMBER = 6;
          int genderNumber = Integer.parseInt(idCodeValue.substring(0, 1));
          return genderNumber >= 1 && genderNumber <= MAX_GENDER_NUMBER;
      }

      /**
       * Check if the year number is correct.
       * @return boolean describing whether the year number is correct.
       */
      private boolean isYearNumberCorrect() {
          final int MAX_YEAR_NUMBER = 99;
          int yearNumber = Integer.parseInt(idCodeValue.substring(1, 3));
          return yearNumber >= 0 && yearNumber <= MAX_YEAR_NUMBER;
      }

      /**
       * Check if the month number is correct.
       * @return boolean describing whether the month number is correct.
       */
      private boolean isMonthNumberCorrect() {
          final int MAX_MONTH_NUMBER = 12;
          int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
          return monthNumber >= 1 && monthNumber <= MAX_MONTH_NUMBER;
      }

      /**
       * Check if the day number is correct.
       * @return boolean describing whether the day number is correct.
       */
      private boolean isDayNumberCorrect() {
          final int EIGHTEENTH_CENTURY = 1800;
          final int NINETEENTH_CENTURY = 1900;
          final int TWO_THOUSAND = 2000;
          final int ID_CODE_DAY_LAST_DIGIT_INDEX = 7;
          int day = Integer.parseInt(idCodeValue.substring(5, ID_CODE_DAY_LAST_DIGIT_INDEX));
          int month = Integer.parseInt(idCodeValue.substring(3, 5));
          int year = Integer.parseInt(idCodeValue.substring(1, 3));
          int genderNumber = Integer.parseInt(idCodeValue.substring(0, 1));
          int century;
          int finalYear;
          if (genderNumber == 1 || genderNumber == 2) {
              century = EIGHTEENTH_CENTURY;
          } else if (genderNumber == 3 || genderNumber == 4) {
              century = NINETEENTH_CENTURY;
          } else {
              century = TWO_THOUSAND;
          }
          finalYear =  century + year;
          final int FEB_LEAP_LAST_DAY = 29;
          final int FEB_LAST_DAY = 28;
          final int JUNE = 6;
          final int SEP = 9;
          final int NOV = 11;
          final int LAST_DAY_THIRTY = 30;
          final int LAST_DAY_THIRTY_ONE = 31;


          if (month == 2 && isLeapYear(finalYear)) {
              return day >= 1 && day <= FEB_LEAP_LAST_DAY;
          } else if (month == 2) {
              return day >= 1 && day <= FEB_LAST_DAY;
          } else if (month == 4 || month == JUNE || month == SEP || month == NOV) {
              return day >= 1 && day <= LAST_DAY_THIRTY;
          } else {
              return day >= 1 && day <= LAST_DAY_THIRTY_ONE;
          }
      }

      /**
       * Check if the control number is correct.
       * @return boolean describing whether the control number is correct.
       */
      private boolean isControlNumberCorrect() {
          final int SIX = 6;
          final int SEVEN = 7;
          final int EIGHT = 8;
          final int NINE = 9;
          final int ID_CODE_LENGTH = 11;
          int[] positions1 = {1, 2, 3, 4, 5, SIX, SEVEN, EIGHT, NINE, 1};
          int[] positions2 = {3, 4, 5, SIX, SEVEN, EIGHT, NINE, 1, 2, 3};
          int sum = 0;
          int remainder;
          int controlNumber = Integer.parseInt(idCodeValue.substring(10));

          for (int i = 0; i < 10; i++) {
              int digit = Integer.parseInt(idCodeValue.substring(i, i + 1));

              sum += digit * positions1[i];
          }

          remainder = sum % ID_CODE_LENGTH;
          if (remainder == 10) {
              sum = 0;
              for (int i = 0; i < 10; i++) {
                  int digit = Integer.parseInt(idCodeValue.substring(i, i + 1));

                  sum += digit * positions2[i];
              } remainder = sum % ID_CODE_LENGTH;
              if (remainder == 10) {
                  remainder = 0;
              }
          }

          return controlNumber == remainder;
      }

      /**
       * Check if the given year is a leap year.
       * @param fullYear
       * @return boolean describing whether the given year is a leap year.
       */
      private boolean isLeapYear(int fullYear) {
          final int LEAP = 400;
          return (fullYear % 4 == 0 && fullYear % 100 != 0) || fullYear % LEAP == 0;
      }

      /**
       * Run tests.
       * @param args info.
       */
      public static void main(String[] args) {
          IdCode validMaleIdCode = new IdCode("50002290231");
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
