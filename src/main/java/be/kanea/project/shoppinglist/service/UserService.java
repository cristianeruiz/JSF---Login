package be.kanea.project.shoppinglist.service;

import be.kanea.project.shoppinglist.item.Item;
import be.kanea.project.shoppinglist.item.ItemDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristiane
 */
public class UserService {
    
    private List<Item> users = new ArrayList<Item>();

    public List<Item> fetchAll() {

        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.selectAll();
    }
    
}
