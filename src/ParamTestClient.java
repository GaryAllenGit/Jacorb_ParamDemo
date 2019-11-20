import ParamTest.*;

import org.omg.CORBA.*;
import java.io.*;

public class ParamTestClient {
	public static void main( String[] args ) {
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			BufferedReader in = new BufferedReader(new FileReader("server.ref"));
			String stringified_ior = in.readLine();

			System.out.println("stringified_ior = " + stringified_ior);
			org.omg.CORBA.Object server_ref =
					orb.string_to_object(stringified_ior);

			ParamTest.MyServer s =
					ParamTest.MyServerHelper.narrow(server_ref);

			s.print("this is a message from the client! \n\n");

			DoubleHolder doh = new DoubleHolder();
			s.addNums(5, 6, doh);
			System.out.println("addNums 5 and 6 gives: " + doh.value);

			String[] a;
			stringSeqHolder sh = new stringSeqHolder( );
			s.op1( "hi_there" , sh );
			a = sh.value;
			System.out.println( "String array contains: ");
			for( int i = 0; i < a.length; i++ )
				System.out.println( "\t" + i + ": " + a[i] );

			my_structHolder moh = new my_structHolder();
			s.op3(moh);
			my_struct m = moh.value;
			System.out.println( "Struct contains: " + m.s + " " + m.l);

			stringArrayHolder sah = new stringArrayHolder();
			s.op4(sah);
			String[] my_array = sah.value;
			System.out.println("Array size: " + my_array.length );

			StringHolder sh1 = new StringHolder();
			String sh2 = s.op5( sh1 );
			System.out.println( sh2 + " out: " + sh1.value );


			// an example for a sequence of sequences of sequences of string
			//
			// set up a 3-dimensional string array

			String [][][] string_cube = new String[1][2][3];
			for( int i=0; i<string_cube.length;i++)
				for( int j = 0; j < string_cube[i].length;j++)
					for( int k = 0; k < string_cube[i][j].length;k++)
						string_cube[i][j][k] = "("+i+","+ k + "," +j+")";

			// put it into the appropriate holder for inout semantics

			stringCubeHolder sf = new stringCubeHolder(string_cube);

			// invoke the operation

			s.stringCubeInOut(sf);

			// get the returned string cube

			string_cube = sf.value;

			System.out.println( "string_cube after operation: ");

			for( int i=0; i<string_cube.length;i++)
				for( int j = 0; j < string_cube[i].length;j++)
					for( int k = 0; k < string_cube[i][j].length;k++)
						System.out.println(string_cube[i][j][k]);

			System.out.println("---Everything went alright, closing down now---");

		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
}




