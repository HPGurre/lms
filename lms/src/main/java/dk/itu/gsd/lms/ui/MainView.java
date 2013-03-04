package dk.itu.gsd.lms.ui;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(MainView.NAME)
@Theme("runo")
public class MainView extends Panel implements View
{
    public static final String NAME = "profile";

    @Autowired
    private transient ApplicationContext applicationContext;


    @PostConstruct
    public void PostConstruct()
    {
        Layout mainLayout = new VerticalLayout();
        mainLayout.addComponent( new Label("This is the building ui"));
        
        setContent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
    }
}
