

import scala.actors.Actor
import scala.actors.Actor._

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat
import java.io._
import java.net.{InetAddress,ServerSocket,Socket,SocketException}
import java.io.DataInputStream;


object server {

  def main(args: Array[String]): Unit = {
    try {
      val listener = new ServerSocket(9999);
		
		var mainDir = new File("photos");
		
		if (!mainDir.exists()) {
			mainDir.mkdir();
		}
		
		while (true)
        new conThread(listener.accept()).start
      listener.close()
    }
    catch {
      case e: IOException =>
        System.err.println("Could not listen on port: 9999.")
        System.exit(-1)
    }
  }

}


class conThread(sock: Socket) extends Actor {
	
	def act() {
		
		System.out.println("Connecting...");
		
		var filesize=6022386;
		
		// receive file
		var mybytearray  = new Array[Byte](filesize);
		
		var start = System.currentTimeMillis();
		
		var is = sock.getInputStream();
		
		var format = new SimpleDateFormat("yyyy-MM-dd");
		var finalDirName = format.format(new Date);
		
		var mainDir = new File("photos" + separator + finalDirName);
		
		if (!mainDir.exists()) {
			mainDir.mkdir();
		}
		
		var fos = new FileOutputStream("test/toto");

		var bos = new BufferedOutputStream(fos);
		var bytesRead = is.read(mybytearray,0,mybytearray.length);
		var current = bytesRead;
		
		do {
		   bytesRead =
		      is.read(mybytearray, current, (mybytearray.length-current));
		   if(bytesRead >= 0) current += bytesRead;
		} while(bytesRead > -1);
		
		bos.write(mybytearray, 0 , current);
		bos.flush();
		var end = System.currentTimeMillis();
		System.out.println(end-start);
		bos.close();
		sock.close();

			
	}
	
} 




