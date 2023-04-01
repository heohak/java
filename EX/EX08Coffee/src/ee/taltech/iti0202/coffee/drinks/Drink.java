package ee.taltech.iti0202.coffee.drinks;

public class Drink {

    DrinkType drinkType;


    public Drink(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public enum DrinkType {
        CACAO,
        CAPPUCCINO,
        LATTE,

    }

    public DrinkType getDrinkType() {
        return drinkType;
    }
}
