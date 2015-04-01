import java.util.HashMap;

public class TabIdent 
{
	private HashMap<String,Ident> table;
	private HashMap<String ,Ident> locaux;
	public int cptParam = 0;
	
	

	
	/**
	 * Constructor
	 * @param size
	 */
	public TabIdent()
	{
		this.table = new HashMap<String, Ident>();
	}
	
	/**
	 * Find the ident
	 * @param key
	 * @return
	 */
	public Ident findIdent (String key)
	{
		return table.get(key);
	}
	
	/**
	 * Return true if key exist
	 * @param key
	 * @return
	 */
	public boolean identExist (String key)
	{
		return table.containsKey(key);
	}
	
	/**
	 * Put ident
	 * @param key
	 * @param id
	 */
	public void putIdent (String key, Ident id)
	{
		if (identExist(key)) {
			System.out.println("Ident : "+key+" Erreur cet Ident existe d�j� dans la table des identificateurs ligne"+SimpleCharStream.getEndLine()+"\n");
			return;
		}
		table.put(key, id);
	}
	
	/**
	 * Get the type of the ident 
	 * @param key
	 * @return Type
	 */
	public Type getType (String key)
	{
		if (this.identExist(key)){
			Ident ident = this.table.get(key);
			return ident.getType();
		}
		else {
			System.out.println("Ident : "+key+" Erreur cet Ident n'existe pas dans la table des identificateurs ligne "+SimpleCharStream.getEndLine()+"\n");
			return Type.ERREUR;
		}
	}
	
	/**
	 * Get the value of the ident
	 * @param key
	 * @return int
	 */
	public int getValue (String key) {
		if (identExist(key)) {
			return findIdent(key).getValue();
		}
		else {
			System.out.println("Ident : "+key+" Erreur cet Ident n'existe pas dans la table des identificateurs ligne "+SimpleCharStream.getEndLine()+"\n");
			return -1;
		}	
	}
	
	public void clearLoco(){
		this.cptParam=0;
		this.locaux.clear();
	}
	
	public void addLoco(String s,Ident i){
		locaux.put(s, i);
	}
	
	public void calculateOffsets()
	{
		int rang=1;
		int size = locaux.size();
		for (String mapKey : locaux.keySet()) {
			Ident ident = locaux.remove(mapKey);
			ident.setValue(size + 4 - (rang * 2));
			locaux.put(mapKey,ident);
			rang ++;
			// utilise ici hashMap.get(mapKey) pour acc�der aux valeurs
		}
	}
	
	

}


