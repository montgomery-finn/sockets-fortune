import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException {
		
		// Cria o socket com o recurso desejado na porta especificada
		Socket s = new Socket("127.0.0.1", 7000);

		// Cria a Stream de saida de dados
		DataInputStream in = new DataInputStream(s.getInputStream());
		DataOutputStream os = new DataOutputStream(s.getOutputStream());

		os.writeUTF("SET-FORTUNE\nA vida trar� coisas boas se tiver paci�ncia.\n");
		os.writeUTF("SET-FORTUNE\nDemonstre amor e alegria em todas as oportunidades e ver� que a paz nasce dentro de si.\n");
		os.writeUTF("SET-FORTUNE\nN�o compense na ira o que lhe falta na raz�o.\n");
		os.writeUTF("SET-FORTUNE\nDefeitos e virtudes s�o apenas dois lados da mesma moeda.\n");
		os.writeUTF("SET-FORTUNE\nA maior de todas as torres come�a no solo\n");
		os.writeUTF("SET-FORTUNE\nN�o h� que ser forte. H� que ser flex�vel\n");
		os.writeUTF("SET-FORTUNE\nTodos os dias organiza os seus cabelos, por que n�o faz o mesmo com o cora��o?\n");

		os.writeUTF("GET-FORTUNE");
		String fortune = in.readUTF();
		
		System.out.println(fortune);
		
		os.writeUTF("exit");
		s.close();
	}
}
