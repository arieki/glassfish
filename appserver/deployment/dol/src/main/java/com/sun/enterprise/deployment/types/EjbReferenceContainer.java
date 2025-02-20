/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.enterprise.deployment.types;

import com.sun.enterprise.deployment.EjbReferenceDescriptor;

import java.util.Set;

/**
 * This interface defines the behaviour of a J2EE component containaing ejb references
 *
 * @author Jerome Dochez
 */
public interface EjbReferenceContainer {

    /**
     * Add a reference to an ejb.
     *
     * @param the ejb reference
     */
    void addEjbReferenceDescriptor(EjbReferenceDescriptor ejbReference);

    /**
     * Looks up an ejb reference with the given name.
     *
     * @param the name of the ejb-reference
     * @throws an IllegalArgumentException if it is not found.
     */
    EjbReferenceDescriptor getEjbReference(String name);

    /**
     * @return the set of references to ejbs that I have.
     */
    Set<EjbReferenceDescriptor> getEjbReferenceDescriptors();
}

