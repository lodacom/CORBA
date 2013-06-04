import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class ClientConsult {

    public void run (String args[]) {
	try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

		//org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		//NamingContext nsRef = NamingContextHelper.narrow(objRef);

            // get object reference from the comand line 
            org.omg.CORBA.Object ref = orb.string_to_object (args[1]) ;
            IBanque href = IBanqueHelper.narrow(ref);
		
	//NameComponent[] nsNom=new NameComponent[1];
	//nsNom[0]=new NameComponent("banque","");
	
	//org.omg.CORBA.Object objRef2 = null;
	//objRef2 = nsRef.resolve(nsNom);
	//IBanque href2 = IBanqueHelper.narrow(objRef2);

	    // perform the method call
	    System.out.println ("Invoking object hello") ;
		try{
			ICompte recup=href.rechercheCompte("Duplouy");
			recup.credit((float)100);
			try{
				System.out.println(recup.debit((float)0));
			}catch(SoldeDeficitaire sd){
	
			}
		}catch(CompteNullException cne){

		}
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }

    public static void main (String args[]) {
	ClientConsult cli = new ClientConsult () ;
	cli.run (args) ;
    }
}
