import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class ParamTestServer {
	public static void main( String[] args ) {
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			ParamTestServerImpl srv = new ParamTestServerImpl();

			// Get the 'stringified IOR'
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(srv);
			String stringified_ior = orb.object_to_string(ref);

			// Save IOR to file
			BufferedWriter out = new BufferedWriter(new FileWriter("server.ref"));
			out.write(stringified_ior);
			out.close();

			System.out.println("stringified_ior = " + stringified_ior);

			// wait for invocations from clients
			orb.run();

		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}



