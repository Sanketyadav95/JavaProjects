import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    String[] buttonValues = {
        "AC", "DEL", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-", 
        "1", "2", "3", "+", 
        "0", ".", "√", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "DEL", "%"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;

    public Calculator() {
        setupFrame();
        setupDisplay();
        setupButtons();
        frame.setVisible(true);
    }

    void setupFrame() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    void setupDisplay() {
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);
    }

    void setupButtons() {
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for (String buttonValue : buttonValues) {
            JButton button = new JButton(buttonValue);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            } else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }

            button.addActionListener(e -> handleButtonClick(buttonValue));
            buttonsPanel.add(button);
        }
    }

    void handleButtonClick(String buttonValue) {
        if (Arrays.asList(rightSymbols).contains(buttonValue)) {
            handleOperator(buttonValue);
        } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
            handleTopButton(buttonValue);
        } else {
            handleDigitOrDecimal(buttonValue);
        }
    }

    void handleOperator(String buttonValue) {
        if (buttonValue.equals("=")) {
            if (A != null && operator != null) {
                try {
                    B = displayLabel.getText().split("[" + operator + "]")[1].trim();
                    double numA = Double.parseDouble(A);
                    double numB = Double.parseDouble(B);

                    double result = switch (operator) {
                        case "+" -> numA + numB;
                        case "-" -> numA - numB;
                        case "×" -> numA * numB;
                        case "÷" -> numA / numB;
                        default -> 0;
                    };

                    displayLabel.setText(removeZeroDecimal(result));
                    clearAll();
                } catch (Exception ex) {
                    displayLabel.setText("Error");
                    clearAll();
                }
            }
        } else if ("+-×÷".contains(buttonValue)) {
            if (operator == null) {
                A = displayLabel.getText();
                operator = buttonValue;
                displayLabel.setText(A + " " + operator + " ");
            }
        }
    }

    void handleTopButton(String buttonValue) {
        switch (buttonValue) {
            case "AC" -> {
                clearAll();
                displayLabel.setText("0");
            }
            case "DEL" -> {
                String current = displayLabel.getText();
                if (current.length() > 1) {
                    if (current.endsWith(" ")) {
                        current = current.substring(0, current.length() - 3); // Remove " x "
                    } else {
                        current = current.substring(0, current.length() - 1); // Remove last char
                    }
                    displayLabel.setText(current);
                } else {
                    displayLabel.setText("0");
                }
            }
            case "%" -> {
                String[] parts = displayLabel.getText().split(" ");
                String last = parts[parts.length - 1];
                double numDisplay = Double.parseDouble(last);
                numDisplay /= 100;
                parts[parts.length - 1] = removeZeroDecimal(numDisplay);
                displayLabel.setText(String.join(" ", parts));
            }
        }
    }

    void handleDigitOrDecimal(String buttonValue) {
        if (buttonValue.equals(".")) {
            String[] parts = displayLabel.getText().split(" ");
            String last = parts[parts.length - 1];
            if (!last.contains(".")) {
                displayLabel.setText(displayLabel.getText() + buttonValue);
            }
        } else if ("0123456789".contains(buttonValue)) {
            if (displayLabel.getText().equals("0") || displayLabel.getText().endsWith(" " + operator + " ")) {
                displayLabel.setText(displayLabel.getText() + buttonValue);
            } else {
                displayLabel.setText(displayLabel.getText() + buttonValue);
            }
        }
    }

    void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }

    String removeZeroDecimal(double num) {
        return (num % 1 == 0) ? Integer.toString((int) num) : Double.toString(num);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }

    
}
