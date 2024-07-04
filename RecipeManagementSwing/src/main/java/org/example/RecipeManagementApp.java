package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RecipeManagementApp extends JFrame {
    private RecipeManager recipeManager;
    private DefaultListModel<Recipe> recipeListModel;
    private JList<Recipe> recipeList;
    private JTextArea ingredientsTextArea;
    private JTextArea instructionsTextArea;
    // Constructorul clasei
    public RecipeManagementApp() {
        recipeManager = new RecipeManager();
        recipeListModel = new DefaultListModel<>();
        for (Recipe recipe : recipeManager.getRecipes()) {
            recipeListModel.addElement(recipe);
        }

        setTitle("Recipe Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        recipeList = new JList<>(recipeListModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.addListSelectionListener(e -> showRecipeDetails(recipeList.getSelectedValue()));
        JScrollPane listScrollPane = new JScrollPane(recipeList);

        ingredientsTextArea = new JTextArea();
        instructionsTextArea = new JTextArea();
        ingredientsTextArea.setEditable(false);
        instructionsTextArea.setEditable(false);

        JPanel detailsPanel = new JPanel(new GridLayout(2, 1));
        detailsPanel.add(new JScrollPane(ingredientsTextArea));
        detailsPanel.add(new JScrollPane(instructionsTextArea));

        JButton addButton = new JButton("Add Recipe");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecipe();
            }
        });

        JButton deleteButton = new JButton("Delete Recipe");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecipe();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(listScrollPane, BorderLayout.WEST);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
// Afiseaza detaliile retetei selectate
    private void showRecipeDetails(Recipe recipe) {
        if (recipe != null) {
            ingredientsTextArea.setText(recipe.getIngredients());
            instructionsTextArea.setText(recipe.getInstructions());
        } else {
            ingredientsTextArea.setText("");
            instructionsTextArea.setText("");
        }
    }
// adauga o noua reteta
    private void addRecipe() {
        JTextField nameField = new JTextField();
        JTextArea ingredientsArea = new JTextArea();
        JTextArea instructionsArea = new JTextArea();
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsArea);
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsArea);

        Object[] message = {
                "Name:", nameField,
                "Ingredients:", ingredientsScrollPane,
                "Instructions:", instructionsScrollPane
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Recipe", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String ingredients = ingredientsArea.getText();
            String instructions = instructionsArea.getText();
            Recipe recipe = new Recipe(name, ingredients, instructions);
            recipeManager.addRecipe(recipe);
            recipeListModel.addElement(recipe);
        }
    }
// sterge reteta selectata
    private void deleteRecipe() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this recipe?", "Delete Recipe", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                recipeManager.deleteRecipe(selectedRecipe);
                recipeListModel.removeElement(selectedRecipe);
            }
        }
    }
 //punctul de intrare al aplicatiei
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecipeManagementApp app = new RecipeManagementApp();
            app.setVisible(true);
        });
    }
}

