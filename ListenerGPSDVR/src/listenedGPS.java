import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class listenedGPS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("DVR GPS AND SENSOR STATE LISTENER COLTRACK 28/01/2015");
		ServerSocket serverSocket = null; 
		Connection conDB = null;
		try {
			serverSocket = new ServerSocket(6608);
			Socket clientSocket = null; 
			//clientSocket.setSoTimeout(0);
			clientSocket = serverSocket.accept(); 
			System.out.println("DVR conected...");
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true); 
			BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream())); 
			//clientSocket.setSoTimeout(30000);
			String inputLine; 
			
			while ((inputLine = in.readLine()) != null){ 
				System.out.println("Report: "+inputLine);
				System.out.println("Desglosing: ");
				String[] parts=inputLine.split(";");
				System.out.println("Header: "+parts[0]);
				if (parts[0].equals("$RPT")){
					System.out.println("Firmware version: "+parts[1]);
					System.out.println("DVR Id: "+parts[2]);
					System.out.println("Event code: "+parts[3]);
					int event = Integer.parseInt(parts[3]);
					switch (event){
						case 1:
							System.out.println("Periodic Report");
						break;
						case 4:
							System.out.println("Emergency Report");
						break;
						case 5:
							System.out.println("Message Report");
						break;
						case 10:
							System.out.println("ignition on");
						break;					
						case 11:
							System.out.println("ignition off");
						break;
						case 12:
							System.out.println("sensor 1 activated");
						break;
						case 13:
							System.out.println("sensor 1 deactivated");
						break;
						case 14:
							System.out.println("sensor 2 activated");
						break;
						case 15:
							System.out.println("sensor 2 deactivated");
						break;
						case 16:
							System.out.println("sensor 3 activated");
						break;
						case 17:
							System.out.println("sensor 3 deactivated");
						break;
						case 30:
							System.out.println("over speed");
						break;
						case 31:
							System.out.println("speed normalized");
						break;
						case 40:
							System.out.println("main power disconnected");
						break;
						case 41:
							System.out.println("main power reconnected");
						break;
						case 50:
							System.out.println("SD card inserted");
						break;
						case 51:
							System.out.println("SD card removed");
						break;
						case 52:
							System.out.println("SD disk full");
						break;
						case 53:
							System.out.println("USB device inserted");
						break;
						case 54:
							System.out.println("USB device removed");
						break;
						case 55:
							System.out.println("USB device full");
						break;
						case 60:
							System.out.println("GPS disconnected");
						break;
						case 61:
							System.out.println("GPS reconnected");
						break;
						case 70:
							System.out.println("enter sleep mode");
						break;
						case 71:
							System.out.println("exit sleep mode");
						break;
						case 80:
							System.out.println("Firmware downloading");
						break;
						case 81:
							System.out.println("Firmware downloading failed");
						break;
						case 82:
							System.out.println("Firmware updating");
						break;
						case 83:
							System.out.println("Firmware updating failed");
						break;
						case 84:
							System.out.println("Firmware updated");
						break;
						case 90:
							System.out.println("GSM Network connected");
						break;
						case 91:
							System.out.println("GSM Network disconnected");
						break;
						case 92:
							System.out.println("WIFI Network connected");
						break;
						case 93:
							System.out.println("WIFI Network disconnected");
						break;
						case 94:
							System.out.println("RJ45 connected");
						break;
						case 95:
							System.out.println("RJ45 disconnected");
						break;
						case 100:
							System.out.println("Video 1 lost");
						break;
						case 101:
							System.out.println("Video 1 recovered");
						break;
						case 102:
							System.out.println("Video 2 lost");
						break;
						case 103:
							System.out.println("Video 2 recovered");
						break;
						case 104:
							System.out.println("Video 3 lost");
						break;
						case 105:
							System.out.println("Video 3 recovered");
						break;
						case 106:
							System.out.println("Video 4 lost");
						break;
						case 107:
							System.out.println("Video 4 recovered");
						break;
						case 108:
							System.out.println("Video 5 lost");
						break;
						case 109:
							System.out.println("Video 5 recovered");
						break;
						case 110:
							System.out.println("Video 6 lost");
						break;
						case 111:
							System.out.println("Video 6 recovered");
						break;
						case 112:
							System.out.println("Video 7 lost");
						break;
						case 113:
							System.out.println("Video 7 recovered");
						break;
						case 114:
							System.out.println("Video 8 lost");
						break;
						case 115:
							System.out.println("Video 8 recovered");
						break;
						case 116:
							System.out.println("Schedule recording started");
						break;
						case 117:
							System.out.println("Schedule recording stopped");
						break;
						case 118:
							System.out.println("Alarm recording started");
						break;
						case 119:
							System.out.println("Alarm recording stopped");
						break;
						case 120:
							System.out.println("Motion detection recording started");
						break;
						case 121:
							System.out.println("Motion detection recording stopped");
						break;
					}
					System.out.println("DateTime: "+parts[4]);
					String year,month,day,hour,minute,second;
					year=parts[4].substring(0, 4);
					month=parts[4].substring(4, 6);
					day=parts[4].substring(6, 8);
					hour=parts[4].substring(8, 10);
					minute=parts[4].substring(10, 12);
					second=parts[4].substring(10, 12);
					System.out.println("Time: "+year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second);
					System.out.println("Latitude: "+parts[5]);
					System.out.println("Longitude: "+parts[6]);
					System.out.println("Speed: "+parts[7]+ "kph");
					System.out.println("Course: "+parts[8]);
					if (parts[9].equals("1")){
						System.out.println("Gps Fixed");
					}
					if (parts[9].equals("0")){
						System.out.println("Gps Not Fixed");
					}
					if (parts[10].equals("1")){
						System.out.println("Gps Connected");
					}
					if (parts[10].equals("0")){
						System.out.println("Gps Disconnected");
					}
					System.out.println("Sensors: "+parts[11]);
					//System.out.println("sensor 1: "+parts[11].substring(0, 1));
					System.out.println("Sensor S3 State: "+parts[11].substring(0, 1));
					System.out.println("Sensor S2 State: "+parts[11].substring(1, 2));
					System.out.println("Sensor S1 State: "+parts[11].substring(2, 3));
					System.out.println("Sensor Ignition State: "+parts[11].substring(3,4));							
					System.out.println("Battery Voltage: "+parts[12]);
					System.out.println("\r\n\r\n\r\n");
				}	
			}
			clientSocket.close(); 
		    serverSocket.close();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Server socket problem: "+e.toString());
		}
	}
}
