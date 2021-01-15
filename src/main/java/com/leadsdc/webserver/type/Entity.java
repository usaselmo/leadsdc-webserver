package com.leadsdc.webserver.type;

import java.io.Serializable;

public interface Entity<ID> extends Serializable {

	ID getId();

}
