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

package org.glassfish.web.deployment.annotation.impl;

import com.sun.enterprise.deployment.WebBundleDescriptor;
import com.sun.enterprise.deployment.WebComponentDescriptor;
import com.sun.enterprise.deployment.annotation.impl.ModuleScanner;
import com.sun.enterprise.deployment.web.AppListenerDescriptor;
import com.sun.enterprise.deployment.web.ServletFilter;

import jakarta.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.glassfish.api.deployment.archive.ReadableArchive;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.internal.api.ClassLoaderHierarchy;
import org.glassfish.web.deployment.descriptor.WebFragmentDescriptor;
import org.jvnet.hk2.annotations.Service;


/**
 * Implementation of the Scanner interface for war.
 *
 * @author Shing Wai Chan
 */
@Service(name="war")
@PerLookup
public class WarScanner extends ModuleScanner<WebBundleDescriptor> {

    private boolean scanOtherLibraries;

    @Inject
    private ClassLoaderHierarchy clHierarchy;

    public void setScanOtherLibraries(boolean scanOtherLibraries) {
        this.scanOtherLibraries = scanOtherLibraries;
    }

    public boolean isScanOtherLibraries() {
        return scanOtherLibraries;
    }


    @Override
    protected void process(File archiveFile, WebBundleDescriptor webBundleDesc, ClassLoader classLoader)
        throws IOException {
        this.archiveFile = archiveFile;
        this.classLoader = classLoader;
    }


    @Override
    protected final void completeProcess(WebBundleDescriptor descriptor, ReadableArchive archive) throws IOException {
        if (isScanOtherLibraries()) {
            addLibraryJars(descriptor, archive);
            return;
        }
        File webinf = new File(archiveFile, "WEB-INF");
        if (descriptor instanceof WebFragmentDescriptor) {
            WebFragmentDescriptor webFragmentDesc = (WebFragmentDescriptor) descriptor;
            File lib = new File(webinf, "lib");
            if (lib.exists()) {
                File jarFile = new File(lib, webFragmentDesc.getJarName());
                if (jarFile.exists()) {
                    // support exploded jar file
                    if (jarFile.isDirectory()) {
                        addScanDirectory(jarFile);
                    } else {
                        addScanJar(jarFile);
                    }
                }
            }
        } else {
            File classes = new File(webinf, "classes");
            if (classes.exists()) {
                addScanDirectory(classes);
            }
        }
        scanXmlDefinedClassesIfNecessary(descriptor);
    }


    // This is not mandated by the spec. It is for WSIT.
    // We will also scan any servlets/filters/listeners classes specified
    // in web.xml additionally if those classes are not resided in the wars.
    private void scanXmlDefinedClassesIfNecessary(WebBundleDescriptor webBundleDesc) throws IOException {
        ClassLoader commonCL = clHierarchy.getCommonClassLoader();

        for (WebComponentDescriptor element : webBundleDesc.getWebComponentDescriptors()) {
            if (element.isServlet()) {
                String servletName = element.getWebComponentImplementation();
                if (isScan(servletName, commonCL)) {
                    addScanClassName(servletName);
                }
            }
        }

        Vector<ServletFilter> servletFilters = webBundleDesc.getServletFilters();
        for (int i = 0; i < servletFilters.size(); i++) {
            ServletFilter filter = servletFilters.elementAt(i);
            String filterName = filter.getClassName();
            if (isScan(filterName, commonCL)) {
                addScanClassName(filter.getClassName());
            }
        }

        Vector<AppListenerDescriptor> listeners = webBundleDesc.getAppListenerDescriptors();
        for (int j = 0; j < listeners.size(); j++) {
            AppListenerDescriptor listenerDesc = listeners.elementAt(j);
            String listenerName = listenerDesc.getListener();
            if (isScan(listenerName, commonCL)) {
                addScanClassName(listenerDesc.getListener());
            }
        }
    }


    private boolean isScan(String className, ClassLoader commonCL) throws IOException {
        boolean result = false;
        // TODO ignore delegate in sun-web.xml in this moment
        String resourceName = "/" + className.replace(".", "/") + ".class";
        return commonCL.getResource(resourceName) != null;
    }
}
