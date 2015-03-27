package testapp.view.thymeleaf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

	private final static Map<String, String> MENU = new HashMap<String, String>();
	private final static Map<String, List<String>> MENU_VIEW_MAP = new HashMap<String, List<String>>();

	static {
		MENU_VIEW_MAP.put("home", Arrays.asList("index", "error"));
		MENU_VIEW_MAP.put("users", Arrays.asList("admin/user/userList", "admin/user/userEdit"));
		MENU_VIEW_MAP.put("messages", Arrays.asList("auth/message/messageList"));
		MENU_VIEW_MAP.put("contact", Arrays.asList("public/contact"));
		MENU_VIEW_MAP.put("devTest", Arrays.asList("admin/devtest/devTest"));
		
		for(String menu : MENU_VIEW_MAP.keySet()){
			for(String view : MENU_VIEW_MAP.get(menu)){
				MENU.put(view, menu);
			}
		}
	}
	
	public static String getMenuByView(String view){
		return MENU.get(view);
	}
}
