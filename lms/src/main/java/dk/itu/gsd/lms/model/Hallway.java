package dk.itu.gsd.lms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("H")
public class Hallway extends AbstractRoom {

}
