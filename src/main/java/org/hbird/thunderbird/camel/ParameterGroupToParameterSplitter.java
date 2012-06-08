package org.hbird.thunderbird.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hbird.core.commons.tmtc.Parameter;
import org.hbird.core.commons.tmtc.ParameterGroup;

public class ParameterGroupToParameterSplitter {

	private final List<String> qualifiedNames;

	public ParameterGroupToParameterSplitter(final List<String> qualifiedNames) {
		this.qualifiedNames = qualifiedNames;

		System.out.println(StringUtils.join(qualifiedNames, ","));

	}

	/**
	 * The split body method returns something that is iterable such as a java.util.List.
	 *
	 * @param body the payload of the incoming message
	 * @return a list containing each part split
	 */
	public List<Parameter<?>> splitBody(final ParameterGroup pg) {
		final List<Parameter<?>> results = new ArrayList<Parameter<?>>();

		for(final Parameter<?> p : pg.getAllParametersAsList()) {
			for(final String qualifiedName : qualifiedNames) {
				if(p.getQualifiedName().equals(qualifiedName)) {
					results.add(p);
				}
			}
		}
		return results;
	}

}
