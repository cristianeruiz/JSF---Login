package be.kanea.project.shoppinglist.item;

import be.kanea.project.shoppinglist.client.WebServiceSeguridad;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.JSONObject;


@SessionScoped
@ManagedBean(name = "itemManagedBean", eager = true)
public class ItemManagedBean  implements Serializable {
	
    public String result;
    
    WebServiceSeguridad webServiceSeguridad = new WebServiceSeguridad("http://localhost:8080/api/");

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
	private static final long serialVersionUID = -645545444545554509L;
	
	private Item item = new Item();
	private List<Item> items;
	
	private int id;
	
	public ItemManagedBean() {
		ItemDAO itemDAO = new ItemDAO();
		this.items = itemDAO.selectAll();
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Item getItem() {
		return item;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	/*public String prepareInsert() {	
		this.item = new Item();
		return "insertItem?faces-redirect=true";
	}*/
        
        public String prepareInsert() {	
            JSONObject data = new JSONObject();
            data.put("username", "mod");
            data.put("password", "123");
            
            //setResult("Good Luck ZZZ: " + webServiceSeguridad.getUsuario("Bearer "+webServiceSeguridad.getToken(data), "mod") );
            String token = webServiceSeguridad.getToken(data);
            JSONObject json = webServiceSeguridad.getUsuario("Bearer "+token, "mod");
            setResult("Good Luck ZZZ: " + json.getString("message") );
            
            return "login/index?faces-redirect=true";
	}
        
	
	public String insert() {
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.insert(this.item);
		this.items = itemDAO.selectAll();
		return "index?faces-redirect=true";
	}
	public String effacer() {
		System.out.println("Effacer");
		return "ok";
	}
	public String delete(String id) {
		System.out.println("delete");
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.delete(Integer.valueOf(id));
		this.items = itemDAO.selectAll();
		//return "";
		return "index?faces-redirect=true";
	}
	
	public String prepareUpdate(String id) {
		ItemDAO itemDAO = new ItemDAO();	
		this.item = itemDAO.selectById(Integer.valueOf(id));
		return "editItem?faces-redirect=true";
	}
	
	public String update() {
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.update(this.item);
		this.items = itemDAO.selectAll();
		return "index?faces-redirect=true";
	}
}	
