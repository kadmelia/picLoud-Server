import java.io._
import java.net.{InetAddress,ServerSocket,Socket,SocketException}

object client {
     
  def main(args: Array[String]) {


	try {
		val ia = InetAddress.getByName("localhost")
		val socket = new Socket(ia, 9999)
		
		
		
		// sendfile
		var myFile = new File ("boutDcode.png");
		println(myFile.length())
		var mybytearray:Array[Byte]  = new Array[Byte](myFile.length().toInt);
		
		var fis = new FileInputStream(myFile);
		var bis = new BufferedInputStream(fis);
		
		bis.read(mybytearray,0,mybytearray.length);
		
		var os = socket.getOutputStream();
		System.out.println("Sending...");
		
		os.write(mybytearray,0,mybytearray.length);
		
		os.flush();
		
		socket.close()
		
		println(mybytearray.length)

	}
	catch {
	  case e: IOException =>
	    e.printStackTrace()
	}
  }

}

