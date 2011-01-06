/*
 * Copyright 2010-2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.androidannotations.generation;

import com.googlecode.androidannotations.model.Instruction;

/**
 * @author Benjamin Fellous
 * @author Pierre-Yves Ricau
 */
public class LongClickInstruction implements Instruction {

	private static final String FORMAT_RETURN_TRUE = //
	"" + //
			"        (findViewById(%s)).setOnLongClickListener(new android.view.View.OnLongClickListener() {\n" + //
			"			public boolean onLongClick(android.view.View v) {\n" + //
			"				%s(%s);\n" + //
			"				return true;\n" + //
			"			}\n" + //
			"		});\n" + //
			"\n";

	private static final String FORMAT_RETURN_RESULT = //
	"" + //
			"        (findViewById(%s)).setOnLongClickListener(new android.view.View.OnLongClickListener() {\n" + //
			"			public boolean onLongClick(android.view.View v) {\n" + //
			"				return %s(%s);\n" + //
			"			}\n" + //
			"		});\n" + //
			"\n";

	private final String methodName;

	private final String clickQualifiedId;

	private final boolean viewParameter;

	private final boolean returnMethodResult;

	public LongClickInstruction(String methodName, String clickQualifiedId, boolean viewParameter, boolean returnMethodResult) {
		this.methodName = methodName;
		this.clickQualifiedId = clickQualifiedId;
		this.viewParameter = viewParameter;
		this.returnMethodResult = returnMethodResult;
	}

	@Override
	public String generate() {
		String viewParameterValue = viewParameter ? "v" : "";
		String format = returnMethodResult ? FORMAT_RETURN_RESULT : FORMAT_RETURN_TRUE;
		return String.format(format, clickQualifiedId, methodName, viewParameterValue);
	}

}
