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

package com.sun.enterprise.deployment.util;

import com.sun.enterprise.deployment.BundleDescriptor;

import org.glassfish.deployment.common.DescriptorVisitor;

/**
 * This class defines the protocol for visiting J2EE Component DOL
 * related classes
 *
 * @author Jerome Dochez 2002
 */
public interface ComponentVisitor extends DescriptorVisitor {

    /**
     * visits a J2EE component bundle descriptor.
     */
    void accept(BundleDescriptor bundleDesc);
}

