package view;
import javax.swing.JOptionPane;

import controller.RedesController;

public class main {
	public static void main(String[] args) {
		int opc=0;
		
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção: \n 1 - Exibir info sobre o sistema operacional \n 2 - Exibir info sobre o IPv4 \n 3 - Testar ping com o google \n 9 - Sair"));
			switch(opc) {
			case 1,2,3:
				JOptionPane.showMessageDialog(null,RedesController.escolha(opc));
				break;
			case 9:
				break;
			default:
				JOptionPane.showMessageDialog(null,"Verifique a opção digitada");
				break;
			}
		}while(opc != 9);
		
	}
}
