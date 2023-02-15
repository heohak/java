package ee.taltech.iti0202.lotr;

public class Ring {

     private Type type;
     private Material material;

    enum Type {

        THE_ONE, GOLDEN, NENYA, OTHER
    }
    enum Material {

        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }

    /**
     *
     * @param type
     * @param material
     */
    public Ring(Type type, Material material) {

        this.type = type;
        this.material = material;

    }

    /**
     *
     * @return Type
     */
    public Type getType() {

        return this.type;
    }

    /**
     *
     * @return Material
     */
    public Material getMaterial() {

        return this.material;

    }

}
