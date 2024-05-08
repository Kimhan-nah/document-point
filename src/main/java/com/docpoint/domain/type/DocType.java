package com.docpoint.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DocType {
	CONFLUENCE("Confluence"), GITLAB("GitLab");
	private final String docCategory;

	DocType(String docCategory) {
		this.docCategory = docCategory;
	}

	@JsonCreator
	public static DocType getEnumFromValue(String value) {
		for (DocType e : values()) {
			if (e.docCategory.equals(value)) {
				return e;
			} else if (e.docCategory.equalsIgnoreCase(value)) {
				return e;
			}
		}
		return null;
	}
}
