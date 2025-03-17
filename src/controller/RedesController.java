package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	public RedesController() {
		super();
	}
	
	public static String escolha(int opc){
		if (opc == 1) {
			return sistemaOperacional();
		}
		else if (opc == 2) {
			return configIp();
		}
		else if (opc == 3) {
			return leiPing();
		}
		else {
			return null;
		}
	}
	
	private static String sistemaOperacional() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(System.getProperty("os.name"));
		sBuffer.append(" ");
		sBuffer.append(System.getProperty("os.version"));
		sBuffer.append(" ");
		sBuffer.append(System.getProperty("os.arch"));
		
		return sBuffer.toString();
	}
	
	private static String configIp() {
		String SO = sistemaOperacional();
		String cmdIp;
		String resultado = " ";
		int adapt = 1;
		
		if (SO.contains("Windows")) {
			cmdIp = "ipconfig";
		}
		else {
			cmdIp = "ifconfig";
		}
		
		String[] cutProc = cmdIp.split(" ");
		
		try {
			Process processo = Runtime.getRuntime().exec(cutProc);
			InputStream iStream = processo.getInputStream();
			InputStreamReader riStream = new InputStreamReader(iStream);
			BufferedReader bReader = new BufferedReader(riStream);
			String leiLinha = bReader.readLine();
			String[] cutRes;
			StringBuffer sBuffer = new StringBuffer();
			
			while (leiLinha != null) {
				if (leiLinha.contains("inet ")){
					cutRes = leiLinha.split(" ");
					sBuffer.append("Adaptador " + adapt + " :" + cutRes[1]);
					sBuffer.append("\n");
					adapt++;
				} else if (leiLinha.contains("IPv4")){
					cutRes = leiLinha.split(":");
					sBuffer.append("Adaptador " + adapt + " :" + cutRes[1]);
					sBuffer.append("\n");
					adapt++;
				}
				resultado = sBuffer.toString();
				leiLinha = bReader.readLine();
			}
			
			bReader.close();
			riStream.close();
			iStream.close();
			
		}catch (Exception err) {
			System.err.println(err.getMessage());
		}
	
		return resultado;
	}
	
	private static String leiPing() {
		String SO = sistemaOperacional();
		String pingCmd;
		String[] fatiaCmd;
		String res = " ";
		
		if (SO.contains("Windows")){
			pingCmd = "ping -n 10 www.google.com.br";
		}
		else {
			pingCmd = "ping -c 10 www.google.com.br";
		}
		
		fatiaCmd = pingCmd.split(" ");
		
		try {
			Process pRet = Runtime.getRuntime().exec(fatiaCmd);
			InputStream iStream = pRet.getInputStream();
			InputStreamReader liStream = new InputStreamReader(iStream);
			BufferedReader bReader = new BufferedReader(liStream);
			String leiLinha = bReader.readLine();
			System.out.print("Carregando");
			
			while(leiLinha != null) {
				System.out.print(".");
				String[] segSplit;
				if(leiLinha.contains("avg")){
					segSplit = leiLinha.split("/");
					res = ("Média = " + segSplit[4] + "ms");
				}
				else if(leiLinha.contains("dia")){
					segSplit = leiLinha.split(",");
					res = (segSplit[2]);
				}

				leiLinha = bReader.readLine();
			}
			
			bReader.close();
			liStream.close();
			iStream.close();
			
		}catch (Exception err){
			System.err.println(err.getMessage());
		}
		
		return res;
	}
}
