import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.CosNaming.*;

public class Server {

    public void run (String args[]) {
	try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
		
		//org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		//NamingContext nsRef = NamingContextHelper.narrow(objRef);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa =
                POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            BanqueAdmin banque = new BanqueAdmin();
		BanqueClient banqueCli = new BanqueClient();

	//NameComponent[] nsNom=new NameComponent[1];
	//nsNom[0]=new NameComponent("banque","");
	

            // get object reference from the servant
            org.omg.CORBA.Object ref =
		rootpoa.servant_to_reference(banque);
	org.omg.CORBA.Object ref2 =
		rootpoa.servant_to_reference(banqueCli);
		//nsRef.bind(nsNom,ref);

            IBanqueAdmin href = IBanqueAdminHelper.narrow(ref);
	IBanque href2 = IBanqueHelper.narrow(ref2);

	    System.out.println (orb.object_to_string (href)) ;
	System.out.println (orb.object_to_string (href2)) ;

            System.err.println("JavaM2Server ready and waiting ...");

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        } 
    }
	
    public static void main (String args[]) {
	Server srv = new Server () ;
	srv.run (args) ;
    }
}
