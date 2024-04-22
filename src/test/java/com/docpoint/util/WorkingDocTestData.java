package com.docpoint.util;

import java.util.List;
import java.util.Map;

import com.docpoint.domain.model.Working;
import com.docpoint.domain.type.WorkingStatusType;
import com.docpoint.util.WorkingTestData;
import com.docpoint.domain.model.WorkingDoc;
import com.docpoint.domain.type.DocType;

public class WorkingDocTestData {
	public static WorkingDoc createWorkingDoc() {
		Working working = WorkingTestData.createWorking();
		working = working.updateStatus(WorkingStatusType.DONE);
		return new WorkingDoc(working, "title", "content", createLinks());
	}

	public static Map<DocType, List<String>> createLinks() {
		return Map.of(DocType.CONFLUENCE, List.of("confluence.com"), DocType.GITLAB, List.of("gitlab.com"));
	}

	public static Map<DocType, List<String>> createLinks(DocType docType, List<String> links) {
		return Map.of(docType, links);
	}
}
