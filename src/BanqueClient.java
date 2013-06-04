import java.util.ArrayList;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class BanqueClient extends IBanquePOA{
	
	private int index;
	private int indexRech;
	private ArrayList<Compte> bq;

	public BanqueClient(){
		bq=BanqueAdmin.bq;
	}

	public ICompte rechercheCompte(String p_nom) throws CompteNullException{
		int i=0;
		while (i<bq.size() && !bq.get(i).nom().equals(p_nom)){
			i++;
		}
		if (i<bq.size()){
			indexRech=i;
			org.omg.CORBA.Object ref = null;
			try {
				ref = _poa().servant_to_reference(bq.get(i));
			} catch (ServantNotActive | WrongPolicy e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ICompte href = ICompteHelper.narrow(ref);
			return href;
		}else{
			indexRech=-1;
			String erreur="Le compte n'existe pas";
			throw new CompteNullException(erreur);
		}
	}
}  