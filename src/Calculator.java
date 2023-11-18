import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Calculator implements ActionListener {

        private String currentOperator = "";
        JFrame frame = new JFrame();
        JPanel calculations = new JPanel();
        JPanel buttons_panel = new JPanel();
        JLabel textfield = new JLabel();
        JButton[] buttons = new JButton[20];


        Calculator() {

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.getContentPane().setBackground(Color.GRAY);
            frame.setLayout(new BorderLayout());
            frame.setVisible(true);

            textfield.setBackground(new Color(0, 0, 0));
            textfield.setForeground(new Color(25, 255, 0));
            textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
            textfield.setText(" ");
            textfield.setOpaque(true);

            calculations.setLayout(new BorderLayout());
            calculations.setBounds(0, 0, 800, 100);

            buttons_panel.setLayout(new GridLayout(5, 4));
            buttons_panel.setBounds(100, 200, 300, 400);
            buttons_panel.setBackground(new Color(150, 150, 150));

            for (int i = 0; i < 20; i++) {
                buttons[i] = new JButton();
                buttons_panel.add(buttons[i]);
                buttons[i].setFont(new Font("MV Boli", Font.BOLD, 100));
                buttons[i].setFocusable(false);
                buttons[i].addActionListener(this);
                buttons[i].setActionCommand(buttons[i].getText());
            }

            buttons[19].setText("=");
            buttons[18].setText(",");
            buttons[17].setText("0");
            buttons[16].setText("AC");
            buttons[15].setText("+");
            buttons[14].setText("3");
            buttons[13].setText("2");
            buttons[12].setText("1");
            buttons[11].setText("-");
            buttons[10].setText("6");
            buttons[9].setText("5");
            buttons[8].setText("4");
            buttons[7].setText("X");
            buttons[6].setText("9");
            buttons[5].setText("8");
            buttons[4].setText("7");
            buttons[3].setText("/");
            buttons[2].setText("%");
            buttons[1].setText("00");
            buttons[0].setText("C");

            calculations.add(textfield);
            frame.add(calculations, BorderLayout.NORTH);
            frame.add(buttons_panel);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            String buttonText = clickedButton.getText();
            System.out.println("Button Clicked: " + buttonText);

            switch (buttonText) {
                case "=":
                    calculateResult();
                    break;
                case "+":
                case "-":
                case "X":
                case "/":
                case "%":

                    currentOperator = buttonText;
                    textfield.setText(textfield.getText() + buttonText);
                    break;

                case "AC":
                    clearCalculator();
                    break;

                case "C":
                    clearLastEntry();
                    break;
                default:
                    textfield.setText(textfield.getText() + buttonText);


            }

        }

        private void calculateResult() {

            String expression = textfield.getText();

            String[] tokens = expression.split("[+\\-X/%]");


            if (tokens.length != 2) {
                textfield.setText("Error");
                return;
            }

            double operand1 = Double.parseDouble(tokens[0]);
            double operand2 = Double.parseDouble(tokens[1]);
            String operator = expression.replaceAll("[0-9.]", "");

            double result = 0.0;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    textfield.setText(String.valueOf(result));
                    break;
                case "-":
                    result = operand1 - operand2;
                    textfield.setText(String.valueOf(result));
                    break;
                case "X":
                    result = operand1 * operand2;
                    textfield.setText(String.valueOf(result));
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                        textfield.setText(String.valueOf(result));
                    } else {
                        textfield.setText("Error");
                        return;
                    }
                    break;
                case "%":
                    result = operand1 % operand2;
                    textfield.setText(String.valueOf(result));
                    break;
                default:
                    textfield.setText("Error");
                    return;
            }
            textfield.setText(String.valueOf(result));
        }

        private void clearCalculator() {
            textfield.setText("");

        }

        private void clearLastEntry() {
            String currentText = textfield.getText();

            if (!currentText.isEmpty()) {
                String newText = currentText.substring(0, currentText.length() - 1);
                textfield.setText(newText);
            }
        }
    }


