/*
 * Exemplo de Servidor de Sockets em Java.
 * 
 * Neste exemplo, o servidor está configurado para responder a conexão de apenas um cliente.
 * Enquanto um cliente estiver conectado, outro cliente não poderá se conectar até que
 * a conexão atual seja finalizada.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Servidor {
	private int port = 7000;
	private ServerSocket serverSocket;
	
	List<String> fortunes;
	Random random;
	
	public Servidor() throws ServerException, IOException {
		
		serverSocket = new ServerSocket(port);
	    fortunes = new ArrayList<String>();
		random = new Random();
	    
		System.out.println("Servidor iniciado na porta " + port);

		while (true) {
		
			// Aguarda uma conexão na porta especificada e cria retorna o socket que irá comunicar com o cliente
			Socket s = serverSocket.accept();
			String ip = s.getInetAddress().getHostAddress();
			System.out.println("Conectado com " + ip);

			// Cria um DataInputStream para o canal de entrada de dados do socket
			DataInputStream  in  = new DataInputStream(s.getInputStream());
			
			// Cria um DataOutputStream para o canal de saída de dados do socket
			DataOutputStream out = new DataOutputStream(s.getOutputStream());

			String str = "";
			String cleanStr = "";
			int index = 0;
			String fortune;
			
			do{	
				str = in.readUTF();
				
				if(str.equals("GET-FORTUNE")) {
					index = random.nextInt(fortunes.size() - 1);
					fortune = fortunes.get(index);
					out.writeUTF(fortune);
				}
				
				else if(str.contains("SET-FORTUNE")) {
					cleanStr = str.replace("SET-FORTUNE", "").replace("\n", "");
					fortunes.add(cleanStr);
				}
				
			}while( !str.equals("exit") ); 

			s.close();
			System.out.println("Desconectado de " + ip);
		}

		// Encerro o ServerSocket
		// serv.close();
	}

	public static void main(String[] args) {
		
		try {
			new Servidor();
		} catch (ServerException e) {
			System.out.println("A conexão com o cliente foi resetada!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}