
public class Compte extends IComptePOA{
	private String nom;
	private float compte;
 
	public Compte(String p_nom){
		nom=p_nom;
		compte=0;
	}

	public float debit(float p_debit) throws SoldeDeficitaire {
		if (compte-p_debit<0){
			String erreur="Si vous effectuez cette operation votre compte sera en deficit";
		throw new SoldeDeficitaire(erreur);
		}else{
			compte-=p_debit;
			return compte;
		}
	}

	public float credit(float p_credit){
		compte+=p_credit;
		return compte;
	}

	public String toString(){
		return "Le compte appartenant à: "+nom+" est credite de: "+compte+"\n"; 
	}

	@Override
	public String nom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public void nom(String newNom) {
		// TODO Auto-generated method stub
		nom=newNom;
	}

	@Override
	public float compte() {
		// TODO Auto-generated method stub
		return compte;
	}

	@Override
	public void compte(float newCompte) {
		// TODO Auto-generated method stub
		compte=newCompte;
	}
}