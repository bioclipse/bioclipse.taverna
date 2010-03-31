/* *****************************************************************************
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

import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.managers.business.IBioclipseManager;

import org.apache.log4j.Logger;

/**
 * 
 * @author ola
 *
 */
public class TavernaManager implements IBioclipseManager {

    private static final Logger logger = Logger.getLogger(TavernaManager.class);

    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "taverna";
    }

    public void runRemote(String workflow) throws BioclipseException{
        throw new BioclipseException( "Not yet implemented." );
    }

    public void runRemote(String workflow, String server) 
    throws BioclipseException{

        throw new BioclipseException( "Not yet implemented." );

    }

}
