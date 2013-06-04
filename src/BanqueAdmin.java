import java.util.ArrayList;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class BanqueAdmin extends IBanqueAdminPOA{
	
	private int indexRech;
	public static ArrayList<Compte> bq;
	
	public BanqueAdmin(){
		bq=new ArrayList<Compte>();
	}

	public boolean creationCompte(String p_nom) throws CompteAlreadyExist{
		try{
			rechercheCompte(p_nom);
		}finally{
			if (indexRech!=-1){
				String erreur="Un compte existe deja a ce nom";
				throw new CompteAlreadyExist(erreur);
			}else{
				int taille=bq.size();
				Compte cpt=new Compte(p_nom);
				bq.add(cpt);
				return (bq.size()==taille+1);
			}
		}
	}

	public boolean deleteCompte(String p_nom){
		try{
			rechercheCompte(p_nom);
		}finally{
			if (indexRech==-1){
				return false;
			}else{
				bq.remove(indexRech);
				return true;
			}
		}
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