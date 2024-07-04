package org.example;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String name;
    private String ingredients;
    private String instructions;
    // Constructorul clasei
    public Recipe(String name, String ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
 //gettere pentru fiecare camp
    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
    // metoda toString pentru a afisa numele retetei in lista de reteta
    @Override
    public String toString() {
        return name;
    }
}

