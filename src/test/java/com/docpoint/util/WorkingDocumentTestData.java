package com.docpoint.util;

import java.util.List;
import java.util.Map;

import com.docpoint.domain.model.Working;
import com.docpoint.domain.model.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;
import com.docpoint.domain.type.WorkingStatusType;

public class WorkingDocumentTestData {
	/**
	 * WorkingDocument 생성
	 * <p> 생성된 WorkingDocument는 status가 검토중(REVIEW)이다. </p>
	 * @return
	 */
	public static WorkingDocument createWorkingDocument() {
		Working working = WorkingTestData.createWorking();
		working = working.updateStatus(WorkingStatusType.DONE);
		return new WorkingDocument(working, "title", "content", DocStatusType.REVIEW, createLinks(), false);
	}

	public static Map<DocType, List<String>> createLinks() {
		return Map.of(DocType.CONFLUENCE, List.of("confluence.com"), DocType.GITLAB, List.of("gitlab.com"));
	}

	public static Map<DocType, List<String>> createLinks(DocType docType, List<String> links) {
		return Map.of(docType, links);
	}
}
