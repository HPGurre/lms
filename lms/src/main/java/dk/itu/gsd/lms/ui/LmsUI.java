package dk.itu.gsd.lms.ui;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Main UI class
 */
@Component("ui")
@Scope("prototype")
public class LmsUI extends UI implements Serializable{
	
	@Autowired
	private WebApplicationContext applicationContext;

	@Override
	protected void init(VaadinRequest request) {
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
        //navigator.navigateTo(UI.getCurrent().getPage().getUriFragment());
        
        navigator.navigateTo(MainView.NAME);
		
//		request.getAttribute(getPrimaryStyleName());
//		SimpleViewDisplay display = new SimpleViewDisplay();
//		setContent(display);
//
//		DiscoveryNavigator navigator = new DiscoveryNavigator(applicationContext, Page.getCurrent(), layout);
//		navigator.navigateTo(MainView.NAME);
	}

//	@Override
//	protected void init(VaadinRequest request) {
//		final VerticalLayout layout = new VerticalLayout();
//		layout.setMargin(true);
//		setContent(layout);
//
//		Button button = new Button("Click Me");
//		button.addClickListener(new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//				layout.addComponent(new Label("Thank you for clicking"));
//			}
//		});
//		layout.addComponent(button);
//	}
	
//	 @Override
//     protected void init(VaadinRequest request) {
//         final VerticalLayout layout = new VerticalLayout();
//         layout.setMargin(true);
//         setContent(layout);
//     
//         Button button = new Button("Click Me");
//         button.addClickListener(new Button.ClickListener() {
//         
//         private static final long serialVersionUID = 1L;
//
//         public void buttonClick(ClickEvent event) {
//             
//  
//                 
//             }
//         });
//         layout.addComponent(button);
//     }

}