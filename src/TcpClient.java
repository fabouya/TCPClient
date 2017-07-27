
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner; 

public class TcpClient 
{
    private static int    _port=6133;
    private static Socket connectionSocket;

    public static void main(String[] args)
    {
        try
        {
			
			Scanner sc = new Scanner(System.in);
			String str = "";
			
			while(! str.equals("fin\n"))
			{			
		        	connectionSocket = new Socket("localhost", _port);        	
		            
		            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
					DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
				
					System.out.println("Veuillez saisir un mot :");
					str = sc.nextLine();
					str += "\n";
					
					outToServer.writeBytes(str);
					outToServer.writeByte(0);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String clientSentence = inFromServer.readLine();
					System.out.println("Reponse du serveur :");
					System.out.println(clientSentence);				
					
					inFromServer.close();
					outToServer.close();
					connectionSocket.close();
			}
			
			
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connectionSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
