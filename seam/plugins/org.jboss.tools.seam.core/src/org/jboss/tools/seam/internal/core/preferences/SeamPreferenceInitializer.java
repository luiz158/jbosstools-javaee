/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.seam.internal.core.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.jboss.tools.seam.core.SeamCorePlugin;
import org.jboss.tools.seam.core.SeamPreferences;

/**
 * @author Viacheslav Kabanovich
 */
public class SeamPreferenceInitializer extends AbstractPreferenceInitializer {
	
	public SeamPreferenceInitializer() {}

	@Override
	public void initializeDefaultPreferences() {

		IEclipsePreferences defaultPreferences = ((IScopeContext) new DefaultScope()).getNode(SeamCorePlugin.PLUGIN_ID);
		for (String name : SeamPreferences.severityOptionNames) {
			defaultPreferences.put(name, SeamPreferences.ERROR);
		}

	}

}
