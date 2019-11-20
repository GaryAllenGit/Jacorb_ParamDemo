A demo program to show a range of in, inout, and out parameters in Java with the Jacorb Orb.

Dr Gary Allen.
University of Huddersfield

If you don't already have it, download Jacorb from the Jacorb web site and unpack it somewhere.

Also download the library jboss-rmi-api_1.0_spec-1.0.6.Final.jar from the Maven Repository at:

    https://mvnrepository.com/artifact/org.jboss.spec.javax.rmi/jboss-rmi-api_1.0_spec/1.0.6.Final.

This is a workaround to fix an issue with Java 11 and above.

To run the demo:

1.  Add all the libraries from the Jacorb lib folder to the project (they are not all required, but I don't know the minimum set) **AND** add the jboss library too.

2.  Precompile the idl.  To do this use the terminal built into Intellij (or an external terminal if you prefer) and type:


    cd src
    <path_to_jacorb_dir>/bin/idl ParamTest.idl

e.g. I would type:

    cd src
    /spare/jacorb-3.9/bin/idl ParamTest.idl


3.  Start the Server and then run the Client.  The server persists so has to be stopped externally.

The Client output will look something like:

	addNums 5 and 6 gives: 11.0
	String array contains: 
		0: hi_there
		1: hi_there
		2: hi_there
		3: hi_there
		4: hi_there
	Struct contains: hallo 4711
	Array size: 5
	op5 done. out: 1234567890
	string_cube after operation: 
	Returned element [0][0][0]
	Returned element [0][0][1]
	Returned element [0][0][2]
	Returned element [0][1][0]
	Returned element [0][1][1]
	Returned element [0][1][2]
	---Everything went alright, closing down now---


Note that there will be output on the Server window as well.

Study the code and make sure you understand what is going on.

4.  Note that to stop Jacorb displaying extensive logging information you can add the following VM args to the ParamTestServer and ParamTestClient run configurations:


    -Djacorb.log.default.verbosity=2




IF you wish to run the demo from a command line (instead of from the IDE) then do the following:

1. Translate the IDL

	cd <to the src folder containing all the code>
	/spare/jacorb-3.9/bin/idl ParamTest.idl


2. Compile the sources

	javac  *.java


3. Start the server

	java ParamTestServer

 
4. In another window ....
... Having cd'd to the right (same) directory ...
... Start the client

	java ParamTestClient

