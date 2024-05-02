package com.docpoint.util;

import java.util.List;
import java.util.Map;

import com.docpoint.domain.entity.Working;
import com.docpoint.domain.entity.WorkingDocument;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;
import com.docpoint.domain.type.WorkingStatusType;

public class WorkingDocumentTestData {
	private static Long workingId = 1L;

	/**
	 * WorkingDocument 생성
	 * <p> 생성된 WorkingDocument는 status가 검토중(REVIEW)이다. </p>
	 */
	public static WorkingDocument createWorkingDocument() {
		Working working = WorkingTestData.createWorking();
		// working = working.updateStatus(WorkingStatusType.DONE);
		return new WorkingDocument(workingId++, working, "title", "content", DocStatusType.REVIEW, DocType.GITLAB,
			"gitlab.com", false);
	}

	public static WorkingDocument createDeletedWorkingDocument() {
		Working working = WorkingTestData.createDeletedWorking();
		return new WorkingDocument(workingId++, working, "title", "content", DocStatusType.REVIEW, DocType.GITLAB,
			"gitlab.com", true);
	}

	public static WorkingDocument createWorkingDocumentWithWorking(Working working) {
		return new WorkingDocument(workingId++, working, "title", "content", DocStatusType.REVIEW, DocType.GITLAB,
			"gitlab.com", false);
	}

	public static WorkingDocument createWorkingDocumentWithStatus(DocStatusType status) {
		Working working = WorkingTestData.createWorking();
		working = working.updateStatus(WorkingStatusType.DONE);
		return new WorkingDocument(workingId++, working, "title", "content", status, DocType.GITLAB, "gitlab.com",
			false);
	}

	public static Map<DocType, List<String>> createLinks(DocType docType, List<String> links) {
		return Map.of(docType, links);
	}
}
