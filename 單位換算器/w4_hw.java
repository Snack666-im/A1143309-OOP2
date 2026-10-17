import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class w4_hw extends JFrame implements ActionListener{
	static JPanel centerPanel = new JPanel();
    static JPanel row1 = new JPanel();
    static JPanel row2 = new JPanel();
    static JPanel northPanel = new JPanel();
    static JPanel southPanel = new JPanel();
	
	static JLabel lblCategory = new JLabel("換算類型：");
    static JLabel lblSource = new JLabel("來源：");
    static JLabel lblTarget = new JLabel("目標：");
	
	static JComboBox<String> cbCategory = new JComboBox<>(new String[]{"長度", "重量", "溫度"});
    static JTextField tfInput = new JTextField(10);
    static JComboBox<String> cbSourceUnit = new JComboBox<>(new String[]{"公尺", "公分", "英吋", "英呎"});
    static JTextField tfOutput = new JTextField(10);
    static JComboBox<String> cbTargetUnit = new JComboBox<>(new String[]{"公尺", "公分", "英吋", "英呎"});
    
    static JButton btnConvert = new JButton("換算");
	
	static final String[] lengthUnits = {"公尺", "公分", "英吋", "英呎"};
    static final String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    static final String[] tempUnits = {"攝氏", "華氏", "克氏"};
	
	public static void main(String[] args) {
		w4_hw frm = new w4_hw();
		frm.setTitle("單位換算器");
        frm.setSize(480, 280);
		
		BorderLayout border=new BorderLayout();
		frm.setLayout(border);
		
		northPanel.add(lblCategory);
        northPanel.add(cbCategory);
        frm.add(northPanel, BorderLayout.NORTH);
		
		centerPanel.setLayout(new GridLayout(2, 1, 5, 5));
		
		row1.add(lblSource);
        row1.add(tfInput);
        row1.add(cbSourceUnit);
		
		tfOutput.setEditable(false);
		row2.add(lblTarget);
        row2.add(tfOutput);
        row2.add(cbTargetUnit);
		
		centerPanel.add(row1);
        centerPanel.add(row2);
        frm.add(centerPanel, BorderLayout.CENTER);
		
		southPanel.add(btnConvert);
        frm.add(southPanel, BorderLayout.SOUTH);
		
		cbCategory.addActionListener(frm);
        btnConvert.addActionListener(frm);
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
    }
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == cbCategory) {
			String selectedCategory = (String) cbCategory.getSelectedItem();
            cbSourceUnit.removeAllItems();
            cbTargetUnit.removeAllItems();
			
			String[] targetUnits = lengthUnits;
            if ("長度".equals(selectedCategory)) {
                targetUnits = lengthUnits;
            } else if ("重量".equals(selectedCategory)) {
                targetUnits = weightUnits;
            } else if ("溫度".equals(selectedCategory)) {
                targetUnits = tempUnits;
            }
			for (String unit : targetUnits) {
                cbSourceUnit.addItem(unit);
                cbTargetUnit.addItem(unit);
            }
        }
		else if (e.getSource() == btnConvert) {
            try {
                double inputValue = Double.parseDouble(tfInput.getText());
                String category = (String) cbCategory.getSelectedItem();
                String srcUnit = (String) cbSourceUnit.getSelectedItem();
                String tgtUnit = (String) cbTargetUnit.getSelectedItem();
                
                double result = calculate(category, srcUnit, tgtUnit, inputValue);
                tfOutput.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "請輸入有效的數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // 3. 數學換算邏輯獨立成一個輔助方法
    private double calculate(String category, String src, String tgt, double val) {
        if (src.equals(tgt)) return val;

        if ("長度".equals(category)) {
            double meters = val;
            if (src.equals("公分")) meters = val / 100.0;
            else if (src.equals("英吋")) meters = val * 0.0254;
            else if (src.equals("英呎")) meters = val * 0.3048;

            if (tgt.equals("公尺")) return meters;
            if (tgt.equals("公分")) return meters * 100.0;
            if (tgt.equals("英吋")) return meters / 0.0254;
            if (tgt.equals("英呎")) return meters / 0.3048;
        } 
        else if ("重量".equals(category)) {
            double kg = val;
            if (src.equals("公克")) kg = val / 1000.0;
            else if (src.equals("磅")) kg = val * 0.45359237;
            else if (src.equals("盎司")) kg = val * 0.0283495231;

            if (tgt.equals("公斤")) return kg;
            if (tgt.equals("公克")) return kg * 1000.0;
            if (tgt.equals("磅")) return kg / 0.45359237;
            if (tgt.equals("盎司")) return kg / 0.0283495231;
        }
        else if ("溫度".equals(category)) {
            double celsius = val;
            if (src.equals("華氏")) celsius = (val - 32) * 5.0 / 9.0;
            else if (src.equals("克氏")) celsius = val - 273.15;

            if (tgt.equals("攝氏")) return celsius;
            if (tgt.equals("華氏")) return celsius * 9.0 / 5.0 + 32;
            if (tgt.equals("克氏")) return celsius + 273.15;
        }
        return val;
    }
}