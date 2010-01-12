/*******************************************************************************
 * Copyright (c) 2009  Ola Spjuth <ola@bioclipse.net>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.taverna.business;

import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.Recorded;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="A Manager for executing Taverna Workflows"
)
public interface ITavernaManager extends IBioclipseManager {

    @Recorded
    @PublishedMethod( 
        params = "String workflow",
        methodSummary = "Runs a taverna workflow on the default remote server")
    public void runRemote(String workflow) throws BioclipseException;

    @Recorded
    @PublishedMethod( 
        params = "String workflow, String server",
        methodSummary = "Runs a taverna workflow on a remote server")
    public void runRemote(String workflow, String server) 
    throws BioclipseException;

}
