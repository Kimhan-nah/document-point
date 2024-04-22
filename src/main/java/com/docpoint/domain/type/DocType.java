package com.docpoint.domain.type;

public enum DocType {
	CONFLUENCE("Confluence"), GITLAB("GitLab");
	private final String docCategory;

	DocType(String docCategory) {
		this.docCategory = docCategory;
	}
}
