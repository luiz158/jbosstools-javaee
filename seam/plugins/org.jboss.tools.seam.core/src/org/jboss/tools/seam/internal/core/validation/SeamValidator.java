 /*******************************************************************************
  * Copyright (c) 2007 Red Hat, Inc.
  * Distributed under license by Red Hat, Inc. All rights reserved.
  * This program is made available under the terms of the
  * Eclipse Public License v1.0 which accompanies this distribution,
  * and is available at http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributor:
  *     Red Hat, Inc. - initial API and implementation
  ******************************************************************************/
package org.jboss.tools.seam.internal.core.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidatorJob;
import org.jboss.tools.seam.core.ISeamTextSourceReference;

/**
 * @author Alexey Kazakov
 *
 */
public abstract class SeamValidator implements IValidatorJob {

	private IValidationContext helper;
	private IReporter reporter;
	
	public IStatus validateInJob(IValidationContext helper, IReporter reporter)	throws ValidationException {
		this.helper = helper;
		this.reporter = reporter;
		return OK_STATUS;
	}

	public void cleanup(IReporter reporter) {
		reporter = null;
	}

	public void validate(IValidationContext helper, IReporter reporter)	throws ValidationException {
		validateInJob(helper, reporter);
	}

	protected String getBundleName() {
		// TODO
		return "seamvalitation";
	}

	protected void addError(String messageId, String[] messageArguments, ISeamTextSourceReference target) {
		IMessage message = new Message(getBundleName(), IMessage.HIGH_SEVERITY, messageId, messageArguments, target.getResource());
		message.setLength(target.getLength());
		message.setOffset(target.getStartPosition());
		reporter.addMessage(this, message);
	}

	protected void addError(String messageId, ISeamTextSourceReference target) {
		addError(messageId, new String[0], target);
	}
}