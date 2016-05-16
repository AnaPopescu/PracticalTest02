package ro.pub.cs.systems.pdsd.practicaltest02;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;


public class CommunicationThread extends Thread {
	
	private ServerThread serverThread;
	private Socket       socket;
	
	public CommunicationThread(ServerThread serverThread, Socket socket) {
		this.serverThread = serverThread;
		this.socket       = socket;
	}
	
	public void run() {
		if (socket != null) {
			try {
				BufferedReader bufferedReader = Utilities.getReader(socket);
				PrintWriter    printWriter    = Utilities.getWriter(socket);
				if (bufferedReader != null && printWriter != null) {
					Log.i(Constants.TAG, "[COMMUNICATION THREAD] Waiting for parameters from client!");
					Integer operator1 = Integer.parseInt(bufferedReader.readLine());
					Integer operator2 = Integer.parseInt(bufferedReader.readLine());
					Integer result = 0;
					String operand = bufferedReader.readLine();
					if (operator1 >= Integer.MAX_VALUE && operator2 >= Integer.MAX_VALUE && operand.startsWith(Constants.OPERAND1))
						result = operator1 + operator2;
					else if (operator1 >= Integer.MAX_VALUE && operator2 >= Integer.MAX_VALUE && operand.startsWith(Constants.OPERAND2))
						result = operator1 * operator2;
					
					printWriter.println(result);
					
					
				}
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "[COMMUNICATION THREAD] An exception has occurred: " + ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}
			}
		}
	}
}
	
