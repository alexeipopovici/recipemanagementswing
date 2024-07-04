package org.example;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private static final String FILE_NAME = "recipes.ser";
    private List<Recipe> recipes;
    //Constructorul clasei
    public RecipeManager() {
        recipes = loadRecipes();
    }
    // Adauga o reteta si salveaza lista de retete
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveRecipes();
    }
    // Sterge o reteta si salveaza lista de retete
    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
        saveRecipes();
    }
// Returneaza lista de retete
    public List<Recipe> getRecipes() {
        return recipes;
    }
// Incarca lista de retete din fisier
    @SuppressWarnings("unchecked")
    private List<Recipe> loadRecipes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Recipe>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //Salveaza lista de retete in fisier
    private void saveRecipes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

