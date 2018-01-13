package Bibliotheque;
import java.util.List;

public class Bibliotheque {
	
	private List<Abonne> listAbo;
	private List<Document> listDoc;
	
	public Bibliotheque(List<Abonne> listAbo,List<Document> listDoc){
		this.listAbo = listAbo;
		this.listDoc = listDoc;
	}
	
	
	public Document getDoc(int id){
		for(Document l : listDoc){
			if (l.numero() == id){
				return l;
			}
		}
		return null;
	}
	
	public Abonne getAbo(int id){
		for (Abonne a : listAbo){
			if (a.getId() == id){
				return a;
			}
		}
		return null;
	}
	
	public void addDoc(Livre d){
		listDoc.add(d);
	}
	
	
	public void addAbo(Abonne a){
		listAbo.add(a);
	}
}
